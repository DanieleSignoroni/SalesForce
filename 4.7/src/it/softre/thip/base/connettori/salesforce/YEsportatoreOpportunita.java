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
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.softre.thip.base.connettori.api.YApiRequest.Method;
import it.softre.thip.base.connettori.salesforce.generale.YPsnDatiSalesForce;
import it.softre.thip.base.connettori.salesforce.tabelle.YClientiInseriti;
import it.softre.thip.base.connettori.salesforce.tabelle.YOrdiniInseriti;
import it.softre.thip.base.connettori.salesforce.tabelle.YOrdiniInseritiTM;
import it.softre.thip.base.connettori.utils.YApiManagement;
import it.thera.thip.api.client.ApiResponse;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.base.cliente.ClienteVenditaTM;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.vendite.ordineVE.OrdineVendita;

public class YEsportatoreOpportunita extends BatchRunnable{

	public String endpoint = null;

	public String id = "Opportunity";

	public String apiPath = null;

	@Override
	protected boolean run() {
		boolean ret = true;
		writeLog("*** ESPORT ORDINI VENDITA - OPPORTUNITA SALES FORCE ***");
		YPsnDatiSalesForce psnDati = YPsnDatiSalesForce.getCurrentPersDatiSalesForce(Azienda.getAziendaCorrente());
		if(psnDati != null) {
			endpoint = psnDati.getInstanceUrl() + "/sobjects/";
			apiPath = psnDati.getToken();
			List<YOrdiniInseriti> lista = getListaOrdiniVenditaDaEsportare();
			for (Iterator<YOrdiniInseriti> iterator = lista.iterator(); iterator.hasNext();) {
				YOrdiniInseriti yOrdiniInseriti = (YOrdiniInseriti) iterator.next();
				try {
					OrdineVendita ordineVendita = (OrdineVendita)
							OrdineVendita.elementWithKey(OrdineVendita.class,
									yOrdiniInseriti.getKey(), PersistentObject.NO_LOCK);
					if(ordineVendita != null) {
						writeLog("--------- Processo l'ordine : "+ordineVendita.getIdNumeroOrdIC()+" ---------");
						String idClienteSalesFroce = getIdSalesForceCliente(ordineVendita.getIdAnagrafico());
						if(idClienteSalesFroce != null) {
							writeLog("L'id dell'account e' : "+idClienteSalesFroce);
							String json = getJSONAdd(ordineVendita, idClienteSalesFroce);
							if(yOrdiniInseriti.isProcessato()) {
								writeLog("L'ordine e' gia' stato importato, vado ad aggiornare quello esistente che ha id = "+yOrdiniInseriti.getIdSalesForce());
								//patch
								ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+yOrdiniInseriti.getIdSalesForce(), Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
								if(read.success()) {
									//esiste davvero, vado in edit
									String command = "curl -X PATCH "+endpoint+id+"/"+yOrdiniInseriti.getIdSalesForce()+"  -H \"Content-Type: application/json\"     -H \"Authorization: Bearer "+this.apiPath+"\" ";
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
										writeLog("Opportunita aggiornata correttamente");
										if(yOrdiniInseriti.save() >= 0) {
											writeLog("Aggiornata correttamente la tabella di appoggio : "+yOrdiniInseriti.getAbstractTableManager().getMainTableName());
											ConnectionManager.commit();
										}else {
											writeLog("Vi sono stati errori nell'aggiornamento della tabella di appoggio : "+yOrdiniInseriti.getAbstractTableManager().getMainTableName());
											ConnectionManager.rollback();
										}
									}else {
										writeLog("Opportunita aggiornata con errori");
									}
								}
							}else {
								writeLog("Cerco di inserire l'ordine");
								//insert
								ApiResponse response = YApiManagement.callApi(endpoint+id, Method.POST, MediaType.APPLICATION_JSON, null, null, json,apiPath);
								if(response.success()) {
									String respKey = (String) response.getBodyAsJSONObject().get("id");
									ApiResponse read = YApiManagement.callApi(endpoint+id+"/"+respKey, Method.GET, MediaType.APPLICATION_JSON, null, null, null,apiPath);
									if(read.success()) {
										writeLog("Ordine inserito correttamente sotto forma di Opportunita' ");
										writeLog("Flaggo come processato l'ordine e salvo il record ");
										//inserito correttamente
										yOrdiniInseriti.setIdSalesForce(respKey);
										yOrdiniInseriti.setProcessato(true);
										if(yOrdiniInseriti.save() >= 0) {
											writeLog("Record nella tabella di appoggio salvato correttamente");
											ConnectionManager.commit();
										}else {
											writeLog("Vi sono stati errori nel salvataggio, ri-processare l'ordine ");
											ConnectionManager.rollback();
										}
									}
								}else {
									writeLog("Si sono verificati errori nella chiamata \n "+response.getBodyAsString());
								}
							}
						}else {
							writeLog("**!! In Sales Force non esiste un account per il cliente :"+ordineVendita.getIdCliente()+ " !!**");
						}
						writeLog("--------- Ho finito di processare l'ordine : "+ordineVendita.getIdNumeroOrdIC()+" ---------");
					}else {
						writeLog(" **!! L'ordine "+yOrdiniInseriti.getKey()+" non esiste piu' in panthera !!**");
						writeLog("Cancello anche il record nella tabella di appoggio ");
						int del = yOrdiniInseriti.delete();
						if(del > 0) {
							writeLog("Record nella tabella di appoggio cancellato correttamente");
							ConnectionManager.commit();
						}else {
							writeLog("Vi sono stati errori nella cancellazione, si consiglia di procedere a mano \n chiave : "+yOrdiniInseriti.getKey());
							ConnectionManager.rollback();
						}
					}

				} catch (SQLException e) {
					ret = false;
					e.printStackTrace();
				} catch (JSONException e) {
					ret = false;
					e.printStackTrace();
				} catch (IOException e) {
					ret = false;
					e.printStackTrace();
				}
			}
		}else {
			ret = false;
			writeLog("--- Non e' stata definita la personalizzazione dati Sales Force, parametro obbligatorio ");
		}
		writeLog("*** TERMINE ESPORT ORDINI VENDITA - OPPORTUNITA SALES FORCE ***");
		return ret;
	}

	public String getJSONAdd(OrdineVendita ord,String idClienteSalesFroce) {
		JsonObject json = new JsonObject();
		json.addProperty("AccountId", idClienteSalesFroce);
		json.addProperty("Description", ord.getNota() != null ? ord.getNota() : "");
		json.addProperty("StageName","Negotiation/Review");
		json.addProperty("Amount",ord.getTotaleDocumento().toString());
		json.addProperty("CloseDate",ord.getDataConsegnaConfermata().toString());
		json.addProperty("Type","Existing Customer - Upgrade");
		json.addProperty("OrderNumber__c", ord.getIdNumeroOrdIC());
		switch (ord.getStatoAvanzamento()) {
		case StatoAvanzamento.TEMPLATE:
			json.addProperty("DeliveryInstallationStatus__c", "Yet to begin");
			break;
		case StatoAvanzamento.PROVVISORIO:
			json.addProperty("DeliveryInstallationStatus__c", "In progress");
			break;
		case StatoAvanzamento.DEFINITIVO:
			json.addProperty("DeliveryInstallationStatus__c", "Completed");
			break;
		default:
			break;
		}
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public List<YOrdiniInseriti> getListaOrdiniVenditaDaEsportare(){
		writeLog("\\ Reperisco gli ordini vendita stampati da esportare //");
		List<YOrdiniInseriti> lista = new ArrayList<YOrdiniInseriti>();
		try {
			String where = " "+YOrdiniInseritiTM.ID_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' AND "+YOrdiniInseritiTM.PROCESSATO+" = 'N' ";
			writeLog("\\ WHERE STRING = "+where);
			lista = YOrdiniInseriti.retrieveList(YOrdiniInseriti.class,where, "", false);
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
		return "YOpportunitaSF";
	}

}
