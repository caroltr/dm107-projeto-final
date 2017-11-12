package com.inatel.dm107.entities;

import java.sql.Date;

public class Entrega {

	private int numPedido; // obrigatório
	private int idCliente; // obrigatório
	private String nomeRecebedor;
	private String cpfRecebedor;
	private Date dataHoraEntrega;

	public Entrega() {}
	
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

	@Override
	public String toString() {
		return "Entrega [numPedido=" + numPedido + ", idCliente=" + idCliente + ", nomeRecebedor=" + nomeRecebedor
				+ ", cpfRecebedor=" + cpfRecebedor + ", dataHoraEntrega=" + dataHoraEntrega + "]";
	}
	
}
