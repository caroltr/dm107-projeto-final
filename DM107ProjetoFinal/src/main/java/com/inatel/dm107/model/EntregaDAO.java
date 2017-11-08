package com.inatel.dm107.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.inatel.dm107.database.JdbcAcesso;

public class EntregaDAO {
	
	public ArrayList<Entrega> listar() {

		ArrayList<Entrega> entregas = new ArrayList<>();

		String sql = "SELECT * FROM entrega";

		try (Connection connection = JdbcAcesso.connect();
				Statement sttm = connection.createStatement();
				ResultSet rs = sttm.executeQuery(sql);) {

			while (rs.next()) {

				int numPedido = rs.getInt("num_pedido");
				int idCliente = rs.getInt("id_cliente");
				String nomeRecebedor = rs.getString("nome_recebedor");
				String cpfRecebedor = rs.getString("cpf_recebedor");
				Date dataHoraEntrega = rs.getDate("data_hora_entrega");

				entregas.add(new Entrega(numPedido, idCliente, nomeRecebedor, cpfRecebedor, dataHoraEntrega));
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			System.err.println("Ocorreu um erro no acesso ao banco de dados: " + e.getMessage());
		}

		return entregas;
	}
}
