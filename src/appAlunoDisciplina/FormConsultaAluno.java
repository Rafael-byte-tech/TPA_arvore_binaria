package appAlunoDisciplina;

import controlAlunoDisciplina.Control;
import controlAlunoDisciplina.StudentNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormConsultaAluno extends JFrame implements ActionListener
{
    private Control control;

    private Container container;
    private JLabel labelTitle;
    private JLabel labelNameQuery;
    private JTextField textFieldNameQuery;
    private JButton buttonNameQuery;
    private JLabel labelEnrollmentQuery;
    private JTextField textFieldEnrollmentQuery;
    private JButton buttonEnrollmentQuery;
    private JLabel labelDeleteQuery;
    private JTextField textFieldDelete;
    private JButton buttonDelete;

    public FormConsultaAluno (Control control)
    {
        this.control = control;

        setTitle("Formulário de Consulta do Aluno");
        setBounds(300, 90, 800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        this.container = this.getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Formulário de Consulta do Aluno");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        this.labelTitle.setSize(400, 20);
        this.labelTitle.setLocation(220, 30);
        this.container.add(this.labelTitle);

        this.labelNameQuery = new JLabel("Nome do Aluno");
        this.labelNameQuery.setFont(new Font("Arial", Font.PLAIN, 17));
        this.labelNameQuery.setSize(140, 25);
        this.labelNameQuery.setLocation(100, 100);
        this.container.add(this.labelNameQuery);

        this.textFieldNameQuery = new JTextField();
        this.textFieldNameQuery.setFont(new Font("Arial", Font.PLAIN, 17));
        this.textFieldNameQuery.setSize(200, 25);
        this.textFieldNameQuery.setLocation(100, 125);
        this.container.add(textFieldNameQuery);

        this.buttonNameQuery = new JButton("Consultar por Nome");
        this.buttonNameQuery.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonNameQuery.setSize(230, 25);
        this.buttonNameQuery.setLocation(320, 125);
        this.buttonNameQuery.addActionListener(this);
        this.container.add(this.buttonNameQuery);

        this.labelEnrollmentQuery = new JLabel("Matrícula do Aluno");
        this.labelEnrollmentQuery.setFont(new Font("Arial", Font.PLAIN, 17));
        this.labelEnrollmentQuery.setSize(140, 25);
        this.labelEnrollmentQuery.setLocation(100, 175);
        this.container.add(this.labelEnrollmentQuery);

        this.textFieldEnrollmentQuery = new JTextField();
        this.textFieldEnrollmentQuery.setFont(new Font("Arial", Font.PLAIN, 17));
        this.textFieldEnrollmentQuery.setSize(200, 25);
        this.textFieldEnrollmentQuery.setLocation(100, 200);
        this.container.add(textFieldEnrollmentQuery);

        this.buttonEnrollmentQuery = new JButton("Consultar por Matrícula");
        this.buttonEnrollmentQuery.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonEnrollmentQuery.setSize(230, 25);
        this.buttonEnrollmentQuery.setLocation(320, 200);
        this.buttonEnrollmentQuery.addActionListener(this);
        this.container.add(this.buttonEnrollmentQuery);

        this.labelDeleteQuery = new JLabel("Deletar Aluno");
        this.labelDeleteQuery.setFont(new Font("Arial", Font.PLAIN, 17));
        this.labelDeleteQuery.setSize(140, 25);
        this.labelDeleteQuery.setLocation(100, 250);
        this.container.add(this.labelDeleteQuery);

        this.textFieldDelete = new JTextField();
        this.textFieldDelete.setFont(new Font("Arial", Font.PLAIN, 17));
        this.textFieldDelete.setSize(200, 25);
        this.textFieldDelete.setLocation(100, 275);
        this.container.add(this.textFieldDelete);

        this.buttonDelete = new JButton("Deletar por Matrícula");
        this.buttonDelete.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonDelete.setSize(230, 25);
        this.buttonDelete.setLocation(320, 275);
        this.buttonDelete.addActionListener(this);
        this.container.add(this.buttonDelete);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        InfoPane infoPane;

        if (event.getSource() == buttonNameQuery)
        {
            try
            {
                infoPane = new InfoPane(this.control.buscarAlunoNome(this.textFieldNameQuery.getText()));
            }
            catch (StudentNotFoundException exception)
            {
                JOptionPane.showMessageDialog(this, "Nome não consta nos registros" , exception.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (event.getSource() == buttonEnrollmentQuery)
        {
            try
            {
                infoPane = new InfoPane(this.control.buscarAlunoMatricula(Integer.parseInt(this.textFieldEnrollmentQuery.getText())));
            }
            catch (NumberFormatException exception)
            {
                JOptionPane.showMessageDialog(this, "Insira apenas números inteiros" , exception.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
            catch (StudentNotFoundException exception)
            {
                JOptionPane.showMessageDialog(this, "Matrícula não consta nos registros" , exception.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
        }

        else
        {
            try
            {
                this.control.excluirAlunoMatricula(Integer.parseInt(this.textFieldDelete.getText()));
                JOptionPane.showMessageDialog(this, "Aluno EXPURGADO com sucesso!!!!", "Exorcismo Completo", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (NumberFormatException exception)
            {
                JOptionPane.showMessageDialog(this, "Insira apenas números inteiros" , exception.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
            catch (StudentNotFoundException exception)
            {
                JOptionPane.showMessageDialog(this, "Matrícula não consta nos registros" , exception.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
