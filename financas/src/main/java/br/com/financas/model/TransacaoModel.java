package br.com.financas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_TRANSACOES")
public class TransacaoModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(nullable = false, length = 50)
	private String descricao;
	
	@Column(nullable = false, length = 50)
	private Date data;
	
	@Column(nullable = false, length = 50)
	private char credito;

	@Column(nullable = false, length = 50)
	private float valor;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private UsuarioModel usuario;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private CategoriaModel categoria;
}













