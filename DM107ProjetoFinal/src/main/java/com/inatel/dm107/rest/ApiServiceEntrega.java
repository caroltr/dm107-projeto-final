package com.inatel.dm107.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.inatel.dm107.model.Entrega;
import com.inatel.dm107.model.EntregaDAO;

@Path("/entrega")
public class ApiServiceEntrega {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEstoques() {

		EntregaDAO entregaDao = new EntregaDAO();
		List<Entrega> entregas = entregaDao.listar();
		
		return
				Response
					.status(200)
					.entity(entregas)
					.build();
	}
}
