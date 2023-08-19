package frontend;

import com.projeto.poo.*;

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
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/colab.png");
        setIconImage(icon.getImage());

        vagaPanel = new JPanel();
        vagaPanel.setLayout(new BoxLayout(vagaPanel, BoxLayout.Y_AXIS));

        refreshVagas(this);

        add(vagaPanel);
    }

    JTextArea criarAreaCurriculo(){

        JTextArea curriculoTextArea = new JTextArea(
                "\t\t\tSeu currículo\n\n" +
                        "Formação: " + candidato.getCurriculo().getFormacao() + "\n" +
                        "Cursos Complementares: " + candidato.getCurriculo().getCursosComplementares() + "\n" +
                        "Pretensão salarial: " + candidato.getCurriculo().getPretensaoSalarial()
        );
        curriculoTextArea.setEditable(false);

        curriculoTextArea.setFont(new Font("Bauhaus 93", Font.ROMAN_BASELINE, 18));
        curriculoTextArea.setForeground(new Color(10, 10, 15));
        curriculoTextArea.setBackground(new Color(130, 163, 215, 211));

        return curriculoTextArea;

    }

    public void refreshVagas(TelaCandidato telaCandidato) {
        vagaPanel.removeAll();

        // Usando BorderLayout para adicionar o título no centro
        JLabel titleLabel = new JLabel("Olá, " + candidato.getNome());
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        titleLabel.setForeground(new Color(243, 131, 6));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adicionando o título ao centro do painel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBackground(Color.LIGHT_GRAY);

        // Criar um novo painel para as vagas
        JPanel vagasPanel = new JPanel();
        vagasPanel.setLayout(new BoxLayout(vagasPanel, BoxLayout.Y_AXIS));

        //Painel para os botões
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
                        TelaCandidato.this,
                        "Tem certeza que deseja excluir sua conta?",
                        "Excluir Conta",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmResult == JOptionPane.YES_OPTION) {
                    CandidatoService.deletaCandidato(candidato.getUsername(), candidato.getCpf());
                    dispose(); // Fechar a tela após exclusão
                }
            }
        });

        // Campo de exibição do currículo
        JTextArea curriculoTextArea = criarAreaCurriculo();

        // Botão para editar currículo
        JButton editarCurriculoButton = new JButton("Editar");
        editarCurriculoButton.setBackground(new Color(243, 131, 6));
        editarCurriculoButton.setForeground(new Color(255, 255, 255, 255));
        editarCurriculoButton.setBorder(new RoundedBorder(5, Color.WHITE));

        //Adicionando os botões no painel de botões
        buttonPanel.add(editarCurriculoButton);
        buttonPanel.add(excluirContaButton);
        editarCurriculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new TelaEditarCurriculo(candidato,telaCandidato).setVisible(true);
            }
        });

        //inserindo os componentes

        vagaPanel.add(buttonPanel);
        vagaPanel.add(Box.createVerticalStrut(10)); // Espaçamento

        vagaPanel.add(titlePanel);

        vagaPanel.add(curriculoTextArea);
        vagaPanel.add(Box.createVerticalStrut(10)); // Espaçamento

        vagaPanel.add(vagasPanel);

        LinkedList<Vaga> vagas = new LinkedList<>();
        for (Map.Entry<Key, Empresa> entry : empresaService.empresas.entrySet()) {
            vagas.addAll(entry.getValue().listaDeVagas());
        }

        for (Vaga vaga : vagas) {

            if(vaga.isDisponivel()){
                JPanel vagaRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
                vagaRow.setBackground(new Color(255, 230, 15));

                JLabel vagaLabel = new JLabel(vaga.getNome() + " - " + vaga.getEmpresa());
                JButton curtirButton = new JButton("Curtir");
                curtirButton.setBackground(new Color(243, 131, 6));
                curtirButton.setForeground(new Color(255, 255, 255, 255));
                curtirButton.setBorder(new RoundedBorder(5, Color.WHITE));

                curtirButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        candidato.curtir(vaga.getEmpresa());

                        CandidatoService.gravaCurtida(candidato, vaga.getEmpresa());

                        if(candidato.verificarMatch(vaga.getEmpresa())){
                            // Carregar a imagem que deseja exibir
                            ImageIcon icon = new ImageIcon("src/img/colab.png");

                            // Criar um painel para conter a imagem e o texto
                            JPanel panel = new JPanel();
                            panel.setLayout(new BorderLayout());

                            // Adicionar a imagem ao painel
                            JLabel imageLabel = new JLabel(icon);
                            panel.add(imageLabel, BorderLayout.CENTER);

                            // Criar uma label com o texto "It's a match!"
                            JLabel textLabel = new JLabel("It's a match! Entre em contato com a empresa: "+ vaga.getEmpresa().getEmail());
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

                        refreshVagas(telaCandidato);
                    }
                });


                vagaRow.add(vagaLabel);
                vagaRow.add(curtirButton);

                vagaPanel.add(vagaRow);
            }
        }

        revalidate();
        repaint();
    }
}