insert into cozinha (id, nome) values (1, 'Tailandeza');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Gulz', 9.75, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tio Arno', 10, 2);

insert into estado (id, nome) values (1, 'Rio Grande do Sul');
insert into estado (id, nome) values (2, 'Santa Catarina');

insert into cidade (nome, estado_id) values ('Três de Maio', 1);
insert into cidade (nome, estado_id) values ('Pomerode', 2);

insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo');
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte');

insert into forma_pagamento (descricao) values ('A Vista');
insert into forma_pagamento (descricao) values ('A Prazo');