#Termo de Consentimento do Usuário

   This is designed to store all terms of use, exposing the endpoints of one CRUD in the remaind

#Pré-requisites
   It's required a IDE Eclipse or InteliJ.
   Minimum versionTerm required is Java 11.
   Maven

#Tecnologia
SpringBoot com Maven 
Java 11

#Para inicializar o projeto utilizar em modo desenvolvimento.
 mvn spring-boot:run -Dspring-boot.run.profiles=dev
 ou apenas mvn spring-boot:run

#O banco de dados ultilizado e o Postgres
Configurações para conectar ao banco de dados esta no arquivo application-dev.properties.
  
 
#Endpoints local
http://localhost:8090/swagger-ui.html#/api/term/aceite-termo
http://localhost:8090/swagger-ui.html#/api/term/user/registry
http://localhost:8090/swagger-ui.html#/api/term/user/{id}
 
#Payload JSON to insert new term of user
{
<<<<<<< HEAD
	 "loginUser":"t34904918827",
	 "descriptionTerm":"termo de uso",
	 "summaryTerm":"sumario",
	 "versionTerm":"v1",
	 "status":"ACTIVE"
	 
=======


  "descricao_termo": "string",
  "flag_atualizacao": true,
  "resumo_termo": "string",
  "status": "ACTIVE",
  "usuario": "string"

}

#Payload JSON aceite Termo
{
  "cip": 0,
  "id_termo": 0,
  "mdm_id": "string",
  "resposta_cliente": true

}

#Documentação
https://jiradasa.atlassian.net/wiki/spaces/AT/pages/892634187/Documenta+o+Api-Termo



 
 