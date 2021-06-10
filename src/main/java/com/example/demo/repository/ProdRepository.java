package com.example.demo.repository;

import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdRepository extends JpaRepository<Produto, Long>{
    
    @Query("select u from Produto u where u.nome = ?1")
    Produto findPeloNome(String nome);
}
