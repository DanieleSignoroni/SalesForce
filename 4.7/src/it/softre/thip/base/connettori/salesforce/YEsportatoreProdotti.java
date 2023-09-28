package it.softre.thip.base.connettori.salesforce;

import java.io.IOException;
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
		boolean ret = true;
		writeLog("*** ESPORT ARTICOLI - PRODOTTI SALES FORCE ***");
		YPsnDatiSalesForce psnDati = YPsnDatiSalesForce.getCurrentPersDatiSalesForce(Azienda.getAziendaCorrente());
		if(psnDati != null) {
			endpoint = psnDati.getInstanceUrl() + "/sobjects/";
			apiPath = psnDati.getToken();
			List<Articolo> prodotti = getListaArticoliValidi();
			for (Iterator<Articolo> iterator = prodotti.iterator(); iterator.hasNext();) {
				writeLog("");
				try {
					Articolo articolo = (Articolo) iterator.next();
					writeLog("--------- Processo l'articolo : "+articolo.getKey()+" -------------");
					String json = getJSONAdd(articolo);
					YProdottiInseriti tab = getProdottoInseritoByKey(articolo.getKey());
					if(tab == null) {
						writeLog("L'articolo non e' ancora stato esportato in Sales Force, procedo all'inserimento");
						ApiResponse response = YApiManagement.callApi(endpoint+id, Method.POST, MediaType.APPLICATION_JSON, null, null, json,apiPath);
						if(response.success()) {
							String respKey = (String) response.getBodyAsJSONObject().get("id");
							ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+respKey, Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
							if(read.success()) {
								writeLog("Articolo inserito in Sales Force correttamente");
								YProdottiInseriti ins = (YProdottiInseriti) Factory.createObject(YProdottiInseriti.class);
								ins.setKey(articolo.getKey());
								ins.setIdSalesForce(respKey);
								if(ins.save() >= 0) {
									writeLog("Popolata correttamente la tabella di appoggio : "+ins.getAbstractTableManager().getMainTableName());
									ConnectionManager.commit();
								}else {
									writeLog("Vi sono stati errori nella popolazione della tabella di appoggio : "+ins.getAbstractTableManager().getMainTableName());
									ConnectionManager.rollback();
								}
							}
						}else {
							writeLog("Impossibile inserire l'articolo, errori: \n"+response.getBodyAsString());
						}
					}else {
						ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+tab.getIdSalesForce(), Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
						if(read.success()) {
							writeLog("L'articolo e' gia presente in Sales Force \n id = "+tab.getIdSalesForce()+", procedo con l'aggiornamento");
							//esiste davvero, vado in edit
							String command = "curl -X PATCH "+endpoint+id+"/"+tab.getIdSalesForce()+"  -H \"Content-Type: application/json\"     -H \"Authorization: Bearer "+this.apiPath+"\" ";
							String formattedJson = json.replace("\"", "\"\"\"");
							command += " -d "+formattedJson+" ";
							Process powerShellProcess = Runtime.getRuntime().exec(command);
							powerShellProcess.getOutputStream().close();
							int exitValue = -1;
							try {
								exitValue = powerShellProcess.waitFor();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if(exitValue == 0) {
								writeLog("Prodotto aggiornato correttamente");
								if(tab.save() >= 0) {
									writeLog("Aggiornata correttamente la tabella di appoggio : "+tab.getAbstractTableManager().getMainTableName());
									ConnectionManager.commit();
								}else {
									writeLog("Vi sono stati errori nell'aggiornamento della tabella di appoggio : "+tab.getAbstractTableManager().getMainTableName());
									ConnectionManager.rollback();
								}
							}else {
								writeLog("Prodotto aggiornato con errori");
							}
						}
					}
					writeLog("--------- Ho finito di processare l'articolo : "+articolo.getKey()+" -------------");
				} catch (JSONException e) {
					ret = false;
					e.printStackTrace();
				} catch (SQLException e) {
					ret = false;
					e.printStackTrace();
				} catch (IOException e) {
					ret = false;
					e.printStackTrace();
				}
			}
		}
		return ret;
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
		writeLog("\\ Reperisco gli articoli da esportare //");
		List<Articolo> lista = new ArrayList<Articolo>();
		try {
			String where = " "+ArticoloTM.ID_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' AND "+ArticoloTM.STATO+" = '"+DatiComuniEstesi.VALIDO+"' ";
			where += " AND ID_ARTICOLO = '0.FSH1670511245666CK' ";
			writeLog("\\ WHERE STRING = "+where);
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
		writeLog("\\ Ho trovato :"+lista.size()+" record da processare");
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
