package frontend;

import com.projeto.poo.Candidato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarCurriculo extends JFrame {

    private Candidato candidato;
    private TelaCandidato telaCandidato;
    private JTextArea formacaoTextArea;
    private JTextArea cursosComplementaresTextArea;
    private JTextField pretensaoSalarialField;

    public TelaEditarCurriculo(Candidato candidato, TelaCandidato telaCandidato) {
        this.candidato = candidato;
        this.telaCandidato = telaCandidato;

        setTitle("Linketinder - Editar Currículo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Definindo ícone da aplicação
        ImageIcon icon = new ImageIcon("src/img/colab.png");
        setIconImage(icon.getImage());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel formacaoLabel = new JLabel("Formação:");
        formacaoTextArea = new JTextArea(candidato.getCurriculo().getFormacao(), 5, 20);
        JScrollPane formacaoScrollPane = new JScrollPane(formacaoTextArea);

        JLabel cursosComplementaresLabel = new JLabel("Cursos Complementares:");
        cursosComplementaresTextArea = new JTextArea(candidato.getCurriculo().getCursosComplementares(), 5, 20);
        JScrollPane cursosComplementaresScrollPane = new JScrollPane(cursosComplementaresTextArea);

        JLabel pretensaoSalarialLabel = new JLabel("Pretensão Salarial:");
        pretensaoSalarialField = new JTextField(String.valueOf(candidato.getCurriculo().getPretensaoSalarial()), 20);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setBackground(new Color(243, 131, 6));
        salvarButton.setForeground(new Color(255, 255, 255, 255));
        salvarButton.setBorder(new RoundedBorder(5, Color.WHITE));

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novaFormacao = formacaoTextArea.getText();
                String novosCursosComplementares = cursosComplementaresTextArea.getText();
                double novaPretensaoSalarial = Double.parseDouble(pretensaoSalarialField.getText());

                candidato.getCurriculo().setFormacao(novaFormacao);
                candidato.getCurriculo().setCursosComplementares(novosCursosComplementares);
                candidato.getCurriculo().setPretensaoSalarial(novaPretensaoSalarial);

                telaCandidato.refreshVagas(telaCandidato);
                JOptionPane.showMessageDialog(null, "Currículo atualizado com sucesso!");

                dispose(); // Fechar a tela após salvar
            }
        });

        contentPanel.add(formacaoLabel);
        contentPanel.add(formacaoScrollPane);
        contentPanel.add(cursosComplementaresLabel);
        contentPanel.add(cursosComplementaresScrollPane);
        contentPanel.add(pretensaoSalarialLabel);
        contentPanel.add(pretensaoSalarialField);
        contentPanel.add(salvarButton);

        add(contentPanel);
    }
}
