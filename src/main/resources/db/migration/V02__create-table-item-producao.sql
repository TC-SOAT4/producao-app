CREATE TABLE ItemProducao (
    idItemProducao INT NOT NULL AUTO_INCREMENT,
    pedidoProducaoid INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (idItemProducao),
    FOREIGN KEY (pedidoProducaoid) REFERENCES PedidoProducao(idPedidoProducao)
);