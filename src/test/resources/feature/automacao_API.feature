@Automacao_Cenarios_API
Feature: Leads HunterIO
  Automacao de dois cenários baseados na documentacao Leads do HunterIO

  @GET
  Scenario Template: Requisicao de consulta de leads com ID valido
    Given que seja digitado um <id> valido
    When executar uma requisicao <metodo>
    Then espero que o codigo HTTP de retorno seja <codigo>
    And espero que o tempo de resposta seja menor de <tempo> segundos
    And espero que a estrutura de response body esteja igual ao documento
    
    Examples: 
      | 		id 		| metodo | codigo| tempo |
      |  90148541 |  "GET" | 	200  |	 5	 |

  @PUT
  Scenario Template: Requisicao de edicao de leads com confident_score invalido
    Given que seja editado o campo confident_score com o valor <valor>
    When executar uma requisicao "PUT"
    Then espero que o codigo HTTP de retorno seja 422
    And espero que o tempo de resposta seja menor de 5 segundos
    And espero que a estrutura de response body esteja igual ao documento
    
    Examples: 
      | valor |
      |  101 	|