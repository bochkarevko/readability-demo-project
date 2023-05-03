package com.example;

import java.util.Random;

class Treap {
    private TreapNode root;
    private static final Random random = new Random();

    private static final TreapNode nil = new TreapNode();

    /**
     * Constructor
     **/
    public Treap() {
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

    /* Function to left-rotate a given treap

          n                         R
         / \      Left Rotate      / \
        L   R        ———>         n   Y
           / \                   / \
          X   Y                 L   X
    */
    public static TreapNode rotateLeft(TreapNode node) {
        TreapNode R = node.right;
        TreapNode X = node.right.left;
        // rotate
        R.left = node;
        node.right = X;
        // set a new root
        return R;
    }

    private static TreapNode rotateLeftIfNecessary(TreapNode node) {
        if (node.right != nil && node.right.priority > node.priority) {
            return rotateLeft(node);
        }
        return node;
    }

    /* Function to right-rotate a given treap

            n                        L
           / \     Right Rotate     / \
          L   R        ———>        X   n
         / \                          / \
        X   Y                        Y   R
    */
    public static TreapNode rotateRight(TreapNode node) {
        TreapNode L = node.left;
        TreapNode Y = node.left.right;
        // rotate
        L.right = node;
        node.left = Y;
        // set a new root
        return L;
    }

    private static TreapNode checkAndRotateRight(TreapNode node) {
        if (node.left != nil && node.left.priority > node.priority) {
            return rotateRight(node);
        }
        return node;
    }

    private TreapNode insert(int element, TreapNode node) {
        if (element == node.element) {
            return node;
        }

        if (element < node.element) {
            node.left = insert(element, node.left);
            node = rotateLeftIfNecessary(node);
        } else {
            node.right = insert(element, node.right);
            node = checkAndRotateRight(node);
        }

        return node;
    }

    /**
     * Functions to search for an element
     **/
    public boolean search(int val) {
        return search(root, val);
    }

    private boolean search(TreapNode r, int val) {
        boolean found = false;
        while ((r != nil) && !found) {
            int rval = r.element;
            if (val < rval) r = r.left;
            else if (val > rval) r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    static class TreapNode {
        TreapNode left, right;
        int priority, element;

        /**
         * Constructor
         **/
        public TreapNode() {
            element = 0;
            left = this;
            right = this;
            priority = Integer.MAX_VALUE;
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
            element = ele;
            left = l;
            right = r;
            priority = random.nextInt();
        }
    }
}
