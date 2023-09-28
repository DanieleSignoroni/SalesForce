package it.softre.thip.base.connettori.salesforce;

import java.io.IOException;
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
		boolean ret = true;
		writeLog("*** ESPORT RUBRICA - REFERENTI SALES FORCE ***");
		YPsnDatiSalesForce psnDati = YPsnDatiSalesForce.getCurrentPersDatiSalesForce(Azienda.getAziendaCorrente());
		if(psnDati != null) {
			endpoint = psnDati.getInstanceUrl() + "/sobjects/";
			apiPath = psnDati.getToken();
			List<RubricaEstesa> prodotti = getListaReferentiValidi();
			for (Iterator<RubricaEstesa> iterator = prodotti.iterator(); iterator.hasNext();) {
				writeLog("");
				try {
					RubricaEstesa referente = (RubricaEstesa) iterator.next();
					writeLog("--------- Processo la rubrica  : "+referente.getKey()+" -------------");
					String idAccountSalesForce = getIdSalesForceCliente(referente.getIdAnagrafico());
					if(idAccountSalesForce != null) {
						String json = getJSONAdd(referente,idAccountSalesForce);
						YReferentiInseriti tab = getReferenteInseritoByKey(referente.getKey());
						if(tab == null) {
							writeLog("Il referente non e' ancora stato esportato in Sales Force, procedo all'inserimento");
							ApiResponse response = YApiManagement.callApi(endpoint+id, Method.POST, MediaType.APPLICATION_JSON, null, null, json,apiPath);
							if(response.success()) {
								String respKey = (String) response.getBodyAsJSONObject().get("id");
								ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+respKey, Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
								if(read.success()) {
									writeLog("Referente inserito in Sales Force correttamente");
									YReferentiInseriti ins = (YReferentiInseriti) Factory.createObject(YReferentiInseriti.class);
									ins.setKey(referente.getKey());
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
								writeLog("Impossibile inserire il referente, errori: \n"+response.getBodyAsString());
							}
						}else {
							//edit
							ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+tab.getIdSalesForce(), Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
							if(read.success()) {
								writeLog("Il referente e' gia presente in Sales Force \n id = "+tab.getIdSalesForce()+", procedo con l'aggiornamento");
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
									writeLog("Referente aggiornato correttamente");
									if(tab.save() >= 0) {
										writeLog("Aggiornata correttamente la tabella di appoggio : "+tab.getAbstractTableManager().getMainTableName());
										ConnectionManager.commit();
									}else {
										writeLog("Vi sono stati errori nell'aggiornamento della tabella di appoggio : "+tab.getAbstractTableManager().getMainTableName());
										ConnectionManager.rollback();
									}
								}else {
									writeLog("Referente aggiornato con errori");
								}
							}
						}
					}else {
						writeLog("Non esiste un account collegato al cliente con anagrafico = "+referente.getIdAnagrafico());
					}
					writeLog("--------- Ho finito di processare la rubrica  : "+referente.getKey()+" -------------");
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

	public static YReferentiInseriti getReferenteInseritoByKey(String key) {
		try {
			return (YReferentiInseriti) YReferentiInseriti.elementWithKey(YReferentiInseriti.class,key, PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getJSONAdd(RubricaEstesa obj,String idAccount) {
		JsonObject json = new JsonObject();
		json.addProperty("AccountId", idAccount);
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
		writeLog("Reperisco le rubriche da esportare come referenti");
		List<RubricaEstesa> lista = new ArrayList<RubricaEstesa>();
		try {
			String where = "";
			writeLog("\\ WHERE STRING = "+where);
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
		writeLog("Ho trovato : "+lista.size()+" record da processare");
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

	protected void writeLog(String text) {
		System.out.println(text);
		getOutput().println(text);
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YReferentiSF";
	}

}
