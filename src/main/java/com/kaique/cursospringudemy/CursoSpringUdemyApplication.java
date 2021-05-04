package com.kaique.cursospringudemy;

import java.text.SimpleDateFormat;
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
import com.kaique.cursospringudemy.domain.Pagamento;
import com.kaique.cursospringudemy.domain.PagamentoBoleto;
import com.kaique.cursospringudemy.domain.PagamentoCartao;
import com.kaique.cursospringudemy.domain.Pedido;
import com.kaique.cursospringudemy.domain.Produto;
import com.kaique.cursospringudemy.domain.enums.EstadoPagamento;
import com.kaique.cursospringudemy.domain.enums.TipoCliente;
import com.kaique.cursospringudemy.repositories.CategoriaRepository;
import com.kaique.cursospringudemy.repositories.CidadeRepository;
import com.kaique.cursospringudemy.repositories.ClienteRepository;
import com.kaique.cursospringudemy.repositories.EnderecoRepository;
import com.kaique.cursospringudemy.repositories.EstadoRepository;
import com.kaique.cursospringudemy.repositories.PagamentoRepository;
import com.kaique.cursospringudemy.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 =  new Pedido(null,sdf.parse("30/09/2017 10:32"), cli1,end1); 
		Pedido ped2 =  new Pedido(null,sdf.parse("10/10/2017 19:35"), cli1,end2);
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	}

	
}
