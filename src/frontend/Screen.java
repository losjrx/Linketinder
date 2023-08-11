package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Screen extends JFrame {

    JTextField texto;
    public Screen(){
        setTitle("Linketinder");
        setVisible(true);
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton jButton = new JButton("Entrar");
        jButton.setBounds(100,200,250,70);
        jButton.setFont(new Font("Arial", Font.BOLD, 48));
        jButton.setForeground(new Color(121,254,237));
        jButton.setBackground(new Color(53,162,159));
        add(jButton);

        jButton.addActionListener(this::teste); //o mesmo que action -> { this.teste(action); }

        texto = new JTextField("Clique aqui.");
        texto.setBounds(100,100,500,100);
        texto.setFont(new Font("Arial", Font.ITALIC, 48));
        add(texto);
    }

    private void teste(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(null,texto.getText(),"Teste",JOptionPane.INFORMATION_MESSAGE);
    }
}
