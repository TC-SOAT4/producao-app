package com.fiap.producaoapp.application.producao.usecases;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.producaoapp.application.pedido.messages.PedidoMessageClient;
import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;

public class FinalizarProducao {

    private final PedidoProducaoGateway pedidoProducaoGateway;

    private final PedidoMessageClient pedidoMessageClient;

    public static final String PEDIDO_STATUS_PRONTO = "Pronto";

    public FinalizarProducao(PedidoProducaoGateway pedidoProducaoGateway, PedidoMessageClient pedidoMessageClient) {
        this.pedidoProducaoGateway = pedidoProducaoGateway;
        this.pedidoMessageClient = pedidoMessageClient;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class })
    public void finalizar(Integer idPedidoProducao) {
        var pedidoProducao = pedidoProducaoGateway.buscar(idPedidoProducao);
        pedidoMessageClient.enviarPedidoParaFinalizacao(pedidoProducao.getPedidoId(), PEDIDO_STATUS_PRONTO);
        pedidoProducaoGateway.remover(pedidoProducao.getIdPedidoProducao());
    }

}
