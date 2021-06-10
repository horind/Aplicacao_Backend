package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")//URL do front-end
@RestController
@RequestMapping("/api")
public class Controller {
    
    @Autowired
    ProdRepository prodRepository;
    
    @GetMapping(value = "/produtos")
    public ResponseEntity<List<Produto>> findAll(){
        return new ResponseEntity<>(prodRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/produtos/{nome}")
    public ResponseEntity<Produto> findByName(@PathVariable String nome) {
        return new ResponseEntity<>(prodRepository.findPeloNome(nome), HttpStatus.OK);
    }

    @PostMapping(value = "/produtos")
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        return new ResponseEntity<>(prodRepository.save(produto), HttpStatus.OK);
    }

    @PutMapping(value = "/produtos/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") long id, @RequestBody Produto produto) {
        Optional<Produto> produtoDado = prodRepository.findById(id);
        
        if(produtoDado.isPresent()){
            Produto novoProduto = produtoDado.get();
            novoProduto.setCodigo(produto.getCodigo());
            novoProduto.setNome(produto.getNome());
            novoProduto.setDescricao(produto.getDescricao());
            novoProduto.setCategoria(produto.getCategoria());
            novoProduto.setStatus(produto.getStatus());
            novoProduto.setPreco(produto.getPreco());
            novoProduto.setQuantidade(produto.getQuantidade());
            
            return new ResponseEntity<>(prodRepository.save(novoProduto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/produtos/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        prodRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/produtos")
    public ResponseEntity<HttpStatus> deleteAll() {
        prodRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
