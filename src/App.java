import com.projeto.poo.*;
import frontend.TelaInicial;

import javax.swing.*;
import java.util.LinkedList;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CandidatoService().carregaCandidatos();
				new EmpresaService().carregaEmpresas();
				new CandidatoService().carregaCurtidas();
				new TelaInicial().setVisible(true);
			}
		});

	}

}
