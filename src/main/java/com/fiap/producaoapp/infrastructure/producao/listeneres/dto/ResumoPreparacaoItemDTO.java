package com.fiap.producaoapp.infrastructure.producao.listeneres.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResumoPreparacaoItemDTO {

    private String nome;
    private Integer quantidade;

}
