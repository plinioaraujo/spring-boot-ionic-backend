package com.nelioalves.cursomc.domain;

import java.util.Date;

import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

public class PagamentoComBoleto extends Pagamento {

	private Date dataPagamento;
	private Date dataVencimento;

	public PagamentoComBoleto() {

	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento,
			Date dataVencimento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	

}
