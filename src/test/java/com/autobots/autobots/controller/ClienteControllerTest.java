package com.autobots.autobots.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCadastrarEBuscarCliente() throws Exception {
		String jsonCliente = """
				{
					"nome": "João Souza",
					"nomeSocial": "João",
					"endereco": {
						"estado": "RJ",
						"cidade": "Niterói",
						"bairro": "Icaraí",
						"rua": "Rua Ator Paulo Gustavo",
						"numero": "100",
						"codigoPostal": "24230-000"
					},
					"telefones": [
						{
							"ddd": "21",
							"numero": "999998888"
						}
					],
					"documentos": [
						{
							"tipo": "CPF",
							"numero": "12345678900"
						}
					]
				}
				""";

		mockMvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCliente))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.nome").value("João Souza"))
				.andExpect(jsonPath("$.endereco.cidade").value("Niterói"))
				.andExpect(jsonPath("$.telefones[0].numero").value("999998888"))
				.andExpect(jsonPath("$.documentos[0].tipo").value("CPF"));

		mockMvc.perform(get("/clientes"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nome").value("João Souza"));
	}
}
