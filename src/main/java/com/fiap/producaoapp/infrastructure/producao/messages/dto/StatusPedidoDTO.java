package com.fiap.producaoapp.infrastructure.producao.messages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusPedidoDTO {

    private Integer idPedido;
    private String status;
    private String taskApp;

}
