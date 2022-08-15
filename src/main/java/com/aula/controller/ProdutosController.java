package com.aula.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.entidades.Produtos;
import com.aula.repository.IProdutosRepository;
import com.aula.services.ProdutosService;

@RestController
@RequestMapping("/")
public class ProdutosController {
	
	@Autowired
	IProdutosRepository repo;
	
	@Autowired
	ProdutosService service;
	
	@GetMapping
	public String xpto() {
		return "Index";
	}
	
	@GetMapping("/produtos")
	public ResponseEntity<List<Produtos>> getProdutos() {
		List<Produtos> produtos = service.consultarProdutos();
		return ResponseEntity.status(HttpStatus.OK).body(produtos) ;
	}

	@GetMapping("/produtos/{idproduto}")
	public ResponseEntity <Produtos> getProdutoById(@PathVariable("idproduto") Long idproduto) {
		
		return ResponseEntity.ok(service.consultarProdutoPorId(idproduto));
	}
	
	@PostMapping ("/produtos")
	public ResponseEntity<Produtos> saveProduto(@RequestBody Produtos produto) {
		Produtos pt = service.salvar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(pt);
	}
	
	@DeleteMapping("/produtos/{idproduto}")
	public ResponseEntity<Void> deleteProduto(@PathVariable("idproduto") Long idproduto) {
		service.excluirProduto(idproduto);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/produtos/{idproduto}")
	public ResponseEntity<Produtos> saveContato(@PathVariable("idproduto") Long idproduto, @RequestBody Produtos produto) {
		return ResponseEntity.ok(service.alterarProduto(idproduto, produto));
	}
}
