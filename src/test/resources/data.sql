INSERT INTO PedidoProducao (idPedidoProducao, pedidoId, data)
VALUES (1,11, '2023-07-01 13:00:00.10000' ),
       (2,22, '2023-07-01 14:00:00.20000' ),
       (3,33, '2023-07-01 15:00:00.40000' );

INSERT INTO ItemProducao (idItemProducao, pedidoProducaoid, nome, quantidade)
VALUES (1, 1, 'Produto A', 1),
       (2, 1, 'Produto B', 2),
       (3, 2, 'Produto C', 3),
       (4, 2, 'Produto D', 4),
       (5, 3, 'Produto E', 5);