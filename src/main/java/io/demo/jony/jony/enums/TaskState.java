package io.demo.jony.jony.enums;

/**
 * Enum .
 *
 * @author Virtus
 */
public enum TaskState {

	initial("Initial"), opened("Opened"), banked("Banked"), closed("Closed");

	private String descricao;

	TaskState(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}