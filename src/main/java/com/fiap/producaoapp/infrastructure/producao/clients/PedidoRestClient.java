package com.fiap.producaoapp.infrastructure.producao.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;

public class PedidoRestClient implements PedidoClient {

    public static final String URI_PEDIDO_ATUALIZAR_STATUS = "http://localhost:8080/pedido-app/api/pedidos";

    private final RestTemplate restTemplate;

    public PedidoRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void atualizarStatus(Integer idPedidoProducao, String statusPedido) {

        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder
                .fromHttpUrl(URI_PEDIDO_ATUALIZAR_STATUS.concat("/{idPedidoProducao}"))
                .queryParam("novoStatus", statusPedido)
                .encode()
                .toUriString();

        var response = restTemplate.exchange(urlTemplate,
                HttpMethod.PUT, entity, String.class, idPedidoProducao);

        if (response.getStatusCode().is2xxSuccessful())
            return;

        throw new RuntimeException("Não foi possivel realizar a tentiva de pagamento");
    }

}
