package com.aula.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.entidades.Produtos;
import com.aula.repository.IProdutosRepository;


@Service
public class ProdutosService {
	
	@Autowired
	IProdutosRepository repo;
	
	public Produtos salvar (Produtos produto) {
		return repo.save(produto);
	}
	
	public List<Produtos> consultarProdutos(){
		List <Produtos> produtos = repo.findAll();
		return produtos;
	}

	public Produtos consultarProdutoPorId (Long id) {
		Optional<Produtos> op = repo.findById(id);
		Produtos pt = op.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
		return pt;
	}
	
	public void excluirProduto(Long idproduto) {
		Produtos pt = consultarProdutoPorId(idproduto);
		repo.delete(pt);
	}
	
	public Produtos alterarProduto(Long id, Produtos produto) {
		Produtos pt = consultarProdutoPorId(id);
		pt.setNome(produto.getNome());
		pt.setPreco(produto.getPreco());
		pt.setEstoque(produto.getEstoque());
		
		return repo.save(pt);
	}

}
