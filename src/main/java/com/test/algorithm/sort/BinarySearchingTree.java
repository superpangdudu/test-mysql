package com.test.algorithm.sort;

import sun.reflect.generics.tree.Tree;

/**
 * BinarySearchingTree
 */
public class BinarySearchingTree {

    static class TreeNode {
        int key;
        TreeNode parent = null;
        TreeNode left = null;
        TreeNode right = null;
    }

    //
    private TreeNode root;

    //=========================================================================
    public void insert(int key) {
        TreeNode insertedNode = new TreeNode();
        insertedNode.key = key;

        //
        if (root == null) {
            root = insertedNode;
            return;
        }

        //
        TreeNode node = root;
        TreeNode tmp = null;
        while (node != null) {
            tmp = node;
            if (node.key <= key)
                node = node.right;
            else
                node = node.left;
        }

        //
        if (key <= tmp.key)
            tmp.left = insertedNode;
        else
            tmp.right = insertedNode;
        insertedNode.parent = tmp;
    }

    // TODO
    public void delete(int key) {

    }

    /**
     *
     * @param key
     * @return
     */
    public TreeNode search(int key) {
        return searchTree(root, key);
    }

    private TreeNode searchTree(TreeNode node, int key) {
        // search root node
        if (node == null || key == node.key)
            return node;

        // search leaf nodes
        if (key < node.key)
            return searchTree(node.left, key);
        return searchTree(node.right, key);
    }

    //=========================================================================
    public static void main(String[] args) {
        BinarySearchingTree obj = new BinarySearchingTree();
        obj.insert(10);
        obj.insert(4);
        obj.insert(17);
        obj.insert(1);
        obj.insert(9);
        obj.insert(10);
        obj.insert(21);

        int n = 0;
    }
}
