package appAlunoDisciplina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controlAlunoDisciplina.Control;

public class FormDisciplina extends JFrame implements ActionListener
{
    private  Control control;

    private Container container;
    private JLabel labelTitle;
    private JLabel labelName;
    private JTextField textFieldName;
    private JLabel labelCode;
    private JTextField textFieldCode;
    private JLabel labelWorkload;
    private JTextField textFieldWorkload;
    private JButton buttonSave;

    public FormDisciplina(Control control)
    {
        this.control = control;

        setTitle("Formulário de Disciplinas");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        this.container = this.getContentPane();
        this.container.setLayout(null);

        this.labelTitle = new JLabel("Registro de Disciplina");
        this.labelTitle.setFont(new Font("Arial", Font.PLAIN, 27));
        this.labelTitle.setForeground(Color.BLACK);
        this.labelTitle.setSize(400, 30);
        this.labelTitle.setLocation(330, 100);
        this.container.add(this.labelTitle);

        this.labelName = new JLabel("Nome");
        this.labelName.setFont(new Font("Arial", Font.PLAIN, 24));
        this.labelName.setSize(100, 25);
        this.labelName.setLocation(300, 200);
        this.container.add(labelName);

        this.textFieldName = new JTextField();
        this.textFieldName.setFont(new Font("Arial", Font.PLAIN, 15));
        this.textFieldName.setSize(180, 24);
        this.textFieldName.setLocation(420, 200);
        this.container.add(this.textFieldName);

        this.labelCode = new JLabel("Código");
        this.labelCode.setFont(new Font("Arial", Font.PLAIN, 24));
        this.labelCode.setSize(110, 25);
        this.labelCode.setLocation(290, 250);
        this.container.add(this.labelCode);

        this.textFieldCode = new JTextField();
        this.textFieldCode.setFont(new Font("Arial", Font.PLAIN, 15));
        this.textFieldCode.setSize(180, 25);
        this.textFieldCode.setLocation(420, 250);
        this.container.add(this.textFieldCode);

        this.labelWorkload = new JLabel("Carga Hora");
        this.labelWorkload.setFont(new Font("Arial", Font.PLAIN, 24));
        this.labelWorkload.setSize(140, 25);
        this.labelWorkload.setLocation(260, 300);
        this.container.add(this.labelWorkload);

        this.textFieldWorkload = new JTextField();
        this.textFieldWorkload.setFont(new Font("Arial", Font.PLAIN, 15));
        this.textFieldWorkload.setSize(180, 25);
        this.textFieldWorkload.setLocation(420, 300);
        this.container.add(this.textFieldWorkload);

        this.buttonSave = new JButton("Salvar");
        this.buttonSave.setFont(new Font("Arial", Font.PLAIN, 18));
        this.buttonSave.setBackground(Color.BLACK);
        this.buttonSave.setForeground(Color.WHITE);
        this.buttonSave.setSize(150, 45);
        this.buttonSave.setLocation(360, 400);
        this.buttonSave.addActionListener(this);
        this.container.add(this.buttonSave);

        this.setVisible(true);
    }

    private void register() throws NumberFormatException
    {
        int code, workLoad;
        String name;

        code = Integer.parseInt(this.textFieldCode.getText());
        name = this.textFieldName.getText();
        workLoad = Integer.parseInt(this.textFieldWorkload.getText());

        if (this.control.registrarDisciplina(code, name, workLoad))
        {
            JOptionPane.showMessageDialog(this, "Disciplina registrada", "Salvo", JOptionPane.INFORMATION_MESSAGE);
        }

        else JOptionPane.showMessageDialog(this, "Já há uma disciplina com esse código", "Código ocupado", JOptionPane.WARNING_MESSAGE);
    }

    public void actionPerformed(ActionEvent event)
    {
        try
        {
            register();
        }

        catch (NumberFormatException exception)
        {
            JOptionPane.showMessageDialog(this, "Insira apenas números.", "Número invalido", JOptionPane.WARNING_MESSAGE);
        }
    }
}
