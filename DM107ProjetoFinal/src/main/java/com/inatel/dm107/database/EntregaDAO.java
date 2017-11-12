package com.inatel.dm107.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.inatel.dm107.entities.Entrega;

public class EntregaDAO {

	/**
	 * Obtém uma entrega armazenada no banco de dados, por meio do número de pedido
	 * especificado.
	 * 
	 * @param numPedido
	 *            número do pedido para buscar a entrega.
	 * @return a entrega.
	 * @throws SQLException
	 */
	public Entrega obter(int numPedido) throws SQLException {

		String sql = "SELECT * FROM entrega WHERE num_pedido = " + numPedido;

		try (Connection connection = JdbcAcesso.connect();
				Statement sttm = connection.createStatement();
				ResultSet rs = sttm.executeQuery(sql);) {

			if (rs.next()) {

				int idCliente = rs.getInt("id_cliente");
				String nomeRecebedor = rs.getString("nome_recebedor");
				String cpfRecebedor = rs.getString("cpf_recebedor");
				Date dataHoraEntrega = rs.getDate("data_hora_entrega");

				Entrega entrega = new Entrega();
				entrega.setNumPedido(numPedido);
				entrega.setIdCliente(idCliente);
				entrega.setNomeRecebedor(nomeRecebedor);
				entrega.setCpfRecebedor(cpfRecebedor);
				entrega.setDataHoraEntrega(dataHoraEntrega);

				return entrega;
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			// Ocorreu um erro no acesso ao banco de dados.
			throw e;
		}

		// Entrega não encontrada
		return null;
	}

	/**
	 * Cria uma entrega.
	 * 
	 * @param entrega
	 *            objeto entrega a ser armazenado no banco de dados.
	 * @return <code>true</code> se a entrega foi armazenada com sucesso,
	 *         <code>false</code> caso contrário.
	 */
	public boolean criar(Entrega entrega) {

		String sqlCriarEntrega = "INSERT INTO entrega (num_pedido, id_cliente, nome_recebedor, cpf_recebedor, data_hora_entrega) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = JdbcAcesso.connect();
				PreparedStatement psttm = connection.prepareStatement(sqlCriarEntrega)) {

			psttm.setInt(1, entrega.getNumPedido());
			psttm.setInt(2, entrega.getIdCliente());
			psttm.setString(3, entrega.getNomeRecebedor());
			psttm.setString(4, entrega.getCpfRecebedor());
			psttm.setDate(5, entrega.getDataHoraEntrega());

			if (psttm.executeUpdate() != 0) {
				// A entrega foi cadastrada com sucesso!
				return true;

			} else {
				// Ocorreu um erro ao cadastrar a entrega.
				return false;
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			// Ocorreu um erro no acesso ao banco de dados.
			return false;
		}
	}	
}
