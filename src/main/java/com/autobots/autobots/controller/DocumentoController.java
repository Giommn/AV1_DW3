package com.autobots.autobots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.autobots.model.Documento;
import com.autobots.autobots.service.DocumentoService;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

	@Autowired
	private DocumentoService documentoService;

	@GetMapping
	public ResponseEntity<List<Documento>> obterTodos() {
		List<Documento> documentos = documentoService.obterTodos();
		return ResponseEntity.ok(documentos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> obterPorId(@PathVariable Long id) {
		Documento documento = documentoService.obterPorId(id);
		return ResponseEntity.ok(documento);
	}

	@PostMapping
	public ResponseEntity<Documento> cadastrar(@RequestBody Documento documento) {
		Documento novoDocumento = documentoService.cadastrar(documento);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoDocumento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Documento> atualizar(@PathVariable Long id, @RequestBody Documento atualizacao) {
		Documento documentoAtualizado = documentoService.atualizar(id, atualizacao);
		return ResponseEntity.ok(documentoAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		documentoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
