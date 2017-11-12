package com.inatel.dm107.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.inatel.dm107.entities.Usuario;

public class UsuarioDAO {

	/**
	 * Retorna se um usuario existe no banco de dados ou não, verificando se o nome de
	 * usuario e senha passados conferem.
	 * 
	 * @param usuario
	 *            objeto usuario contendo o nome de usuario e senha
	 * @return <code>true</code> se o nome de usuario e sua respectiva senha estão
	 *         cadastrados no banco de dados, <code>false</code> caso contrário.
	 */
	public boolean usuarioExiste(Usuario usuario) {

		String sql = "SELECT * FROM usuario WHERE usuario = '" + usuario.getUsuario() + "' AND senha = '"
				+ usuario.getSenha() + "'";

		try (Connection connection = JdbcAcesso.connect();
				Statement sttm = connection.createStatement();
				ResultSet rs = sttm.executeQuery(sql);) {

			if (rs.next()) {
				System.out.println("TRUE");
				return true;
			} else {
				System.out.println("FALSE");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
