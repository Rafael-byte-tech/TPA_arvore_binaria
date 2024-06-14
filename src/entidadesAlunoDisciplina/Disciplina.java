package entidadesAlunoDisciplina;

import java.util.ArrayList;

public class Disciplina
{
    private final int     codigo;
    private final String  nome;
    private final int     cargaHoraria;
    private final ArrayList<Disciplina> requisitos;

    public Disciplina(int codigo, String nome, int cargaHoraria)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.requisitos = new ArrayList<Disciplina>();
    }

    public int getCodigo()
    {
        return this.codigo;
    }

    public String getNome()
    {
        return this.nome;
    }

    public int getCargaHoraria()
    {
        return this.cargaHoraria;
    }

    public ArrayList<Disciplina> getRequisitos()
    {
        return this.requisitos;
    }

    @Override
    public String toString()
    {
        return "CODIGO: " + getCodigo() + "     \n" + "NOME:    " + getNome() + "\n\n";
    }
}
