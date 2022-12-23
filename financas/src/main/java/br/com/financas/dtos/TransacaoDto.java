package br.com.financas.dtos;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransacaoDto {
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Date data;
	
	@NotNull
	private char credito;

	@NotNull
	private float valor;
	
	@NotNull
	private UUID usuario_id;
	
	@NotNull
	private UUID categoria_id;
	
	@NotNull
	private UUID conta_id;

}
