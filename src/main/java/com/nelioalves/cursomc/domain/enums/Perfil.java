package com.nelioalves.cursomc.domain.enums;

public enum Perfil {

	
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	
	private Integer id;
	private String nome;
	
	private Perfil(Integer id, String nome) {
		this.id=id;
		this.nome=nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public static Perfil toEnum(Integer cod){
		
		if (cod == null){
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
		
			if(cod.equals(x.getId())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido" + cod);
		
	}
	
	
}
