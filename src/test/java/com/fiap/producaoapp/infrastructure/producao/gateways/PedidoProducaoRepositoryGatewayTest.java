package com.fiap.producaoapp.infrastructure.producao.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;
import com.fiap.producaoapp.domain.producao.entity.ItemProducao;
import com.fiap.producaoapp.domain.producao.entity.PedidoProducao;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
@Sql(scripts = {"/clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class PedidoProducaoRepositoryGatewayTest {

    @Autowired
    private PedidoProducaoGateway pedidoProducaoGateway;


    @Test
    void testBuscar_PedidoExistente() {
        testSalvar();
        var idPedidoASerPesquisado = pedidoProducaoGateway.listarTodos().stream().findFirst().get().getIdPedidoProducao();
        var pedido = pedidoProducaoGateway.buscar(idPedidoASerPesquisado);
        assertEquals(idPedidoASerPesquisado, pedido.getIdPedidoProducao());
    }

    @Test
    void testListarTodos_PedidosExistentes() {
        testSalvar();
        var lista = pedidoProducaoGateway.listarTodos();
        assertEquals(3, lista.size());
    }

    @Test
    void testListarTodos_NaoExistemPEdido() {
        var lista = pedidoProducaoGateway.listarTodos();
        assertEquals(0, lista.size());
    }

    @Test
    void testRemover() {
        testSalvar();
        var lista = pedidoProducaoGateway.listarTodos();
        var idPedidoASerDeletado= pedidoProducaoGateway.listarTodos().stream().findFirst().get().getIdPedidoProducao();
        pedidoProducaoGateway.remover(idPedidoASerDeletado);
        var listaAposDelete = pedidoProducaoGateway.listarTodos();
        assertNotEquals(listaAposDelete.size(), lista.size());
    }

    @Test
    private void testSalvar() {

        var itemA = ItemProducao.builder().nome("Produto A").quantidade(1).build();
        var itemB = ItemProducao.builder().nome("Produto B").quantidade(2).build();
        var itemC = ItemProducao.builder().nome("Produto C").quantidade(3).build();
        var itemD = ItemProducao.builder().nome("Produto D").quantidade(4).build();
        var itemE = ItemProducao.builder().nome("Produto E").quantidade(5).build();


        var pedido1 = PedidoProducao.builder().pedidoId(1).data(LocalDateTime.now()).itens(List.of(itemA, itemB)).build();
        var pedido2 = PedidoProducao.builder().pedidoId(1).data(LocalDateTime.now()).itens(List.of(itemB, itemC)).build();
        var pedido3 = PedidoProducao.builder().pedidoId(1).data(LocalDateTime.now()).itens(List.of(itemD, itemE)).build();

        pedido1 = pedidoProducaoGateway.salvar(pedido1);
        pedido2 = pedidoProducaoGateway.salvar(pedido2);
        pedido3 = pedidoProducaoGateway.salvar(pedido3);

        assertNotNull(pedido1.getIdPedidoProducao());
        assertNotNull(pedido2.getIdPedidoProducao());
        assertNotNull(pedido3.getIdPedidoProducao());

    }

}
