package com.fiap.producaoapp.application.producao.usecases;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.application.producao.gateways.ProducaoGateway;

public class FinalizarProducao {

    private final ProducaoGateway producaoGateway;

    private final PedidoClient pedidoClient;

    public FinalizarProducao(ProducaoGateway producaoGateway, PedidoClient pedidoClient) {
        this.producaoGateway = producaoGateway;
        this.pedidoClient = pedidoClient;
    }


    
   
}
