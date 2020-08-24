package com.stefanini.selecao.business.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.selecao.business.IPessoaService;
import com.stefanini.selecao.dao.IPessoaRepository;
import com.stefanini.selecao.model.Pessoa;


@Service
public class PessoaService implements IPessoaService{

  
    @Autowired
    private IPessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) {
    	Pessoa pessoaAux = null;
    	pessoaAux = this.buscarPorCpf(pessoa.getCpf());
    	if(pessoaAux == null && pessoa.getCpf() != null && pessoa.getNome() != null && pessoa.getDataNascimento() != null) {
    		pessoa = pessoaRepository.save(pessoa);
        	return pessoa;
    	}
    	return null;
    }

    public Pessoa buscarPorCpf(String cpf) {
    	Pessoa pessoa = pessoaRepository.findByCpf(cpf);    	
    	return pessoa;    	
    }
    
    public Optional<Pessoa> buscarPorId(Long id) {
    	Optional<Pessoa> pessoa = pessoaRepository.findById(id);    	
    	return pessoa;    	
    }
    
    public List<Pessoa> buscarTodos() {
    	List<Pessoa> pessoas = pessoaRepository.findAll();    	
    	return pessoas;    	
    }
    
    public Boolean excluir(Long id) {
    	Boolean sucesso = false; 
    	pessoaRepository.deleteById(id);
    	sucesso = true;
    	return sucesso;    	
    }
}
