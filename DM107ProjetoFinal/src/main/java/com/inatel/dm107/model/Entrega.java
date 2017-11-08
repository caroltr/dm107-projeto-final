package com.inatel.dm107.model;

import java.sql.Date;

public class Entrega {

	private int numPedido;
	private int idCliente;
	private String nomeRecebedor;
	private String cpfRecebedor;
	private Date dataHoraEntrega;
	
	public Entrega() {}
	
	public Entrega(int numPedido, int idCliente, String nomeRecebedor, String cpfRecebedor, Date dataHoraEntrega) {

		this.numPedido = numPedido;
		this.idCliente = idCliente;
		this.nomeRecebedor = nomeRecebedor;
		this.cpfRecebedor = cpfRecebedor;
		this.dataHoraEntrega = dataHoraEntrega;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeRecebedor() {
		return nomeRecebedor;
	}

	public void setNomeRecebedor(String nomeRecebedor) {
		this.nomeRecebedor = nomeRecebedor;
	}

	public String getCpfRecebedor() {
		return cpfRecebedor;
	}

	public void setCpfRecebedor(String cpfRecebedor) {
		this.cpfRecebedor = cpfRecebedor;
	}

	public Date getDataHoraEntrega() {
		return dataHoraEntrega;
	}

	public void setDataHoraEntrega(Date dataHoraEntrega) {
		this.dataHoraEntrega = dataHoraEntrega;
	}	
}
