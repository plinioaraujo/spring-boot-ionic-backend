package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1,"Pessoa Física"), PESSOAJURIDICA(2,"Pessoa Jurídica");
	
	private Integer id;
	private String nome;
	
	private TipoCliente(Integer id, String nome) {
		this.id=id;
		this.nome=nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public static TipoCliente toEnum(Integer cod){
		
		if (cod == null){
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
		
			if(cod.equals(x.getId())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido" + cod);
		
	}
	
}
