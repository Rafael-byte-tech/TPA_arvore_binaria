package controlAlunoDisciplina;

public class DisciplineNotFoundException extends Exception
{
    @Override
    public String toString() {
        return "Disciplina não registrada";
    }
}
