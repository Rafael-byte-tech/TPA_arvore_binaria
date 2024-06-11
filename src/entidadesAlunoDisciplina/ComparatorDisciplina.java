package entidadesAlunoDisciplina;

import java.util.Comparator;

public class ComparatorDisciplina implements Comparator<Disciplina>
{
    public int compare(Disciplina d1, Disciplina d2)
    {
        return Integer.compare(d1.getCodigo(), d2.getCodigo());
    }
}
