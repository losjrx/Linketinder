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
        JScrollPane vagasPanel = createVagasPanel(this);

        // Criando o painel com a lista de candidatos
        JPanel candidatosPanel = createCandidatosPanel();

        //Painel para o botão Excluit conta
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Botão para excluir conta
        JButton excluirContaButton = new JButton("Excluir conta");
        excluirContaButton.setBackground(new Color(243, 131, 6));
        excluirContaButton.setForeground(new Color(255, 255, 255, 255));
        excluirContaButton.setBorder(new RoundedBorder(5, Color.WHITE));
        excluirContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmResult = JOptionPane.showConfirmDialog(
                        TelaEmpresa.this,
                        "Tem certeza que deseja excluir sua conta?",
                        "Excluir Conta",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmResult == JOptionPane.YES_OPTION) {
                    EmpresaService.deletaEmpresa(empresa.getUsername(), empresa.getCnpj());
                    dispose(); // Fechar a tela após exclusão
                }
            }
        });

        buttonPanel.add(excluirContaButton);

        // Adicionando os painéis ao contentPanel
        contentPanel.add(buttonPanel);
        contentPanel.add(titlePanel);
        contentPanel.add(cadastrarVagaPanel);
        contentPanel.add(vagasPanel);
        contentPanel.add(candidatosPanel);

        add(contentPanel);

        contentPanel.add(Box.createVerticalStrut(10)); // Espaçamento
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
        cadastrarVagaButton.setBackground(new Color(243, 131, 6));
        cadastrarVagaButton.setForeground(new Color(255, 255, 255, 255));
        cadastrarVagaButton.setBorder(new RoundedBorder(5, Color.WHITE));

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

    public void atualizarListaVagas(TelaEmpresa telaEmpresa) {
        JScrollPane vagasScrollPane = createVagasPanel(telaEmpresa);
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

    private JScrollPane createVagasPanel(TelaEmpresa telaEmpresa) {
        JPanel vagasPanel = new JPanel();
        vagasPanel.setLayout(new BoxLayout(vagasPanel, BoxLayout.Y_AXIS));

        for (Vaga vaga : empresa.listaDeVagas()) {
            JPanel vagaRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel vagaLabel = new JLabel(vaga.getNome() + " - " + vaga.getTipo());
            vagaLabel.setForeground(vaga.isDisponivel() ? Color.BLUE : Color.RED);

            JButton editarButton = new JButton("Editar");
            editarButton.setBackground(new Color(243, 131, 6));
            editarButton.setForeground(new Color(255, 255, 255, 255));
            editarButton.setBorder(new RoundedBorder(5, Color.WHITE));
            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new TelaEditarVaga(vaga,telaEmpresa).setVisible(true);
                }
            });

            JButton excluirButton = new JButton("Excluir");
            excluirButton.setBackground(new Color(243, 131, 6));
            excluirButton.setForeground(new Color(255, 255, 255, 255));
            excluirButton.setBorder(new RoundedBorder(5, Color.WHITE));
            excluirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirmResult = JOptionPane.showConfirmDialog(
                            TelaEmpresa.this,
                            "Tem certeza que deseja excluir esta vaga?",
                            "Excluir Vaga",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmResult == JOptionPane.YES_OPTION) {
                        EmpresaService.excluiVaga(vaga);
                        empresa.excluirVaga(vaga);
                        atualizarListaVagas(telaEmpresa); // Atualiza a lista de vagas após a exclusão
                    }
                }
            });

            vagaRow.add(vagaLabel);
            vagaRow.add(editarButton);
            vagaRow.add(excluirButton);
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
            curtirButton.setBackground(new Color(243, 131, 6));
            curtirButton.setForeground(new Color(255, 255, 255, 255));
            curtirButton.setBorder(new RoundedBorder(5, Color.WHITE));

            curtirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    empresa.curtir(candidato);

                    EmpresaService.gravaCurtida(empresa, candidato);

                    if(empresa.verificarMatch(candidato)){
                        // Carregar a imagem que deseja exibir
                        ImageIcon icon = new ImageIcon("src/img/colab.png");

                        // Criar um painel para conter a imagem e o texto
                        JPanel panel = new JPanel();
                        panel.setLayout(new BorderLayout());

                        // Adicionar a imagem ao painel
                        JLabel imageLabel = new JLabel(icon);
                        panel.add(imageLabel, BorderLayout.CENTER);

                        // Criar uma label com o texto "It's a match!"
                        JLabel textLabel = new JLabel("It's a match! Entre em contato com o candidato: "+candidato.getEmail());
                        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
                        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel.add(textLabel, BorderLayout.SOUTH);

                        // Exibir o JOptionPane personalizado
                        JOptionPane.showMessageDialog(
                                null,
                                panel,
                                "It's a match!",
                                JOptionPane.PLAIN_MESSAGE
                        );
                    } else {
                        // Carregar a imagem que deseja exibir
                        ImageIcon icon = new ImageIcon("src/img/like.png");

                        // Definir o tamanho desejado para a imagem
                        int width = 100; // Largura desejada
                        int height = 100; // Altura desejada

                        // Redimensionar a imagem para o tamanho desejado
                        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(resizedImage);

                        // Criar um painel para conter a imagem e o texto
                        JPanel panel = new JPanel();
                        panel.setLayout(new BorderLayout());

                        // Adicionar a imagem ao painel
                        JLabel imageLabel = new JLabel(icon);
                        panel.add(imageLabel, BorderLayout.CENTER);

                        // Criar uma label com o texto "Curtida registrada!"
                        JLabel textLabel = new JLabel("Curtida registrada!");
                        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
                        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel.add(textLabel, BorderLayout.SOUTH);

                        // Exibir o JOptionPane personalizado
                        JOptionPane.showMessageDialog(
                                null,
                                panel,
                                "Like!",
                                JOptionPane.PLAIN_MESSAGE
                        );
                    }
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