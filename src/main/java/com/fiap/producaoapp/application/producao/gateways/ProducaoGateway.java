package com.fiap.producaoapp.application.producao.gateways;

import java.util.List;

import com.fiap.producaoapp.domain.producao.entity.Producao;

public interface ProducaoGateway {

    public List<Producao> listarTodos();

    public void remover(Integer id);

    public void salvar(Integer id);

}
