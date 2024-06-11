package entidadesAlunoDisciplina;

import lib.ArvoreBinariaExemplo;

public class Aluno
{
    private int matricula;
    private String nome;
    private final ArvoreBinariaExemplo<Disciplina> arvoreDisciplinasCursadas;

    public Aluno(int matricula, String nome)
    {
        this.matricula = matricula;
        this.nome = nome;
        this.arvoreDisciplinasCursadas = new ArvoreBinariaExemplo<>(new ComparatorDisciplina());
    }


    public int getMatricula()
    {
        return matricula;
    }

    public void setMatricula(int matricula)
    {
        this.matricula = matricula;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public ArvoreBinariaExemplo<Disciplina> getArvoreDisciplinasCursadas()
    {
        return this.arvoreDisciplinasCursadas;
    }
}
