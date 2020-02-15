insert into cozinha (id, nome) values (1, 'Tailandeza');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp);

insert into estado (id, nome) values (1, 'Rio Grande do Sul');
insert into estado (id, nome) values (2, 'Santa Catarina');

insert into cidade (nome, estado_id) values ('Três de Maio', 1);
insert into cidade (nome, estado_id) values ('Pomerode', 2);

insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo');
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte');

insert into forma_pagamento (id,descricao) values (1, 'A Vista');
insert into forma_pagamento (id,descricao) values (2, 'A Prazo');

insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1);
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1);
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2);
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2);

insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2);

