package br.com.financas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CATEGORIAS")
public class CategoriaModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(nullable = false, length = 50)
	private String nome;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
	private List<TransacaoModel> transacoes = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private UsuarioModel usuario;
}
