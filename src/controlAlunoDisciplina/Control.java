package controlAlunoDisciplina;

import entidadesAlunoDisciplina.*;
import lib.ArvoreBinariaExemplo;
import java.util.ArrayList;

public class Control
{
    private final ArvoreBinariaExemplo<Aluno> arvoreAluno;
    private final ArvoreBinariaExemplo<Disciplina> arvoreDisciplina;

    public Control()
    {
        this.arvoreAluno = new ArvoreBinariaExemplo<>(new ComparatorMatricula());
        this.arvoreDisciplina = new ArvoreBinariaExemplo<>(new ComparatorDisciplina());
    }

    public boolean cadastrarAluno(int matricula, String nome)
    {
        Aluno aluno;

        aluno = new Aluno(matricula, nome);

        if (this.arvoreAluno.pesquisar(aluno) == null)
        {
            this.arvoreAluno.adicionar(aluno);
            return true;
        }

        else return false;
    }

    public void cadastrarDisciplina(int codigo, String nome, int cargaHoraria)
    {
        Disciplina disciplina;

        disciplina = new Disciplina(codigo, nome, cargaHoraria);

        this.arvoreDisciplina.adicionar(disciplina);
    }

    public Aluno buscarAlunoNome(Aluno aluno)
    {
        return this.arvoreAluno.pesquisar(aluno, new ComparatorNome());
    }

    public Aluno buscarAlunoMatricula(Aluno aluno)
    {
        return this.arvoreAluno.pesquisar(aluno);
    }

    public Aluno excluirAlunoMatricula(Aluno aluno)
    {
        return this.arvoreAluno.remover(aluno);
    }

    public void informarRequisito(Disciplina requisito, Disciplina disciplina)
    {
        disciplina.getRequisitos().add(requisito);
    }

    public ArrayList<Disciplina> informarDisciplinaCursada(Aluno aluno, Disciplina disciplina)
    {
        ArrayList<Disciplina> faltam;

        faltam = new ArrayList<>();

        for (Disciplina requisito : disciplina.getRequisitos())
        {
            if (aluno.getArvoreDisciplinasCursadas().pesquisar(requisito) == null)
            {
                faltam.add(requisito);
            }
        }

        if (faltam.isEmpty())
        {
            aluno.getArvoreDisciplinasCursadas().adicionar(disciplina);
        }

        return faltam;
    }
}