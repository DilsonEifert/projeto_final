package br.com.financas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.financas.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID>{
	/*
	@Query(
		value = "SELECT DISTINCT r FROM RoleModel r "
				+ "INNER JOIN UsersRolesModel ur ON r.role_id = ur.role_id "
				+ "INNER JOIN UsuarioModel u ON ur.usuario_id = u.role_id "
				+ "WHERE u.nome = ?1")
	RoleModel findByUsername(String username);
	*/
}
