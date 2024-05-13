package com.fiap.producaoapp.application.producao.usecases;

import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;
import com.fiap.producaoapp.domain.producao.entity.ItemProducao;
import com.fiap.producaoapp.domain.producao.entity.PedidoProducao;
import com.fiap.producaoapp.infrastructure.producao.listeneres.dto.ResumoPreparacaoPedidoDTO;

public class SalvarPedidoRecebidos {

    private final PedidoProducaoGateway pedidoProducaoGateway;

     public SalvarPedidoRecebidos(PedidoProducaoGateway pedidoProducaoGateway) {
        this.pedidoProducaoGateway = pedidoProducaoGateway;
    }

    public PedidoProducao salvar(ResumoPreparacaoPedidoDTO resumoPedido) {
        var itensProducao = resumoPedido.getItens().stream().map(item -> ItemProducao.builder().nome(item.getNome()).quantidade(item.getQuantidade()).build()).toList();
       
        var pedidoProducao = PedidoProducao.builder()
        .pedidoId(resumoPedido.getIdPedido())
        .itens(itensProducao)
        .build();

        return pedidoProducaoGateway.salvar(pedidoProducao);
    }

}
