package com.fiap.producaoapp.application.producao.usecases;

import com.fiap.producaoapp.application.producao.gateways.ProducaoGateway;

public class ListarPedidoParaProducao {

     private final ProducaoGateway producaoGateway;

     public ListarPedidoParaProducao(ProducaoGateway producaoGateway) {
        this.producaoGateway = producaoGateway;
    }

}
