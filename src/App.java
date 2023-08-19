import com.projeto.poo.Candidato;
import com.projeto.poo.CandidatoService;
import com.projeto.poo.Empresa;
import com.projeto.poo.Vaga;
import frontend.TelaInicial;

import javax.swing.*;
import java.util.LinkedList;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CandidatoService().carregaCandidatos();
				new TelaInicial().setVisible(true);
			}
		});

	}

}
