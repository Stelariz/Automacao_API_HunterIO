package stepsDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.automacao_API_DSL;

public class automacao_API_StepsDefinition extends automacao_API_DSL {

	@Given("que seja digitado um {int} valido")
	public void que_seja_digitado_um_valido(Integer id) {
		setUriId(
				automacao_API_DSL.urlBase + automacao_API_DSL.recurso + "/" + id + "?" + automacao_API_DSL.autorizacao);
	}

	@When("executar uma requisicao {string}")
	public void executar_uma_requisicao(String metodo) {
		automacao_API_DSL.metodo = metodo;
		if (metodo.toUpperCase().equals("GET")) {
			automacao_API_DSL.response = automacao_API_DSL.request.get(getUriId() + "&" + getParam()).andReturn();
		} else if (metodo.toUpperCase().contains("PUT")) {
			automacao_API_DSL.response = automacao_API_DSL.request.body(getBody()).when().put(getUriId()).andReturn();
		} else {
			System.out.println("O valor selecionado nao e um verbo HTTP valido para o teste!");
			fail("O valor selecionado nao e um verbo HTTP valido para o teste!");
		}
	}

	@Then("espero que o codigo HTTP de retorno seja {int}")
	public void espero_que_o_codigo_http_de_retorno_seja(int codigo) {
		try {
			assertEquals(codigo, automacao_API_DSL.response.statusCode());
		} catch (Exception e) {
			fail("O codigo retornado noo foi compativel com o esperado: " + codigo);
		}

	}

	@Then("espero que o tempo de resposta seja menor de {long} segundos")
	public void espero_que_o_tempo_de_resposta_seja_menor_de_segundos(long tempo) {
		try {
			automacao_API_DSL.response.then().time(lessThanOrEqualTo(tempo), TimeUnit.SECONDS);
		} catch (Exception e) {
			fail("O tempo de resposta foi maior que " + tempo + " segundos.");
		}
	}

	@Then("espero que a estrutura de response body esteja igual ao documento")
	public void espero_que_a_estrutura_de_response_body_esteja_igual_ao_documento() {

		if (automacao_API_DSL.metodo.equals("GET")) {
			valida_response_body_get();
		} else if (automacao_API_DSL.metodo.equals("PUT")) {
			valida_response_body_put();
		} else {
			fail("Metodo invalido");
		}
	}

