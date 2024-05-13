package com.fiap.producaoapp.infrastructure.producao.gateways;

import java.time.LocalDateTime;
import java.util.List;

import com.fiap.producaoapp.application.producao.gateways.PedidoProducaoGateway;
import com.fiap.producaoapp.domain.producao.entity.ItemProducao;
import com.fiap.producaoapp.domain.producao.entity.PedidoProducao;
import com.fiap.producaoapp.infrastructure.producao.persistence.entity.ItemProducaoEntity;
import com.fiap.producaoapp.infrastructure.producao.persistence.entity.PedidoProducaoEntity;
import com.fiap.producaoapp.infrastructure.producao.persistence.repository.PedidoProducaoRepository;

public class PedidoProducaoRepositoryGateway implements PedidoProducaoGateway {

    private final PedidoProducaoRepository pedidoProducaoRepository;

    public PedidoProducaoRepositoryGateway(PedidoProducaoRepository pedidoProducaoRepository) {
        this.pedidoProducaoRepository = pedidoProducaoRepository;
    }

    @Override
    public List<PedidoProducao> listarTodos() {
        return pedidoProducaoRepository.findAllByOrderByDataAsc().stream().map(pedidoProducaoEntity -> {

            var itens = pedidoProducaoEntity.getItens().stream()
                    .map(item -> ItemProducao.builder().nome(item.getNome()).quantidade(item.getQuantidade()).build())
                    .toList();

            return PedidoProducao.builder()
                    .idPedidoProducao(pedidoProducaoEntity.getIdPedidoProducao())
                    .pedidoId(pedidoProducaoEntity.getPedidoId())
                    .data(pedidoProducaoEntity.getData())
                    .itens(itens)
                    .build();

        }).toList();
    }

    @Override
    public void remover(Integer idPedidoProducao) {
        var pedidoProducaoEntity = pedidoProducaoRepository.findById(idPedidoProducao).orElseThrow();
        pedidoProducaoRepository.delete(pedidoProducaoEntity);
    }

    @Override
    public PedidoProducao salvar(PedidoProducao pedidoProducao) {

        var itensProducaoEntity = pedidoProducao.getItens().stream()
                .map(item -> ItemProducaoEntity.builder().nome(item.getNome()).quantidade(item.getQuantidade()).build())
                .toList();

        var pedidoProducaoEntity = PedidoProducaoEntity.builder()
                .pedidoId(pedidoProducao.getPedidoId())
                .data(LocalDateTime.now())
                .itens(itensProducaoEntity)
                .build();

        pedidoProducaoEntity.setPedidoProducaoNosItens();

        pedidoProducaoEntity = pedidoProducaoRepository.save(pedidoProducaoEntity);

        var itens = pedidoProducaoEntity.getItens().stream()
                .map(item -> ItemProducao.builder().nome(item.getNome()).quantidade(item.getQuantidade()).build())
                .toList();

        return PedidoProducao.builder()
                .idPedidoProducao(pedidoProducaoEntity.getIdPedidoProducao())
                .pedidoId(pedidoProducaoEntity.getPedidoId())
                .data(pedidoProducaoEntity.getData())
                .itens(itens)
                .build();
    }

    @Override
    public PedidoProducao buscar(Integer idPedidoProducao) {
        var pedidoProducaoEntity = pedidoProducaoRepository.findById(idPedidoProducao).orElseThrow();

        var itens = pedidoProducaoEntity.getItens().stream()
                .map(item -> ItemProducao.builder().nome(item.getNome()).quantidade(item.getQuantidade()).build())
                .toList();

        return PedidoProducao.builder()
                .idPedidoProducao(pedidoProducaoEntity.getIdPedidoProducao())
                .pedidoId(pedidoProducaoEntity.getPedidoId())
                .data(pedidoProducaoEntity.getData())
                .itens(itens)
                .build();
    }

}
