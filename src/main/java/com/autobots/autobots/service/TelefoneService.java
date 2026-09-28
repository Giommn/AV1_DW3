package com.autobots.autobots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.autobots.exception.ResourceNotFoundException;
import com.autobots.autobots.model.Telefone;
import com.autobots.autobots.model.updater.TelefoneAtualizador;
import com.autobots.autobots.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private TelefoneAtualizador telefoneAtualizador;

	public List<Telefone> obterTodos() {
		return telefoneRepository.findAll();
	}

	public Telefone obterPorId(Long id) {
		return telefoneRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado com o id: " + id));
	}

	public Telefone cadastrar(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}

	public Telefone atualizar(Long id, Telefone atualizacao) {
		Telefone telefoneExistente = obterPorId(id);
		telefoneAtualizador.atualizar(telefoneExistente, atualizacao);
		return telefoneRepository.save(telefoneExistente);
	}

	public void deletar(Long id) {
		Telefone telefoneExistente = obterPorId(id);
		telefoneRepository.delete(telefoneExistente);
	}
}
