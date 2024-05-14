package com.fiap.producaoapp.application.producao.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class FinalizarProducaoTest {

    @MockBean
    private PedidoClient pedidoClient;

    @Autowired
    private FinalizarProducao finalizarProducao;

    @Autowired
    private PedidoProducaoGateway pedidoProducaoGateway;
    
    @Test
    @Sql(scripts = {"/clean.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testFinalizar() {

         doNothing().when(pedidoClient).atualizarStatus(anyInt(), anyString());

        var listaAntes = pedidoProducaoGateway.listarTodos();

        Integer idPedidoProducao = 1;
        finalizarProducao.finalizar(idPedidoProducao);

        var listaAposFinalizar = pedidoProducaoGateway.listarTodos();

        assertNotEquals(listaAntes.size(), listaAposFinalizar.size());
        assertEquals(3, listaAntes.size());
        assertEquals(2, listaAposFinalizar.size());

    }
}
