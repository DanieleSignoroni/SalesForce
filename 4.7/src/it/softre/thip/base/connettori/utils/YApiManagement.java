package it.softre.thip.base.connettori.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import com.thera.thermfw.persist.Factory;

import it.softre.thip.base.connettori.api.YApiClient;
import it.softre.thip.base.connettori.api.YApiRequest;
import it.softre.thip.base.connettori.api.YApiRequest.Method;
import it.thera.thip.api.client.ApiResponse;

/**
 * <h1>Softre Solutions</h1>
 * <h2>Api Management</h2>
 * <br>
 * @author Daniele Signoroni 12/09/2023
 * <br><br>
 * <b></b>	<p>Classe di utilities che implementa l'uso di classi thera che gestiscono chiamate API.<br>
 * 			   La seguente fornisce metodi statici di rapido uso e lettura.
 * 			</p>
 */

public class YApiManagement {

	/**
	 * <h1>Metodo che effettua una chiamata API</h1>
	 * <br>
	 * Daniele Signoroni 12/09/2023
	 * <br>
	 * <p>
	 * Metodo di utils per poter effettuare una chiamata API.<br>
	 * La classe {@link ApiClient} gestisce in maniera automatica il tipo di chiamata,
	 * se questa deve essere effettuata con http usa un determinato client, se questa deve essere
	 * effettuata con https usa un client che implementa un certificato di sicurezza.
	 * </p>
	 * @param url di chiamata
	 * @param method uno dei seguenti : {@link Method#DELETE}, {@link Method#GET}, {@link Method#PUT}, {@link Method#POST}
	 * @param contentType della chiamata, reperire da qui {@link MediaType}
	 * @param headers lista headers sotto forma di {key,value} 
	 * @param parameters lista parametri sotto forma di {key,value}
	 * @param body questo puo' essere:
	 * 		<ul>
	 * 			<li>{@link String}</li>
	 * 			<li>{@link Object}</li>
	 * 			<li>{@link JSONObject}</li>
	 * 			<li>{@link JSONArray}</li>
	 * 		</ul>
	 * @param apiPath 
	 * @return Ritorna un oggetto {@link ApiResponse}, da questo e' possibile leggere la response.
	 * <ul>
	 * 	<li>{@link ApiResponse#getStatus()} per leggere lo stato della chiamata</li>
	 *  <li>{@link ApiResponse#success()} ritorna true se status == {@value HttpStatus#SC_OK}, false se <> </li>
	 *  <li>{@link ApiResponse#getBodyAs(Class)} legge il body e crea un oggetto con struttura quella passata </li>
	 *  <li>{@link ApiResponse#getBodyAsJSONArray()} ritorna un JSONArray</li>
	 *  <li>{@link ApiResponse#getBodyAsJSONObject()} ritorna un JSONObject</li>
	 *  <li>{@link ApiResponse#getBodyAsString()} ritorna la response sotto forma di stringa</li>
	 * </ul>
	 */
	public static ApiResponse callApi(String url,Method method,String contentType,Map<String,String> headers,Map<String,String> parameters,Object body, String apiPath) {
		ApiResponse apiResponse = null;
		YApiRequest apiRequest = (YApiRequest) Factory.createObject(YApiRequest.class);
		apiRequest.setMethod(method);
		apiRequest.setURL(url);
		apiRequest.setContentType(contentType);
		if(headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				apiRequest.addHeader(key, val);
			}
		}
		if(parameters != null) {
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				apiRequest.addParameter(key, val);
			}
		}
		if(body != null)
			apiRequest.setBody(body);
		YApiClient apiClient = (YApiClient) new YApiClient("",apiPath);
		try {
			apiResponse = apiClient.send(apiRequest);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return apiResponse;
	}

	/**
	 * <h1>Metodo che effettua una chiamata API</h1>
	 * <br>
	 * Daniele Signoroni 12/09/2023
	 * <br>
	 * <p>
	 * Metodo di utils per poter effettuare una chiamata API.<br>
	 * La classe {@link ApiClient} gestisce in maniera automatica il tipo di chiamata,
	 * se questa deve essere effettuata con http usa un determinato client, se questa deve essere
	 * effettuata con https usa un client che implementa un certificato di sicurezza.
	 * </p>
	 * @param url di chiamata
	 * @param method uno dei seguenti : {@link Method#DELETE}, {@link Method#GET}, {@link Method#PUT}, {@link Method#POST}
	 * @param contentType della chiamata, reperire da qui {@link MediaType}
	 * @param headers lista headers sotto forma di {key,value} 
	 * @param parameters lista parametri sotto forma di {key,value}
	 * @param body questo puo' essere:
	 * 		<ul>
	 * 			<li>{@link String}</li>
	 * 			<li>{@link Object}</li>
	 * 			<li>{@link JSONObject}</li>
	 * 			<li>{@link JSONArray}</li>
	 * 		</ul>
	 * @param bodyAsJson se true viene ritornato un {@link JSONObject}, se false un {@link Object} generico da gestire.
	 * @return Ritorna un oggetto {@link ApiResponse}, da questo e' possibile leggere la response.
	 * <ul>
	 * 	<li>{@link ApiResponse#getStatus()} per leggere lo stato della chiamata</li>
	 *  <li>{@link ApiResponse#success()} ritorna true se status == {@value HttpStatus#SC_OK}, false se <> </li>
	 *  <li>{@link ApiResponse#getBodyAs(Class)} legge il body e crea un oggetto con struttura quella passata </li>
	 *  <li>{@link ApiResponse#getBodyAsJSONArray()} ritorna un JSONArray</li>
	 *  <li>{@link ApiResponse#getBodyAsJSONObject()} ritorna un JSONObject</li>
	 *  <li>{@link ApiResponse#getBodyAsString()} ritorna la response sotto forma di stringa</li>
	 * </ul>
	 */
	public static Object callApi(String url,Method method,String contentType,Map<String,String> headers,Map<String,String> parameters,Object body,boolean bodyAsJson) {
		ApiResponse apiResponse = null;
		Object bodyResponse = null;
		YApiRequest apiRequest = (YApiRequest) Factory.createObject(YApiRequest.class);
		apiRequest.setMethod(method);
		apiRequest.setURL(url);
		apiRequest.setContentType(contentType);
		if(headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				apiRequest.addHeader(key, val);
			}
		}
		if(parameters != null) {
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				apiRequest.addParameter(key, val);
			}
		}
		if(body != null)
			apiRequest.setBody(body);
		YApiClient apiClient = (YApiClient) new YApiClient("","");
		try {
			apiResponse = apiClient.send(apiRequest);
			if(bodyAsJson)
				bodyResponse = apiResponse.getBodyAsJSONObject();
			else
				bodyResponse = apiRequest.getBody();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return bodyResponse;
	}
}
