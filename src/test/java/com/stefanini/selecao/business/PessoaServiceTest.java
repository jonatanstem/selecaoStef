package com.stefanini.selecao.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stefanini.selecao.business.impl.PessoaService;
import com.stefanini.selecao.model.Pessoa;

@SpringBootTest
public class PessoaServiceTest {

	@Autowired
	private PessoaService pessoaService;

	private String cpf = "11133344478";
	private String cpfErro = "33333333333";
	private Long id = Long.valueOf("1");
	private Long idErro = Long.valueOf("-1");
	

	@Test
	public void buscarPorCpfTestSucess() {

		// Act
		Pessoa retorno = pessoaService.buscarPorCpf(cpf);
		String actual = retorno.getCpf();

		// Assert
		String expected = cpf;

		assertEquals(expected, actual);
	}

	@Test
	public void buscarPorCpfTestErro() {

		// Act
		Pessoa retorno = pessoaService.buscarPorCpf(cpfErro);

		// Assert
		assertNull(retorno);

	}

	@Test
	public void buscarPorIdTestSucess() {

		// Act
		Optional<Pessoa> retorno = pessoaService.buscarPorId(id);
		Long actual = retorno.get().getId();

		// Assert
		Long expected = id;

		assertEquals(expected, actual);
	}

	@Test
	public void buscarPorIdTestErro() {

		// Act
		Optional<Pessoa> retorno = pessoaService.buscarPorId(idErro);

		// Assert
		assertFalse(retorno.isPresent());

	}

	// @Test
	public void excluirTestSucess() {
		// Arrange
		Pessoa pessoa = pessoaService.buscarPorCpf(cpf);

		if (pessoa != null) {
			// Act
			boolean retorno = pessoaService.excluir(pessoa.getId());

			// Assert
			assertTrue(retorno);

			pessoaService.salvar(pessoa);
		}

	}

	@Test
	public void salvarTestSucess() throws ParseException {
		// Arrange
		String nome = "Joao";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = sdf.parse("22/10/1994");
		String cpf = "12312312312";

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setCpf(cpf);

		// Act
		Pessoa retorno = pessoaService.salvar(pessoa);
		String actual = retorno.getCpf();

		// Assert
		String expected = cpf;

		assertEquals(expected, actual);
		
		pessoaService.excluir(retorno.getId());

	}
	
	@Test
	public void salvarTestCpfCadastrado() throws ParseException {
		// Arrange
		String nome = "Joao";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = sdf.parse("22/10/1994");
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setCpf(cpf);

		// Act
		Pessoa retorno = pessoaService.salvar(pessoa);

		// Assert
		assertNull(retorno);
		

	}
	
	@Test
	public void salvarTestCpfNull() throws ParseException {
		// Arrange
		String nome = "Joao";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = sdf.parse("22/10/1994");

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setDataNascimento(dataNascimento);
		
		// Act
		Pessoa retorno = pessoaService.salvar(pessoa);

		// Assert
		assertNull(retorno);
		

	}
	
	@Test
	public void salvarTestNomeNull() throws ParseException {
		// Arrange
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = sdf.parse("22/10/1994");
		String cpf = "12312312312";
		
		Pessoa pessoa = new Pessoa();
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setCpf(cpf);
		// Act
		Pessoa retorno = pessoaService.salvar(pessoa);

		// Assert
		assertNull(retorno);
		

	}	
	
	@Test
	public void salvarTestDataNascimentoNull() {
		// Arrange
		String nome = "Joao";
		String cpf = "12312312312";
		
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpf);
		pessoa.setNome(nome);
		
		// Act
		Pessoa retorno = pessoaService.salvar(pessoa);

		// Assert
		assertNull(retorno);
		

	}

}
