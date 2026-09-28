package com.autobots.autobots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.autobots.exception.ResourceNotFoundException;
import com.autobots.autobots.model.Documento;
import com.autobots.autobots.model.updater.DocumentoAtualizador;
import com.autobots.autobots.repository.DocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private DocumentoAtualizador documentoAtualizador;

	public List<Documento> obterTodos() {
		return documentoRepository.findAll();
	}

	public Documento obterPorId(Long id) {
		return documentoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Documento não encontrado com o id: " + id));
	}

	public Documento cadastrar(Documento documento) {
		return documentoRepository.save(documento);
	}

	public Documento atualizar(Long id, Documento atualizacao) {
		Documento documentoExistente = obterPorId(id);
		documentoAtualizador.atualizar(documentoExistente, atualizacao);
		return documentoRepository.save(documentoExistente);
	}

	public void deletar(Long id) {
		Documento documentoExistente = obterPorId(id);
		documentoRepository.delete(documentoExistente);
	}
}
