package com.fiap.producaoapp.infrastructure.producao.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.producaoapp.infrastructure.producao.persistence.entity.PedidoProducaoEntity;

public interface PedidoProducaoRepository extends JpaRepository<PedidoProducaoEntity, Integer> {

    public List<PedidoProducaoEntity> findAllByOrderByDataAsc();
    
}
