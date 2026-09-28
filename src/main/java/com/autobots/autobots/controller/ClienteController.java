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

import com.autobots.autobots.model.Cliente;
import com.autobots.autobots.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> obterTodos() {
		List<Cliente> clientes = clienteService.obterTodos();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterPorId(@PathVariable Long id) {
		Cliente cliente = clienteService.obterPorId(id);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
		Cliente novoCliente = clienteService.cadastrar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente atualizacao) {
		Cliente clienteAtualizado = clienteService.atualizar(id, atualizacao);
		return ResponseEntity.ok(clienteAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
