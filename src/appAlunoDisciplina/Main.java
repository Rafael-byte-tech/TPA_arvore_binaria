package appAlunoDisciplina;

import controlAlunoDisciplina.Control;

public class Main
{
    public static void main(String[] args)
    {
        Control control;

        control = new Control();

        new Hub(control);
    }
}
