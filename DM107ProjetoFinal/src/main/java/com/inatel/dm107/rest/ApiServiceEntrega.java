package com.inatel.dm107.rest;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.inatel.dm107.database.EntregaDAO;
import com.inatel.dm107.entities.Entrega;

@Path("/entrega")
public class ApiServiceEntrega {
	
	@GET
	@Path("/{numPedido}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response obterEntrega(@PathParam("numPedido") int numPedido) {

		try {
			EntregaDAO entregaDao = new EntregaDAO();
			Entrega entrega = entregaDao.obter(numPedido);
			
			if(entrega != null) {
				return
						Response
							.status(Status.OK)
							.entity(entrega)
							.build();
			} else {
				return
						Response
							.status(Status.NOT_FOUND)
							.build();
			}
			
		} catch (Exception e) {
			return
					Response
						.status(Status.INTERNAL_SERVER_ERROR)
						.build();
		}
	}
	
	@POST
	@Path("/criar")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response criarEntrega(Entrega entrega) {		
		
		System.out.println("Entrega: " + entrega.getDataHoraEntrega());
		
		EntregaDAO entregaDao = new EntregaDAO();
		
		if(entrega.getNumPedido() == 0 || 
				entrega.getIdCliente() == 0) {
			
			return Response
					.status(Status.BAD_REQUEST)
					.build(); 
		}
		
		// Verifica se pedido já existe no banco
		try {
			if(entregaDao.obter(entrega.getNumPedido()) != null) {
				
				return Response
						.status(Status.CONFLICT)
						.build();
			}
		} catch (SQLException e) {
			return
					Response
						.status(Status.INTERNAL_SERVER_ERROR)
						.build();
		}

		
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
