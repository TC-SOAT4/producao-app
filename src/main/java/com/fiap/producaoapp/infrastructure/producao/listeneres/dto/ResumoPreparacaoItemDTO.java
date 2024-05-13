package com.fiap.producaoapp.infrastructure.producao.listeneres.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumoPreparacaoItemDTO {

    private String nome;
    private Integer quantidade;

}
