package ru.omsu.imit.novikova.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.omsu.imit.novikova.rest.response.FailureResponse;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ShelterClient {

	private static final Gson GSON = new GsonBuilder().create();
	private static Client createClient() {
		return ClientBuilder.newClient();
	}

	public Object get(String url, Class<?> classResponse) {
		Client client = createClient();
		WebTarget myResource = client.target(url);
		Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
		Response response = (Response)builder.get();
		String body = response.readEntity(String.class);
		int httpCode = response.getStatus();
		Object obj;
		if(httpCode == Response.Status.OK.getStatusCode()) 
			obj = GSON.fromJson(body, classResponse);
			else {
				obj = GSON.fromJson(body, FailureResponse.class);
			}
		client.close();
		return obj;
	}
	
	public Object post(String url, Object object, Class<?> classResponse) {
		Client client = createClient();
		WebTarget myResource = client.target(url);
		Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
		Response response = builder.post(Entity.json(object));
		String body = response.readEntity(String.class);
		int httpCode = response.getStatus();
		Object obj;
		if(httpCode == Response.Status.OK.getStatusCode()) { 
			obj = GSON.fromJson(body, classResponse);
		}
		else {
			obj = GSON.fromJson(body, FailureResponse.class);
		}
		client.close();
		return obj;
	}

	public Object postWrongJson(String url, String json, Class<?> classResponse) {
		Client client = createClient();
		WebTarget myResource = client.target(url);
		Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
		Response response = builder.post(Entity.json(json));
		String body = response.readEntity(String.class);
		Object obj = GSON.fromJson(body, FailureResponse.class);
		client.close();
		return obj;
	}

	public Object put(String url, Object object, Class<?> classResponse) {
		Client client = createClient();
		WebTarget myResource = client.target(url);
		Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
		Response response = builder.put(Entity.json(object));

		String body = response.readEntity(String.class);
		int httpCode = response.getStatus();
		Object obj;
		if(httpCode == Response.Status.OK.getStatusCode()) { 
			obj = GSON.fromJson(body, classResponse);
		}
		else {
			obj = GSON.fromJson(body, FailureResponse.class);
		}
		client.close();
		return obj;
	}

	public Object delete(String url, Class<?> classResponse) {
		Client client = createClient();
		WebTarget myResource = client.target(url);
		Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
		Response response = (Response) builder.delete();
		String body = response.readEntity(String.class);
		int httpCode = response.getStatus();
		Object obj;
		if(httpCode == Response.Status.OK.getStatusCode()) 
			obj = GSON.fromJson(body, classResponse);
			else {
				obj = GSON.fromJson(body, FailureResponse.class);
			}
		client.close();
		return obj;
	}
}
