package br.com.financas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financas.model.RoleModel;
import br.com.financas.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
	Optional<UsuarioModel> findByUsuario (String username);
}
