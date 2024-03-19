/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.dictionary;

import java.util.Iterator;

/**
 *
 * @author Ouda
 * @param <T>
 */

public interface ListInterface <T>  {
    /* Task: Adds a new entry to the end of the list.*/
	public void add(T newEntry);

	/* Task: Adds a new entry at a specified position within the list. */
	public void add(int newPosition, T newEntry);

	/* Task: Removes the entry at a given position from the list.*/
	public T remove(int givenPosition);

	/* Task: Removes all entries from the list. */
	public void clear();

	/* Task: Replaces the entry at a given position in the list.*/
	public T replace(int givenPosition, T newEntry);

	/* Task: Retrieves the entry at a given position in the list.*/
	public T getEntry(int givenPosition);

	/* Retrieves all entries that are in this list in the order in which
       they occur in the list.*/
	public T[] toArray();

	/* Task: Sees whether the list contains a given entry.*/
	public boolean contains(T anEntry);

	/* Task: Gets the length of the list.*/
	public int getLength();

	/* Task: Sees whether the list is empty.*/
	public boolean isEmpty();
        
        /* Task: get the position of a given entry.*/
	public int getPosition(T anEntry);

    public Iterator<T> getIterator();
}
