package com.fiap.producaoapp.application.producao.usecases;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;

public class FinalizarProducao {

    private final PedidoProducaoGateway pedidoProducaoGateway;

    private final PedidoClient pedidoClient;

    public FinalizarProducao(PedidoProducaoGateway pedidoProducaoGateway, PedidoClient pedidoClient) {
        this.pedidoProducaoGateway = pedidoProducaoGateway;
        this.pedidoClient = pedidoClient;
    }
   
}
