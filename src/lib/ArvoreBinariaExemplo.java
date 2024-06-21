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
        if (this.raiz == null) this.raiz = new NoExemplo<>(novoValor);
        else this.raiz = adicionar(novoValor, this.raiz);
    }

    /**
     *  <p>Forma recursiva do método adicionar. Comparando o nó e o valor dados como parâmetros, navega os descendentes do nó comparando-os
     *  até o local correto para alojar o novo valor.
     *  </p>
     * @param valor, valor a ser adicionado na árvore.
     * @param navi nó daonde a navegação começará.
     * */
    protected NoExemplo<T> adicionar(T valor, NoExemplo<T> navi)
    {
        NoExemplo<T> esquerdo;
        NoExemplo<T> direito;

        /*Verifica se novo valor é menor ou maior que o nó*/
        if (this.comparador.compare(valor, navi.getValor()) < 0)
        {
            /*Se o nó está sem filho esquerdo, novo valor é adicionado nesse local. Se não, navega para a esquerda.*/
            if (navi.getFilhoEsquerda() == null) navi.setFilhoEsquerda(new NoExemplo<>(valor));
            else
            {
                esquerdo = adicionar(valor, navi.getFilhoEsquerda());
                navi.setFilhoEsquerda(esquerdo);
            }
        }

        else if (this.comparador.compare(valor, navi.getValor()) > 0)
        {
            /*Se o nó está sem filho direito, novo valor é adicionado nesse local. Se não, navega para a direita.*/
            if (navi.getFilhoDireita() == null) navi.setFilhoDireita(new NoExemplo<>(valor));
            else
            {
                direito = adicionar(valor, navi.getFilhoDireita());
                navi.setFilhoDireita(direito);
            }
        }

        return navi;
    }

    @Override
    public T pesquisar(T valor)
    {
        return pesquisar(valor, this.raiz);
    }

    /**
     * Pesquisa recursiva com o comparador indexado. Se o valor de parâmetro for menor que o nó, busca na subárvore esquerda, se maior busca na direita.
     * @param valor, valor a ser pesquisado.
     * @param navi, nodo de navegação.
     * @return se o valor for encontrado, retorna valor tipo T, se não retorna null.
     */
    private T pesquisar(T valor, NoExemplo<T> navi)
    {
        /*Não mais para onde navegar. Retorna nulo.*/
        if (navi == null) return null;

        /*Se valor for igual ao nó atual, retorna o valor.*/
        else if (this.comparador.compare(valor, navi.getValor()) == 0) return navi.getValor();

        /*Se valor for menor que nó atual, navega para a esquerda*/
        else if (this.comparador.compare(valor, navi.getValor()) < 0) return pesquisar(valor, navi.getFilhoEsquerda());

        /*Por exclusão, se valor for maior que o nó atual, navega para a direita.*/
        else return pesquisar(valor, navi.getFilhoDireita());
    }

   @Override
    public T pesquisar(T valor, Comparator comparador)
   {
       return pesquisar(valor, this.raiz, comparador);
   }

   /**
    * Pesquisa recursiva sem o comparador indexado. Varre toda a árvore em busca de um nó com valor igual ao passado como parâmetro. No pior caso varre todos os nós.
    * @param valor valor pesquisado.
    * @param navi nodo de navegação.
    * @param comparador externo. Seu parâmetro de comparação pode ser diferente do de indexação da árvore.
    * @return valor do tipo T se encontrado. Se não, retorna null.
    * */
    private T pesquisar(T valor, NoExemplo<T> navi, Comparator comparador)
    {
        T valorCorrente;

        /*Nó atual não existe ou possui o valor buscado.*/
        if (navi == null) return null;
        else if (comparador.compare(valor, navi.getValor()) == 0) return navi.getValor();

        /*Busca na subárvore esquerda até achar o valor buscado ou não tiver mais nós para navegar*/
        valorCorrente = pesquisar(valor, navi.getFilhoEsquerda(), comparador);
        if (valorCorrente != null) return valorCorrente;

        /*Busca na subárvore direita até achar o valor buscado ou não tiver mais nós para navegar.*/
        valorCorrente = pesquisar(valor, navi.getFilhoDireita(), comparador);
        return valorCorrente;
    }

    /**
     * Remove nodo com o valor buscado.
     * @param valor valor buscado.
     * @return valor do tipo T se encontrado. Se não, retorna null.
     * */
    @Override
    public T remover(T valor)
    {
        try
        {
            this.raiz = this.remover(valor, this.raiz);
        }

        catch (NullPointerException exception)
        {
            return null;
        }

        return valor;
    }

    /**
     * Método de remoção de nodo com navegação recursiva.
     * @param valor, valor a remover.
     * @param navi, nodo de navegação.
     * @return nodo da chamada recursiva. Se tiver valor a ser excluído retornará null.*/
    protected NoExemplo<T> remover(T valor, NoExemplo<T> navi) throws NullPointerException
    {
        /*Navega árvore*/
        if (this.comparador.compare(valor, navi.getValor()) < 0)
        {
            NoExemplo<T> subArvoreEsquerda;
            subArvoreEsquerda = this.remover(valor, navi.getFilhoEsquerda());
            navi.setFilhoEsquerda(subArvoreEsquerda);   /*Navega subárvore esquerda*/
        }

        else if (this.comparador.compare(valor, navi.getValor()) > 0)
        {
            NoExemplo<T> subArvoreDireita;
            subArvoreDireita = this.remover(valor, navi.getFilhoDireita());
            navi.setFilhoDireita(subArvoreDireita); /*Navega subárvore direita*/
        }

        /*Exclui nodo buscado*/
        else
        {
            /*Sem filhos*/
            if (navi.getFilhoEsquerda() == null && navi.getFilhoDireita() == null) navi = null;

            /*Só subÁrvore esquerda*/
            else if (navi.getFilhoDireita() == null) navi = navi.getFilhoEsquerda();


            /*Só subÁrvore direita*/
            else if (navi.getFilhoEsquerda() == null) navi = navi.getFilhoDireita();

            /*SubÁrvores esquerda e direita*/
            else
            {
                /*Buscar maior nodo da subÁrvore esquerda*/
                NoExemplo<T> substitute;
                T temp;

                substitute = navi.getFilhoEsquerda();

                while (substitute.getFilhoDireita() != null) substitute = substitute.getFilhoDireita();


                /*Substituir valor a remover por valor do substituto*/
                temp = navi.getValor();
                navi.setValor(substitute.getValor());
                substitute.setValor(temp);

                /*Remover valor*/
                NoExemplo<T> subArvoreEsquerda;
                subArvoreEsquerda = remover(valor, navi.getFilhoEsquerda());
                navi.setFilhoEsquerda(subArvoreEsquerda);
            }
        }

        return navi;    /*Retorna navegador para a chamada recursiva*/
    }

    @Override
    public int altura()
    {
        return altura(this.raiz);
    }

    /**
     * Método soma a altura das subárvores recursivamente até chegar à raiz.
     * @param nodo nó a partir do qual será medida a altura.
     * @return inteiro indicando a altura da árvore. Caso a raiz seja nula, retorna -1.
     * */
    protected int altura(NoExemplo<T> nodo)
    {
        /*Alturas da subárvores esquerda e direita tendo como referência o nó dado como parâmetro.*/
        int alturaEsquerda, alturaDireita;

        if (nodo == null) return -1;  /*Retorno para árvore sem raiz.*/
        else if (nodo.getFilhoDireita() == null && nodo.getFilhoEsquerda() == null) return 0;   /*Retorno para folhas*/

        /*Somatórios das alturas das subárvores esquerda e direita*/
        alturaEsquerda = altura(nodo.getFilhoEsquerda());
        alturaDireita = altura(nodo.getFilhoDireita());

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
    private int quantidadeNos(NoExemplo<T> nodo)
    {
        /*Somatórios da quantidade de descendentes das subárvores esquerda e direita*/
        int esquerda, direita;

        /*Retorna 0 caso não haja nó*/
        if (nodo == null) return 0;

        /*Chamadas recursivas das subárvores esquerda e direita.*/
        esquerda = quantidadeNos(nodo.getFilhoEsquerda());
        direita = quantidadeNos(nodo.getFilhoDireita());

        /*Retorna a quantidade de nós das subárvores mais o nó de origem.*/
        return esquerda + direita + 1;
    }

    @Override
    public String caminharEmNivel()
    {
        String string;
        ArrayList<NoExemplo<T>> fila;

        fila = new ArrayList<>();
        if (this.raiz != null ) fila.add(this.raiz);

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
     * @param navi, nó por onde começará a navegação.
     * @return string contendo os valores dos nós percorridos.
     */
    private String caminharEmOrdem(NoExemplo<T> navi)
    {
        String string;

        string = "";

        if (navi != null)
        {
            string += caminharEmOrdem(navi.getFilhoEsquerda());   /*Concatena valor dos nós a esquerda.*/
            string += " \n " + navi.getValor().toString();        /*Concatena valor do nó passado como parâmetro.*/
            string += caminharEmOrdem(navi.getFilhoDireita());    /*Concatena valor dos nós a direita.*/
        }

        return string;
    }
}