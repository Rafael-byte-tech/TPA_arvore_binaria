/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinariaExemplo<T> implements IArvoreBinaria<T>
{
    protected NoExemplo<T> raiz;
    protected Comparator<T> comparador; 
  
    public ArvoreBinariaExemplo(Comparator<T> comp)
    {
        this.raiz = null;
        this.comparador = comp;
    }
    
    @Override
    public void adicionar(T novoValor)
    {
        if (this.raiz == null) this.raiz = new NoExemplo<T>(novoValor);
        else adicionar(novoValor, this.raiz);
    }

    /**
     *  <p>Forma recursiva do método adicionar. Comparando o nó e o valor dados como parâmetros, navega os descendentes do nó comparando-os
     *  até o local correto para alojar o novo valor.
     *  </p>
     * @param novoValor valor a ser adicionado na árvore.
     * @param no nó a partir do qual a navegação começará.
     * */
    private void adicionar(T novoValor, NoExemplo<T> no)
    {
        /*Verifica se novo valor é menor ou maior que o nó*/
        if (this.comparador.compare(novoValor, no.getValor()) < 0)
        {
            /*Se o nó está sem filho esquerdo, novo valor é adicionado nesse local. Se não, navega para a esquerda.*/
            if (no.getFilhoEsquerda() == null) no.setFilhoEsquerda(new NoExemplo<T>(novoValor));
            else adicionar(novoValor, no.getFilhoEsquerda());
        }
        else if (this.comparador.compare(novoValor, no.getValor()) > 0)
        {
            /*Se o nó está sem filho direito, novo valor é adicionado nesse local. Se não, navega para a direita.*/
            if (no.getFilhoDireita() == null) no.setFilhoDireita(new NoExemplo<T>(novoValor));
            else adicionar(novoValor, no.getFilhoDireita());
        }
    }

    @Override
    public T pesquisar(T valor)
    {
        return pesquisar(valor, this.raiz);
    }

    /**
     * Pesquisa com o comparador indexado. Se o valor de parâmetro for menor que o nó, busca na subárvore esquerda, se maior busca na direita.
     * @param valor, valor a ser pesquisado.
     * @param no, nó a ser verificado.
     * @return se o valor for encontrado, retorna valor tipo T se não retorna null.
     */
    private T pesquisar(T valor, NoExemplo<T> no)
    {
        /*Não mais para onde navegar. Retorna nulo.*/
        if (no == null) return null;

        /*Se valor for maior que nó atual, retorna o valor.*/
        else if (this.comparador.compare(valor, no.getValor()) == 0) return valor;

        /*Se valor for menor que nó atual, navega para a esquerda*/
        else if (this.comparador.compare(valor, no.getValor()) < 0) return pesquisar(valor, no.getFilhoEsquerda());

        /*Por exclusão, se valor for maior que o nó atual, navega para a direita.*/
        else return pesquisar(valor, no.getFilhoDireita());
    }

   @Override
    public T pesquisar(T valor, Comparator comparador)
   {
       return pesquisar(valor, this.raiz, comparador);
   }

   /**
    * Pesquisa sem o comparador indexado. Varre toda a árvore em busca de um nó com valor igual ao passado como parâmetro. No pior caso varre todos os nós.
    * @param valor valor pesquisado
    * @param no nó atualmente sendo verificado pela função
    * @param comparador externo. Seu parâmetro de comparação pode ser diferente do de indexação da árvore.
    * @return valor do tipo T se encontrado. Se não, retorna null.
    * */
    private T pesquisar(T valor, NoExemplo<T> no, Comparator comparador)
    {
        T valorCorrente;

        /*Nó atual não existe ou possui o valor buscado.*/
        if (no == null) return null;
        else if (comparador.compare(valor, no.getValor()) == 0) return valor;

        /*Busca na subárvore esquerda até achar o valor buscado ou não tiver mais nós para navegar*/
        valorCorrente = pesquisar(valor, no.getFilhoEsquerda(), comparador);
        if (valorCorrente != null) return valorCorrente;

        /*Busca na subárvore direita até achar o valor buscado ou não tiver mais nós para navegar.*/
        valorCorrente = pesquisar(valor, no.getFilhoDireita(), comparador);
        return valorCorrente;
    }

    @Override
    public T remover(T valor)
    {
        NoExemplo<T> no;

        no = remover(valor, this.raiz);

        if (no == null) return null;
        else return no.getValor();
    }

    /**
     * Função navega árvore de forma binária e remove nó se ele tiver o valor dado como parâmetro.
     * @param valor valor do nó a ser excluído.
     * @param no nó a partir do qual a função navegará a árvore.
     * @return nó excluído caso seja encontrado. Se não, retorna null.*/
    private NoExemplo<T> remover(T valor, NoExemplo<T> no)
    {
        NoExemplo<T> maiorDaEsquerda;

        /*Não há para onde navegar, retorna nulo.*/
        if (no == null) return null;

        /*Se valor é menor que o valor do nó, navega para a esquerda. Se é maior, navega para a direita.*/
        else if (this.comparador.compare(valor, no.getValor()) < 0) no.setFilhoEsquerda(remover(valor, no.getFilhoEsquerda()));
        else if (this.comparador.compare(valor, no.getValor()) > 0) no.setFilhoDireita(remover(valor, no.getFilhoDireita()));

        /*Nó possui o valor buscado. Inicia remoção.*/
        else
        {
            /*Nó não tem filhos. será apenas desreferenciado.*/
            if (no.getFilhoEsquerda() == null && no.getFilhoDireita() == null) no = null;

            /*Nó só tem filho direito, referencia filho direito. Ou nó só tem filho esquerdo, referencia filho esquerdo.*/
            else if (no.getFilhoEsquerda() == null) no = no.getFilhoDireita();
            else if (no.getFilhoDireita() == null) no = no.getFilhoEsquerda();

            /*Nó tem ambos filhos esquerdo e direito.*/
            else
            {
                /*Busca a maior folha à esquerda do nó a ser removido.*/
                maiorDaEsquerda = no.getFilhoEsquerda();
                while (maiorDaEsquerda.getFilhoDireita() != null) maiorDaEsquerda = maiorDaEsquerda.getFilhoDireita();

                /*Troca os valores entre o nó e a maior folha à esquerda. Por fim exclui a folha.*/
                no.setValor(maiorDaEsquerda.getValor());
                maiorDaEsquerda.setValor(valor);
                no.setFilhoEsquerda(remover(valor, no.getFilhoEsquerda()));
            }
        }
        return no;
    }

    @Override
    public int altura()
    {
        return altura(this.raiz);
    }

    /**
     * Método soma a altura das subárvores recursivamente até chegar à raiz.
     * @param no nó a partir do qual será medida a altura.
     * @return inteiro indicando a altura da árvore. Caso a raiz seja nula, retorna -1.
     * */
    private int altura(NoExemplo<T> no)
    {
        /*Alturas da subárvores esquerda e direita tendo como referência o nó dado como parâmetro.*/
        int alturaEsquerda, alturaDireita;

        if (no == null) return -1;  /*Retorno para árvore sem raiz.*/
        else if (no.getFilhoDireita() == null && no.getFilhoEsquerda() == null) return 0;   /*Retorno para folhas*/

        /*Somatórios das alturas das subárvores esquerda e direita*/
        alturaEsquerda = altura(no.getFilhoEsquerda());
        alturaDireita = altura(no.getFilhoDireita());

        /*Retorno da altura da árvore. Altura da maior subárvore mais a altura da raiz*/
        if (alturaEsquerda > alturaDireita) return alturaEsquerda + 1;
        else return alturaDireita + 1;
    }

    @Override
    public int quantidadeNos()
    {
        return quantidadeNos(this.raiz);
    }

    /**
     * <p>
     *  Para todo nó não nulo a função retornará a soma de todos os nós de suas subárvores esquerda e direita
     *  A função acaba quando a chamada recursiva retorna à raiz, que adiciona si mesma ao somatório
     * </p>
     * */
    private int quantidadeNos(NoExemplo<T> no)
    {
        /*Somatórios da quantidade de descendentes das subárvores esquerda e direita*/
        int esquerda, direita;

        /*Inicialização das variáveis*/
        esquerda = 0;
        direita = 0;

        /*Retorna 0 cajo não haja nó*/
        if (no == null) return 0;

        /*Chamadas recursivas das subárvores esquerda e direita.*/
        esquerda = quantidadeNos(no.getFilhoEsquerda());
        direita = quantidadeNos(no.getFilhoDireita());

        /*retorna a quantidade de nós das subárvores mais o nó de origem.*/
        return esquerda + direita + 1;
    }

    @Override
    public String caminharEmNivel()
    {
        String string;
        ArrayList<NoExemplo<T>> fila;

        fila = new ArrayList<>();
        fila.add(this.raiz);

        string = "[";

        string += caminharEmNivel(fila);

        string += "\n]";

        return string;
    }

    /**
     * Função percorre a árvore em nível(largura). Concatenando uma string com os valores dos nós.
     * @param fila fila de nós que prioriza nós de nível maior e mais a esquerda.
     * @return string contendo os .toString() dos valores dos nós.
     * */
    private String caminharEmNivel(ArrayList<NoExemplo<T>> fila)
    {
        String string;

        string = "";

        if (!fila.isEmpty())
        {
            /*Concatena valor do nó.*/
            string += " \n " + fila.getFirst().getValor().toString();

            /*Verifica se filho esquerdo/direito não é nulo. Caso não, adiciona-o à fila.*/
            if (fila.getFirst().getFilhoEsquerda() != null) fila.add(fila.getFirst().getFilhoEsquerda());
            if (fila.getFirst().getFilhoDireita() != null) fila.add(fila.getFirst().getFilhoDireita());

            fila.removeFirst();                 /*Remove primeiro da fila.*/
            string += caminharEmNivel(fila);    /*Concatena a string dos próximos na fila.*/
        }

        return string;
    }
    
    @Override
    public String caminharEmOrdem()
    {
        String string;

        string = "";

        string += '[';

        string += caminharEmOrdem(this.raiz);

        string += "\n]";

        return string;
    }

    /**
     * Percorre a árvore em ordem concatenando uma string com os valores dos nós.
     * @param no nó por onde começará a navegação.
     * @return string contendo os valores dos nós percorridos.
     */
    private String caminharEmOrdem(NoExemplo<T> no)
    {
        String string;

        string = "";

        if (no != null)
        {
            string += caminharEmOrdem(no.getFilhoEsquerda());   /*Concatena valor dos nós a esquerda.*/
            string += " \n " + no.getValor().toString();        /*Concatena valor do nó passado como parâmetro.*/
            string += caminharEmOrdem(no.getFilhoDireita());    /*Concatena valor dos nós a direita.*/
        }

        return string;
    }
}