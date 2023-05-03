package com.example.avl;

public class AVLTree {
    private static final Node nil = new Node();

    private Node root;

    public int height() {
        return root.height;
    }

    public void insert(int newKey) {
        if (root == nil) {
            root = new Node(newKey);
        } else {
            insert(root, newKey);
        }
    }

    private Node insert(Node node, int key) {
        if (node.key == key) {
            return node;
        }

        if (node.key > key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }

        return rebalance(node);
    }

    private static Node rebalance(Node node) {
        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1) {
            if (node.right.right.height > node.right.left.height) {
                node = rotateLeft(node);
            } else {
                node = rotateLeftBig(node);
            }
        } else if (balance < -1) {
            if (node.left.left.height > node.left.right.height) {
                node = rotateRight(node);
            } else {
                node = rotateRightBig(node);
            }
        }

        return node;
    }

    private static Node rotateRight(Node node) {
        Node left = node.left;
        Node right = left.right;
        left.right = node;
        node.left = right;
        node.updateHeight();
        left.updateHeight();
        return left;
    }

    private static Node rotateRightBig(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private static Node rotateLeft(Node node) {
        Node right = node.right;
        Node left = right.left;
        right.left = node;
        node.right = left;
        node.updateHeight();
        right.updateHeight();
        return right;
    }

    private static Node rotateLeftBig(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    static public class Node {
        int key, height;
        Node left, right;

        public Node(int k) {
            key = k;
            height = 1;
            left = nil;
            right = nil;
        }

        public Node() {
            key = Integer.MIN_VALUE;
            height = 0;
            left = nil;
            right = nil;
        }

        void updateHeight() {
            height = 1 + Math.max(left.height, right.height);
        }

        int getBalance() {
            return right.height - left.height;
        }
    }
}
