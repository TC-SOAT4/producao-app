package com.fiap.producaoapp.infrastructure.producao.clients;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.fiap.producaoapp.application.producao.usecases.FinalizarProducao;
import com.fiap.producaoapp.exceptions.AtualizarStatusException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class PedidoRestClientTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PedidoRestClient pedidoRestClient;

    @Test
    void testAtualizarStatus() {

        when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(), eq(String.class), any(Integer.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        pedidoRestClient.atualizarStatus(1, FinalizarProducao.PEDIDO_STATUS_PRONTO);
        verify(restTemplate, times(1)).exchange(any(String.class), eq(HttpMethod.PUT), any(), eq(String.class),
                any(Integer.class));
    }

    @Test
    void testAtualizarStatus_erro() {

        when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(), eq(String.class), any(Integer.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        assertThrows(AtualizarStatusException.class,
                () -> pedidoRestClient.atualizarStatus(1, FinalizarProducao.PEDIDO_STATUS_PRONTO));

        verify(restTemplate, times(1)).exchange(any(String.class), eq(HttpMethod.PUT), any(), eq(String.class),
                any(Integer.class));
    }
}
