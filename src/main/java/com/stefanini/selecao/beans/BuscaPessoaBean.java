package com.stefanini.selecao.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stefanini.selecao.business.IPessoaService;
import com.stefanini.selecao.model.Pessoa;

@Component
@Scope("view")
public class BuscaPessoaBean { 
	
	private Collection<Pessoa> pessoas; 
	
    @Autowired
    private IPessoaService pessoaService;    

    @PostConstruct
    public void buscar() {
    	pessoas = pessoaService.buscarTodos();
    }

	public Collection<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(ArrayList<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
