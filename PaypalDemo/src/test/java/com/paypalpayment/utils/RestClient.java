package com.paypalpayment.utils;

import static io.restassured.RestAssured.given;

import java.util.Map;

import com.paypalpayment.base.TestBase;

//import com.student.specs.SpecificationFactory;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestClient extends TestBase{
	/***
	 * 
	 * @param requestPath
	 * @return
	 */
	public Response doGetRequest(String requestPath) {
		
		return 	given()
					.auth()
					.oauth2(accessToken)
		   		   	.when()
		   		   	.get(requestPath);
	}
	
	/**
	 * 
	 * @param uri
	 * @param body
	 * @return
	 */
	public Response doPostRequest(String uri, String body) {
		
		return given()
				.contentType(ContentType.JSON)
				.auth()
				.oauth2(accessToken)
				.when()
				.body(body)
				.post(uri);
	}
	
	/**
	 * 
	 * 
	 * @param res
	 * @param params
	 * @return
	 */
	public Response doGetRequestWithQueryParam(String res, 
											  Map<String, String> params) {

		Response response = given()
								.queryParams(params)
							.when()
							.get(res);

		return response;

	}
	
	/**
	 * 
	 * @param res
	 * @param headers
	 * @return
	 */
	public Response doGetRequestWithHeader(String res,
										  Map<String, String> headers) {

		Response response = given()
								.headers(headers)
							.when()
							.get(res);

		return response;

	}
	
	
	/**
	 * 
	 * @return
	 */
	public Response doPutRequest(String res , String body) {

		Response response = given()
						   .when()
						   .body(body)
						   		.put(res);

		return response;

	}
	
	/**
	 * 
	 * @return
	 */
	public Response doPatchRequest(String res , String body) {

		Response response = given()
						   .when()
						   .body(body)
						   		.patch(res);

		return response;

	}
	
	/**
	 * 
	 * @return
	 */
	public Response doDeleteRequest(String res) {

		Response response = given()
						   .when()
						   		.delete(res);

		return response;

	}

}
