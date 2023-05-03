package com.example.avl;

public class AVL {
    private InnerAvl root = null;

    void updateHeight(InnerAvl n) {
        n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
    }

    int height(InnerAvl n) {
        return n == null ? -1 : n.height;
    }

    int getBalance(InnerAvl n) {
        return (n == null) ? 0 : height(n.rightChild) - height(n.leftChild);
    }

    InnerAvl rotateRight(InnerAvl y) {
        InnerAvl x = y.leftChild;
        InnerAvl z = x.rightChild;
        x.rightChild = y;
        y.leftChild = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    InnerAvl rotateLeft(InnerAvl y) {
        InnerAvl x = y.rightChild;
        InnerAvl z = x.leftChild;
        x.leftChild = y;
        y.rightChild = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    InnerAvl fixBalance(InnerAvl z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.rightChild.rightChild) > height(z.rightChild.leftChild)) {
                z = rotateLeft(z);
            } else {
                z.rightChild = rotateRight(z.rightChild);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.leftChild.leftChild) > height(z.leftChild.rightChild))
                z = rotateRight(z);
            else {
                z.leftChild = rotateLeft(z.leftChild);
                z = rotateRight(z);
            }
        }
        return z;
    }

    void add(int newKey) {
        if (root == null) {
            root = new InnerAvl(newKey);
        } else {
            root = add(root, newKey);
        }
    }

    InnerAvl add(InnerAvl n, int k) {
        if (n.element > k) {
            n.leftChild = add(n.leftChild, k);
        } else if (n.element < k) {
            n.rightChild = add(n.rightChild, k);
        } else {
            throw new IllegalArgumentException("duplicate Key!");
        }
        return fixBalance(n);
    }

    static public class InnerAvl {
        int element, height;
        InnerAvl leftChild, rightChild;

        public InnerAvl(int k) {
            element = k;
            height = 1;
            leftChild = null;
            rightChild = null;
        }
    }
}
