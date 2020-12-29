package com.kaique.cursospringudemy.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa física"),
	PESSOAJURIDICA(2, "Pessoa jurídica");
	
	private Integer cod;
	private String descricao;

	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null){
			return null;
		}
		for (TipoCliente tipo : TipoCliente.values()) {
			if(cod.equals(tipo.getCod())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + cod);
	}
}