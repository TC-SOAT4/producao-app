package com.fiap.producaoapp.infrastructure.producao.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.application.pedido.messages.PedidoMessageClient;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Sql(scripts = { "/clean.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProducaoControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private PedidoClient pedidoClient;

    @MockBean
    private PedidoMessageClient pedidoMessageClient;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testFinalizar() {

        doNothing().when(pedidoClient).atualizarStatus(any(Integer.class), any(String.class));
        doNothing().when(pedidoMessageClient).enviarPedidoParaFinalizacao(any(Integer.class), any(String.class));


        var idPedidoProducao = 1;

        given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .when()
          .post("producao-app/api/producoes/{idPedidoProducao}/finalizar", idPedidoProducao)
          .then()
          .statusCode(HttpStatus.OK.value());

    }

    @Test
    void testFinalizarPedidoProdrucaoInvalido() {

        doNothing().when(pedidoClient).atualizarStatus(any(Integer.class), any(String.class));

        var idPedidoProducao = 999;

        given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .when()
          .post("producao-app/api/producoes/{idPedidoProducao}/finalizar", idPedidoProducao)
          .then()
          .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    @Test
    void testListarPedidosParaProducao() {

        given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("producao-app/api/producoes")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("data.size()", is(3))
        .body("[0]", hasKey("idPedidoProducao"))
        .body("[0]", hasKey("pedidoId"))
        .body("[0]", hasKey("itens"))
        .body("[0]", hasKey("data"));
    }

    @Test
    @Sql(scripts = { "/clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testListarPedidosParaProducaoSemPEdidosCadastrados() {

        given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("producao-app/api/producoes")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("data.size()", is(0));
    }
}
