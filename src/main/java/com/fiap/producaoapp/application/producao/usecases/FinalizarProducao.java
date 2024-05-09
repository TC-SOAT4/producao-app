package com.fiap.producaoapp.application.producao.usecases;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;

public class FinalizarProducao {

    private final PedidoProducaoGateway pedidoProducaoGateway;

    private final PedidoClient pedidoClient;

    public static final String PEDIDO_STATUS_PRONTO = "Pronto";

    public FinalizarProducao(PedidoProducaoGateway pedidoProducaoGateway, PedidoClient pedidoClient) {
        this.pedidoProducaoGateway = pedidoProducaoGateway;
        this.pedidoClient = pedidoClient;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class })
    public void finalizar(Integer idPedidoProducao) {
        var pedidoProducao = pedidoProducaoGateway.buscar(idPedidoProducao);
        pedidoClient.atualizarStatus(pedidoProducao.getPedidoId(), PEDIDO_STATUS_PRONTO);
        pedidoProducaoGateway.remover(pedidoProducao.getIdPedidoProducao());
    }

}
