package it.softre.thip.base.connettori.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Revisions:
 * Fix     Date          Owner      Description
 * 38753   22/05/2023    PJ         rilascio
 */

public class YApiRequest {

	public enum Method {
		GET,
		PUT,
		POST,
		DELETE,
		PATCH
	}
	private Method method = Method.GET;

	private String url;
	private String contentType = MediaType.APPLICATION_JSON;
	private String accept = MediaType.APPLICATION_JSON;
	
	private Map<String, String> headers = new HashMap<String, String>();
	private Map<String, String> parameters = new HashMap<String, String>();
	
	private Object body = null;
	private boolean stringifyBody = false;
	

	public void setBody(Object body) {
		this.body = body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public void setBody(JSONObject body) {
		this.body = body;
		stringifyBody = true;
	}

	public void setBody(JSONArray body) {
		this.body = body;
		stringifyBody = true;
	}

	public Object getBody() {
		return this.body;
	}
	
	public Entity<?> getBodyEntity() {
		if (body == null) {
			return null;
		}
		
		if (stringifyBody) {
			return Entity.entity(body.toString(), this.contentType);			
		}
		
		return Entity.entity(body, this.contentType);			
	}
	

	public void setURL(String url) {
		this.url = url;
	}
	
	public String getURL() {
		return this.url;
	}


	public void setMethod(Method method) {
		this.method = method;
	}

	public Method getMethod() {
		return this.method;
	}

	
	public void setContentType(MediaType contentType) {
		this.contentType = contentType.getType();
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return this.contentType;
	}
	
	public void setAccept(MediaType accept) {
		this.accept = accept.getType();
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}
	
	public String getAccept() {
		return this.accept;
	}

	
	public void setHeaders(Map<String, String> headers) {
		this.setHeaders(headers);
	}
	
	public Map<String, String> getHeaders() {
		return this.headers;
	}

	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}
	
	public void addHeaders(Map<String, String> headers) {
		this.headers.putAll(headers);
	}
	

	public void setParameters(Map<String, String> parameters) {
		this.setParameters(parameters);
	}
	
	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public void addParameter(String key, String value) {
		this.parameters.put(key, value);
	}
	
	public void addParameters(Map<String, String> parameters) {
		this.parameters.putAll(parameters);
	}


	public YApiRequest() {
	}
	
	public YApiRequest(String url) {
		this.url = url;
	}
	
	public YApiRequest(Method method, String url) {
		this.method = method;
		this.url = url;
	}

}