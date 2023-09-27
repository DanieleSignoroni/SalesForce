package it.softre.thip.base.connettori.salesforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.google.gson.JsonObject;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.softre.thip.base.connettori.salesforce.generale.YPsnDatiSalesForce;
import it.softre.thip.base.connettori.salesforce.tabelle.YClientiInseriti;
import it.softre.thip.base.connettori.salesforce.tabelle.YReferentiInseriti;
import it.softre.thip.base.connettori.utils.YApiManagement;
import it.thera.thip.api.client.ApiResponse;
import it.softre.thip.base.connettori.api.YApiRequest.Method;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.base.cliente.ClienteVenditaTM;
import it.thera.thip.base.partner.RubricaEstesa;

public class YEsportatoreReferenti extends BatchRunnable{

	public String endpoint = null;

	public String id = "Contact";

	public String apiPath = null;

	@Override
	protected boolean run() {
		YPsnDatiSalesForce psnDati = YPsnDatiSalesForce.getCurrentPersDatiSalesForce(Azienda.getAziendaCorrente());
		if(psnDati != null) {
			endpoint = psnDati.getInstanceUrl() + "/sobjects/";
			apiPath = psnDati.getToken();
			List<RubricaEstesa> prodotti = getListaReferentiValidi();
			for (Iterator<RubricaEstesa> iterator = prodotti.iterator(); iterator.hasNext();) {
				try {
					RubricaEstesa referente = (RubricaEstesa) iterator.next();
					String json = getJSONAdd(referente);
					YReferentiInseriti tab = getReferenteInseritoByKey(referente.getKey());
					if(tab == null) {
						//insert
						ApiResponse response = YApiManagement.callApi(endpoint+id, Method.POST, MediaType.APPLICATION_JSON, null, null, json,apiPath);
						if(response.success()) {
							String respKey = (String) response.getBodyAsJSONObject().get("id");
							ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+respKey, Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
							if(read.success()) {
								//inserito correttamente
								YReferentiInseriti ins = (YReferentiInseriti) Factory.createObject(YReferentiInseriti.class);
								ins.setKey(referente.getKey());
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

	public static YReferentiInseriti getReferenteInseritoByKey(String key) {
		try {
			return (YReferentiInseriti) YReferentiInseriti.elementWithKey(YReferentiInseriti.class,key, PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getJSONAdd(RubricaEstesa obj) {
		JsonObject json = new JsonObject();
		json.addProperty("AccountId", getIdSalesForceCliente(obj.getIdAnagrafico()));
		json.addProperty("LastName", obj.getCognome());
		json.addProperty("FirstName", obj.getNome());
		json.addProperty("MailingStreet", obj.getRubIndirizzo() != null ? obj.getRubIndirizzo() : "");
		json.addProperty("MailingCity", obj.getLocalita() != null ? obj.getLocalita() : "");
		json.addProperty("MailingState", obj.getIdProvincia() != null ? obj.getIdProvincia() : "");
		json.addProperty("MailingPostalCode", obj.getCAP() != null ? obj.getCAP() : "");
		json.addProperty("MailingCountry", obj.getAnagraficoDiBase().getIdNazioneNascita() != null ? obj.getAnagraficoDiBase().getIdNazioneNascita() : "");
		json.addProperty("OtherStreet", obj.getRubIndirizzo() != null ? obj.getRubIndirizzo() : "");
		json.addProperty("OtherCity", obj.getLocalita() != null ? obj.getLocalita() : "");
		json.addProperty("OtherState", obj.getIdProvincia() != null ? obj.getIdProvincia() : "");
		json.addProperty("OtherPostalCode", obj.getCAP() != null ? obj.getCAP() : "");
		json.addProperty("OtherCountry", obj.getAnagraficoDiBase().getIdNazioneNascita() != null ? obj.getAnagraficoDiBase().getIdNazioneNascita() : "");
		json.addProperty("Phone", obj.getNumeroTelefono() != null ? obj.getNumeroTelefono() : "");
		json.addProperty("Fax", obj.getNumeroFax() != null ? obj.getNumeroFax() : "");
		json.addProperty("MobilePhone", obj.getNumeroCellulare() != null ? obj.getNumeroCellulare() : "");
		json.addProperty("HomePhone", obj.getIndirizzoPersonale().getTelefono() != null ? obj.getIndirizzoPersonale().getTelefono() : "");
		json.addProperty("Email",obj.getIndirizzoEmail() != null ? obj.getIndirizzoEmail() : "");
		json.addProperty("Birthdate",obj.getDataNascita() != null ? obj.getDataNascita().toString() : "");
		json.addProperty("Title",obj.getFunzioneAziendale() != null ? obj.getFunzioneAziendale() : "");
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public List<RubricaEstesa> getListaReferentiValidi(){
		List<RubricaEstesa> lista = new ArrayList<RubricaEstesa>();
		try {
			lista = RubricaEstesa.retrieveList(RubricaEstesa.class,"", "", false);
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

	@SuppressWarnings("rawtypes")
	public String getIdSalesForceCliente(Integer idAnagrafico) {
		String where = " "+ClienteVenditaTM.R_ANAGRAFICO+" = '"+idAnagrafico+"' ";
		try {
			Vector cli = ClienteVendita.retrieveList(where, "", false);
			if(cli.size() > 0) {
				ClienteVendita cl = (ClienteVendita) cli.get(0);
				YClientiInseriti trovoHash = (YClientiInseriti) YClientiInseriti.elementWithKey(YClientiInseriti.class, 
						KeyHelper.buildObjectKey(new String[] {
								Azienda.getAziendaCorrente(),
								cl.getIdCliente()
						}), 0);
				if(trovoHash != null) {
					return trovoHash.getIdSalesForce();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";

	}

	@Override
	protected String getClassAdCollectionName() {
		return "YReferentiSF";
	}

}
