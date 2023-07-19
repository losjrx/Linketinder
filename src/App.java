import com.projeto.poo.Candidato;
import com.projeto.poo.Empresa;
import com.projeto.poo.Vaga;
import frontend.Screen;
import frontend.Tela;

import javax.swing.*;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Tela().setVisible(true);
			}
		});

		Candidato candidato = new Candidato("Jorge","jorge@jorge.com","Brasil",
				"Goiás","747474-74","Descrição",19,"751526985-52");
		
		candidato.cadastraCurriculo("Bacharel em Sistemas de Informação", "Inglês", 5000.00);
		
		System.out.println(candidato);
		
		Empresa empresa = new Empresa("Amazon","amazon@amazon.com","EUA",
				"Washington","98170","Descrição","0000000000-01");
		
		empresa.cadastraVaga("Analista de suporte", "CLT", 3500.00, "Atendimento", empresa, true);
		
		System.out.println(empresa);
		
		for (Vaga vaga : empresa.listaDeVagas()) {
			System.out.println(vaga);
		}
		
		ArrayList<Vaga> vagas = empresa.listaDeVagas();
		
		candidato.curtir(vagas.get(0).getEmpresa());
		
		empresa.curtir(candidato);
	}

}
