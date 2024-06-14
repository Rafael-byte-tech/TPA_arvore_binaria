package appAlunoDisciplina;

import controlAlunoDisciplina.StudentNotFoundException;

import javax.swing.*;
import java.awt.*;

public class InfoPane extends JFrame
{
    private Container container;
    private ScrollPane scrollPane;
    private JTextArea textAreaStudentInfo;

    public InfoPane(String infoAluno)
    {
        this.setTitle("Consulta do Aluno");
        this.setBounds(300, 90, 800, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.container = this.getContentPane();
        this.container.setLayout(null);

        this.scrollPane = new ScrollPane();
        this.scrollPane.setSize(800, 500);
        this.scrollPane.setLocation(0, 0);
        this.container.add(this.scrollPane);

        this.textAreaStudentInfo = new JTextArea(infoAluno);
        this.textAreaStudentInfo.setFont(new Font("Arial", Font.PLAIN, 18));
        this.textAreaStudentInfo.setLocation(0, 0);
        this.textAreaStudentInfo.setEditable(false);
        this.textAreaStudentInfo.setFocusable(true);
        this.textAreaStudentInfo.setSelectionColor(Color.ORANGE);
        this.scrollPane.add(this.textAreaStudentInfo);

        this.setVisible(true);
    }
}
