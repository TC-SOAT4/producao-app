package com.fiap.producaoapp.infrastructure.producao.listeneres.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResumoPreparacaoPedidoDTO {

    private Integer idPedido;
    private List<ResumoPreparacaoItemDTO> itens;
    
}
