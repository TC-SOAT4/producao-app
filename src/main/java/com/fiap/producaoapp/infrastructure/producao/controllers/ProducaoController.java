package com.fiap.producaoapp.infrastructure.producao.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.producaoapp.application.producao.usecases.FinalizarProducao;
import com.fiap.producaoapp.application.producao.usecases.ListarPedidoParaProducao;
import com.fiap.producaoapp.domain.producao.entity.PedidoProducao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Produção")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/producoes")
public class ProducaoController {

    private final FinalizarProducao finalizarProducao;

    private final ListarPedidoParaProducao listarPedidoParaProducao;

    @PostMapping("/finalizar")
    @Operation(summary = "Fianlizar produção de pedido", description = "Fianlizar produção de pedido e atualizar status do pedido")
    public ResponseEntity<?> finalizar() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Lista pedidos em produção")
    public ResponseEntity<List<PedidoProducao>> listarPedidosParaProducao() {
        return ResponseEntity.ok().body(listarPedidoParaProducao.listar());
    }

}
