package model.Structures;


public interface ITreeAVL<K, V, T> extends ITreeBST<K, V, T> {

    int height(T node);

    void rightRotate(T node);

    void leftRotate(T node);

    int balance(T node);

    void balanceAgain(T node);
}
