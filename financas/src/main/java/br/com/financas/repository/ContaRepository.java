package br.com.financas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financas.model.ContaModel;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, UUID>{

}
