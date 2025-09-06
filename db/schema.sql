create database if not exists db_flabijoux;

use db_flabijoux;

create table tbl_categoria (
id_categoria integer not null auto_increment primary key,
nome_categoria varchar(50) not null);

create table tbl_produto(
id_produto integer not null auto_increment primary key,
nome_produto varchar(45) not null,
descricao_produto text,
preco_produto double,
destaque integer,
disponivel integer);

create table tbl_categoria_produto(
id_categoria integer not null,
id_produto integer not null,
primary key(id_categoria, id_produto),

constraint fk_categoria foreign key (id_categoria) references tbl_categoria(id_categoria),
constraint fk_produto foreign key (id_produto) references tbl_produto(id_produto)
);

create table tbl_variante_produto(
id_variante integer not null auto_increment primary key,
id_produto integer not null,
nome varchar(45),
descricao text,
link_foto varchar(255),

constraint fk_variante_do_produto foreign key (id_produto) references tbl_produto(id_produto)
);

create table tbl_cliente(
id_cliente integer not null auto_increment primary key,
nome_cliente varchar(50) not null,
email_cliente varchar(100) not null unique,
telefone_cliente varchar(20) not null unique,
cep varchar(10),
logradouro varchar(100),
numero varchar(10),
complemento varchar(20),
bairro varchar(50),
cidade varchar(45),
estado varchar(45)
);

create table tbl_pedido(
numero_pedido integer not null auto_increment primary key,
id_cliente integer not null,
data_pedido date,
valor_bruto double,
desconto double,
valor_total double,
status_pedido integer,

constraint fk_pedido foreign key (id_cliente) references tbl_cliente(id_cliente)
);

create table tbl_itempedido(
num_seq integer not null auto_increment primary key,
numero_pedido integer not null,
id_variante integer not null,
valor_unitario double,
quantidade integer,
valor_total double,

constraint fk_item_do_pedido foreign key (numero_pedido) references tbl_pedido(numero_pedido),
constraint fk_item_da_variante foreign key(id_variante) references tbl_variante_produto(id_variante)

);

create table tbl_usuario(
id_usuario integer not null auto_increment primary key,
nome_usuario varchar(45) not null,
login varchar(20) not null,
senha varchar(100) not null
);