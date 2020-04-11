package com.br.silverfox.produto.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.silverfox.produto.apirest.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Optional<Produto> findById(Long id);	
	
}
