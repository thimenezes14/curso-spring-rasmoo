package com.rasmoo.cliente.escola.gradecurricular.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MateriaDTO {
	String nome;
	String codigo;
	int horas;
	int frequencia;
	
	public MateriaDTO(String nome, String codigo, int horas, int frequencia) {
		this.nome = nome;
		this.codigo = codigo;
		this.horas = horas;
		this.frequencia = frequencia;
	}
}
