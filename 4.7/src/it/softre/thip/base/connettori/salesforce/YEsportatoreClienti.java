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
import it.softre.thip.base.connettori.salesforce.tabelle.YClientiInseriti;
import it.softre.thip.base.connettori.utils.YApiManagement;
import it.thera.thip.api.client.ApiResponse;
import it.softre.thip.base.connettori.api.YApiRequest.Method;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.base.cliente.ClienteVenditaTM;
import it.thera.thip.cs.DatiComuniEstesi;

public class YEsportatoreClienti extends BatchRunnable{

	public String endpoint = null;

	public String id = "Account";

	public String apiPath = null;

	@Override
	protected boolean run() {
		boolean ret = true;
		writeLog("*** ESPORT CLIENTI - ACCOUNT SALES FORCE ***");
		YPsnDatiSalesForce psnDati = YPsnDatiSalesForce.getCurrentPersDatiSalesForce(Azienda.getAziendaCorrente());
		if(psnDati != null) {
			endpoint = psnDati.getInstanceUrl() + "/sobjects/";
			apiPath = psnDati.getToken();
			List<ClienteVendita> prodotti = getListaClientiValidi();
			for (Iterator<ClienteVendita> iterator = prodotti.iterator(); iterator.hasNext();) {
				writeLog("");
				try {
					ClienteVendita cliente = (ClienteVendita) iterator.next();
					writeLog("--------- Processo l'cliente : "+cliente.getKey()+" -------------");
					String json = getJSONAdd(cliente);
					YClientiInseriti tab = getClienteInseritoByKey(cliente.getKey());
					if(tab == null) {
						writeLog("L'cliente non e' ancora stato esportato in Sales Force, procedo all'inserimento");
						ApiResponse response = YApiManagement.callApi(endpoint+id, Method.POST, MediaType.APPLICATION_JSON, null, null, json,apiPath);
						if(response.success()) {
							String respKey = (String) response.getBodyAsJSONObject().get("id");
							ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+respKey, Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
							if(read.success()) {
								writeLog("cliente inserito in Sales Force correttamente");
								YClientiInseriti ins = (YClientiInseriti) Factory.createObject(YClientiInseriti.class);
								ins.setKey(cliente.getKey());
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
							writeLog("Impossibile inserire l'cliente, errori: \n"+response.getBodyAsString());
						}
					}else {
						ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+tab.getIdSalesForce(), Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
						if(read.success()) {
							writeLog("L'cliente e' gia presente in Sales Force \n id = "+tab.getIdSalesForce()+", procedo con l'aggiornamento");
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
								writeLog("Account aggiornato correttamente");
								if(tab.save() >= 0) {
									writeLog("Aggiornata correttamente la tabella di appoggio : "+tab.getAbstractTableManager().getMainTableName());
									ConnectionManager.commit();
								}else {
									writeLog("Vi sono stati errori nell'aggiornamento della tabella di appoggio : "+tab.getAbstractTableManager().getMainTableName());
									ConnectionManager.rollback();
								}
							}else {
								writeLog("Account aggiornato con errori");
							}
						}
					}
					writeLog("--------- Ho finito di processare l'cliente : "+cliente.getKey()+" -------------");
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

	public static YClientiInseriti getClienteInseritoByKey(String key) {
		try {
			return (YClientiInseriti) YClientiInseriti.elementWithKey(YClientiInseriti.class,key, PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getJSONAdd(ClienteVendita obj) {
		JsonObject json = new JsonObject();
		json.addProperty("Name", obj.getRagioneSociale());
		json.addProperty("BillingStreet", obj.getIndirizzo() != null ? obj.getIndirizzo() : "");
		json.addProperty("BillingCity",obj.getLocalita() != null ? obj.getLocalita() : "");
		json.addProperty("BillingState",obj.getIdProvincia() != null ? obj.getIdProvincia() : "");
		json.addProperty("BillingPostalCode",obj.getCAP() != null ? obj.getCAP() : "");
		json.addProperty("BillingCountry",obj.getIdNazione() != null ? obj.getIdNazione() : "");
		json.addProperty("ShippingStreet", obj.getIndirizzo() != null ? obj.getIndirizzo() : "");
		json.addProperty("ShippingCity",obj.getLocalita() != null ? obj.getLocalita() : "");
		json.addProperty("ShippingState",obj.getIdProvincia() != null ? obj.getIdProvincia() : "");
		json.addProperty("ShippingPostalCode",obj.getCAP() != null ? obj.getCAP() : "");
		json.addProperty("ShippingCountry",obj.getIdNazione() != null ? obj.getIdNazione() : "");
		json.addProperty("Phone",obj.getAnagrafico().getNumeroTelefono() != null ? obj.getAnagrafico().getNumeroTelefono() : "");
		json.addProperty("Fax",obj.getAnagrafico().getNumeroFax() != null ? obj.getAnagrafico().getNumeroFax() : "");
		json.addProperty("Website",obj.getAnagrafico().getSitoInternet() != null ? obj.getAnagrafico().getSitoInternet() : "");
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public List<ClienteVendita> getListaClientiValidi(){
		List<ClienteVendita> lista = new ArrayList<ClienteVendita>();
		try {
			String where = " "+ClienteVenditaTM.ID_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' AND "+ClienteVenditaTM.STATO+" = '"+DatiComuniEstesi.VALIDO+"' ";
			lista = ClienteVendita.retrieveList(ClienteVendita.class,where, "", false);
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

	protected void writeLog(String text) {
		System.out.println(text);
		getOutput().println(text);
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YClientiSF";
	}

}
