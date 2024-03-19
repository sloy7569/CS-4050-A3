package assignment.dictionary;

/*

 */

//
import javafx.scene.Node;

import java.util.*;
import java.io.*;
import java.util.Dictionary;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.ArrayList;

/**
 * Node implementation for a linked list in the hash array
 * @param <K> key
 * @param <V> value
 */
class HashNode<K, V> {
    K key;
    V value;
    //int hashCode;
    HashNode<K, V> next;

    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
        //this.hashCode = hashCode;
    }
}


/**
 * Hash table implementation with a type AList array of Nodes (above) using separate chaining to handle collisions
 * @param <K>
 * @param <V>
 */
public class MyHashTable<K,V>
{
    // You need to implement this class without using the
    // Hashtable class from Java (“java.util.Hashtable<K,V>”).
    private int DEFAULT_LEN = 5;
    //private ArrayList<HashNode<K, V>> hashArray;
    private AList<HashNode<K, V>> hashArray;
    private int size;

    //constructor
    MyHashTable() {
        hashArray = new AList<>();
        size = 0;

        for (int i = 0; i < DEFAULT_LEN; i++) {
            hashArray.add(null);
        }
    }

    private int hashIndex(K key) {
        return key.hashCode() % DEFAULT_LEN;
    }

    /**
     * Searches hash array for specified key and returns its value if present.
     * If it is not present, returns null.
     *
     * @param key specified key
     * @return value, if present, of the specified key
     */
    public V get(K key) {
        if (key == null) {
            return null;
        }
        //get index of key and set root equal to it
        int index = hashIndex(key);
        HashNode<K, V> root = hashArray.getEntry(index);

        //iterate through linked list at the index to find the value
        while (root != null) {
            if (root.key.equals(key)) {
                return root.value;
            }
            root = root.next;
        }

        return null;
    }

    /**
     * Adds a key/value pair to the hash table. First checks
     * table to see if key is already present, if not, adds
     * key/value pair to table
     *
     * @param key   specified key
     * @param value specified value
     * @return null if value is successfully added to table,
     * or if key is present, returns the value before updated
     */
    public V put(K key, V value) {
        //if(key == null){
        // return V;
        //}

        int index = hashIndex(key);
        HashNode<K, V> root = hashArray.getEntry(index);

        while (root != null) {
            if (root.key.equals(key)) {
                V temp = root.value;
                root.value = value;
                return temp;
            }
            root = root.next;
        }

        root = hashArray.getEntry(index);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = root;
        hashArray.replace(index, newNode);
        size++;
        return null;
    }

    public V remove(K key) {
        int index = hashIndex(key);
        HashNode<K, V> root = hashArray.getEntry(index);

        if(root.next != null){
            HashNode<K, V> temp = hashArray.remove(index);
            return temp.value;
        }

        else{
            HashNode<K, V> parent = null;
            while(root != null){
                if(root.key.equals(key)){
                    break;
                }

                parent = root;
                root = root.next;
            }

            if (root == null){
                return null;
            }

            if(parent != null){
                parent.next = root.next;
            }
            else{
                hashArray.replace(index, root.next);
            }

            return root.value;
        }
    }

    /**
     *
     * @return returns size of hash table
     */
    public int size(){
        return size;
    }

    /**
     *
     * @return returns boolean true if empty, false if not
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * clears hash array of all Nodes
     */
    public void clear(){
        hashArray.clear();
    }

    /**
     * traverses the hash index of the argument key
     * @param key
     * @return returns true if hash table contains key, false if not
     */
    public boolean containsKey(K key){
        if (key == null) {
            return false;
        }

        //get index of key and set root equal to it
        int index = hashIndex(key);
        HashNode<K, V> root = hashArray.getEntry(index);

        while (root != null) {
            if (root.key.equals(key)) {
                return true;
            }
            root = root.next;
        }

        return false;
    }

    public AList<K> keySet(){
        AList<K> keys = new AList<>();
        Iterator<HashNode<K, V>> it = hashArray.iterator();
        while(it.hasNext()){
            HashNode<K, V> temp = it.next();
            keys.add(temp.key);
            while(temp.next != null){
                keys.add(temp.next.key);
            }
        }
        return keys;
    }

    public AList<V> values(){
        AList<V> valueList = new AList<>();
        Iterator<HashNode<K, V>> it = hashArray.iterator();
        while(it.hasNext()){
            HashNode<K, V> temp = it.next();
            valueList.add(temp.value);
            while(temp.next != null){
                valueList.add(temp.next.value);
            }
        }
        return valueList;
    }


}
