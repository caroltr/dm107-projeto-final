package com.inatel.dm107.web;

import java.io.IOException;
import java.util.Base64;

import com.inatel.dm107.model.Usuario;
import com.inatel.dm107.model.UsuarioDAO;

public class AuthenticationService {
	
	public boolean authenticate(String credentials) {

		if (null == credentials)
			return false;
		
		// extraindo o valor vindo do header "Basic encodedstring" for Basic
		final String encodedUserPassword = credentials.replaceFirst("Basic" + " ", "");
		System.out.println("encodedUserPassword: " + encodedUserPassword);
		
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("usernameAndPassword: " + usernameAndPassword);
		
		final String usernameAndPassSplit[] = usernameAndPassword.split(":");
		final String username = usernameAndPassSplit[0];
		final String password = usernameAndPassSplit[1];

		UsuarioDAO usuarioDao = new UsuarioDAO();
		boolean authenticationStatus = usuarioDao.usuarioExiste(new Usuario(username, password));

		return authenticationStatus;
	}
}