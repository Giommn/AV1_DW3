package com.autobots.autobots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.autobots.exception.ResourceNotFoundException;
import com.autobots.autobots.model.Cliente;
import com.autobots.autobots.model.updater.ClienteAtualizador;
import com.autobots.autobots.model.updater.ClienteSelecionador;
import com.autobots.autobots.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteAtualizador clienteAtualizador;

	@Autowired
	private ClienteSelecionador clienteSelecionador;

	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}

	public Cliente obterPorId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + id));
	}

	public Cliente obterDaLista(Long id) {
		List<Cliente> clientes = clienteRepository.findAll();
		Cliente cliente = clienteSelecionador.selecionar(clientes, id);
		if (cliente == null) {
			throw new ResourceNotFoundException("Cliente não encontrado na lista com o id: " + id);
		}
		return cliente;
	}

	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Long id, Cliente atualizacao) {
		Cliente clienteExistente = obterPorId(id);
		clienteAtualizador.atualizar(clienteExistente, atualizacao);
		return clienteRepository.save(clienteExistente);
	}

	public void deletar(Long id) {
		Cliente clienteExistente = obterPorId(id);
		clienteRepository.delete(clienteExistente);
	}
}
