package model.Structures;

public interface ITreeBST<K, V, T> {

    T search(T node, K key);

    void insert(K key, V value);

    void delete(K key);

    T min(T root);

    T max(T root);

    T successor(T node);

    T predecessor(T node);
}

