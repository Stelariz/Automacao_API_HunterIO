package stepsDefinitions;

import utils.automacao_API_DSL;

import static io.restassured.RestAssured.*;

import io.cucumber.java.Before;

public class hook_Automacao_API extends automacao_API_DSL{
	
	@Before
	public void initTest () {
		automacao_API_DSL.request = given ().header("Accept", "application/json").header("Content-Type", "application/json");
	}

}
