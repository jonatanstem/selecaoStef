package com.stefanini.selecao.business;

import java.util.List;
import java.util.Optional;

import com.stefanini.selecao.model.Pessoa;


public interface IPessoaService {
    
    public Pessoa salvar(Pessoa pessoa);

    public Pessoa buscarPorCpf(String cpf);
    
    public Optional<Pessoa> buscarPorId(Long id);
    
    public List<Pessoa> buscarTodos();
    
    public Boolean excluir(Long id);
    
 
}
