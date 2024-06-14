package controlAlunoDisciplina;

import entidadesAlunoDisciplina.*;
import lib.ArvoreBinariaExemplo;

import javax.swing.*;
import java.util.ArrayList;

public class Control
{
    private final ArvoreBinariaExemplo<Aluno> arvoreAluno;
    private final ArvoreBinariaExemplo<Disciplina> arvoreDisciplina;

    public Control()
    {
        this.arvoreAluno = new ArvoreBinariaExemplo<>(new ComparatorMatricula());
        this.arvoreDisciplina = new ArvoreBinariaExemplo<Disciplina>(new ComparatorDisciplina());
    }

    /*OK*/
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

    /*OK*/
    public boolean registrarDisciplina(int codigo, String nome, int cargaHoraria)
    {
        Disciplina disciplina;

        disciplina = new Disciplina(codigo, nome, cargaHoraria);

        if (this.arvoreDisciplina.pesquisar(disciplina) == null)
        {
            this.arvoreDisciplina.adicionar(disciplina);
            return true;
        }

        else return false;
    }

    public int informarPreRequisito(int codReq, int codDep) {
        Disciplina requisite, dependent;

        if (codReq == codDep) return -4;

        requisite = new Disciplina(codReq, "\0", 33);
        dependent = new Disciplina(codDep, "\0", 666);

        requisite = this.arvoreDisciplina.pesquisar(requisite);
        dependent = this.arvoreDisciplina.pesquisar(dependent);

        if (requisite != null && dependent != null)
        {
            if (dependent.getRequisitos().contains(requisite))
            {
                return -3;
            }

            dependent.getRequisitos().add(requisite);

            return 0;
        }

        else if (requisite == null)
        {
            return -1;
        }

        else return -2;
    }

    public ArrayList<Disciplina> buscarPendencias(Aluno aluno, Disciplina disciplina)
    {
        ArrayList<Disciplina> faltam;

        faltam = new ArrayList<Disciplina>();

        for (Disciplina requisito : disciplina.getRequisitos())
        {
            if (aluno.getArvoreDisciplinasCursadas().pesquisar(requisito) == null)
            {
                faltam.add(requisito);
            }
        }

        return faltam;
    }

    public DefaultListModel<String> informarDisciplinaCursada(int matricula, int codigo) throws StudentNotFoundException, DisciplineNotFoundException, InvalidInputException
    {
        Aluno aluno;
        Disciplina disciplina;
        ArrayList<Disciplina> faltam;
        DefaultListModel<String> pendentes;

        aluno = this.arvoreAluno.pesquisar(new Aluno(matricula, "\0"));

        if (aluno == null) throw new StudentNotFoundException();

        disciplina = this.arvoreDisciplina.pesquisar(new Disciplina(codigo, "\0", 0));

        if (disciplina == null) throw new DisciplineNotFoundException();

        if (aluno.getArvoreDisciplinasCursadas().pesquisar(disciplina) != null) throw new InvalidInputException();

        faltam = buscarPendencias(aluno, disciplina);

        if (faltam.isEmpty())
        {
            aluno.getArvoreDisciplinasCursadas().adicionar(disciplina);
            return null;
        }

        pendentes = new DefaultListModel<String>();

        for (Disciplina falta : faltam)
        {
            pendentes.addElement(falta.toString());
        }

        return pendentes;
    }

    public String buscarAlunoNome(String nome) throws StudentNotFoundException
    {
        Aluno aluno;

        aluno = this.arvoreAluno.pesquisar(new Aluno(-1, nome), new ComparatorNome());

        if (aluno == null) throw new StudentNotFoundException();

        return aluno.toString();
    }

    public String buscarAlunoMatricula(int matricula) throws StudentNotFoundException
    {
        Aluno aluno;

        aluno = this.arvoreAluno.pesquisar(new Aluno(matricula, "\0"));

        if (aluno == null) throw new StudentNotFoundException();

        return aluno.toString();
    }

    public void excluirAlunoMatricula(int matricula) throws StudentNotFoundException
    {
        Aluno removido;

        removido = this.arvoreAluno.remover(new Aluno(matricula, "\0"));

        if (removido == null) throw new StudentNotFoundException();
    }
}