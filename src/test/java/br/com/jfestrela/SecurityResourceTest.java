package br.com.jfestrela;

import io.quarkus.test.junit.QuarkusTest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

@QuarkusTest
public class GreetingResourceTest {

	@ConfigProperty(name = "greenting.name")
	private String name;
	
	private static final String HELLO = "hello";
	private static final String GREETING = "greeting";
	private static final String NAME = "name";

	@Test
	public void testHelloEndpoint() {
		given().when().get("/"+HELLO).then().statusCode(200).body(is(HELLO+"\n"));
	}

	@Test
	public void testGreetingIamEndpoint() {
		given().pathParam(name, name)
		       .when().get("/"+HELLO+"/"+GREETING+"/")
		       .then().statusCode(200)
			   .body(is(HELLO+" " + name + "\n"));
	}

	@Test
	public void testGreetingEndpoint() {
		String uuid = UUID.randomUUID().toString();
		given().pathParam(NAME, uuid)
		       .when().get("/"+HELLO+"/"+GREETING+"/{"+NAME+"}")
		       .then().statusCode(200)
			   .body(is(HELLO+" " + uuid + "\n"));
	}
}