package it.softre.thip.base.connettori.api;
import java.util.Map;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import it.softre.thip.base.connettori.api.YApiRequest.Method;
import it.thera.thip.api.client.ApiResponse;
import it.thera.thip.api.client.auth.ApiAuthentication;
import it.thera.thip.api.client.auth.NoAuthentication;
import it.thera.thip.api.client.jersey.JerseyClientManager;

/*
 * Revisions:
 * Fix     Date          Owner      Description
 * 38753   22/05/2023    PJ         rilascio
 */

public class YApiClient {
	
	private ApiAuthentication authentication = new NoAuthentication();
	
	private final String apiPath;

	private String token;
	
	public String getApiPath() {
		return apiPath;
	}
	
	public YApiClient(String apiPath,String token) {
		this.apiPath = apiPath;
		this.token = token;
	}
	
	public void setAuthentication(ApiAuthentication authentication) {
		this.authentication = authentication;
	}
	
	public ApiResponse send(YApiRequest request) throws KeyManagementException, NoSuchAlgorithmException {
		String absoluteURL = this.apiPath + request.getURL(); 
		JerseyClientManager cm = new JerseyClientManager();
		Client c = cm.httpClientFor(absoluteURL);

		if (request.getParameters().size() > 0) {
			String queryString = null;
			for (Map.Entry<String, String> param : request.getParameters().entrySet()) {
				queryString = (queryString == null ? "" : queryString + "&") + param.getKey() + "=" + param.getValue();
			}
			absoluteURL = absoluteURL + (absoluteURL.contains("?") ? "&" : "?") + queryString;
		}
		
		WebTarget wt = c.target(absoluteURL);
        Invocation.Builder ib = wt.request().header("Authorization","Bearer "+ this.token);
        if(request.getMethod() == Method.PATCH) {
        	ib.method("PATCH",request.getBodyEntity());
        }
		ib.header("accept", request.getAccept());
		ib.header("content-type", request.getContentType());
		if (authentication != null) {
			String authorizationHeader = authentication.authorizationHeader(this.apiPath);
			if (authorizationHeader != null) {
				ib.header("Authorization", authorizationHeader);
			}
		}
		
		for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
			ib.header(header.getKey(), header.getValue());
		}
		
		Response response = null;
		
		switch (request.getMethod()) {
		case GET:
			response = ib.get();
			break;
		case POST:
			response = ib.post(request.getBodyEntity());
			break;
		case PUT:
			response = ib.put(request.getBodyEntity());
			break;
		case DELETE:
			response = ib.delete();
			break;

		default:
			break;
		}
		
		ApiResponse pr = new ApiResponse(response);
		return pr;
	}

	
}