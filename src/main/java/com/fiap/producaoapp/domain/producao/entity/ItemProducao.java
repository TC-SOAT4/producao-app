package com.fiap.producaoapp.domain.producao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ItemProducao {

    private Integer idItemProducao;    
    private String nome;
    private Integer quantidade;

}
