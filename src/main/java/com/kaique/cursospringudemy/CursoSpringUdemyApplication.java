package com.kaique.cursospringudemy;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kaique.cursospringudemy.domain.Categoria;
import com.kaique.cursospringudemy.domain.Cidade;
import com.kaique.cursospringudemy.domain.Cliente;
import com.kaique.cursospringudemy.domain.Endereco;
import com.kaique.cursospringudemy.domain.Estado;
import com.kaique.cursospringudemy.domain.Produto;
import com.kaique.cursospringudemy.domain.enums.TipoCliente;
import com.kaique.cursospringudemy.repositories.CategoriaRepository;
import com.kaique.cursospringudemy.repositories.CidadeRepository;
import com.kaique.cursospringudemy.repositories.ClienteRepository;
import com.kaique.cursospringudemy.repositories.EnderecoRepository;
import com.kaique.cursospringudemy.repositories.EstadoRepository;
import com.kaique.cursospringudemy.repositories.ProdutoRepository;
// implementando commandLineRunner apenas para instanciar objetos e facilitar teste
//em um ambiente de producao não é necessário
@SpringBootApplication
public class CursoSpringUdemyApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringUdemyApplication.class, args);
	}
	
	//método criado apenas para facilitar teste
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "computados", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "3665658251",
									TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("2131231232132", "1123213123"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "270", "apt 303", "Jardim", "066585485", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "066785485", cli1, c2);
	
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
	
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

	
}
