# 🎯 Automação de API - Hunter.io (RestAssured & Cucumber)

Este projeto automatiza a validação dos principais endpoints da **API Hunter.io**, focando na verificação de domínios, busca de e-mails e validação de entregabilidade, garantindo que a integração com o serviço de enriquecimento de dados esteja íntegra.

## 🛠️ Stack Técnica e Padrões

* **Linguagem:** Java 11+
* **Framework de Teste:** JUnit
* **Automação de API:** RestAssured (DSL para validação HTTP)
* **BDD (Behavior Driven Development):** Cucumber (Gherkin)
* **Gerenciador de Dependências:** Maven
* **Segurança:** Manipulação de API Key via variáveis de ambiente/config.

---

## 🧪 Detalhamento dos Testes (Cenários)

A automação cobre os fluxos principais da API, validando tanto o caminho feliz quanto o tratamento de erros:

### 🔍 1. Domain Search API
* **Busca por Domínio Válido:** Valida se a API retorna `200 OK` e uma lista de e-mails associados ao domínio consultado.
* **Validação de Payload:** Verifica se campos obrigatórios como `domain`, `organization` e `emails` estão presentes no JSON de retorno.
* **Domínio Inexistente:** Garante que a API trate corretamente consultas a domínios que não possuem dados.

### 📧 2. Email Verifier API
* **Verificação de Entregabilidade:** Testa o endpoint de validação de e-mail, checando os status de `format`, `mx_records` e `smtp_check`.
* **Consumo de Créditos:** Valida se o retorno indica corretamente o status da conta após a consulta.

### 🛡️ 3. Segurança e Autenticação
* **Acesso sem API Key:** Valida o retorno `401 Unauthorized` quando a chave de acesso não é enviada.
* **API Key Inválida:** Testa o comportamento com chaves malformadas ou expiradas.
* **Rate Limit:** (Opcional) Cenários para validar o comportamento ao atingir o limite de requisições do plano.

---

## 📊 Relatórios e Evidências

Para testes de API, a visibilidade do tráfego de dados é crucial:

### **Log de Requisições (Request/Response)**
O projeto utiliza os filtros do **RestAssured** para logar no console:
* **Request:** Headers, Query Params e Body enviados.
* **Response:** Status Code, Tempo de resposta e JSON Body recebido.

### **Cucumber HTML Reports**
Relatório visual consolidado gerado após a execução:
* **Localização:** `target/cucumber-reports/index.html`
* **Detalhes:** Exibe os exemplos da `Examples Table` do Gherkin que passaram ou falharam.

---

## 🚀 Como Executar o Projeto

### **1. Configuração da API Key**
Para rodar os testes, você precisa de uma chave válida do [Hunter.io](https://hunter.io/api).
> **Dica:** Nunca suba sua chave real para o GitHub. Use um arquivo `config.properties` (ignorado no .gitignore) ou variáveis de ambiente.

### **2. Execução via Terminal**

```bash
# Clone o repositório
git clone [https://github.com/Stelariz/Automacao_API_HunterIO.git](https://github.com/Stelariz/Automacao_API_HunterIO.git)

# Instale as dependências
mvn clean install

# Execute todos os testes de API
mvn test
