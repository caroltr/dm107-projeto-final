package com.inatel.dm107.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAcesso {

	private static final String url = "jdbc:mysql://localhost/dm107_projeto_final";
	private static final String username = "root";
	private static final String password = "admin";

	/**
	 * Conecta ao banco de dados MySQL
	 *
	 * @return um objeto Connection
	 */
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * Fecha um objeto PreparedStatement
	 * 
	 * @param psttm
	 */
	public static void psttmClose(PreparedStatement psttm) {
		try {
			if (psttm != null) {
				psttm.close();
			}
		} catch (Exception e) {
			/* ignored */
		}
	}

	/**
	 * Fecha um objeto ResultSet
	 * 
	 * @param rs
	 */
	public static void rsClose(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			/* ignored */
		}
	}
}
