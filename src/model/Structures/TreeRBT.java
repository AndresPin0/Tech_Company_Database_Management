package model.Structures;
import java.io.*;
import java.util.*;

public class TreeRBT<K extends Comparable<K>, V> implements Serializable {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private int size;

    private class Node implements Serializable {

        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        public boolean isRed() {
            return color == RED;
        }

        public boolean isBlack() {
            return color == BLACK;
        }

    }

    private Node root;

    public TreeRBT() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null)
        {
            return BLACK;
        }
        return node.color;
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node insert(Node node, K key, V value) {

        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else
        {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left))
        {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left))
        {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right))
        {
            flipColors(node);
        }
        return node;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        if (node == null)
            return null;
        else
            return node.value;
    }

    private Node getNode(Node node, K key) {

        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }

    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }

        node.value = newValue;
    }

    public V minimumV() {
        return minimum(root).value;
    }

    public Node minimum() {
        return minimum(root);
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    public Node search(Node root, K key) {
        if (root == null || root.key.equals(key)) {
            return root;
        } else if (key.compareTo(root.key) < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public V search(K key) {
        if (root == null || root.key.equals(key)) {
            assert root != null;
            return root.value;
        } else if (key.compareTo(root.key) < 0) {
            return search(root.left, key).value;
        } else {
            return search(root.right, key).value;
        }
    }

    public ArrayList<V> searchApproximate(String query) {
        ArrayList<V> results = new ArrayList<>();
        return searchApproximate(root, query, results);
    }

    public ArrayList<V> searchApproximate(Node node, String query, ArrayList<V> results) {
        if (node == null) {
            return results;
        } else if (node.key.toString().contains(query)) {
            results = searchApproximate(node.left, query, results);
            results.add(node.value);
            return searchApproximate(node.right, query, results);
        }
        results = searchApproximate(node.left, query, results);
        results = searchApproximate(node.right, query, results);
        return results;
    }

    public Node getRoot() {
        return root;
    }
}
