# Projeto final da disciplina DM107 - Desenvolvimento de Web Services com Segurança sob plataforma Java e PHP
## Pós Graduação em Desenvolvimento de Aplicações para Dispositivos Móveis e Cloud Computing - INATEL

### Banco de dados

entrega           | usuario
-------------     | -------------
id                | id
num_pedido        | usuario
id_cliente        | senha
nome_recebedor    | 
cpf_recebedor     | 
data_hora_entrega | 

### Projeto em Java

Utilizados Gradle e Jersey.

* Criar uma entrega com "_número do pedido_" e "_id do cliente_" obrigatórios;
* Obter uma entrega pelo "_número do pedido_".

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
