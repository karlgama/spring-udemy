package com.kaique.cursospringudemy.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1,"pendente"),
	QUITADO(2,"quitado"),
	CANCELADO(3,"cancelado");
	
	private Integer cod;
	private String descricao;

	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null){
			return null;
		}
		for (EstadoPagamento tipo : EstadoPagamento.values()) {
			if(cod.equals(tipo.getCod())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + cod);
	}
}
