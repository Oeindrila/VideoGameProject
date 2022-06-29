package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

//import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import static io.restassured.response.Response.*;
//import static io.restassured.specification.RequestSpecification.*;

public class videoGameTest {
	
	@Test (priority = 1)
	
	public void getAllVideoGamesList () {
		
		given()
		
		.when ()
			.get("http://localhost:8080/app/videogames")
		.then()
			.assertThat()
			.statusCode(200)
			.contentType("application/xml")
			.header("content-length","1862");			
		
	}
	
	@Test (priority =2)
	public void addNewVideoGameTest() {
		
		HashMap <String, String> data = new HashMap <String, String>();
		
		data.put("id", "100");
		data.put("name", "Pacman");
		data.put("releaseDate", "2022-10-28T45:20:03.890Z");
		data.put("reviewScore", "4");
		data.put("category", "Fun");
		data.put("rating", "good");
		
	String res =
			given()
			.header("content-type", "application/json")
			.body(data)
		.when()
			.post("http://localhost:8080/app/videogames")
		.then()
			.assertThat()
			.statusCode(200)
			.log().body()
			.extract().response().asString();
	
	
		AssertJUnit.assertEquals(res.contains("Record Added Successfully"), true);		
		
	}
	
	@Test(priority = 3)
	public void getSingleGameByID () {
		
		when()
			.get("http://localhost:8080/app/videogames/100")
		.then()
			.statusCode(200)
			.assertThat()
			.log().body()
			.contentType("application/xml");
			
	}
	
	
	@Test(priority =4)
	
	public void updateGameId() {
		
		HashMap data = new HashMap ();
		
		data.put("id", "10");
		data.put("name", "Goldman");
		data.put("releaseDate", "2022-12308T45:20:03.890Z");
		data.put("reviewScore", "5");
		data.put("category", "Fun");
		data.put("rating", "Excellent");
		
			given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("http://localhost:8080/app/videogames/100")
		.then()
			.statusCode(200)
			.log().body();
			
		
	}
	
	@Test(priority = 5)
	public void deleteVideoGame() {
		
	String res =	given()
			.when()
				.delete("http://localhost:8080/app/videogames/100")
			.then()
				.assertThat()
				.log().body()
				.extract().response().asString();
	
	AssertJUnit.assertEquals(res.contains("Record Deleted Successfully"), true);
		
		
	}

	
	

}
