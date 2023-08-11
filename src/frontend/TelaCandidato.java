package frontend;

import com.projeto.poo.Candidato;
import com.projeto.poo.Empresa;
import com.projeto.poo.EmpresaService;
import com.projeto.poo.Key;
import com.projeto.poo.Vaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Map;

public class TelaCandidato extends JFrame {

    private Candidato candidato;
    private EmpresaService empresaService;
    private JPanel vagaPanel;

    public TelaCandidato(Candidato candidato, EmpresaService empresaService) {
        this.candidato = candidato;
        this.empresaService = empresaService;

        setTitle("Linketinder - Candidato");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/colab.png");
        setIconImage(icon.getImage());

        vagaPanel = new JPanel();
        vagaPanel.setLayout(new BoxLayout(vagaPanel, BoxLayout.Y_AXIS));

    // Usando BorderLayout para adicionar o título no centro
        JLabel titleLabel = new JLabel("Olá, " + candidato.getNome());
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        titleLabel.setForeground(new Color(243, 131, 6));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Adicionando o título ao centro do painel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);

    // Criar um novo painel para as vagas
        JPanel vagasPanel = new JPanel();
        vagasPanel.setLayout(new BoxLayout(vagasPanel, BoxLayout.Y_AXIS));

    // Adicionar o titlePanel e vagasPanel ao vagaPanel
        vagaPanel.add(titlePanel);
        vagaPanel.add(vagasPanel);

        refreshVagas();

        add(vagaPanel);

    }

    private void refreshVagas() {
        vagaPanel.remove(1);

        LinkedList<Vaga> vagas = new LinkedList<>();
        for (Map.Entry<Key, Empresa> entry : empresaService.empresas.entrySet()) {
            vagas.addAll(entry.getValue().listaDeVagas());
        }

        for (Vaga vaga : vagas) {
            JPanel vagaRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel vagaLabel = new JLabel(vaga.getNome() + " - " + vaga.getEmpresa());
            JButton curtirButton = new JButton("Curtir");

            curtirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candidato.curtir(vaga.getEmpresa());
                    refreshVagas();
                }
            });

            vagaRow.add(vagaLabel);
            vagaRow.add(curtirButton);

            vagaPanel.add(vagaRow);
        }

        revalidate();
        repaint();
    }
}