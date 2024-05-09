package com.fiap.producaoapp.application.producao.gateways;

import java.util.List;

import com.fiap.producaoapp.domain.producao.entity.PedidoProducao;

public interface PedidoProducaoGateway {

    public List<PedidoProducao> listarTodos();

    public void remover(Integer id);

    public void salvar(PedidoProducao pedidoProducao);

}
