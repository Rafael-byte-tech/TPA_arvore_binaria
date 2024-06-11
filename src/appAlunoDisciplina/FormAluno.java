package appAlunoDisciplina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controlAlunoDisciplina.Control;

public class FormAluno extends JFrame implements ActionListener
{
    private Control control;

    private Container container;
    private JLabel labelTitle;
    private JLabel labelName;
    private JTextField textFieldName;
    private JLabel labelEnroll;
    private JTextField textFieldEnroll;
    private JButton buttonSave;

    public FormAluno(Control control)
    {
        this.control = control;

        setTitle("Formulário de Alunos");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.container = getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Formulário de Alunos.");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        this.labelTitle.setSize(300, 30);
        this.labelTitle.setLocation(300, 30);
        this.container.add(this.labelTitle);

        this.labelName = new JLabel("Nome");
        this.labelName.setFont(new Font("Arial", Font.PLAIN, 20));
        this.labelName.setSize(100, 20);
        this.labelName.setLocation(100, 100);
        this.container.add(labelName);

        this.textFieldName = new JTextField();
        this.textFieldName.setFont(new Font("Arial", Font.PLAIN, 15));
        this.textFieldName.setSize(190, 20);
        this.textFieldName.setLocation(200, 100);
        this.container.add(this.textFieldName);

        this.labelEnroll = new JLabel("Matrícula");
        this.labelEnroll.setFont(new Font("arial", Font.PLAIN, 20));
        this.labelEnroll.setSize(100, 20);
        this.labelEnroll.setLocation(100, 150);
        this.container.add(this.labelEnroll);

        this.textFieldEnroll = new JTextField();
        this.textFieldEnroll.setFont(new Font("Arial", Font.PLAIN, 15));
        this.textFieldEnroll.setSize(150, 20);
        this.textFieldEnroll.setLocation(200, 150);
        this.container.add(this.textFieldEnroll);

        this.buttonSave = new JButton("Salvar");
        this.buttonSave.setFont(new Font("Arial", Font.PLAIN, 15));
        this.buttonSave.setSize(100, 20);
        this.buttonSave.setLocation(150, 450);
        this.buttonSave.addActionListener(this);
        this.container.add(this.buttonSave);

        setVisible(true);
    }

    public void enroll() throws NumberFormatException
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
        int matricula;
        String nome;

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