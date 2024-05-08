package com.fiap.producaoapp.infrastructure.producao.listeneres.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResumoPreparacaoPedidoDTO {

    private Integer idPedido;
    private List<ResumoPreparacaoItemDTO> itens;
    
}
