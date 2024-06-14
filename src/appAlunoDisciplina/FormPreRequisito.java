package appAlunoDisciplina;

import controlAlunoDisciplina.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormPreRequisito extends JFrame implements ActionListener
{
    private Control control;

    private Container container;
    private JLabel labelTitle;
    private JLabel labelRequisiteCode;
    private JTextField textFieldRequisiteCode;
    private JLabel labelDependentCode;
    private JTextField textFieldDependentCode;
    private JButton buttonSave;

    public FormPreRequisito(Control control)
    {
        this.control = control;

        setTitle("Formulário de Pré-requisitos");
        setBounds(300, 90, 800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        this.container = this.getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Formulário de Pré-requisitos");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        this.labelTitle.setSize(400, 20);
        this.labelTitle.setLocation(220, 30);
        this.container.add(this.labelTitle);

        this.labelRequisiteCode = new JLabel("Req code");
        this.labelRequisiteCode.setFont(new Font("Arial", Font.PLAIN, 18));
        this.labelRequisiteCode.setSize(100, 25);
        this.labelRequisiteCode.setLocation(100, 100);
        this.container.add(this.labelRequisiteCode);

        this.textFieldRequisiteCode = new JTextField();
        this.textFieldRequisiteCode.setFont(new Font("Arial", Font.PLAIN, 18));
        this.textFieldRequisiteCode.setSize(150, 25);
        this.textFieldRequisiteCode.setLocation(200, 100);
        this.container.add(this.textFieldRequisiteCode);

        this.labelDependentCode = new JLabel("Dep code");
        this.labelDependentCode.setFont(new Font("Arial", Font.PLAIN, 18));
        this.labelDependentCode.setSize(100, 25);
        this.labelDependentCode.setLocation(100, 150);
        this.container.add(this.labelDependentCode);

        this.textFieldDependentCode = new JTextField();
        this.textFieldDependentCode.setFont(new Font("Arial", Font.PLAIN, 18));
        this.textFieldDependentCode.setSize(150, 25);
        this.textFieldDependentCode.setLocation(200, 150);
        this.container.add(this.textFieldDependentCode);

        this.buttonSave = new JButton("Salvar");
        this.buttonSave.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonSave.setSize(100, 30);
        this.buttonSave.setLocation(300, 300);
        this.buttonSave.addActionListener(this);
        this.container.add(this.buttonSave);

        this.setVisible(true);
    }

    public void inform() throws NumberFormatException
    {
        int codReq, codDep, answer;

        codReq = Integer.parseInt(this.textFieldRequisiteCode.getText());
        codDep = Integer.parseInt(this.textFieldDependentCode.getText());

        answer = control.informarPreRequisito(codReq, codDep);

        switch (answer)
        {
            case 0:
                JOptionPane.showMessageDialog(this, "Pré-requisito informado", "Informado", JOptionPane.INFORMATION_MESSAGE);
                break;
            case -1:
                JOptionPane.showMessageDialog(this, "Disciplina de Pré-requisito não consta nos registros", "Disciplina não registrada", JOptionPane.WARNING_MESSAGE);
                break;
            case -2:
                JOptionPane.showMessageDialog(this, "Disciplina Dependente não consta nos registros", "Disciplina não registrada", JOptionPane.WARNING_MESSAGE);
                break;
            case -3:
                JOptionPane.showMessageDialog(this, "Disciplina já é pré requisito", "Já é Pré-requisito", JOptionPane.WARNING_MESSAGE);
                break;
            case -4:
                JOptionPane.showMessageDialog(this, "Não insira códigos iguais", "Código igual", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        try
        {
            inform();
        }

        catch (NumberFormatException exception)
        {
            JOptionPane.showMessageDialog(this, "Insira apenas números.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
        }
    }
}
