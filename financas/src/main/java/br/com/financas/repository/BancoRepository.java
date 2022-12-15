package br.com.financas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financas.model.BancoModel;

@Repository
public interface BancoRepository extends JpaRepository<BancoModel, UUID>{

}
