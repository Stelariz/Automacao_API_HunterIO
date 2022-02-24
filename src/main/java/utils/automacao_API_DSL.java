package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class automacao_API_DSL {
	
	private String body;
	private String param;
	private static String uriId;
	
	public static String getUriId() {
		return uriId;
	}
	public static void setUriId(String uri) {
		uriId = uri;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getParam() {
		return param;
	}
	
	public static String urlBase = "https://api.hunter.io";
	public static String recurso = "/v2/leads";
	public static String autorizacao = "api_key=67d5ce25cdab378fe689bf7c25aa12f6c410d8e9";
	public static Response response;
	public static String metodo;
	public static RequestSpecification request;
}