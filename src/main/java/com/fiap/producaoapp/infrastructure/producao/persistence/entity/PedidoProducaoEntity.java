package com.fiap.producaoapp.infrastructure.producao.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "PedidoProducao")
public class PedidoProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedidoProducao;

    private Integer pedidoId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoProducao")
    private List<ItemProducaoEntity> itens;

    private LocalDateTime data;

    public void setPedidoProducaoNosItens() {
        itens.forEach(item -> item.setPedidoProducao(this));
    }

}