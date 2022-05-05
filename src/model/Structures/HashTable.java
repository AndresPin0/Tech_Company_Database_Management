package model.Structures;

public interface HashTable<K, V> {
    int hash(K key);
    int insert(K key, V value) throws Exception;

    void delete(K key) throws Exception;

    Object search(K key);

    boolean isEmpty();

    int size();
}
