package model.Structures;

import java.io.*;

public class linkList<T> implements Serializable {
    private static final long sVUID = 4L;
    private LinkedListNode<T> first;
    private LinkedListNode<T> last;
    private int size;

    public linkList() {
        first = null;
        size = 0;
        last = null;
    }

    public LinkedListNode<T> getFirst() {
        return first;
    }

    public LinkedListNode<T> getLast() {
        return last;
    }

    public boolean isEmpty() {
        if(first == null)
            return true;
        else
            return false;
    }

    public int size() {
        return size;
    }

    public void add(T e) {

        if (first == null) {
            first = new LinkedListNode<T>(e);
            last = first;
        }else
            add(e, last, 0);
        size += 1;
    }

    private void add(T e, LinkedListNode<T> tmp, int valid) {

        if(valid == 0){
            tmp.setNext(new LinkedListNode<T>(e));
            tmp.getNext().setPrev(tmp);
            last = tmp.getNext();
            valid += 1;
        }

        if(tmp.getPrev() != null && valid == 1){
            add(e, tmp.getPrev(), valid);

        }else{
            first = tmp;
        }
    }

    public int indexOf(T e){
        return indexOf(e, first, 0);
    }

    private int indexOf(T e, LinkedListNode<T> tmp, int count){
        if(e.equals(tmp.getItem()))
            return count;
        else
            return indexOf(e, tmp.getNext(), count + 1);
    }

    public T get(int i){

        return get(i, first);

    }

    private T get(int i, LinkedListNode<T> temp){

        if(i == 0){
            return temp.getItem();

        }else{
            return get(i - 1, temp.getNext());

        }

    }

    public LinkedListNode<T> getNode(int i){

        return getNode(i, first);

    }

    private LinkedListNode<T> getNode(int i, LinkedListNode<T> tmp){
        if(i == 0)
            return tmp;
        else
            return getNode(i - 1, tmp.getNext());
    }

    public void remove(int i){
        if(i == 0){
            first = first.getNext();
        }else{
            getNode(i).getPrev().setNext(getNode(i).getNext());
            getNode(i).getNext().setPrev(getNode(i).getPrev());
        }
        size -= 1;
    }
}

class LinkedListNode<T> implements Serializable{

    private static final long sVUID = 5L;
    private T item;
    private LinkedListNode<T> next;
    private LinkedListNode<T> prev;
    private LinkedListNode<T> root;

    public LinkedListNode(T element) {
        this.item = element;
        this.next = null;
        this.prev = null;
    }

    public LinkedListNode<T> getRoot() {
        return root;
    }

    public void setRoot(LinkedListNode<T> root) {
        this.root = root;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
    }
}

