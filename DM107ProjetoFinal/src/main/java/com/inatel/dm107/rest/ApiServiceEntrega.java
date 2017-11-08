package com.inatel.dm107.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
					.status(Status.OK)
					.entity(entregas)
					.build();
	}
	
	@POST
	@Path("/criar")
	@Consumes({MediaType.APPLICATION_JSON})	
	public Response criarEntrega(Entrega entrega) {

		EntregaDAO entregaDao = new EntregaDAO();
		boolean criado = entregaDao.criar(entrega);
				
		if(criado) {
		
			return Response
					.status(Status.CREATED)
					.build();
		} else {
			
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
}
