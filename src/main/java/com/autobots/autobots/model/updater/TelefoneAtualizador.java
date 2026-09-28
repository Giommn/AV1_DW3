package com.autobots.autobots.model.updater;

import java.util.List;
import org.springframework.stereotype.Component;
import com.autobots.autobots.model.Telefone;

@Component
public class TelefoneAtualizador {

	private final StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Telefone telefone, Telefone atualizacao) {
		if (atualizacao != null && telefone != null) {
			if (!verificador.verificar(atualizacao.getDdd())) {
				telefone.setDdd(atualizacao.getDdd());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				telefone.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Telefone> telefones, List<Telefone> atualizacoes) {
		if (atualizacoes != null && telefones != null) {
			for (Telefone atualizacao : atualizacoes) {
				if (atualizacao.getId() != null) {
					for (Telefone telefone : telefones) {
						if (atualizacao.getId().equals(telefone.getId())) {
							atualizar(telefone, atualizacao);
						}
					}
				} else {
					telefones.add(atualizacao);
				}
			}
		}
	}
}
