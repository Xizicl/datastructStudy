package test;

import d_Map.BSTMap;
import d_Map.Map;
import i_AVLTree.AVLMap;
import j_RBTree.RBTree;
import k_HashTable.HashTable;

import java.util.ArrayList;

public class Main {

    private static double testMap(Map<String, Integer> map, String filename) {

        long startTime = System.nanoTime();

//        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            for (String word : words) {
                map.contains(word);
            }

//            System.out.println("Total different words: " + map.getSize());
//            System.out.println("Frequency of PRIDE: " + map.get("pride"));
//            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "src/test/pride-and-prejudice.txt";

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + " s");


//        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
//        double time2 = testMap(linkedListMap, filename);
//        System.out.println("Linked List Map: " + time2 + " s");

//        System.out.println();

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        double time3 = testMap(avlMap, filename);
        System.out.println("AVL Map: " + time3 + " s");



        HashTable<String, Integer> hashTable = new HashTable<>();
        double time4 = testMap(hashTable, filename);
        System.out.println("hashTable: " + time4 + " s");


        RBTree<String, Integer> rbTree = new RBTree<>();
        double time5 = testMap(rbTree, filename);
        System.out.println("rbTree: " + time5 + " s");
    }


}
