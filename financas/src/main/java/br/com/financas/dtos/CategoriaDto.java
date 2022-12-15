package br.com.financas.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoriaDto {

	@NotBlank
	private String nome;
	@NotNull
	private char credito;
	@NotNull
	private UUID usuario_id;

	

}
