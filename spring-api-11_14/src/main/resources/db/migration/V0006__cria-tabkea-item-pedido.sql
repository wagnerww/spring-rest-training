create table item_pedido (id bigint not null auto_increment, preco_total decimal(19,2) not null, observacao varchar(255), quantidade integer not null, valor decimal(19,2) not null, pedido_id bigint not null, produto_id bigint not null, primary key (id)) engine=InnoDB;

alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id);
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id);
