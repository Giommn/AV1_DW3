package com.autobots.autobots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.autobots.exception.ResourceNotFoundException;
import com.autobots.autobots.model.Endereco;
import com.autobots.autobots.model.updater.EnderecoAtualizador;
import com.autobots.autobots.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EnderecoAtualizador enderecoAtualizador;

	public List<Endereco> obterTodos() {
		return enderecoRepository.findAll();
	}

	public Endereco obterPorId(Long id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com o id: " + id));
	}

	public Endereco cadastrar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Endereco atualizar(Long id, Endereco atualizacao) {
		Endereco enderecoExistente = obterPorId(id);
		enderecoAtualizador.atualizar(enderecoExistente, atualizacao);
		return enderecoRepository.save(enderecoExistente);
	}

	public void deletar(Long id) {
		Endereco enderecoExistente = obterPorId(id);
		enderecoRepository.delete(enderecoExistente);
	}
}
