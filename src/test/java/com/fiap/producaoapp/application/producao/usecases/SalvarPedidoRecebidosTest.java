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
public class SalvarPedidoRecebidosTest {

    @Autowired
    private SalvarPedidoRecebidos salvarPedidoRecebidos;
    

    @Test
    @Sql(scripts = { "/clean.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testSalvar() {

        var itemA = ResumoPreparacaoItemDTO.builder().nome("Produto A").quantidade(1).build();
        var itemB = ResumoPreparacaoItemDTO.builder().nome("Produto B").quantidade(2).build();

        var resumoPedido = ResumoPreparacaoPedidoDTO.builder()
                .idPedido(117)
                .itens(List.of(itemA, itemB))
                .build();

        var pedidoProducao = salvarPedidoRecebidos.salvar(resumoPedido);

        assertNotNull(pedidoProducao.getIdPedidoProducao());
    }
}
