package com.fiap.producaoapp.application.pedido.clients;

public interface PedidoClient {

    public void atualizarStatus(Integer idPedido, String statusPedido);

}
