<?php
    use \Psr\Http\Message\ServerRequestInterface as Request;
    use \Psr\Http\Message\ResponseInterface as Response;

    require '../vendor/autoload.php';

    $app = new \Slim\App;
    
    // Conectando ao banco de dados
    $host = "localhost";
    $tableName = "dm107_projeto_final";
    $user = "admin";
    $pass = "admin";

    try {

        $pdo = new PDO("mysql:host=" .  $host . ";dbname=" . $tableName, $user, $pass);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
        
        $db = new NotORM($pdo);

    } catch(PDOException $e) {
        print "Error ao conectar no banco de dados! " . $e->getMessage();
        die();
    }

    // Adicionando autenticação
    // https://github.com/tuupola/slim-basic-auth

    // Buscar usuários do banco de dados
    $usuarios = $db->usuario();
    $arrayUsuarios = array(); // HashMap com key = usuario e value = senha
    foreach($usuarios as $usuario) {
        $arrayUsuarios[$usuario['usuario']] = $usuario['senha'];
    }

    $app->add(new \Slim\Middleware\HttpBasicAuthentication([
		"users" => $arrayUsuarios
	]));

    // Remove uma entrega do banco de dados
    $app->delete('/entrega/delete/{numPedido}', function(Request $request, Response $response) use ($db) {
        
        $numPedido = $request->getAttribute("numPedido"); // número de um pedido é único

        $entrega = $db->entrega()->where("num_pedido", $numPedido);
        if ($entrega->fetch()) {
            $deleted = $entrega->delete(); // retorno é a quantidade de linhas afetadas

            if($deleted > 0) {
                // Sucesso ao remover entrega
                return $response->withStatus(200);
            } else {
                // Erro ao remover entrega
                return $response->withStatus(500); // NOT WORKING
            }
        } else {
            // Entrega não encontrada
            return $response->withStatus(404); // NOT WORKING
        }
    });

    // Edita uma entrega do banco de dados
    $app->put('/entrega/update', function(Request $request, Response $response) use ($db) {
        
        $data = $request->getParsedBody();

        $dadosValidos = isset($data['numPedido']) && // dado obrigatório para reconhecer o pedido
        isset($data['nomeRecebedor']) && isset($data['cpfRecebedor']) && isset($data['dataHoraEntrega']); // dados obrigatórios para edição

        if(!$dadosValidos) {
            return $response->withStatus(400);
        }
        
        $numPedido = $data['numPedido'];
        $idCliente = $data['idCliente'];
        $nomeRecebedor = $data['nomeRecebedor'];
        $cpfRecebedor = $data['cpfRecebedor'];

        $timestamp = strtotime($data['dataHoraEntrega']);
        $dataHoraEntrega = date("Y-m-d H:i:s", $timestamp); // formato de saída: "2017-11-02 00:00:00"


        $entrega = $db-> entrega()->where("num_pedido", $numPedido); // número de um pedido é único

        if ($entrega->fetch()) {

            $entregaUpdate = (array(
                // Não faz sentido alterar numero do pedido e id do cliente.-
                "nome_recebedor" => $nomeRecebedor,
                "cpf_recebedor" => $cpfRecebedor,
                "data_hora_entrega" => $dataHoraEntrega
                )
            );

            $updated = $entrega->update($entregaUpdate);

            if($updated) {
                // Sucesso ao atualizar entrega
                return $response->withStatus(200);
            } else {
                // Falha ao atualizar campo
                return $response->withStatus(500);
            }

        } else {
            // Entrega não encontrada
            return $response->withStatus(404);
        }
    });

    $app->run();
?>