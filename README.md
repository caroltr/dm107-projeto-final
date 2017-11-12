# Projeto final da disciplina DM107 - Desenvolvimento de Web Services com Segurança sob plataforma Java e PHP
## Pós Graduação em Desenvolvimento de Aplicações para Dispositivos Móveis e Cloud Computing - INATEL

### Modelo banco de dados

entrega           | usuario
-------------     | -------------
id                | id
num_pedido        | usuario
id_cliente        | senha
nome_recebedor    | 
cpf_recebedor     | 
data_hora_entrega | 

-------------------------------------------------------

### Projeto em Java

Utilizados Gradle e Jersey.

* Criar uma entrega com "_número do pedido_" e "_id do cliente_" obrigatórios;
* Obter uma entrega pelo "_número do pedido_".

**Script para banco de dados (MySQL)**:

```
CREATE DATABASE dm107_projeto_final;

USE dm107_projeto_final;

CREATE TABLE usuario (
	id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
	usuario VARCHAR(50) NOT NULL,
	senha VARCHAR(50) NOT NULL
);

CREATE TABLE entrega (
	id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
	num_pedido int NOT NULL,
	id_cliente int NOT NULL,
	nome_recebedor VARCHAR(100),
    cpf_recebedor VARCHAR(15),
    data_hora_entrega datetime
);
```

**Endpoints**:

```
[POST]  /api/entrega/criar
[GET]   /api/entrega/{numeroPedido}
```

**Json exemplo para _body_ da Criação**:
```
{
  "numPedido": 15,
  "idCliente": 1,
  "nomeRecebedor": "caroline",
  "cpfRecebedor": "123.123.123-00",
  "dataHoraEntrega": "2017-11-12"
}
```

-------------------------------------------------------

### Projeto em PHP

Utilizados Slim e NotORM.

* Atualizar uma entrega com "_nome do recebedor_", "_CPF do recebedor_" e "_data e hora da entrega_" obrigatórios;
* Deletar uma entrega pelo "_número do pedido_".

**Script para banco de dados (PHPMyAdmin)**:

```
CREATE DATABASE IF NOT EXISTS `dm107_projeto_final`;

USE `dm107_projeto_final`;

CREATE TABLE IF NOT EXISTS `entrega` (
  `id` integer NOT NULL auto_increment,
  `num_pedido` integer NOT NULL default 0,
  `id_cliente` integer NOT NULL default 0,
  `nome_recebedor` varchar(100) NOT NULL default '',
  `cpf_recebedor` varchar(15) NOT NULL default '',
  `data_hora_entrega` datetime,

  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` integer NOT NULL auto_increment,
  `usuario` varchar(50) NOT NULL default '',
  `senha` varchar(15) NOT NULL default '',

  PRIMARY KEY (`id`)
);

CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';

GRANT ALL PRIVILEGES ON dm107_projeto_final.* TO 'root'@'localhost';
```

**Endpoints**:

```
[PUT]     /entrega/update
[DELETE]  /entrega/delete/{numeroPedido}
```

**Json exemplo para _body_ da atualização**:
```
{
  "numPedido": 1,
  "idCliente": 1,
  "nomeRecebedor": "caroline",
  "cpfRecebedor": "123.123.123-00",
  "dataHoraEntrega": "11/09/2017 11:25 PM"
}
```
