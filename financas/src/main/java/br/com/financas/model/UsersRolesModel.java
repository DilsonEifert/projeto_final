package br.com.financas.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*@Entity*/
@Table(name = "TB_USERS_ROLES")
public class UsersRolesModel {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID usuario_id;
	
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID role_id;
	
}










