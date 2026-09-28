package com.autobots.autobots.model.updater;

import java.util.List;
import org.springframework.stereotype.Component;
import com.autobots.autobots.model.Documento;

@Component
public class DocumentoAtualizador {

	private final StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Documento documento, Documento atualizacao) {
		if (atualizacao != null && documento != null) {
			if (!verificador.verificar(atualizacao.getTipo())) {
				documento.setTipo(atualizacao.getTipo());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				documento.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Documento> documentos, List<Documento> atualizacoes) {
		if (atualizacoes != null && documentos != null) {
			for (Documento atualizacao : atualizacoes) {
				if (atualizacao.getId() != null) {
					for (Documento documento : documentos) {
						if (atualizacao.getId().equals(documento.getId())) {
							atualizar(documento, atualizacao);
						}
					}
				} else {
					documentos.add(atualizacao);
				}
			}
		}
	}
}
