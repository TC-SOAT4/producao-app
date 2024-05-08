package com.fiap.producaoapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.application.producao.gateways.ProducaoGateway;
import com.fiap.producaoapp.application.producao.usecases.FinalizarProducao;
import com.fiap.producaoapp.application.producao.usecases.ListarPedidoParaProducao;
import com.fiap.producaoapp.infrastructure.producao.clients.PedidoRestClient;
import com.fiap.producaoapp.infrastructure.producao.gateways.ProducaoRepositoryGateway;

@Configuration
public class ProducaoConfig {

    @Bean
    FinalizarProducao finalizarProducao(ProducaoGateway producaoGateway, PedidoClient pedidoClient) {
        return new FinalizarProducao(producaoGateway, pedidoClient);
    }

    @Bean
    ListarPedidoParaProducao ListarPedidoParaProducao(ProducaoGateway producaoGateway) {
        return new ListarPedidoParaProducao(producaoGateway);
    }

    @Bean
    ProducaoGateway producaoGateway() {
        return new ProducaoRepositoryGateway();
    }

    @Bean
    PedidoClient pedidoClient() {
        return new PedidoRestClient();
    }
    

}
