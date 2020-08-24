package com.stefanini.selecao.beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stefanini.selecao.business.IPessoaService;
import com.stefanini.selecao.model.Pessoa;

@Controller
@Scope("view")
public class CadastroPessoaBean {

    private Pessoa pessoa = new Pessoa();

    @Autowired
    private IPessoaService pessoaService;

    public void salvar() {
    	if(pessoa.getId() == null)
    		pessoa.setDataCadastro(new Date());
    	pessoa.setDataUltimaAtualizacao(new Date());
    	Pessoa pessoaAux = pessoaService.salvar(pessoa);
    	if(pessoaAux == null) {
    		FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF já cadastrado", pessoa.getCpf()));
    	}else {
    		FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente salvo!"));
            pessoa = new Pessoa();
    	}
        
    }

    public void buscarPorCpf() {
    	pessoa = pessoaService.buscarPorCpf(pessoa.getCpf());
    }
    
    public void buscarPorId() {
    	pessoa = pessoaService.buscarPorId(pessoa.getId()).get();
    }
    
    public void excluir() {
    	
    	if(pessoaService.excluir(pessoa.getId())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente excluido!"));
            pessoa = new Pessoa();
    	}else {
    		FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir!", ""));
    	}
    	
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
