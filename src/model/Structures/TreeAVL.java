package model.Structures;
//import model.interfaces.IAVL;
import java.io.*;

public class TreeAVL<T extends Comparable<T>, K> extends TreeBST<T, V> implements ITreeAVL<T, V, TreeNode<T,V>>, Serializable {
    private static final long svUID = 1;

    private int rF;
    public static final int MAIN_DIFFERENCE = 1;

    @Override
    public int height(Node<T, K> node) {
        if (node == null) return -1;
        else if (node.left() == null && node.right() == null) return 0;
        else {
            return Math.max(height(node.left()), height(node.right())) + 1;
        }
    }


    @Override
    public void rightRotate(Node<T, K> x) {
        if (x == null) return;
        Node<T,K> pivot = x.left();
        if (pivot == null) return;
        if (x.parent() == null) {
            setRoot(pivot);
            pivot.setParent(null);
        }
        else if (x.key().compareTo(x.parent().key()) >= 0) x.parent().setRight(pivot);
        else x.parent().setLeft(pivot);
        pivot.setParent(x.parent());
        x.setParent(pivot);
        x.setLeft(pivot.right());
        pivot.setRight(x);
    }

    @Override
    public void leftRotate(Node<T, K> x) {
        if (x == null) return;
        Node<T, K> pivot = x.right();
        if (pivot == null) return;
        if (x.parent() == null) {
            setRoot(pivot);
            pivot.setParent(null);
        } else if (x.key().compareTo(x.parent().key()) >= 0) x.parent().setRight(pivot);
        else x.parent().setLeft(pivot);
        pivot.setParent(x.parent());
        x.setParent(pivot);
        x.setRight(pivot.left());
        pivot.setLeft(x);
    }

    @Override
    public void insert(T key, K value) {
        super.insert(key, value);
        Node<T, K> inserted = search(root(), key);
        while (inserted != null) {
            balanceAgain(inserted);
            inserted = inserted.parent();
        }
    }

    @Override
    public void delete(T key) {
        Node<T, K> deleted = search(root(), key);
        super.delete(key);
        while (deleted != null) {
            balanceAgain(deleted);
            deleted = deleted.parent();
        }
    }

    public boolean isBalanced(){
        return isBalanced(super.root());
    }

    public boolean isBalanced(Node<T, K> root){
        if(root.right() != null && root.left() !=null){
            rF = height(root.right()) - height(root.left());
            return height(root.right()) - height(root.left()) <= MAIN_DIFFERENCE && height(root.right()) - height(root.left()) > -2;
        }else if(root.right()!=null){
            rF = height(root.right());
            return height(root.right()) <=MAIN_DIFFERENCE && height(root.right())> -2;
        } else if(root.left()!=null){
            rF = -height(root.left());
            return -height(root.left()) <=MAIN_DIFFERENCE && -height(root.left())> -2;
        }else{
            return true;
        }
    }


    public void balanceAgain(Node<T,K> root){
        if(!isBalanced(root)){
            if(rF >1){
                if (balance(root.right()) < 0) {
                    rightRotate(root.right());
                }else{
                    leftRotate(root);
                }
            }else{
                if (balance(root.left()) > 0) {
                    leftRotate(root.left());
                }else{
                    rightRotate(root);
                }
            }
            balance(root);
        }
    }

    public int getrF() {
        return rF;
    }

    @Override
    public int balance(Node<T,K> node){
        return height(node.right())-height(node.left());
    }
}

