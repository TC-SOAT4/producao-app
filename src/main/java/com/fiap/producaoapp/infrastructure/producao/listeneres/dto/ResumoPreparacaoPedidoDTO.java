package com.fiap.producaoapp.infrastructure.producao.listeneres.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumoPreparacaoPedidoDTO {

    private Integer idPedido;
    private List<ResumoPreparacaoItemDTO> itens;
    
}
