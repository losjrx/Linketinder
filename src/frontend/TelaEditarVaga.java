package frontend;

import com.projeto.poo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarVaga extends JFrame {

    private Vaga vaga;
    private TelaEmpresa telaEmpresa;

    public TelaEditarVaga(Vaga vaga, TelaEmpresa telaEmpresa) {
        this.vaga = vaga;
        this.telaEmpresa = telaEmpresa;

        setTitle("Editar Vaga");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/colab.png");
        setIconImage(icon.getImage());

        // Criar os componentes da tela
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 linhas, 2 colunas, espaçamento de 10
        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameTextField = new JTextField(vaga.getNome());

        JLabel typeLabel = new JLabel("Tipo:");
        JTextField typeTextField = new JTextField(vaga.getTipo());

        JLabel salaryLabel = new JLabel("Salário:");
        JTextField salaryTextField = new JTextField(Double.toString(vaga.getSalario()));

        JLabel definitionLabel = new JLabel("Definição:");
        JTextArea definitionTextArea = new JTextArea(vaga.getDefinicao());

        JLabel availableLabel = new JLabel("Disponível:");
        JComboBox<String> availableComboBox = new JComboBox<>(new String[]{"Sim", "Não"});
        availableComboBox.setSelectedItem(vaga.isDisponivel() ? "Sim" : "Não");

        JButton saveButton = new JButton("Salvar");
        saveButton.setBackground(new Color(243, 131, 6));
        saveButton.setForeground(new Color(255, 255, 255, 255));
        saveButton.setBorder(new RoundedBorder(5, Color.WHITE));

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(243, 131, 6));
        cancelButton.setForeground(new Color(255, 255, 255, 255));
        cancelButton.setBorder(new RoundedBorder(5, Color.WHITE));

        // Adicionar os componentes ao painel
        contentPanel.add(nameLabel);
        contentPanel.add(nameTextField);
        contentPanel.add(typeLabel);
        contentPanel.add(typeTextField);
        contentPanel.add(salaryLabel);
        contentPanel.add(salaryTextField);
        contentPanel.add(definitionLabel);
        contentPanel.add(definitionTextArea);
        contentPanel.add(availableLabel);
        contentPanel.add(availableComboBox);
        contentPanel.add(saveButton);
        contentPanel.add(cancelButton);

        // Configurar o botão de salvar
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualizar os valores da vaga com os dados dos campos
                vaga.setNome(nameTextField.getText());
                vaga.setTipo(typeTextField.getText());
                vaga.setSalario(Double.parseDouble(salaryTextField.getText()));
                vaga.setDefinicao(definitionTextArea.getText());
                vaga.setDisponivel(availableComboBox.getSelectedItem().equals("Sim"));

                EmpresaService.editaVaga(vaga);

                // Atualizar a vaga no serviço
                telaEmpresa.atualizarListaVagas(telaEmpresa);

                // Fechar a tela de edição
                dispose();
            }
        });

        // Configurar o botão de cancelar
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(contentPanel);
    }
}
