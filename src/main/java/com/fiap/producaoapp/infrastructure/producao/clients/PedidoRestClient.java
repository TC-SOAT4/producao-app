package com.fiap.producaoapp.infrastructure.producao.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fiap.producaoapp.application.pedido.clients.PedidoClient;
import com.fiap.producaoapp.exceptions.AtualizarStatusException;

public class PedidoRestClient implements PedidoClient {

    @Value("${uri.api.pedido.atualizar.status}")
    private String uriPedidoAtualizarStatus;

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
                .fromHttpUrl(uriPedidoAtualizarStatus.concat("/{idPedidoProducao}"))
                .queryParam("novoStatus", statusPedido)
                .encode()
                .toUriString();

        var response = restTemplate.exchange(urlTemplate,
                HttpMethod.PUT, entity, String.class, idPedidoProducao);

        if (response.getStatusCode().is2xxSuccessful())
            return;

        throw new AtualizarStatusException("Não foi possivel realizar a tentiva de pagamento");
    }

}
