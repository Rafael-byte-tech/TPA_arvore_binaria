package testing;

import app.Aluno;
import lib.ArvoreAVLExemplo;
import lib.ArvoreBinariaExemplo;

public class TreeTest
{
    public static void treeTest()
    {
        ArvoreAVLExemplo<Integer> tree;

        tree = new ArvoreAVLExemplo<Integer>(new ComparatorInteger());

        for(int i=0; i<10; i++)
        {
            tree.adicionar(i);
        }

        System.out.println("AVL:\n" + tree.caminharEmNivel() + "\nHeight = " + tree.altura());

        System.out.println("\nRemoção\n");

        tree.remover(0);
        tree.remover(2);

        System.out.println("AVL:\n" + tree.caminharEmNivel() + "\nHeight = " + tree.altura());
    }
}
