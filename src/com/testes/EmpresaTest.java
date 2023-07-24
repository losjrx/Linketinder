package com.testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projeto.poo.Empresa;
import com.projeto.poo.Usuario;

class EmpresaTest {

	@BeforeEach
    void setUp() {
		Empresa.zeraQuantidadeEmpresas();
        Usuario.zeraQuantidadeUsuarios();
    }

	Empresa user1 = new Empresa("Amazon","amazon@amazon.com","EUA",
			"Washington","98170","Descrição", "amazon", "12345","0000000000-01");

	Empresa user2 = new Empresa("Amazon","amazon@amazon.com","EUA",
			"Washington","98170","Descrição", "amazon", "12345","0000000000-01");
	
	@Test
	void verificaQuantidadeDeUsuarios() {
		assertEquals(0, Usuario.quantidadeUsuarios());
	}
	
	@Test
	void verificaIdDoCandidato() {
			assertEquals(1, user1.getId());
	}
	
	@Test
	void verificaSeArrayListVagasFoiInstanciado() {
		 assertNotNull(user1.listaDeVagas());
	}
	
	@Test
	void verificaSeMapaFoiInstanciado() {
		 assertNotNull(user1.getCurtidas());
	}
	
	@Test
	void verificaSeListaFoiInstanciada() {
		 assertNotNull(user1.getMatches());
	}
	
	@Test
	void verificaMatch() {
		 assertFalse(user1.verificarMatch(user1));
	}

	@Test
	void verificarSeDeuMatch() {
		user1.adicionaCurtida(user2.getCnpj(), user2);
		user2.adicionaCurtida(user1.getCnpj(), user1);
		assertFalse(user1.verificarMatch(user2));
	}
}
