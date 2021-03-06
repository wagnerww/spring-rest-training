create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null auto_increment, preco_total decimal(19,2) not null, observacao varchar(255), quantidade integer not null, valor decimal(19,2) not null, pedido_id bigint not null, produto_id bigint not null, primary key (id)) engine=InnoDB
create table pedido (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, data_cancelamento datetime, data_criacao datetime not null, data_entrega datetime, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status integer, sub_total decimal(19,2) not null, taxa_frete decimal(19,2) not null, valor_total decimal(19,2) not null, usuario_cliente_id bigint not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FKcccmjvm9ytuxbe00h3wmtm77y foreign key (usuario_cliente_id) references usuario (id)
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id)
alter table pedido add constraint FKqaa411xsl0xu4tkvt1wpccd3b foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null auto_increment, preco_total decimal(19,2) not null, observacao varchar(255), quantidade integer not null, valor decimal(19,2) not null, pedido_id bigint not null, produto_id bigint not null, primary key (id)) engine=InnoDB
create table pedido (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, data_cancelamento datetime, data_criacao datetime not null, data_entrega datetime, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status integer, sub_total decimal(19,2) not null, taxa_frete decimal(19,2) not null, valor_total decimal(19,2) not null, usuario_cliente_id bigint not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restautante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FKcccmjvm9ytuxbe00h3wmtm77y foreign key (usuario_cliente_id) references usuario (id)
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id)
alter table pedido add constraint FKqaa411xsl0xu4tkvt1wpccd3b foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restautante_forma_pagamento add constraint FKses7w3g1i8e9i6ot6sgj2qmyt foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restautante_forma_pagamento add constraint FKak4oqex3aub1swifwu9d51lrq foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandeza')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Gulz', 9.75, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Tio Arno', 10, 2, utc_timestamp, utc_timestamp)
insert into estado (id, nome) values (1, 'Rio Grande do Sul')
insert into estado (id, nome) values (2, 'Santa Catarina')
insert into cidade (nome, estado_id) values ('Três de Maio', 1)
insert into cidade (nome, estado_id) values ('Pomerode', 2)
insert into permissao (nome, descricao) values ('ADM', 'Pode de tudo')
insert into permissao (nome, descricao) values ('Suporte', 'Somente da suporte')
insert into forma_pagamento (id,descricao) values (1, 'A Vista')
insert into forma_pagamento (id,descricao) values (2, 'A Prazo')
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (1, "Sopa", "Sopa normal deliciosa", 10.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (2, "Alcatra", "Alcatra na chapa", 20.00, true, 1)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (3, "Ala-minuta", "Alá minuta, acompanha salada", 25.00, true, 2)
insert into produto (id, nome, descricao,  preco, ativo, restaurante_id) values (4, "Xis de bacon", "ovo, bife e bacon", 25.00, true, 2)
insert into restautante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 2)
