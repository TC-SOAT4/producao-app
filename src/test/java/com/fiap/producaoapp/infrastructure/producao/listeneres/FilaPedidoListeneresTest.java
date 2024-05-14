package com.fiap.producaoapp.infrastructure.producao.listeneres;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.producaoapp.application.producao.usecases.SalvarPedidoRecebidos;
import com.fiap.producaoapp.domain.producao.entity.PedidoProducao;
import com.fiap.producaoapp.infrastructure.producao.listeneres.dto.ResumoPreparacaoPedidoDTO;

@ExtendWith(MockitoExtension.class)
class FilaPedidoListeneresTest {

    @Mock
    private SalvarPedidoRecebidos salvarPedidoRecebidos;

    @InjectMocks
    private FilaPedidoListeneres filaPedidoListeneres;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        filaPedidoListeneres = new FilaPedidoListeneres(salvarPedidoRecebidos);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    private String jsonMsg = """
                {
                    "idPedido": 12,
                    "itens": [
                      {
                        "nome": "Produto A",
                        "quantidade": 2
                      }
                    ]
                  }
            """;

    private String jsonInvalidoMsg = """
                {
                    "pedidoId": 12,

                  }
            """;

    @Test
    void testReceiveMessage() throws JsonProcessingException {
        when(salvarPedidoRecebidos.salvar(any(ResumoPreparacaoPedidoDTO.class))).thenReturn(new PedidoProducao());
        filaPedidoListeneres.receiveMessage(jsonMsg);
        verify(salvarPedidoRecebidos, times(1)).salvar(any(ResumoPreparacaoPedidoDTO.class));
    }

    @Test
    void testReceiveMessage_jsonInvalido() throws JsonProcessingException {
        assertThrows(JsonProcessingException.class, () -> filaPedidoListeneres.receiveMessage(jsonInvalidoMsg));
        verify(salvarPedidoRecebidos, times(0)).salvar(any(ResumoPreparacaoPedidoDTO.class));
    }
}
