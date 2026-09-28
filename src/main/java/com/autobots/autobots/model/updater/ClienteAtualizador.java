package com.autobots.autobots.model.updater;

import org.springframework.stereotype.Component;
import com.autobots.autobots.model.Cliente;

@Component
public class ClienteAtualizador {

	private final StringVerificadorNulo verificador = new StringVerificadorNulo();
	private final EnderecoAtualizador enderecoAtualizador = new EnderecoAtualizador();
	private final DocumentoAtualizador documentoAtualizador = new DocumentoAtualizador();
	private final TelefoneAtualizador telefoneAtualizador = new TelefoneAtualizador();

	private void atualizarDados(Cliente cliente, Cliente atualizacao) {
		if (atualizacao != null && cliente != null) {
			if (!verificador.verificar(atualizacao.getNome())) {
				cliente.setNome(atualizacao.getNome());
			}
			if (!verificador.verificar(atualizacao.getNomeSocial())) {
				cliente.setNomeSocial(atualizacao.getNomeSocial());
			}
			if (atualizacao.getDataNascimento() != null) {
				cliente.setDataNascimento(atualizacao.getDataNascimento());
			}
		}
	}

	public void atualizar(Cliente cliente, Cliente atualizacao) {
		if (atualizacao != null && cliente != null) {
			atualizarDados(cliente, atualizacao);

			if (atualizacao.getEndereco() != null) {
				if (cliente.getEndereco() == null) {
					cliente.setEndereco(atualizacao.getEndereco());
				} else {
					enderecoAtualizador.atualizar(cliente.getEndereco(), atualizacao.getEndereco());
				}
			}

			if (atualizacao.getDocumentos() != null && !atualizacao.getDocumentos().isEmpty()) {
				documentoAtualizador.atualizar(cliente.getDocumentos(), atualizacao.getDocumentos());
			}

			if (atualizacao.getTelefones() != null && !atualizacao.getTelefones().isEmpty()) {
				telefoneAtualizador.atualizar(cliente.getTelefones(), atualizacao.getTelefones());
			}
		}
	}
}
