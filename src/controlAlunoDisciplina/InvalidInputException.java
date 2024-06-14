package controlAlunoDisciplina;

public class InvalidInputException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Entrada inválida";
    }
}
