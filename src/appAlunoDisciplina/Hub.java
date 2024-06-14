package appAlunoDisciplina;

import controlAlunoDisciplina.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hub extends JFrame implements ActionListener
{
    private Control control;

    private Container container;
    private JLabel labelTitle;
    private JButton buttonFormAluno;
    private JButton buttonFormDisciplina;
    private JButton buttonFormPreRequisito;
    private JButton buttonFormDisciplinaCursada;
    private JButton buttonFormConsultaAluno;

    public Hub(Control control)
    {
        this.control = control;

        this.setTitle("Painel de Controle");
        this.setBounds(300, 90, 900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.container = getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Sistema de Disciplinar Aluno");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 27));
        this.labelTitle.setForeground(Color.BLACK);
        this.labelTitle.setSize(500, 60);
        this.labelTitle.setLocation(270, 30);
        this.container.add(this.labelTitle);

        this.buttonFormAluno = new JButton("Cadastrar Aluno");
        this.buttonFormAluno.setFont(new Font("Arial", Font.PLAIN, 20));
        this.buttonFormAluno.setBackground(Color.BLACK);
        this.buttonFormAluno.setForeground(Color.WHITE);
        this.buttonFormAluno.setSize(300, 60);
        this.buttonFormAluno.setLocation(300, 150);
        this.buttonFormAluno.addActionListener(this);
        this.container.add(this.buttonFormAluno);

        this.buttonFormDisciplina = new JButton("Registrar Disciplina");
        this.buttonFormDisciplina.setFont(new Font("Arial", Font.PLAIN, 20));
        this.buttonFormDisciplina.setBackground(Color.black);
        this.buttonFormDisciplina.setForeground(Color.white);
        this.buttonFormDisciplina.setSize(300, 60);
        this.buttonFormDisciplina.setLocation(300, 210);
        this.buttonFormDisciplina.addActionListener(this);
        this.container.add(this.buttonFormDisciplina);

        this.buttonFormPreRequisito = new JButton("Informar Pré-requisito");
        this.buttonFormPreRequisito.setFont(new Font("Arial", Font.PLAIN, 20));
        this.buttonFormPreRequisito.setBackground(Color.black);
        this.buttonFormPreRequisito.setForeground(Color.white);
        this.buttonFormPreRequisito.setSize(300, 60);
        this.buttonFormPreRequisito.setLocation(300, 270);
        this.buttonFormPreRequisito.addActionListener(this);
        this.container.add(this.buttonFormPreRequisito);

        this.buttonFormDisciplinaCursada = new JButton("Informar Disciplina Cursada");
        this.buttonFormDisciplinaCursada.setFont(new Font("Arial", Font.PLAIN, 20));
        this.buttonFormDisciplinaCursada.setBackground(Color.black);
        this.buttonFormDisciplinaCursada.setForeground(Color.white);
        this.buttonFormDisciplinaCursada.setSize(300, 60);
        this.buttonFormDisciplinaCursada.setLocation(300, 330);
        this.buttonFormDisciplinaCursada.addActionListener(this);
        this.container.add(this.buttonFormDisciplinaCursada);

        this.buttonFormConsultaAluno = new JButton("Consultar Aluno");
        this.buttonFormConsultaAluno.setFont(new Font("Arial", Font.PLAIN, 20));
        this.buttonFormConsultaAluno.setBackground(Color.black);
        this.buttonFormConsultaAluno.setForeground(Color.white);
        this.buttonFormConsultaAluno.setSize(300, 60);
        this.buttonFormConsultaAluno.setLocation(300, 390);
        this.buttonFormConsultaAluno.addActionListener(this);
        this.container.add(this.buttonFormConsultaAluno);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        FormAluno formAluno;
        FormDisciplina formDisciplina;
        FormPreRequisito formPreRequisito;
        FormDisciplinaCursada formDisciplinaCursada;
        FormConsultaAluno formConsultaAluno;

        if(event.getSource() == this.buttonFormAluno)
        {
            formAluno = new FormAluno(this.control);
        }
        else if (event.getSource() == this.buttonFormDisciplina)
        {
            formDisciplina = new FormDisciplina(this.control);
        }
        else if (event.getSource() == this.buttonFormPreRequisito)
        {
             formPreRequisito = new FormPreRequisito(this.control);
        }
        else if (event.getSource() == this.buttonFormDisciplinaCursada)
        {
            formDisciplinaCursada = new FormDisciplinaCursada(this.control);
        }
        else
        {
            formConsultaAluno = new FormConsultaAluno(this.control);
        }
    }
}
