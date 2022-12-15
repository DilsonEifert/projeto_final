package br.com.financas.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaDto {

	@NotBlank
	private String descricao;
	@NotBlank
	private String numero;
	@NotNull
	private float valor;
	@NotNull
	private UUID usuario_id;
	@NotNull
	private UUID banco_id;
}
