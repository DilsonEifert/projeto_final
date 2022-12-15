package br.com.financas.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BancoDto {
	@NotBlank
	private String nome;
	@NotBlank
	private String codigo;
}
