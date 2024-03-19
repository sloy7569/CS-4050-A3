package assignment.dictionary;

/** 
 * 
 * An implementation of the ADT Dictionary that adapts (uses a private instance) of 
 * the Hashtahash class from Java 
 * 
 * Mostly the adaption consists of making calling the appropriate method of the
 * MyHashTable class.
 * 
 */

import java.util.*;
        
public class HashedMapAdaptor<K,V> implements DictionaryInterface<K,V>,
        java.io.Serializable
{
    private MyHashTable<K,V> myTable; // the hash table
    
    public HashedMapAdaptor()
    {
        myTable = new MyHashTable<K,V>();
    }
    
    
    /** Adds a new entry to this dictionary. If the given search
    key already exists in the dictionary, replaces the
    corresponding value.
    @param key An object search key of the new entry.
    @param value An object associated with the search key.
    @return Either null if the new entry was added to the dictionary
    or the value that was associated with key if that value
    was replaced. */
    public V add(K key, V value)
    {
        return myTable.put(key,value);
    }


    /** Removes a specific entry from this dictionary.
    @param key An object search key of the entry to be removed.
    @return Either the value that was associated with the search key
    or null if no such object exists. */
    public V remove(K key)
    {
        return myTable.remove(key);
    }
    
    
    /** Retrieves from this dictionary the value associated with a given
    search key.
    @param key An object search key of the entry to be retrieved.
    @return Either the value that is associated with the search key
    or null if no such object exists. */
    public V getValue(K key)
    {
        return myTable.get(key);
    }    
    
    /** Sees whether a specific entry is in this dictionary.
    @param key An object search key of the desired entry.
    @return True if key is associated with an entry in the
    dictionary. */
    public boolean contains(K key)
    {
        return myTable.containsKey(key);
    }    
    
    /** Creates an iterator that traverses all search keys in this
    dictionary.
    @return An iterator that provides sequential access to the search
    keys in the dictionary. */
    public Iterator<K> getKeyIterator()
    {
        return myTable.keySet().iterator();
    }
    
    
    /** Creates an iterator that traverses all values in this dictionary.
    @return An iterator that provides sequential access to the values
    in this dictionary. */
    public Iterator<V> getValueIterator()
    {
        return myTable.values().iterator();
    }    
    
    /** Sees whether this dictionary is empty.
    @return True if the dictionary is empty. */
    public boolean isEmpty()
    {
        return myTable.isEmpty();
    }    
       
    
    /** Gets the size of this dictionary.
    @return The number of entries (key-value pairs) currently
    in the dictionary. */
    public int getSize()
    {
        return myTable.size();
    }
    
    
    /** Removes all entries from this dictionary. */
    public void clear()
    {
        myTable.clear();
    }
    
    // Use the equals method of the map.
    public boolean equals(Object other)
    {
        return myTable.equals(other);
    }
    
    // Use the toString method of the map.
    public String toString()
    {
        return myTable.toString();
    }
}
