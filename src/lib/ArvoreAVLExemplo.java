package lib;

import java.util.Comparator;

public class ArvoreAVLExemplo <T> extends ArvoreBinariaExemplo<T>
{
    public ArvoreAVLExemplo(Comparator<T> comparator)
    {
        super(comparator);
    }
    /*Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...*/

    /**
     * Método retorna fator de balanceamento do nodo.
     * @param nodo, nó de referência para medir o fator de balanceamento.
     * @return inteiro indicando o fator de balanceamento. (-1 < fb < 1) => balanceado.
     * */
    private int fatorBalanceamento(NoExemplo<T> nodo)
    {
        int fb, alturaEsquerda, alturaDireita;

        alturaEsquerda = this.altura(nodo.getFilhoEsquerda());
        alturaDireita = this.altura(nodo.getFilhoDireita());

        fb = alturaDireita - alturaEsquerda;

        return fb;
    }

    private NoExemplo<T> giroEsquerdo(NoExemplo<T> nodo)
    {
        NoExemplo<T> pivotal;

        pivotal = nodo.getFilhoDireita();

        nodo.setFilhoDireita(pivotal.getFilhoEsquerda());
        pivotal.setFilhoEsquerda(nodo);

        return pivotal;
    }

    private NoExemplo<T> giroDireito(NoExemplo<T> nodo)
    {
        NoExemplo<T> pivotal;

        pivotal = nodo.getFilhoEsquerda();

        nodo.setFilhoEsquerda(pivotal.getFilhoDireita());
        pivotal.setFilhoDireita(nodo);

        return pivotal;
    }

    private NoExemplo<T> giroEsquerdoDireito(NoExemplo<T> nodo)
    {
        nodo.setFilhoEsquerda(this.giroEsquerdo(nodo.getFilhoEsquerda()));

        return this.giroDireito(nodo);
    }

    private NoExemplo<T> giroDireitoEsquerdo(NoExemplo<T> nodo)
    {
        nodo.setFilhoDireita(this.giroDireito(nodo.getFilhoDireita()));

        return this.giroEsquerdo(nodo);
    }

    /**
     * Balanceia árvore/subárvore chamando os métodos de balanceamento.
     * @param nodo, nodo raiz da árvore/subárvore.
     * @return nodo, nodo raiz referênciando a árvore/subárvore balanceada.*/
    private NoExemplo<T> balancear(NoExemplo<T> nodo)
    {
        int fb;

        fb = fatorBalanceamento(nodo);

        if (fb > 1)
        {
            fb = fatorBalanceamento(nodo.getFilhoDireita());

            if (fb > 0) nodo = giroEsquerdo(nodo);

            else nodo = giroDireitoEsquerdo(nodo);
        }

        else if (fb < -1)
        {
            fb = fatorBalanceamento(nodo.getFilhoEsquerda());

            if (fb < 0) nodo = giroDireito(nodo);

            else nodo = giroEsquerdoDireito(nodo);
        }

        return nodo;
    }

    /**
     * Método de adição de nodo com navegação recursiva e balanceamento de subárvores.
     * @param valor, valor a adicionar.
     * @param navi, nodo de navegação.
     * @return nodo da chamada recursiva.*/
    @Override
    protected NoExemplo<T> adicionar(T valor, NoExemplo<T> navi)
    {
        super.adicionar(valor, navi);

        navi = balancear(navi);

        return navi;
    }

    /**
     * Método de remoção de nodo com navegação recursiva e balanceamento de subárvores.
     * @param valor, valor a remover.
     * @param navi, nodo de navegação.
     * @return nodo da chamada recursiva. Se tiver valor a ser excluído retornará null.*/
    @Override
    protected NoExemplo<T> remover(T valor, NoExemplo<T> navi) throws NullPointerException
    {
        /*Navega árvore*/
        if (this.comparador.compare(valor, navi.getValor()) < 0)    /*valor buscado é menor*/
        {
            NoExemplo<T> subArvoreEsquerda;
            subArvoreEsquerda = this.remover(valor, navi.getFilhoEsquerda());
            navi.setFilhoEsquerda(subArvoreEsquerda);   /*Navega a subárvore esquerda*/
        }

        else if (this.comparador.compare(valor, navi.getValor()) > 0)   /*valor buscado é maior*/
        {
            NoExemplo<T> subArvoreDireita;
            subArvoreDireita = this.remover(valor, navi.getFilhoDireita());
            navi.setFilhoDireita(subArvoreDireita); /*Navega a subárvore direita*/
        }

        /*Exclui nodo*/
        else
        {
            /*Sem filhos*/
            if (navi.getFilhoEsquerda() == null && navi.getFilhoDireita() == null) return null;

            /*Só subÁrvore esquerda*/
            else if (navi.getFilhoDireita() == null) return navi.getFilhoEsquerda();

            /*Só subÁrvore direita*/
            else if (navi.getFilhoEsquerda() == null) return navi.getFilhoDireita();


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

        navi = balancear(navi); /*Balanceia nodo após remoção*/

        return navi;    /*Retorna nodo para a chamada recursiva*/
    }
}