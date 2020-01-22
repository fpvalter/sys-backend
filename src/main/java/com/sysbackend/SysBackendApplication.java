package com.sysbackend;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sysbackend.domain.Categoria;
import com.sysbackend.domain.Cidade;
import com.sysbackend.domain.Cliente;
import com.sysbackend.domain.Endereco;
import com.sysbackend.domain.Estado;
import com.sysbackend.domain.Pagamento;
import com.sysbackend.domain.PagamentoBoleto;
import com.sysbackend.domain.PagamentoCartao;
import com.sysbackend.domain.Pedido;
import com.sysbackend.domain.PedidoItem;
import com.sysbackend.domain.Produto;
import com.sysbackend.domain.enums.EstadoPagamento;
import com.sysbackend.domain.enums.TipoCliente;
import com.sysbackend.repositories.CategoriaRepository;
import com.sysbackend.repositories.CidadeRepository;
import com.sysbackend.repositories.ClienteRepository;
import com.sysbackend.repositories.EnderecoRepository;
import com.sysbackend.repositories.EstadoRepository;
import com.sysbackend.repositories.PagamentoRepository;
import com.sysbackend.repositories.PedidoItemRepository;
import com.sysbackend.repositories.PedidoRepository;
import com.sysbackend.repositories.ProdutoRepository;

@SpringBootApplication
public class SysBackendApplication implements CommandLineRunner{

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
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SysBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 3000.00);
		Produto prod2 = new Produto(null, "Impressora", 1000.00);
		Produto prod3 = new Produto(null, "Mouse", 50.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod1.getCategorias().addAll(Arrays.asList(cat1));
								
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "1234567890", TipoCliente.PESSOAFISICA);
		cli1.getTelefonesSet().addAll(Arrays.asList("123456", "321564"));
		Endereco end1 = new Endereco(null, "Rua 1", "1", "compl", "bairro", "7894561", c1, cli1);
		Endereco end2 = new Endereco(null, "Rua 2", "2", "compl", "bairro", "94561", c2, cli1);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, df.parse("30/12/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, df.parse("10/10/2017 19:32"), cli1, end2);
		
		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, df.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		PedidoItem pi1 = new PedidoItem(ped1, prod1, 0.00, 1.00, 2000.00);
		PedidoItem pi2 = new PedidoItem(ped1, prod3, 0.00, 2.00, 80.00);
		PedidoItem pi3 = new PedidoItem(ped2, prod2, 100.00, 1.00, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(pi1, pi2));
		ped2.getItens().addAll(Arrays.asList(pi3));
		
		prod1.getItens().addAll(Arrays.asList(pi1));
		prod2.getItens().addAll(Arrays.asList(pi3));
		prod3.getItens().addAll(Arrays.asList(pi2));		
		
		pedidoItemRepository.saveAll(Arrays.asList(pi1, pi2, pi3));
	}

}
