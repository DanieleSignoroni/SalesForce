package it.softre.thip.base.connettori.salesforce;

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

import it.softre.thip.base.connettori.salesforce.tabelle.YProdottiInseriti;
import it.softre.thip.base.connettori.utils.YApiManagement;
import it.thera.thip.api.client.ApiResponse;
import it.thera.thip.api.client.ApiRequest.Method;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.ArticoloTM;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.DatiComuniEstesi;

public class YEsportatoreProdotti extends BatchRunnable{

	public String endpoint = "https://softresrl-dev-ed.develop.my.salesforce.com/services/data/v58.0/sobjects/";

	public String id = "Product2";

	public String apiPath = "00D7R000004wxNy!ASAAQNiFGQuty4a.9KM6XuWg.96y23vqI9iD4kOd0Q41lMrLN4ShGeIlMQ1xyvM2dJ8GsUF88RIzNwWpHgdQxYgBhoXP87L0";

	@Override
	protected boolean run() {
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
						//ApiResponse response = YApiManagement.callApi(endpoint+id+"/"+tab.getIdSalesForce(), Method.PATCH, MediaType.APPLICATION_JSON, null, null, json,apiPath);
						// Get the response
						//						if(response.success()) {
						//							if(tab.save() >= 0) {
						//								ConnectionManager.commit();
						//							}else {
						//								ConnectionManager.rollback();
						//							}
						//						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
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
