package com.inatel.dm107.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.inatel.dm107.database.JdbcAcesso;

public class UsuarioDAO {

	public ArrayList<Usuario> listar() {

		ArrayList<Usuario> usuarios = new ArrayList<>();

		String sql = "SELECT * FROM usuario";

		try (Connection connection = JdbcAcesso.connect();
				Statement sttm = connection.createStatement();
				ResultSet rs = sttm.executeQuery(sql);) {

			while (rs.next()) {

				String usuario = rs.getString("usuario");
				String senha = rs.getString("senha");

				usuarios.add(new Usuario(usuario, senha));
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			System.err.println("Ocorreu um erro no acesso ao banco de dados: " + e.getMessage());
		}

		return usuarios;
	}
}
