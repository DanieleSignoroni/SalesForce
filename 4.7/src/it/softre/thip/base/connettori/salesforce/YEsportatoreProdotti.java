package it.softre.thip.base.connettori.salesforce;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

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
	
	public String apiPath = "OAuth 00D7R000004wxNy!ASAAQLjJGfuv.54fL5BWOliBZNkrdzvqGf9qazWhlia5aSvVZfpCAEggrct_VSfMYQnbtV6sw2_GJwKcQOBBqNdZXBNpqKhz";

	@Override
	protected boolean run() {
		List<Articolo> prodotti = getListaArticoliValidi();
		for (Iterator<Articolo> iterator = prodotti.iterator(); iterator.hasNext();) {
			try {
				Articolo articolo = (Articolo) iterator.next();
				YProdottiInseriti tab = getProdottoInseritoByKey(articolo.getKey());
				if(tab == null) {
					//insert
					String json = getJSONAdd(articolo);
					ApiResponse response = YApiManagement.callApi(endpoint+id, Method.POST, MediaType.APPLICATION_JSON, null, null, json,apiPath);
					if(response.success()) {
						JSONObject resp = response.getBodyAsJSONObject().getJSONObject("response");
						String id = resp.getString("Id");
						Map<String,String> parameters = new HashMap<String, String>();
						parameters.put("Id", id);
						ApiResponse read = YApiManagement.callApi(endpoint+id, Method.GET, MediaType.APPLICATION_JSON, null, parameters, null,apiPath);
						if(read.success()) {
							//inserito correttamente
							YProdottiInseriti ins = (YProdottiInseriti) Factory.createObject(YProdottiInseriti.class);
							ins.setKey(articolo.getKey());
							if(ins.save() >= 0) {
								ConnectionManager.commit();
							}else {
								ConnectionManager.rollback();
							}
						}
					}
				}else {
					//edit
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
			lista = Articolo.retrieveList(Articolo.class," "+ArticoloTM.ID_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' AND "+ArticoloTM.STATO+" = '"+DatiComuniEstesi.VALIDO+"' ", "", false);
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
