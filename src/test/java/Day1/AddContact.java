package Day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class AddContact {
    String token;

    @Test(priority = 1)
    public void postRequest() {

        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = Constants.login;

        Response resp = given().log().all().body("{\n" +
                "    \"email\": \"rri@fake.com\",\n" +
                "    \"password\": \"myPassword\"\n" +
                "}").contentType(ContentType.JSON).post();
        int respCode = resp.getStatusCode();
        Assert.assertEquals(respCode, 200, "Invalid Response code");
        // resp.prettyPrint();
        token = resp.then().extract().path("token");
        System.out.println("Token: " + token);
    }

    @Test(priority = 2)
    public void post_AddContact() {
        RestAssured.baseURI = Constants.baseURl;
        RestAssured.basePath = Constants.contacts;

        HashMap<String,String> newContact= new HashMap<String,String>();
        newContact.put("firstName","Ram");
        newContact.put("lastName","NAir");
        newContact.put("birthdate","1999-08-01");
        newContact.put("email","ram@mailinator.com");
        newContact.put("phone","4293293923");
        newContact.put("street1","banglagoe");
        newContact.put("street2","asdsdf");
        newContact.put("city","Bangalore");
        newContact.put("stateProvince","Kar");
        newContact.put("postalCode","345232345");
        newContact.put("country","USA");
        Response resps= given().log().all().contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token) //Take Care of Space issue Next Bearer Object
                .body(newContact).post();

        resps.prettyPrint();


    }

}
