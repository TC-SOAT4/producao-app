package com.fiap.producaoapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;
import com.fiap.producaoapp.application.producao.usecases.FinalizarProducao;
import com.fiap.producaoapp.application.producao.usecases.ListarPedidoParaProducao;
import com.fiap.producaoapp.application.producao.usecases.SalvarPedidoRecebidos;
import com.fiap.producaoapp.infrastructure.producao.clients.PedidoRestClient;
import com.fiap.producaoapp.infrastructure.producao.gateways.PedidoProducaoRepositoryGateway;
import com.fiap.producaoapp.infrastructure.producao.persistence.repository.PedidoProducaoRepository;

@Configuration
public class ProducaoConfig {

    @Bean
    FinalizarProducao finalizarProducao(PedidoProducaoGateway pedidoProducaoGateway, PedidoClient pedidoClient) {
        return new FinalizarProducao(pedidoProducaoGateway, pedidoClient);
    }

    @Bean
    ListarPedidoParaProducao ListarPedidoParaProducao(PedidoProducaoGateway pedidoProducaoGateway) {
        return new ListarPedidoParaProducao(pedidoProducaoGateway);
    }

    @Bean
    PedidoProducaoGateway producaoGateway(PedidoProducaoRepository pedidoProducaoRepository) {
        return new PedidoProducaoRepositoryGateway(pedidoProducaoRepository);
    }

    @Bean
    PedidoClient pedidoClient(RestTemplate restTemplate) {
        return new PedidoRestClient(restTemplate);
    }

    @Bean
    SalvarPedidoRecebidos salvarPedidoRecebidos(PedidoProducaoGateway pedidoProducaoGateway) {
        return new SalvarPedidoRecebidos(pedidoProducaoGateway);
    }

}
