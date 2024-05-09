package com.fiap.producaoapp.infrastructure.producao.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ItemProducao")
public class ItemProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemProducao;

    @ManyToOne
    @JoinColumn(name = "pedidoProducaoid")
    private PedidoProducaoEntity pedidoProducao;

    private String nome;
    private Integer quantidade;

}
