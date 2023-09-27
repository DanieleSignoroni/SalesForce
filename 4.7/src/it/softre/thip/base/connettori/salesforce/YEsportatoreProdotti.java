package it.softre.thip.base.connettori.salesforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.google.gson.JsonObject;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.PersistentObject;

import it.softre.thip.base.connettori.salesforce.generale.YPsnDatiSalesForce;
import it.softre.thip.base.connettori.salesforce.tabelle.YProdottiInseriti;
import it.softre.thip.base.connettori.utils.YApiManagement;
import it.thera.thip.api.client.ApiResponse;
import it.softre.thip.base.connettori.api.YApiRequest.Method;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.ArticoloTM;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.DatiComuniEstesi;

public class YEsportatoreProdotti extends BatchRunnable{

	public String endpoint = null;

	public String id = "Product2";

	public String apiPath = null;

	@Override
	protected boolean run() {
		YPsnDatiSalesForce psnDati = YPsnDatiSalesForce.getCurrentPersDatiSalesForce(Azienda.getAziendaCorrente());
		if(psnDati != null) {
			endpoint = psnDati.getInstanceUrl() + "/sobjects/";
			apiPath = psnDati.getToken();
			List<Articolo> prodotti = getListaArticoliValidi();
			for (Iterator<Articolo> iterator = prodotti.iterator(); iterator.hasNext();) {
				try {
					Articolo articolo = (Articolo) iterator.next();
					String json = getJSONAdd(articolo);
					YProdottiInseriti tab = getProdottoInseritoByKey(articolo.getKey());
					if(tab == null) {
						//insert
						ApiResponse response = YApiManagement.callApi(endpoint+id, Method.POST, MediaType.APPLICATION_JSON, null, null, json,apiPath);
						if(response.success()) {
							String respKey = (String) response.getBodyAsJSONObject().get("id");
							ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+respKey, Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
							if(read.success()) {
								//inserito correttamente
								YProdottiInseriti ins = (YProdottiInseriti) Factory.createObject(YProdottiInseriti.class);
								ins.setKey(articolo.getKey());
								ins.setIdSalesForce(respKey);
								if(ins.save() >= 0) {
									ConnectionManager.commit();
								}else {
									ConnectionManager.rollback();
								}
							}
						}
					}else {
						//edit
						ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+tab.getIdSalesForce(), Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
						if(read.success()) {
							//esiste davvero, vado in edit
							String command = "curl -X PATCH "+endpoint+id+"/"+tab.getIdSalesForce()+"  -H \"Content-Type: application/json\"     -H \"Authorization: Bearer "+this.apiPath+"\" ";
					        String formattedJson = json.replace("\"", "\"\"\"");
							command += " -d "+formattedJson+" ";
							Process powerShellProcess = Runtime.getRuntime().exec(command);
							powerShellProcess.getOutputStream().close();
							String line;
							System.out.println("Standard Output:");
							BufferedReader stdout = new BufferedReader(new InputStreamReader(
									powerShellProcess.getInputStream()));
							while ((line = stdout.readLine()) != null) {
								System.out.println(line);
							}
							stdout.close();
							System.out.println("Standard Error:");
							BufferedReader stderr = new BufferedReader(new InputStreamReader(
									powerShellProcess.getErrorStream()));
							while ((line = stderr.readLine()) != null) {
								System.out.println(line);
							}
							stderr.close();
							System.out.println("Done");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static YProdottiInseriti getProdottoInseritoByKey(String key) {
		try {
			return (YProdottiInseriti) YProdottiInseriti.elementWithKey(YProdottiInseriti.class,key, PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getJSONAdd(Articolo obj) {
		JsonObject json = new JsonObject();
		json.addProperty("Name", obj.getIdArticolo());
		json.addProperty("Description", obj.getDescrizioneArticoloNLS().getDescrizioneEstesa());
		return json.toString();
	}


	@SuppressWarnings("unchecked")
	public List<Articolo> getListaArticoliValidi(){
		List<Articolo> lista = new ArrayList<Articolo>();
		try {
			String where = " "+ArticoloTM.ID_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' AND "+ArticoloTM.STATO+" = '"+DatiComuniEstesi.VALIDO+"' ";
			where += " AND ID_ARTICOLO = 'ZH9080285' ";
			lista = Articolo.retrieveList(Articolo.class,where, "", false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YProdottiSF";
	}

	protected void writeLog(String text) {
		System.out.println(text);
		getOutput().println(text);
	}
}
