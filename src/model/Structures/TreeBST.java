package model.Structures;

import java.io.Serializable;
import java.util.ArrayList;

public class TreeBST<T extends Comparable<T>, K> implements ITreeBST<T, K, Node<T, K>>, Serializable {

    private Node<T, K> root;
    private int size;
    private String treeInfo;
    private int weight;
    private static final long svUID = 1;

    public TreeBST() {
        root = null;
        size = 0;
        treeInfo = "";
        weight = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Node<T, K> search(Node<T, K> root, T key) {
        if(root.key().compareTo(key)==0){
            return root;
        }else{
            if(key.compareTo(root.key())>=0){
                return search(root.right(), key);
            }else{
                return search(root.left(),key);
            }
        }
    }

    public Node<T, K> search(T key) {
        if(root.key().compareTo(key)==0){
            return root;
        }else{
            if(key.compareTo(root.key())>=0){
                return search(root.right(), key);
            }else{
                return search(root.left(),key);
            }
        }
    }

    public ArrayList<K> searchApproximate(String query) {
        ArrayList<K> results = new ArrayList<>();
        return searchApproximate(root, query, results);
    }

    public ArrayList<K> searchApproximate(Node<T, K> node, String query, ArrayList<K> results) {
        if (node == null) {
            return results;
        } else if (node.key().toString().contains(query)) {
            results = searchApproximate(node.left(), query, results);
            results.add(node.value());
            return searchApproximate(node.right(), query, results);
        }
        results = searchApproximate(node.left(), query, results);
        results = searchApproximate(node.right(), query, results);
        return results;
    }

    @Override
    public void insert(T key, K value) {
        Node<T, K> node = new Node<>(key, value);
        Node<T, K> road = null;
        Node<T, K> actual = root;
        while (actual != null) {
            road = actual;
            if (node.key().compareTo(actual.key()) < 0) {
                actual = actual.left();
            } else {
                actual = actual.right();
            }
            node.setParent(road);
        }
        if (road == null) {
            root = node;
        } else if (node.key().compareTo(road.key()) < 0) {
            road.setLeft(node);
        } else {
            road.setRight(node);
        }
        size++;
    }

    @Override
    public void delete(T key) {
        Node<T, K> toDelete = search(root, key);
        System.out.println("Root:: "+ root.value());
        System.out.println("To delete:: "+toDelete.value());
        delete(toDelete);
    }

    private void delete(Node<T, K> toDelete){
        if(toDelete!=null){
            if(toDelete.left() == null && toDelete.right()== null){
                if(toDelete.parent()!=null){
                    Node<T, K>aux = toDelete.parent();
                    System.out.println("Father:: "+aux.value());
                    if(aux.left() != null ){
                        if(aux.left().equals(toDelete)){
                            toDelete.parent().setLeft(null);
                        }else{
                            toDelete.parent().setRight(null);
                        }
                    }else if(aux.right()!=null){
                        toDelete.parent().setRight(null);
                    }
                    toDelete.setParent(null);
                }
            }else{
                if(toDelete.left() == null){
                    Node<T, K> aux = toDelete.right();
                    toDelete.setRight(null);
                    aux.setParent(toDelete.parent());
                }else if(toDelete.right() == null){
                    Node<T, K> aux = toDelete.left();
                    toDelete.setLeft(null);
                    aux.setParent(toDelete.parent());
                }else{
                    Node<T, K> successor = successor(toDelete);
                    toDelete.setValue(successor.value());
                    delete(successor);
                }
            }
        }
    }

    public Node<T, K> min() {
        return min(root);
    }

    @Override
    public Node<T, K> min(Node<T, K> root) {
        while (root.left() != null) {
            root = root.left();
        }
        return root;
    }

    public Node<T, K> max() {
        return max(root);
    }

    @Override
    public Node<T, K> max(Node<T, K> root) {
        while (root.right() != null) {
            root = root.right();
        }
        return root;
    }

    @Override
    public Node<T, K> successor(Node<T, K> current) {
        if(current.right() != null){
            return min(current.right());
        }else if(current.parent().key().compareTo(current.key()) > 0){
            return current.parent();
        }else{
            return current.parent().parent();
        }
    }

    @Override
    public Node<T, K> predecessor(Node<T, K> node) {
        if (node.left() != null) {
            return max(node.left());
        }
        Node<T, K> successor = node.parent();
        while (successor != null && node == successor.left()) {
            node = successor;
            successor = successor.parent();
        }
        return successor;
    }

    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        return inorderRec(root, sb);
    }

    private String inorderRec(Node<T, K> root, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left(), sb);
            sb.append(root.key().toString()).append(" ");
            inorderRec(root.right(), sb);
        }
        return sb.toString();
    }

    public String inOrderValue() {
        StringBuilder sb = new StringBuilder();
        return inOrderRecValue(root, sb);
    }

    private String inOrderRecValue(Node<T, K> root, StringBuilder sb) {
        if (root != null) {
            inOrderRecValue(root.left(), sb);
            sb.append(root.value().toString()).append(" ");
            inOrderRecValue(root.right(), sb);
        }
        return sb.toString();
    }

    public ArrayList<K> toArrayList() {
        ArrayList<K> array = new ArrayList<>();
        return toArrayListRec(root, array);
    }

    private ArrayList<K> toArrayListRec(Node<T, K> root, ArrayList<K> array) {
        if (root != null) {
            toArrayListRec(root.left(), array);
            array.add(root.value());
            toArrayListRec(root.right(), array);
        }
        return array;
    }

    public int getWeight(){
        weight = 0;
        if(root!=null) {
            getWeight(root);
        }

        return weight;
    }

    private void getWeight(Node<T, K> node){
        if(node!=null){
            printInOrder(node.left());
            weight++;
            printInOrder(node.right());
        }
    }

    public String printInOrder(){
        treeInfo = "";
        if(root!=null) {
            printInOrder(root);
        }

        return treeInfo;
    }

    private void printInOrder(Node<T, K> node){
        if(node!=null){
            printInOrder(node.left());
            treeInfo+=node.value().toString()+" ";
            printInOrder(node.right());
        }
    }

    public Node<T, K> root() {
        return root;
    }

    public void setRoot(Node<T, K> root) {
        this.root = root;
    }

    public int size() {
        return size;
    }
}
