package appAlunoDisciplina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controlAlunoDisciplina.Control;

public class FormAluno extends JFrame implements ActionListener
{
    private final Control control;

    private final Container container;
    private final JLabel labelTitle;
    private final JLabel labelName;
    private final JTextField textFieldName;
    private final JLabel labelEnroll;
    private final JTextField textFieldEnroll;
    private final JButton buttonSave;

    public FormAluno(Control control)
    {
        this.control = control;

        setTitle("Formulário de Aluno");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        this.container = getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Cadastro de Aluno");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 27));
        this.labelTitle.setForeground(Color.BLACK);
        this.labelTitle.setSize(300, 30);
        this.labelTitle.setLocation(330, 100);
        this.container.add(this.labelTitle);

        this.labelName = new JLabel("Nome");
        this.labelName.setFont(new Font("Arial", Font.PLAIN, 24));
        this.labelName.setSize(100, 24);
        this.labelName.setLocation(300, 200);
        this.container.add(labelName);

        this.textFieldName = new JTextField();
        this.textFieldName.setFont(new Font("Arial", Font.PLAIN, 15));
        this.textFieldName.setSize(180, 24);
        this.textFieldName.setLocation(420, 200);
        this.container.add(this.textFieldName);

        this.labelEnroll = new JLabel("Matrícula");
        this.labelEnroll.setFont(new Font("arial", Font.PLAIN, 24));
        this.labelEnroll.setSize(100, 24);
        this.labelEnroll.setLocation(300, 250);
        this.container.add(this.labelEnroll);

        this.textFieldEnroll = new JTextField();
        this.textFieldEnroll.setFont(new Font("Arial", Font.PLAIN, 15));
        this.textFieldEnroll.setSize(180, 24);
        this.textFieldEnroll.setLocation(420, 250);
        this.container.add(this.textFieldEnroll);

        this.buttonSave = new JButton("Salvar");
        this.buttonSave.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonSave.setBackground(Color.BLACK);
        this.buttonSave.setForeground(Color.WHITE);
        this.buttonSave.setSize(150, 45);
        this.buttonSave.setLocation(360, 400);
        this.buttonSave.addActionListener(this);
        this.container.add(this.buttonSave);

        setVisible(true);
    }

    private void enroll() throws NumberFormatException
    {
        int matricula;
        String nome;

        matricula = Integer.parseInt(this.textFieldEnroll.getText());
        nome = this.textFieldName.getText();

        if (this.control.cadastrarAluno(matricula, nome))
        {
            JOptionPane.showMessageDialog(this, "Aluno cadastrado", "Salvo", JOptionPane.INFORMATION_MESSAGE);
        }

        else JOptionPane.showMessageDialog(this, "Já há aluno com esta matrícula", "Matrícula Existente", JOptionPane.WARNING_MESSAGE);
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            enroll();
        }
        catch (NumberFormatException exception)
        {
            JOptionPane.showMessageDialog(this, "Insira apenas números.", "Número invalido", JOptionPane.WARNING_MESSAGE);
        }
    }
}