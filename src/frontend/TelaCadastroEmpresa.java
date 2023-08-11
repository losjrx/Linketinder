package frontend;

import com.projeto.poo.Empresa;
import com.projeto.poo.EmpresaService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroEmpresa extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    EmpresaService service = new EmpresaService();

    public TelaCadastroEmpresa() {
        // Configurações básicas da janela
        setTitle("Cadastro de Empresa");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/company.png");
        setIconImage(icon.getImage());

        // Criando um painel para conter o formulário de cadastro
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Adicionando a imagem na posição central e inferior do painel
        ImageIcon imageIcon = new ImageIcon("src/img/office.png");
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(imageLabel, gbc);

        // Título "EMPRESA" no centro
        JLabel titleLabel = new JLabel("EMPRESA");
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 36));
        titleLabel.setForeground(new Color(243, 131, 6));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        mainPanel.add(titleLabel, gbc);

        // Criação dos componentes do formulário de cadastro
        JLabel nameLabel = new JLabel("Nome:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel countryLabel = new JLabel("País:");
        JLabel stateLabel = new JLabel("Estado:");
        JLabel cepLabel = new JLabel("CEP:");
        JLabel aboutLabel = new JLabel("Descrição:");
        JLabel cnpjLabel = new JLabel("CNPJ:");
        JLabel usernameLabel = new JLabel("Nome de usuário:");
        JLabel passwordLabel = new JLabel("Senha:");

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
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        mainPanel.add(characterCountPanel, gbc);

        JTextField cnpjField = new JTextField(25);
        JTextField usernameField = new JTextField(25);
        JPasswordField passwordField = new JPasswordField(25);

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

        gbc.gridx = 2;
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 2;
        mainPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(countryLabel, gbc);

        gbc.gridx = 2;
        mainPanel.add(countryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(stateLabel, gbc);

        gbc.gridx = 2;
        mainPanel.add(stateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(cepLabel, gbc);

        gbc.gridx = 2;
        mainPanel.add(cepField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        mainPanel.add(aboutLabel, gbc);

        gbc.gridy = 8;
        mainPanel.add(aboutField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        mainPanel.add(cnpjLabel, gbc);

        gbc.gridx = 2;
        mainPanel.add(cnpjField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 2;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 2;
        mainPanel.add(passwordField, gbc);

        gbc.gridy = 12;
        gbc.gridwidth = 2;
        mainPanel.add(cadastrarButton, gbc);

        // Adicionando o painel contendo o formulário de cadastro à janela
        add(mainPanel);

        // Centralizando a janela após adicionar o painel
        setLocationRelativeTo(null);

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
        passwordField.addActionListener(new ActionListener() {
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
                    // Cria a instância de Empresa e realiza o cadastro
                    Empresa empresa = criarEmpresa();
                    // Código para cadastrar a empresa na base de dados aqui
                    System.out.println("Empresa cadastrada: " + empresa);
                    JOptionPane.showMessageDialog(TelaCadastroEmpresa.this,
                            "Cadastro realizado com sucesso!",
                            "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);


                } else {
                    JOptionPane.showMessageDialog(TelaCadastroEmpresa.this, "Preencha todos os campos obrigatórios antes de cadastrar.");
                }
            }

            private Empresa criarEmpresa() {
                String nome = nameField.getText();
                String email = emailField.getText();
                String pais = countryField.getText();
                String estado = stateField.getText();
                String cep = cepField.getText();
                String descricao = aboutField.getText();
                String cnpj = cnpjField.getText();
                String username = usernameField.getText();
                String senha = String.valueOf(passwordField.getPassword());

                service.cadastraEmpresa(nome, email, pais, estado, cep, descricao, cnpj, username, senha);

                return service.getEmpresa(username,cnpj);
            }

            // Método para verificar se todos os campos obrigatórios foram preenchidos
            private boolean isCamposPreenchidos() {
                return !nameField.getText().isEmpty()
                        && !emailField.getText().isEmpty()
                        && !countryField.getText().isEmpty()
                        && !stateField.getText().isEmpty()
                        && !cepField.getText().isEmpty()
                        && !aboutField.getText().isEmpty()
                        && !cnpjField.getText().isEmpty()
                        && !usernameField.getText().isEmpty()
                        && !(passwordField.getPassword().length == 0);
            }
        });
    }
}
