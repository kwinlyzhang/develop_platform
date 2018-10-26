package com.alyer.rbtree;

public class TreeTest {

    public static void main(String [] args) {
        RBTree rbTree = new RBTree();
        int[] ras = {5, 4, 9, 0, 8, 1, 6, 7};
        for (int ra: ras) {
            rbTree.insert(ra);
        }
        int [] treeValue = rbTree.preOrder(5, 5);
        for (int v: treeValue) {
            System.out.println(v);
        }
    }
}
