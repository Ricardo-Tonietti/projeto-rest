package com.br.silverfox.produto.apirest.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.silverfox.produto.apirest.model.Produto;
import com.br.silverfox.produto.apirest.repository.ProdutoRepository;

@RestController
@RequestMapping(value="/api")
public class ProdutoResource {
	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/produtos")
	public ResponseEntity<List<Produto>> getAllProduto(){
		List<Produto> produtoList = produtoRepository.findAll();
		if(produtoList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<List<Produto>>(produtoList,HttpStatus.OK);
		}
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> getOneProduto(@PathVariable(value="id")long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		if(!produto.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Produto>(produto.get(),HttpStatus.OK);
		}
	}

	@PostMapping("/produtos")
	public ResponseEntity<Produto> salvaProduto(
			@RequestBody @Valid Produto produto) {
		return new ResponseEntity<Produto>(produtoRepository.
				save(produto),HttpStatus.CREATED);
	}

	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<?> deleteProduto (@PathVariable(value = "id") long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		if(!produto.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			produtoRepository.delete(produto.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/produtos/{id}")
	public ResponseEntity<Produto> updateProduto(
			@PathVariable(value="id") long id,
			@RequestBody @Valid Produto produto){
		Optional<Produto> produtoO = produtoRepository.findById(id);
		if(!produtoO.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			produto.setId(produtoO.get().getId());
			return new ResponseEntity<Produto>(produtoRepository.save(produto),HttpStatus.ACCEPTED);
		}
	}
	
}
