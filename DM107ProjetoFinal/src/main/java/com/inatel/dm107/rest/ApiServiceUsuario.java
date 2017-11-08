package com.inatel.dm107.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.inatel.dm107.model.Usuario;
import com.inatel.dm107.model.UsuarioDAO;

@Path("/usuario")
public class ApiServiceUsuario {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEstoques() {
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDao.listar();
		
		return
				Response
					.status(200)
					.entity(usuarios)
					.build();
	}
}
