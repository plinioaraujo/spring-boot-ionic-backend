package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "Rio de Janeiro");
		Estado e3 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Alem Paraiba", e1);
		Cidade c2 = new Cidade(null, "Duque de Caxias", e2);
		Cidade c3 = new Cidade(null, "Campinas", e3);
		Cidade c4 = new Cidade(null, "Macaé", e2);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2,c4));
		e3.getCidades().addAll(Arrays.asList(c3));
		
		estadoRepository.saveAll(Arrays.asList(e1,e2,e3));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Cliente cli1 = new Cliente(null, "WebTech", "webtech@gmail.com", "12345678987", TipoCliente.PESSOAJURIDICA);
		Cliente cli2 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678987", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("23658978","986457458"));
		cli2.getTelefones().addAll(Arrays.asList("555555545","223365856"));
		
		Endereco end1 = new Endereco(null, "Rua Mafra", "547", "apto 101", "Jardins", "54657845", cli2, c3);
		Endereco end2 = new Endereco(null, "Rua da Batata", "45", "apto 302", "Penha", "54657845", cli2, c2);
		
		Endereco end3 = new Endereco(null, "Rua da Empresa", "45", "apto 302", "Penha", "54657845", cli1, c4);
		
		cli2.getEnderecos().addAll(Arrays.asList(end1,end2));
		cli1.getEnderecos().addAll(Arrays.asList(end3));
		
		clienteRepository.saveAll(Arrays.asList(cli2, cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2, end3));
		
		
	}

}