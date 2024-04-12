package test;

import lib.ArvoreBinariaExemplo;

public class TestBinTree
{
    public static void test()
    {
        ArvoreBinariaExemplo<Integer> tree;
        IntComparator comparator;
        Integer integer, search;
        String treeScan;

        comparator = new IntComparator();
        tree = new ArvoreBinariaExemplo<>(comparator);
        integer = 4;

        /*Teste de adicionar*/
        tree.adicionar(14);
        tree.adicionar(4);
        tree.adicionar(18);
        tree.adicionar(3);
        tree.adicionar(9);
        tree.adicionar(20);
        tree.adicionar(12);
        tree.adicionar(10);
        tree.adicionar(13);

        /*Teste de altura*/
        System.out.printf("Altura: %d\n", tree.altura());

        /*Teste de quantidade de nós*/
        System.out.printf("Número de elementos: %d\n", tree.quantidadeNos());

        /*Teste de caminhamento em ordem*/
        System.out.print("\nCaminhamento em ordem\n");
        treeScan = tree.caminharEmOrdem();
        System.out.println(treeScan);

        /*Teste de caminhamento em nível*/
        System.out.print("\nCaminhamento em nível\n");
        treeScan = tree.caminharEmNivel();
        System.out.println(treeScan);

        /*Teste de pesquisar com comparador externo*/
        System.out.println("\nPesquisa normal\n");
        search = tree.pesquisar(integer, comparator);
        if (search == null) System.out.printf("Valor %d não encontrado.\n", integer);
        else if (search.equals(integer)) System.out.printf("Valor %d encontrado.\n", search);

        /*Teste de pesquisar com comparador da árvore*/
        System.out.println("\nPesquisa binária\n");
        search = tree.pesquisar(integer);
        if (search == null) System.out.printf("Valor %d não encontrado.\n", integer);
        else if (search.equals(integer)) System.out.printf("Valor %d encontrado.\n", search);

        /*Teste de remoção de nó*/
        System.out.printf("\nRemovendo nó %d\n", integer);
        search = tree.remover(integer);
        search = tree.pesquisar(integer, comparator);
        if (search == null) System.out.printf("Valor %d não encontrado.\n", integer);
        else if (search.equals(integer)) System.out.printf("Valor %d encontrado.\n", search);
    }
}