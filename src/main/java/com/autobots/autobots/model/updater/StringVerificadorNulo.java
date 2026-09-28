package com.autobots.autobots.model.updater;

import org.springframework.stereotype.Component;

@Component
public class StringVerificadorNulo {

	public boolean verificar(String dado) {
		return dado == null || dado.trim().isEmpty();
	}
}
