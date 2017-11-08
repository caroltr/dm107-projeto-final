package com.inatel.dm107.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	
	public boolean criar(Entrega entrega) {
		
		String sqlCriarEntrega = "INSERT INTO entrega (num_pedido, id_cliente, nome_recebedor, cpf_recebedor, data_hora_entrega) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection connection = JdbcAcesso.connect();
			PreparedStatement psttm = connection.prepareStatement(sqlCriarEntrega)) {
			
			psttm.setInt(1, entrega.getNumPedido());
			psttm.setInt(2, entrega.getIdCliente());
			psttm.setString(3, entrega.getNomeRecebedor());
			psttm.setString(4, entrega.getCpfRecebedor());
			psttm.setDate(5, entrega.getDataHoraEntrega());
			
			if(psttm.executeUpdate() != 0) {
				// System.out.println("O atendimento foi cadastrado com sucesso!");				
				return true;
				
			} else {
				// System.err.println("Ocorreu um erro ao cadastraro atendimento.");
				return false;
			}
			
		} catch (SQLException e) {
			// e.printStackTrace();
			// System.err.println("Ocorreu um erro no acesso ao banco de dados: " + e.getMessage());
			return false;
		}
}
}
