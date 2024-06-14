package controlAlunoDisciplina;

public class StudentNotFoundException extends Exception
{
    @Override
    public String toString() {
        return "Estudante não cadastrado";
    }
}
