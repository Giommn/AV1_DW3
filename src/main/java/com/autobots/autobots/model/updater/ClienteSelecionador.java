package com.autobots.autobots.model.updater;

import java.util.List;
import org.springframework.stereotype.Component;
import com.autobots.autobots.model.Cliente;

@Component
public class ClienteSelecionador {

	public Cliente selecionar(List<Cliente> clientes, long id) {
		if (clientes == null) {
			return null;
		}
		for (Cliente cliente : clientes) {
			if (cliente.getId() != null && cliente.getId() == id) {
				return cliente;
			}
		}
		return null;
	}
}
