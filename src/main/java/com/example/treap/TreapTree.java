package com.example.treap;

import java.util.Random;

class TreapTree {
    private TreapNode root;
    private static final Random random = new Random();

    private static final TreapNode nil = new TreapNode();

    /**
     * Constructor
     **/
    public TreapTree() {
        root = nil;
    }

    /**
     * Function to check if tree is empty
     **/
    public boolean isEmpty() {
        return root == nil;
    }

    /**
     * Make the tree logically empty
     **/
    public void makeEmpty() {
        root = nil;
    }

    /**
     * Functions to insert data
     **/
    public void insert(int X) {
        if (root == nil) {
            root = new TreapNode(X);
        } else {
            root = insert(X, root);
        }
    }

    private TreapNode insert(int X, TreapNode node) {
        if (X < node.elementValue) {
            node.leftChild = insert(X, node.leftChild);
            if (node.leftChild.priorityValue < node.priorityValue) {
                TreapNode L = node.leftChild;
                node.leftChild = L.rightChild;
                L.rightChild = node;
                return L;
            }
        } else if (X > node.elementValue) {
            node.rightChild = insert(X, node.rightChild);
            if (node.rightChild.priorityValue < node.priorityValue) {
                TreapNode R = node.rightChild;
                node.rightChild = R.leftChild;
                R.leftChild = node;
                return R;
            }
        }

        return node;
    }

    /**
     * Functions to count number of nodes
     **/
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(TreapNode r) {
        if (r == nil)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.leftChild);
            l += countNodes(r.rightChild);
            return l;
        }
    }

    static class TreapNode {
        TreapNode leftChild, rightChild;
        int priorityValue, elementValue;

        /**
         * Constructor
         **/
        public TreapNode() {
            elementValue = 0;
            leftChild = this;
            rightChild = this;
            priorityValue = Integer.MAX_VALUE;
        }

        /**
         * Constructor
         **/
        public TreapNode(int ele) {
            this(ele, null, null);
        }

        /**
         * Constructor
         **/
        public TreapNode(int ele, TreapNode l, TreapNode r) {
            elementValue = ele;
            leftChild = l;
            rightChild = r;
            priorityValue = random.nextInt();
        }
    }
}
