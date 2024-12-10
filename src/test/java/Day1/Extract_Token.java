package Day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Extract_Token {

    @Test
    public void postRequest(){

        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath="/users/login";

        Response resp= given().log().all().body("{\n" +
                "    \"email\": \"rri@fake.com\",\n" +
                "    \"password\": \"x\"\n" +
                "}").contentType(ContentType.JSON).post();
        int respCode = resp.getStatusCode();
        Assert.assertEquals(respCode,200,"Invalid Response code");
       // resp.prettyPrint();
        String token = resp.then().extract().path("token");
        System.out.println("Token: "+token);

    }

}
