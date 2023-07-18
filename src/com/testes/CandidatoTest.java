package com.testes;

import com.projeto.poo.Candidato;
import com.projeto.poo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidatoTest {
	
	@BeforeEach
    void setUp() {
        Candidato.zeraQuantidadeCandidatos();
        Usuario.zeraQuantidadeUsuarios();
    }
	
	Candidato user1 = new Candidato("Jorge","jorge@jorge.com","Brasil",
			"Goiás","747474-74","Descrição",19,"751526985-52");
	
	Candidato user2 = new Candidato("Jorge","jorge@jorge.com","Brasil",
			"Goiás","747474-74","Descrição",19,"751526985-52");
	
	@Test
	void verificaQuantidadeDeUsuarios() {
		assertEquals(0, Usuario.quantidadeUsuarios());
	}
	
	@Test
	void verificaIdDoCandidato() {
			assertEquals(1, user1.getId());
	}
	
	@Test
	void verificaSeObjetoVagasRequeridasFoiInstanciado() {
		 assertNotNull(user1.getVagas());
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
		user1.adicionaCurtida(user2.getCpf(), user2);
		user2.adicionaCurtida(user1.getCpf(), user1);
		assertFalse(user1.verificarMatch(user2));
	}
}
