package io.demo.jony.jony.enums;

public enum TaskStateAction {
	
	openOffice("OpenOffice"), doBanking("DoBanking"), closeOffice("CloseOffice");

	private String descricao;

	TaskStateAction(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
}
