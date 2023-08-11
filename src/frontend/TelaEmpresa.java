package frontend;

import com.projeto.poo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TelaEmpresa extends JFrame {

    private Empresa empresa;
    private EmpresaService empresaService;

    JPanel contentPanel = new JPanel();

    public TelaEmpresa(Empresa empresa, EmpresaService empresaService) {
        this.empresa = empresa;
        this.empresaService = empresaService;

        setTitle("Linketinder - Empresa");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/colab.png");
        setIconImage(icon.getImage());

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Criando o painel com o título
        JPanel titlePanel = createTitlePanel();

        // Criando o painel com o botão "Cadastre uma vaga"
        JPanel cadastrarVagaPanel = createCadastrarVagaPanel(this);

        // Criando o painel com a lista de vagas
        JScrollPane vagasPanel = createVagasPanel();

        // Criando o painel com a lista de candidatos
        JPanel candidatosPanel = createCandidatosPanel();

        // Adicionando os painéis ao contentPanel
        contentPanel.add(titlePanel);
        contentPanel.add(cadastrarVagaPanel);
        contentPanel.add(vagasPanel);
        contentPanel.add(candidatosPanel);

        add(contentPanel);
    }

    private JPanel createTitlePanel() {
        JLabel titleLabel = new JLabel("Olá, " + empresa.getNome() + ". Aqui você poderá buscar o candidato perfeito para suas vagas!");
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBackground(Color.GRAY);

        return titlePanel;
    }

    private JPanel createCadastrarVagaPanel(TelaEmpresa telaEmpresa) {
        JButton cadastrarVagaButton = new JButton("Cadastre uma vaga");

        JPanel cadastrarVagaPanel = new JPanel();
        cadastrarVagaPanel.add(cadastrarVagaButton);
        cadastrarVagaPanel.setBackground(Color.YELLOW);

        cadastrarVagaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroVaga telaCadastroVaga = new TelaCadastroVaga(empresa,telaEmpresa);
                telaCadastroVaga.setVisible(true);
            }
        });

        return cadastrarVagaPanel;
    }

    public void atualizarListaVagas() {
        JScrollPane vagasScrollPane = createVagasPanel();
        vagasScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Encontre o componente que contém as vagas e substitua-o pelo novo JScrollPane
        for (Component component : contentPanel.getComponents()) {
            if (component instanceof JScrollPane && component.getName() != null && component.getName().equals("vagasScrollPane")) {
                contentPanel.remove(component);
                contentPanel.add(vagasScrollPane);
                contentPanel.revalidate();
                contentPanel.repaint();
                break;
            }
        }
    }

    private JScrollPane createVagasPanel() {
        JPanel vagasPanel = new JPanel();
        vagasPanel.setLayout(new BoxLayout(vagasPanel, BoxLayout.Y_AXIS));

        for (Vaga vaga : empresa.listaDeVagas()) {
            JPanel vagaRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel vagaLabel = new JLabel(vaga.getNome() + " - " + vaga.getTipo());
            vagaLabel.setForeground(vaga.isDisponivel() ? Color.BLUE : Color.RED);
            vagaRow.add(vagaLabel);
            vagasPanel.add(vagaRow);
        }

        JScrollPane vagasScrollPane = new JScrollPane(vagasPanel);
        vagasScrollPane.setBorder(BorderFactory.createEmptyBorder());
        vagasScrollPane.setName("vagasScrollPane"); // Nome para identificação posterior

        return vagasScrollPane;
    }

    private JPanel createCandidatosPanel() {
        JPanel candidatosPanel = new JPanel();
        candidatosPanel.setLayout(new BoxLayout(candidatosPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<Key, Candidato> entry : CandidatoService.candidatos.entrySet()) {
            Candidato candidato = entry.getValue();
            JPanel candidatoRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel candidatoLabel = new JLabel("Nome: " + candidato.getNome() + " | Idade: " + candidato.getIdade()
                    + " | País: " + candidato.getPais() + " | Estado: " + candidato.getEstado()
                    + " | Descrição: " + candidato.getDescricao() + " | Formação: " + candidato.getCurriculo().getFormacao()
                    + " | Cursos Complementares: " + candidato.getCurriculo().getCursosComplementares()
                    + " | Pretensão Salarial: " + candidato.getCurriculo().getPretensaoSalarial());

            JButton curtirButton = new JButton("Curtir");

            curtirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    empresa.curtir(candidato);
                }
            });

            candidatoRow.add(candidatoLabel);
            candidatoRow.add(curtirButton);

            candidatosPanel.add(candidatoRow);
            candidatosPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        return candidatosPanel;
    }
}