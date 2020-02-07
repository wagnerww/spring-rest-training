create table pedido (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, data_cancelamento datetime, data_criacao datetime not null, data_entrega datetime, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status integer, sub_total decimal(19,2) not null, taxa_frete decimal(19,2) not null, valor_total decimal(19,2) not null, usuario_cliente_id bigint not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB;

alter table pedido add constraint fk_usuario_pedido foreign key (usuario_cliente_id) references usuario (id);
alter table pedido add constraint fk_cidade_pedido foreign key (endereco_cidade_id) references cidade (id);
alter table pedido add constraint fk_forma_pagamento_pedido foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table pedido add constraint fk_restaurante_pedido foreign key (restaurante_id) references restaurante (id);
