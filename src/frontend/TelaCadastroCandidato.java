package frontend;

import com.projeto.poo.Candidato;
import com.projeto.poo.CandidatoService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroCandidato extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    CandidatoService service = new CandidatoService();
    public TelaCadastroCandidato() {
        // Configurações básicas da janela
        setTitle("Cadastro de Candidato");
        setSize(600, 750);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/cv.png");
        setIconImage(icon.getImage());

        // Criando um painel para conter o formulário de cadastro
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Adicionando a imagem na posição central e inferior do painel
        ImageIcon imageIcon = new ImageIcon("src/img/cv.png");
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(imageLabel, gbc);

        // Título "CURRÍCULO" no centro
        JLabel titleLabel = new JLabel("CANDIDATO");
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        titleLabel.setForeground(new Color(243, 131, 6));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        mainPanel.add(titleLabel, gbc);

        // Criação dos componentes do formulário de cadastro
        JLabel nameLabel = new JLabel("Nome:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel countryLabel = new JLabel("País:");
        JLabel stateLabel = new JLabel("Estado:");
        JLabel cepLabel = new JLabel("CEP:");
        JLabel aboutLabel = new JLabel("Escreva um pouco sobre você:");
        JLabel ageLabel = new JLabel("Idade:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel usernameLabel = new JLabel("Nome de usuário:");
        JLabel passwordLabel = new JLabel("Senha:");
        JLabel formationLabel = new JLabel("Formação:");
        JLabel complementaryCoursesLabel = new JLabel("Cursos complementares:");
        JLabel salaryExpectationLabel = new JLabel("Pretensão salarial:");

        // Definindo o tamanho preferencial dos campos de texto
        JTextField nameField = new JTextField(25);
        JTextField emailField = new JTextField(25);
        JTextField countryField = new JTextField(25);
        JTextField stateField = new JTextField(25);
        JTextField cepField = new JTextField(25);

        // Usando JTextArea para o campo aboutField
        JTextArea aboutField = new JTextArea(5, 25); // 5 linhas e 25 colunas
        aboutField.setLineWrap(true); // Quebra de linha automática
        aboutField.setWrapStyleWord(true); // Quebra de palavra automática

        // Definindo o limite de caracteres para 300
        int maxCharacters = 300;
        Document doc = aboutField.getDocument();

        // Criando o JLabel para exibir o contador de caracteres
        JLabel characterCountLabel = new JLabel("0/" + maxCharacters);

        // Criando um painel para exibir o contador de caracteres
        JPanel characterCountPanel = new JPanel();
        characterCountPanel.add(characterCountLabel);

        // Adicionando o painel com o contador de caracteres ao painel principal
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        mainPanel.add(characterCountPanel, gbc);

        JTextField ageField = new JTextField(25);
        JTextField cpfField = new JTextField(25);
        JTextField usernameField = new JTextField(25);
        JPasswordField passwordField = new JPasswordField(25);
        JTextField formationField = new JTextField(25);
        JTextField complementaryCoursesField = new JTextField(25);
        JTextField salaryExpectationField = new JTextField(25);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBackground(new Color(243, 131, 6));
        cadastrarButton.setForeground(new Color(255, 255, 255, 255));
        cadastrarButton.setBorder(new RoundedBorder(5, Color.WHITE));

        // Adicionando os componentes ao painel do formulário de cadastro
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(countryLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(countryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(stateLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(stateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(cepLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(cepField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(aboutLabel, gbc);

        gbc.gridy = 8;
        mainPanel.add(aboutField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        mainPanel.add(ageLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        mainPanel.add(cpfLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(cpfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        mainPanel.add(formationLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(formationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        mainPanel.add(complementaryCoursesLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(complementaryCoursesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        mainPanel.add(salaryExpectationLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(salaryExpectationField, gbc);

        gbc.gridy = 16;
        gbc.gridwidth = 2;
        mainPanel.add(cadastrarButton, gbc);

        // Adicionando o painel contendo o formulário de cadastro à janela
        add(mainPanel);

        // Centralizando a janela após adicionar o painel
        setLocationRelativeTo(null);

        // Adicionando um DocumentListener para validar o CPF durante o preenchimento
        cpfField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateCPF();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateCPF();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateCPF();
            }

            private void validateCPF() {
                // Realizar a validação do CPF aqui
                String cpf = cpfField.getText().replaceAll("[^0-9]", ""); // Removendo caracteres não numéricos
                boolean isCpfValid = cpf.matches("([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})");

                if (!isCpfValid) {
                    cpfField.setForeground(Color.RED);
                } else {
                    cpfField.setForeground(Color.BLACK);
                }
            }
        });

        // Aplicando DocumentFilter para limitar a quantidade de caracteres em aboutField
        ((AbstractDocument) aboutField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                // Verifica se a inserção não ultrapassa o limite de caracteres
                if ((fb.getDocument().getLength() + string.length()) <= maxCharacters) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Verifica se a substituição não ultrapassa o limite de caracteres
                int newLength = fb.getDocument().getLength() - length + text.length();
                if (newLength <= maxCharacters) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
            }
        });

        // Atualiza o contador de caracteres ao digitar no campo aboutField
        aboutField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCharacterCount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCharacterCount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCharacterCount();
            }

            private void updateCharacterCount() {
                characterCountLabel.setText(aboutField.getText().length() + "/" + maxCharacters);

                // Verificando o limite de caracteres e alterando a cor do contador se exceder
                if (aboutField.getText().length() > maxCharacters) {
                    characterCountLabel.setForeground(Color.RED);
                } else {
                    characterCountLabel.setForeground(Color.BLACK);
                }
            }
        });

        // Adicionando funcionalidade ao campo de senha (passwordField)
        salaryExpectationField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando a tecla "Enter" for pressionada no campo de senha,
                // aciona o evento do botão "Cadastrar"
                cadastrarButton.doClick();
            }
        });

        // Adicionando funcionalidade ao botão "Cadastrar"
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica se todos os campos obrigatórios foram preenchidos
                if (isCamposPreenchidos()) {
                    // Cria a instância de Candidato e cadastra o currículo

                    try {
                        Candidato candidato = criarCandidato();
                        candidato.cadastraCurriculo(formationField.getText(), complementaryCoursesField.getText(), Double.parseDouble(salaryExpectationField.getText()));

                        // Exemplo de exibição das informações cadastradas
                        System.out.println("Candidato cadastrado: " + candidato);
                        JOptionPane.showMessageDialog(TelaCadastroCandidato.this,
                                "Cadastro realizado com sucesso!",
                                "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                    } catch (NumberFormatException ex) {
                        // Exceção ocorre se a conversão de String para int ou double falhar
                        service.deletaCandidato(usernameField.getText(),cpfField.getText());
                        JOptionPane.showMessageDialog(TelaCadastroCandidato.this,
                                "Insira uma idade e uma pretensão salarial válidas (números inteiros para idade e números para pretensão salarial).",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(TelaCadastroCandidato.this, "Preencha todos os campos obrigatórios antes de cadastrar.");
                }
            }

            private Candidato criarCandidato() {
                    String nome = nameField.getText();
                    String email = emailField.getText();
                    String pais = countryField.getText();
                    String estado = stateField.getText();
                    String cep = cepField.getText();
                    String sobre = aboutField.getText();
                    String username = usernameField.getText();
                    String senha = String.valueOf(passwordField.getPassword());
                    int idade = Integer.parseInt(ageField.getText());
                    String cpf = cpfField.getText();

                    service.cadastraCandidato(nome, email, pais, estado, cep, sobre, username, senha, idade, cpf);

                    return service.getCandidato(username,cpf);
            }

            // Método para verificar se todos os campos obrigatórios foram preenchidos
            private boolean isCamposPreenchidos() {
                return !nameField.getText().isEmpty()
                        && !emailField.getText().isEmpty()
                        && !countryField.getText().isEmpty()
                        && !stateField.getText().isEmpty()
                        && !cepField.getText().isEmpty()
                        && !aboutField.getText().isEmpty()
                        && !usernameField.getText().isEmpty()
                        && !(passwordField.getPassword().length == 0)
                        && !ageField.getText().isEmpty()
                        && !cpfField.getText().isEmpty()
                        && !formationField.getText().isEmpty()
                        && !complementaryCoursesField.getText().isEmpty()
                        && !salaryExpectationField.getText().isEmpty();
            }
        });
    }
}
