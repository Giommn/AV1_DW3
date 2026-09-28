package com.autobots.autobots.model.updater;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.autobots.autobots.model.Cliente;
import com.autobots.autobots.model.Endereco;

public class ClienteAtualizadorTest {

	private ClienteAtualizador clienteAtualizador;

	@BeforeEach
	public void setUp() {
		clienteAtualizador = new ClienteAtualizador();
	}

	@Test
	public void testAtualizarNomeEEndereco() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Carlos Silva");
		cliente.setNomeSocial("Carlos");

		Endereco endereco = new Endereco();
		endereco.setEstado("SP");
		endereco.setCidade("São Paulo");
		cliente.setEndereco(endereco);

		Cliente atualizacao = new Cliente();
		atualizacao.setNome("Carlos Eduardo Silva");
		
		Endereco enderecoAtualizacao = new Endereco();
		enderecoAtualizacao.setCidade("Campinas");
		atualizacao.setEndereco(enderecoAtualizacao);

		clienteAtualizador.atualizar(cliente, atualizacao);

		assertEquals("Carlos Eduardo Silva", cliente.getNome());
		assertEquals("Carlos", cliente.getNomeSocial()); // Mantém o nome social anterior pois atualizacao veio nulo
		assertNotNull(cliente.getEndereco());
		assertEquals("SP", cliente.getEndereco().getEstado()); // Mantém estado SP
		assertEquals("Campinas", cliente.getEndereco().getCidade()); // Atualizou cidade para Campinas
	}
}
