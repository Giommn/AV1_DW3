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

import com.autobots.autobots.model.Telefone;
import com.autobots.autobots.service.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	private TelefoneService telefoneService;

	@GetMapping
	public ResponseEntity<List<Telefone>> obterTodos() {
		List<Telefone> telefones = telefoneService.obterTodos();
		return ResponseEntity.ok(telefones);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Telefone> obterPorId(@PathVariable Long id) {
		Telefone telefone = telefoneService.obterPorId(id);
		return ResponseEntity.ok(telefone);
	}

	@PostMapping
	public ResponseEntity<Telefone> cadastrar(@RequestBody Telefone telefone) {
		Telefone novoTelefone = telefoneService.cadastrar(telefone);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoTelefone);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Telefone> atualizar(@PathVariable Long id, @RequestBody Telefone atualizacao) {
		Telefone telefoneAtualizado = telefoneService.atualizar(id, atualizacao);
		return ResponseEntity.ok(telefoneAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		telefoneService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
