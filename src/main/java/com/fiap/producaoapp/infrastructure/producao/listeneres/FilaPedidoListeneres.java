package com.fiap.producaoapp.infrastructure.producao.listeneres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.producaoapp.application.producao.usecases.SalvarPedidoRecebidos;
import com.fiap.producaoapp.infrastructure.producao.listeneres.dto.ResumoPreparacaoPedidoDTO;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class FilaPedidoListeneres {

    Logger logger = LoggerFactory.getLogger(FilaPedidoListeneres.class);

    private final SalvarPedidoRecebidos salvarPedidoRecebidos;

   @SqsListener("${aws.sqs.name}")
    public void receiveMessage(String json ) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResumoPreparacaoPedidoDTO resumoPreparacaoPedidoDTO = objectMapper.readValue(json, ResumoPreparacaoPedidoDTO.class);
        logger.info("Recebido: " + resumoPreparacaoPedidoDTO.toString());
        salvarPedidoRecebidos.salvar(resumoPreparacaoPedidoDTO);
    }

}
