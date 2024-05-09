
### Tech Challenge - FASE 4
<p align="center">
Sistema desenvolvido como forma de avalição da terceira fase do curso de Pós graduação de Arquitetura de Software. Se trata de um sistema para controle de pedidos para uma lanchonete, a fim de otimizar a eficiência no atendimento aos clientes e gerenciar o estoque de maneira adequada.
</p>

**Sobre o projeto**
* Spring-boot 3 com Java 17
* Banco de dados MariaDD
* Path: /producao-app
* Porta: 8080

**Requisitos para executar**


- Criação do banco de dados

```
$ docker run --detach --name mariadb-producao-db -p 3306:3306 --env MARIADB_DATABASE=producaodb --env  MARIADB_USER=mariadb --env MARIADB_PASSWORD=root --env MARIADB_ROOT_PASSWORD=root mariadb:latest
```

**Executar**

```
$ mvn clean install
```

```
$ spring-boot:run
```


**Executar utilizando o docker**

- Existem um arquivo docker compose na raiz do projeto com as configurações necessária para executar a aplicação e o banco de dados.

```
$ docker-compose up --build
```

**Swagger**

*[ http://localhost:8080/producao-app/swagger-ui/index.html]( http://localhost:8080/producao-app/swagger-ui/index.html " http://localhost:8080/producao-app/swagger-ui/index.html")


------------

***Banco de dados***

------------

**Kubernetes**

- Os arquivos de manifesto(.ymal) se encontra na raiz do projeto na pasta k8s.
	- A ordem de execução dos manifestos de preferência deve ser seguida.
```
k8s
```
- Para a aplicação serão criados:
	-	1 Configmap 
	-	1 Deployment com 2 replicas 
	- 	1 Service
	-	1 ServiceAccount
	- 	HorizontalPodAutoscaler

------------

**Função Lambda para autenticação**

```
curl --request GET \
  --url 'https://g7upbosl28.execute-api.us-east-1.amazonaws.com/Prod/auth/?password=<password>&username=<cpf>' \
  --header 'User-Agent:: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36'
```

**Cadastro de usuário no Cognito**

[SignIn/SignUp - Cognito](https://lanchonete.auth.us-east-1.amazoncognito.com/login?client_id=7dbvinjad7nqfv31ah9thbkgu4&response_type=token&scope=aws.cognito.signin.user.admin+email+openid+phone+profile&redirect_uri=https%3A%2F%2Fexample.com%2F "SignIn/SignUp - Cognito")



