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

import com.autobots.autobots.model.Endereco;
import com.autobots.autobots.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> obterTodos() {
		List<Endereco> enderecos = enderecoService.obterTodos();
		return ResponseEntity.ok(enderecos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> obterPorId(@PathVariable Long id) {
		Endereco endereco = enderecoService.obterPorId(id);
		return ResponseEntity.ok(endereco);
	}

	@PostMapping
	public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco) {
		Endereco novoEndereco = enderecoService.cadastrar(endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco atualizacao) {
		Endereco enderecoAtualizado = enderecoService.atualizar(id, atualizacao);
		return ResponseEntity.ok(enderecoAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		enderecoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
