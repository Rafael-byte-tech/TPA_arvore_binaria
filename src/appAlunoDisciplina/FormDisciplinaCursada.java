package appAlunoDisciplina;

import controlAlunoDisciplina.Control;
import controlAlunoDisciplina.DisciplineNotFoundException;
import controlAlunoDisciplina.InvalidInputException;
import controlAlunoDisciplina.StudentNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDisciplinaCursada extends JFrame implements ActionListener
{
    private Control control;

    private Container container;
    private JLabel labelTitle;
    private JLabel labelEnroll;
    private JTextField textFieldEnroll;
    private JLabel labelCode;
    private JTextField textFieldCode;
    private JButton buttonSave;

    public FormDisciplinaCursada(Control control)
    {
        this.control = control;

        this.setTitle("Formulário de Disciplina Cursada");
        this.setBounds(300, 90, 900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.container = getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Disciplina Cursada");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 27));
        this.labelTitle.setForeground(Color.BLACK);
        this.labelTitle.setSize(400, 30);
        this.labelTitle.setLocation(330, 100);
        this.container.add(this.labelTitle);

        this.labelEnroll = new JLabel("Matrícula do aluno");
        this.labelEnroll.setFont(new Font("Arial", Font.PLAIN, 18));
        this.labelEnroll.setSize(160, 25);
        this.labelEnroll.setLocation(240, 200);
        this.container.add(this.labelEnroll);

        this.textFieldEnroll = new JTextField();
        this.textFieldEnroll.setFont(new Font("Arial", Font.PLAIN, 17));
        this.textFieldEnroll.setSize(180, 25);
        this.textFieldEnroll.setLocation(420, 200);
        this.container.add(this.textFieldEnroll);

        this.labelCode = new JLabel("Código da Disciplina");
        this.labelCode.setFont(new Font("Arial", Font.PLAIN, 18));
        this.labelCode.setSize(170, 25);
        this.labelCode.setLocation(230, 250);
        this.container.add(this.labelCode);

        this.textFieldCode = new JTextField();
        this.textFieldCode.setFont(new Font("Arial", Font.PLAIN, 17));
        this.textFieldCode.setSize(180, 25);
        this.textFieldCode.setLocation(420, 250);
        this.container.add(this.textFieldCode);

        this.buttonSave = new JButton("Adicionar Disciplina");
        this.buttonSave.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonSave.setBackground(Color.BLACK);
        this.buttonSave.setForeground(Color.WHITE);
        this.buttonSave.setSize(190, 45);
        this.buttonSave.setLocation(360, 400);
        this.buttonSave.addActionListener(this);
        this.container.add(this.buttonSave);

        this.setVisible(true);
    }

    public void addDiscipline() throws StudentNotFoundException, DisciplineNotFoundException, InvalidInputException
    {
        int matricula, codigo;
        DefaultListModel<String> pendentes;

        matricula = Integer.parseInt(this.textFieldEnroll.getText());
        codigo = Integer.parseInt(this.textFieldCode.getText());

        pendentes = this.control.informarDisciplinaCursada(matricula, codigo);

        if (pendentes == null)
        {
            JOptionPane.showMessageDialog(this, "Disciplina cursada adicionada", "Adicionada", JOptionPane.INFORMATION_MESSAGE);
        }

        else
        {
            listPane listPendencias = new listPane(pendentes, "Disciplinas Pendêntes");
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        try
        {
            addDiscipline();
        }
        catch (NumberFormatException exception)
        {
            JOptionPane.showMessageDialog(this, "Insira apenas números inteiros", "Caracter Não Inteiro", JOptionPane.WARNING_MESSAGE);
        }
        catch (InvalidInputException exception)
        {
            JOptionPane.showMessageDialog(this, "Disciplina já cursada" , exception.getMessage(), JOptionPane.WARNING_MESSAGE);
        }
        catch (StudentNotFoundException exception)
        {
            JOptionPane.showMessageDialog(this, exception.toString(), "Estudante não Cadastrado", JOptionPane.WARNING_MESSAGE);
        }
        catch (DisciplineNotFoundException exception)
        {
            JOptionPane.showMessageDialog(this, exception.toString(), "Disciplina não Registrada", JOptionPane.WARNING_MESSAGE);
        }
    }
}
