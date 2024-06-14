package testing;

import lib.ArvoreBinariaExemplo;

import java.util.Comparator;

public class TreeTest
{
    public static void treeTest()
    {
        ArvoreBinariaExemplo<Integer> tree;

        tree = new ArvoreBinariaExemplo<>(new ComparatorInteger());

        tree.adicionar(9);
        tree.adicionar(3);
        tree.adicionar(36);
        tree.adicionar(1);
        tree.adicionar(6);
        tree.adicionar(27);
        tree.adicionar(39);
        tree.adicionar(42);

        System.out.println(tree.caminharEmNivel());

        tree.remover(9);

        System.out.println(tree.caminharEmNivel());

        tree.remover(36);

        System.out.println(tree.caminharEmNivel());

        tree.remover(27);

        System.out.println(tree.caminharEmNivel());
    }
}
