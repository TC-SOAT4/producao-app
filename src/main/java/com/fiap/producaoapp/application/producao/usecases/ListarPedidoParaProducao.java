package com.fiap.producaoapp.application.producao.usecases;


import java.util.List;

import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;
import com.fiap.producaoapp.domain.producao.entity.PedidoProducao;

public class ListarPedidoParaProducao {

     private final PedidoProducaoGateway pedidoProducaoGateway;

     public ListarPedidoParaProducao(PedidoProducaoGateway pedidoProducaoGateway) {
        this.pedidoProducaoGateway = pedidoProducaoGateway;
    }

    public List<PedidoProducao> listar() {
        return pedidoProducaoGateway.listarTodos();
    }

}
