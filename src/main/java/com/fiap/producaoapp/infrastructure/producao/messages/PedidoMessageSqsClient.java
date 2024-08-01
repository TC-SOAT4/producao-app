package com.fiap.producaoapp.infrastructure.producao.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.producaoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.producaoapp.infrastructure.producao.messages.dto.StatusPedidoDTO;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PedidoMessageSqsClient implements PedidoMessageClient {

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(PedidoMessageSqsClient.class);

    @Value("${aws.sqs.out.pedido.status.uri}")
    private String endpointPedidoStatus;

    @Override
    public void enviarPedidoParaFinalizacao(Integer idPedido, String status) {
        try {
            var statusPedidoDTO = new StatusPedidoDTO(idPedido, status, "ProducaoApp"); 
            String json = objectMapper.writeValueAsString(statusPedidoDTO);
            sqsTemplate.send(endpointPedidoStatus, MessageBuilder.withPayload(json).build());
            logger.info("Pedido pronto: {}", idPedido);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
