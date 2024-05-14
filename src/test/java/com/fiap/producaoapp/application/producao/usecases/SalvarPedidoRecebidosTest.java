package com.fiap.producaoapp.application.producao.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.producaoapp.infrastructure.producao.listeneres.dto.ResumoPreparacaoItemDTO;
import com.fiap.producaoapp.infrastructure.producao.listeneres.dto.ResumoPreparacaoPedidoDTO;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class SalvarPedidoRecebidosTest {

    @Autowired
    private SalvarPedidoRecebidos salvarPedidoRecebidos;
    

    @Test
    @Sql(scripts = { "/clean.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testSalvar() {

        var itemA = new ResumoPreparacaoItemDTO();
        itemA.setNome("Produto A");
        itemA.setQuantidade(1);

        var itemB = new ResumoPreparacaoItemDTO();
        itemB.setNome("Produto  B");
        itemB.setQuantidade(2);

        var resumoPedido = new ResumoPreparacaoPedidoDTO();
        resumoPedido.setItens(List.of(itemA, itemB));

        var pedidoProducao = salvarPedidoRecebidos.salvar(resumoPedido);

        assertNotNull(pedidoProducao.getIdPedidoProducao());
    }
}
