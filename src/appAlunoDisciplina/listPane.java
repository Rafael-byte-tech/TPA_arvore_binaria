package appAlunoDisciplina;

import javax.swing.*;
import java.awt.*;

public class listPane extends JFrame
{
    private JList<String> list;
    private JScrollPane scrollPane;

    public listPane(DefaultListModel<String> model, String title)
    {
        this.setTitle(title);
        this.setBounds(300, 90, 800, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.list = new JList<String>(model);
        this.list.setFont(new Font("Arial", Font.PLAIN, 18));

        this.scrollPane = new JScrollPane(this.list);
        this.scrollPane.setSize(800, 300);
        this.scrollPane.setLocation(0, 200);
        this.add(this.scrollPane);
        setVisible(true);
    }
}
