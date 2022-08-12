package com.aula.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aula.entidades.Produtos;

@Repository
public interface IProdutosRepository  extends CrudRepository<Produtos, Long>{

}
