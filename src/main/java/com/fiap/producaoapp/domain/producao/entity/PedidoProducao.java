package com.fiap.producaoapp.domain.producao.entity;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PedidoProducao {

    private Integer idPedidoProducao;
    private Integer pedidoId;
    private List<ItemProducao> itens;
    private LocalDateTime data; 

}