package frontend;

import com.projeto.poo.Empresa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroVaga extends JFrame {

    private Empresa empresa;
    private TelaEmpresa telaEmpresa; // Adicione este atributo

    private JTextField nomeField;
    private JTextField tipoField;
    private JTextField salarioField;
    private JTextArea definicaoArea;
    private JComboBox<String> disponivelComboBox;

    public TelaCadastroVaga(Empresa empresa, TelaEmpresa telaEmpresa) {
        this.empresa = empresa;
        this.telaEmpresa = telaEmpresa; // Inicialize o atributo

        setTitle("Linketinder - Cadastro de Vaga");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/colab.png");
        setIconImage(icon.getImage());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel nomeLabel = new JLabel("Nome da vaga:");
        nomeField = new JTextField(20);

        JLabel tipoLabel = new JLabel("Tipo:");
        tipoField = new JTextField(20);

        JLabel salarioLabel = new JLabel("Salário:");
        salarioField = new JTextField(20);

        JLabel definicaoLabel = new JLabel("Definição:");
        definicaoArea = new JTextArea(5, 20);
        JScrollPane definicaoScrollPane = new JScrollPane(definicaoArea);

        JLabel disponivelLabel = new JLabel("Disponível para candidatos:");
        disponivelComboBox = new JComboBox<>(new String[]{"Sim", "Não"});

        // Adicione aqui os elementos gráficos para outros campos

        JButton salvarButton = new JButton("Salvar");

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String tipo = tipoField.getText();
                double salario = Double.parseDouble(salarioField.getText());
                String definicao = definicaoArea.getText();
                boolean disponivel = disponivelComboBox.getSelectedIndex() == 0;

                empresa.cadastraVaga(nome, tipo, salario, definicao, empresa, disponivel);
                telaEmpresa.atualizarListaVagas();

                JOptionPane.showMessageDialog(null, "Vaga cadastrada com sucesso!");

                dispose(); // Fecha a janela de cadastro após salvar
            }
        });

        contentPanel.add(nomeLabel);
        contentPanel.add(nomeField);
        contentPanel.add(tipoLabel);
        contentPanel.add(tipoField);
        contentPanel.add(salarioLabel);
        contentPanel.add(salarioField);
        contentPanel.add(definicaoLabel);
        contentPanel.add(definicaoScrollPane);
        contentPanel.add(disponivelLabel);
        contentPanel.add(disponivelComboBox);
        contentPanel.add(salvarButton);

        add(contentPanel);
    }
}
