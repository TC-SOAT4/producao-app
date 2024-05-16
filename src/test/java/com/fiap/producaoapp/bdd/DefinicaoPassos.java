package com.fiap.producaoapp.bdd;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DefinicaoPassos {

    @LocalServerPort
    private int port;

    private Response response;

    Integer idPedidoEmProducao = 1;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Quando("requisitado a lista dos pedidos que estão em produção")
    public void requisitado_a_lista_dos_pedidos_que_estão_em_produção() {
        response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("producao-app/api/producoes");
    }

    @Então("os pedidos em produção são exibidos")
    public void os_pedidos_em_produção_são_exibidos() {
        response.then()
        .statusCode(HttpStatus.OK.value())
        .body("data.size()", is(3))
        .body("[0]", hasKey("idPedidoProducao"))
        .body("[0]", hasKey("pedidoId"))
        .body("[0]", hasKey("itens"))
        .body("[0]", hasKey("data"));
    }

}
