#api-termo - Termo de Consentimento do Usuário

   This is designed to store all terms of use, exposing the endpoints of one CRUD in the remainder.

#Pré-requisites
   It's required a IDE Eclipse or InteliJ.
   Minimum version required is Java 11.
   Maven

#Compiling 
  mvn clean install
  
#Run Localhost  
In class ApiTermoApplication.java
 Execute "Run -> Java Application"
 
 
#Apis Rest
http://localhost:8080/term/user/registry
http://localhost:8080/term/user/{id}
 
 
#Payload JSON to insert new term of user
{
	 "loginUser":"t34904918827",
	 "descriptionTerm":"termo de uso",
	 "summaryTerm":"sumario",
	 "version":"v1",
	 "status":"ACTIVE"
}
 
 