package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Tela extends JFrame {

    private JRadioButton candidateRadioButton;
    private JRadioButton companyRadioButton;

    public Tela() {
        // Configura��es b�sicas da janela
        setTitle("Linketinder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Definindo �cone da aplica��o
        ImageIcon icon = new ImageIcon("src/img/colab.png");
        setIconImage(icon.getImage());

        // Criando um painel para conter a imagem e o formul�rio
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new RoundedBorder(60,Color.LIGHT_GRAY));

        // Adicionando o t�tulo "LINKETINDER" acima do formul�rio
        JLabel titleLabel = new JLabel("LINKETINDER");
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 36));
        titleLabel.setForeground(new Color(243, 131, 6));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Adicionando a imagem na posi��o central e inferior do painel
        ImageIcon imageIcon = new ImageIcon("src/img/colab.png");
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.SOUTH);

        // Definindo a cor de fundo do painel (neste caso, azul)
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Criando um painel para conter os campos do formul�rio e os bot�es
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        // Cria��o dos componentes do formul�rio
        JLabel usernameLabel = new JLabel("Usu�rio:");
        JLabel passwordLabel = new JLabel("Senha:");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        // Radio Buttons
        candidateRadioButton = new JRadioButton("Candidato");
        companyRadioButton = new JRadioButton("Empresa");

        // Usando o FlowLayout para os radio buttons ficarem lado a lado
        JPanel radioButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioButtonsPanel.setBackground(Color.WHITE);
        radioButtonsPanel.add(candidateRadioButton);
        radioButtonsPanel.add(companyRadioButton);

        // Grupo de radio buttons para garantir que apenas um deles seja selecionado
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(candidateRadioButton);
        radioButtonGroup.add(companyRadioButton);

        // Bot�es
        JButton loginButton = new JButton("Entrar");
        loginButton.setBackground(new Color(243, 131, 6));
        loginButton.setForeground(new Color(255, 255, 255, 255));
        loginButton.setBorder(new RoundedBorder(5, Color.WHITE));
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(243, 131, 6));
        registerButton.setForeground(new Color(255, 255, 255, 255));
        registerButton.setBorder(new RoundedBorder(5, Color.WHITE));

        // Adicionando os componentes ao painel do formul�rio
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Criando um novo GridBagConstraints para os radio buttons
        GridBagConstraints radioButtonsGbc = new GridBagConstraints();
        radioButtonsGbc.gridx = 0;
        radioButtonsGbc.gridy = 2;
        radioButtonsGbc.gridwidth = 2;
        radioButtonsGbc.anchor = GridBagConstraints.CENTER;
        radioButtonsGbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(radioButtonsPanel, radioButtonsGbc);

        // Criando um novo painel para agrupar os bot�es "Entrar" e "Cadastrar"
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);

        // Criando um novo GridBagConstraints para os bot�es
        GridBagConstraints buttonsGbc = new GridBagConstraints();
        buttonsGbc.gridx = 0;
        buttonsGbc.gridy = 3;
        buttonsGbc.gridwidth = 2;
        buttonsGbc.anchor = GridBagConstraints.CENTER;
        buttonsGbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(buttonsPanel, buttonsGbc);

        // Adicionando os pain�is ao painel principal
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Adicionando o painel contendo a imagem e o formul�rio � janela
        add(mainPanel);

        // Centralizando a janela ap�s adicionar o painel
        setLocationRelativeTo(null);

        // Adicionando funcionalidade aos bot�es
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma op��o (Candidato ou Empresa).");
                } else if (usernameField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos (Usu�rio e Senha).");
                } else {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    // Aqui voc� pode adicionar a l�gica de autentica��o, se necess�rio
                    System.out.println("Usu�rio: " + username + ", Senha: " + password + ", Tipo: " + getSelectedType());
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (candidateRadioButton.isSelected()) {
                    // Se a op��o "Candidato" estiver selecionada, abra a tela de cadastro de candidato
                    TelaCadastroCandidato telaCadastroCandidato = new TelaCadastroCandidato();
                    telaCadastroCandidato.setVisible(true);
                } else if (companyRadioButton.isSelected()) {
                    // Se a op��o "Empresa" estiver selecionada, abra a tela de cadastro de empresa
                    TelaCadastroEmpresa telaCadastroEmpresa = new TelaCadastroEmpresa();
                    telaCadastroEmpresa.setVisible(true);
                } else {
                    // Caso nenhuma op��o esteja selecionada, exiba o alerta
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma op��o (Candidato ou Empresa).");
                }
            }
        });
    }

    private String getSelectedType() {
        if (candidateRadioButton.isSelected()) {
            return "Candidato";
        } else if (companyRadioButton.isSelected()) {
            return "Empresa";
        } else {
            return "Indefinido";
        }
    }
}