	private void valida_response_body_get() {
		JSONObject responseBody = new JSONObject(automacao_API_DSL.response.body().asString());
		JSONObject nodeData = responseBody.getJSONObject("data");
		JSONObject nodeVerification = nodeData.getJSONObject("verification");
		JSONObject nodeListLeads = nodeData.getJSONObject("leads_list");

		int id = nodeData.getInt("id");
		String campoId = Integer.toString(id);
		assertTrue("Campo invalido", campoId.matches("^\\d+$"));

		String email = nodeData.getString("email");
		assertTrue("Email invalido", email.matches("^\\S+@\\S+\\.\\S+"));

		String primeiroNome = nodeData.getString("first_name");
		assertTrue("Primeiro nome invalido", primeiroNome.matches("^[a-zA-Z]+$"));

		String sobrenome = nodeData.getString("last_name");
		assertTrue("Sobrenome invalido", sobrenome.matches("^[a-zA-Z]+|\\B$"));

		String posicao = nodeData.getString("position");
		assertTrue("Posicao invalida", posicao.matches("^[a-zA-Z]+|\\B$"));

		String empresa = nodeData.getString("company");
		assertTrue("Empresa invalida", empresa.matches("^\\S+|\\B$"));

		String setor = nodeData.getString("company_industry");
		assertTrue("Setor invalido", setor.matches("^\\S+|\\B$"));

		String qtddFunc = nodeData.getString("company_size");
		assertTrue("Quantidade de funcionarios invalida",
				qtddFunc.matches("^([0-9]+)+[-]+([0-9]+)+\\s([a-zA-Z]+)|\\B|\\S+\\s+\\S+$"));

		int cScore = nodeData.getInt("confidence_score");
		String confScore = Integer.toString(cScore);
		assertTrue("Confidence Score invalido", confScore.matches("^[0-9]$|^[1-9][0-9]$|^(100)|\\B$"));

		String site = nodeData.getString("website");
		assertTrue("Site invalido", site.matches("^\\S+\\.\\S+|\\B$"));

		// O formato utilizado em aula retornava "null" escrito dentro da String, o que
		// dava erro ao comparar com o regex.
		String pais = nodeData.isNull("country_code") ? "" : nodeData.getString("country_code");
		assertTrue("Pais invalido", pais.matches("^[A-Z]{2}|\\B$"));

		String fonte = nodeData.isNull("source") ? "" : nodeData.getString("source");
		assertTrue("Fonte invalida", fonte.matches("^\\S+|\\B$"));

		String linkedin = nodeData.isNull("linkedin_url") ? "" : nodeData.getString("linkedin_url");
		assertTrue("Linkedin invalido", linkedin.matches("^\\S+\\.\\S+\\.\\S+|\\B$"));

		String telefone = nodeData.isNull("phone_number") ? "" : nodeData.getString("phone_number");
		assertTrue("Telefone invalido", telefone.matches("^\\d+|\\B$"));

		String twitter = nodeData.isNull("twitter") ? "" : nodeData.getString("twitter");
		assertTrue("Twitter invalido", twitter.matches("^[@]+\\S+|\\B$"));

		String status = nodeData.isNull("sync_status") ? "" : nodeData.getString("sync_status");
		assertTrue("Status invalido", status.matches("^\\S+|\\B$"));

		String anotacoes = nodeData.isNull("notes") ? "" : nodeData.getString("notes");
		assertTrue("Anotacoes invalidas", anotacoes.matches("^\\S+|\\B$"));

		String enviandoStatus = nodeData.isNull("sending_status") ? "" : nodeData.getString("sending_status");
		assertTrue("Status invalido", enviandoStatus.matches("^\\S+|\\B$"));

		String ultAtividade = nodeData.isNull("last_activity_at") ? "" : nodeData.getString("last_activity_at");
		assertTrue("Campo invalido", ultAtividade.matches("^\\S+|\\B$"));

		String ultContato = nodeData.isNull("last_contacted_at") ? "" : nodeData.getString("last_contacted_at");
		assertTrue("Campo invalido", ultContato.matches("^\\S+|\\B$"));

		String dateLead = nodeVerification.isNull("date") ? "" : nodeVerification.getString("date");
		assertTrue("Dado invalido", dateLead.matches("^\\S+|\\B$"));

		String statusLead = nodeVerification.isNull("status") ? "" : nodeVerification.getString("status");
		assertTrue("Status invalido", statusLead.matches("^\\S+|\\B$"));

		String custID = nodeData.isNull("customer_id") ? "" : nodeData.getString("customer_id");
		assertTrue("Customer ID invalido", custID.matches("^\\S+\\-\\S+|\\B$"));

		int idLL = nodeListLeads.getInt("id");
		String idListLeads = Integer.toString(idLL);
		assertTrue("ID invalido", idListLeads.matches("^\\d+$"));

		// Por conta do " ' ", "Stela's Leads" não é considerada uma palavra pelo regex.
		String nameListLeads = nodeListLeads.get("name").toString();
		assertTrue("Nome invallido", nameListLeads.matches("^\\D+$"));

		int leadsC = nodeListLeads.getInt("leads_count");
		String leadsCount = Integer.toString(leadsC);
		assertTrue("Quantidade de leads invalida", leadsCount.matches("^\\d+$"));
	}

	// @PUT
	private void valida_response_body_put() {
		JSONObject responseBody = new JSONObject(automacao_API_DSL.response.body().asString());
		JSONArray errors = responseBody.getJSONArray("errors");
		JSONObject nodeErrors = errors.getJSONObject(0);

		int arrayErrors = errors.length();

		for (int i = 0; i < arrayErrors; i++) {

			String id = nodeErrors.getString("id");
			assertTrue("Id invalido", id.matches("^\\D+$"));

			int code = nodeErrors.getInt("code");
			String codigo = Integer.toString(code);
			assertTrue("Codigo invalido", codigo.matches("^\\d+$"));

			String detalhes = nodeErrors.getString("details");
			assertTrue("Detalhe invalido", detalhes.matches("^.*$"));
		}
	}

	// @PUT
	@Given("que seja editado o campo confident_score com o valor {int}")
	public void que_seja_editado_o_campo_confident_score_com_o_valor(Integer valor) {
		setBody("{\r\n    \"confidence_score\": " + valor + "\r\n}");
	}

}
