package com.paypalpayment.testcases;

import com.paypalpayment.base.TestBase;
import com.paypalpayment.utils.TestData;
import com.paypalpayment.utils.RestClient;

//import io.restassured.http.ContentType;
import io.restassured.response.Response;

//import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PaymentTestcases extends TestBase {
	static String payment_id;
	public static Response response;
	RestClient restClient = new RestClient();
	TestData td=new TestData();
	public String[][]XData;
	@Test
	public void createAPayment() throws Exception{
		XData=td.readXL("Payment");
		//System.out.println("Body"+td.fieldValue(XData, "CreatePayment", "Path"));
		response=restClient.doPostRequest(td.fieldValue(XData, "CreatePayment", "Path"), td.fieldValue(XData, "CreatePayment", "Body"));
		payment_id=response
		.then()
		.log()
		.all()
		.extract()
		.path("id");
		
		System.out.println(payment_id);
		Assert.assertTrue(payment_id!=null);
		}
	@Test
	public void getPaymentDetails(){
		response=restClient.doGetRequest(td.fieldValue(XData, "GetPaymentDetails", "Path")+payment_id);
		response
		.then()
		.assertThat()
		.statusCode(Integer.parseInt(td.fieldValue(XData, "GetPaymentDetails", "ExpectedResult")));
		
	}

}
