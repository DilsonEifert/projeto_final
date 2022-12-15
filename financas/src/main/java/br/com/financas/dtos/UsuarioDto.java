package br.com.financas.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioDto {

	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	@NotBlank
	private String usuario;
	@NotBlank
	private String senha;
}
