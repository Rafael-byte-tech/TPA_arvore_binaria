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
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        this.container = this.getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Consultar Alunos");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 27));
        this.labelTitle.setForeground(Color.BLACK);
        this.labelTitle.setSize(400, 30);
        this.labelTitle.setLocation(350, 100);
        this.container.add(this.labelTitle);

        this.labelNameQuery = new JLabel("Nome do Aluno");
        this.labelNameQuery.setFont(new Font("Arial", Font.PLAIN, 18));
        this.labelNameQuery.setSize(200, 25);
        this.labelNameQuery.setLocation(250, 170);
        this.container.add(this.labelNameQuery);

        this.textFieldNameQuery = new JTextField();
        this.textFieldNameQuery.setFont(new Font("Arial", Font.PLAIN, 16));
        this.textFieldNameQuery.setSize(180, 30);
        this.textFieldNameQuery.setLocation(250, 200);
        this.container.add(textFieldNameQuery);

        this.buttonNameQuery = new JButton("Consultar por Nome");
        this.buttonNameQuery.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonNameQuery.setBackground(Color.BLACK);
        this.buttonNameQuery.setForeground(Color.WHITE);
        this.buttonNameQuery.setSize(230, 30);
        this.buttonNameQuery.setLocation(450, 200);
        this.buttonNameQuery.addActionListener(this);
        this.container.add(this.buttonNameQuery);

        this.labelEnrollmentQuery = new JLabel("Matrícula do Aluno");
        this.labelEnrollmentQuery.setFont(new Font("Arial", Font.PLAIN, 18));
        this.labelEnrollmentQuery.setSize(160, 25);
        this.labelEnrollmentQuery.setLocation(250, 270);
        this.container.add(this.labelEnrollmentQuery);

        this.textFieldEnrollmentQuery = new JTextField();
        this.textFieldEnrollmentQuery.setFont(new Font("Arial", Font.PLAIN, 16));
        this.textFieldEnrollmentQuery.setSize(180, 30);
        this.textFieldEnrollmentQuery.setLocation(250, 300);
        this.container.add(textFieldEnrollmentQuery);

        this.buttonEnrollmentQuery = new JButton("Consultar por Matrícula");
        this.buttonEnrollmentQuery.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonEnrollmentQuery.setBackground(Color.BLACK);
        this.buttonEnrollmentQuery.setForeground(Color.WHITE);
        this.buttonEnrollmentQuery.setSize(230, 30);
        this.buttonEnrollmentQuery.setLocation(450, 300);
        this.buttonEnrollmentQuery.addActionListener(this);
        this.container.add(this.buttonEnrollmentQuery);

        this.labelDeleteQuery = new JLabel("Deletar Aluno");
        this.labelDeleteQuery.setFont(new Font("Arial", Font.PLAIN, 18));
        this.labelDeleteQuery.setSize(140, 25);
        this.labelDeleteQuery.setLocation(250, 370);
        this.container.add(this.labelDeleteQuery);

        this.textFieldDelete = new JTextField();
        this.textFieldDelete.setFont(new Font("Arial", Font.PLAIN, 16));
        this.textFieldDelete.setSize(180, 30);
        this.textFieldDelete.setLocation(250, 400);
        this.container.add(this.textFieldDelete);

        this.buttonDelete = new JButton("Deletar por Matrícula");
        this.buttonDelete.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonDelete.setBackground(Color.BLACK);
        this.buttonDelete.setForeground(Color.WHITE);
        this.buttonDelete.setSize(230, 30);
        this.buttonDelete.setLocation(450, 400);
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
