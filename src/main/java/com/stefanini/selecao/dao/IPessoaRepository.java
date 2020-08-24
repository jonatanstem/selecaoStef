package com.stefanini.selecao.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanini.selecao.model.Pessoa;

public interface IPessoaRepository extends JpaRepository<Pessoa, Long>{
	public Pessoa findByCpf(String pCpf);
}
