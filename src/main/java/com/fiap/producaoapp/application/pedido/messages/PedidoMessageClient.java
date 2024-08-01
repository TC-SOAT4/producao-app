package com.fiap.producaoapp.application.pedido.messages;

public interface PedidoMessageClient {

        public void enviarPedidoParaFinalizacao(Integer idPedido, String status);


}
