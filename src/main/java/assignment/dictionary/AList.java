/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.dictionary;

/**
 *
 * @author Ouda
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class AList<T> implements ListInterface<T> , Iterable<T>{

    private T[] list;   // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public AList() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public AList(int initialCapacity) {
        if (initialCapacity < DEFAULT_CAPACITY) // Is initialCapacity too small?
        {
            initialCapacity = DEFAULT_CAPACITY;
        } else // Is initialCapacity too big?
        {
            checkCapacity(initialCapacity);
        }

        T[] tempList = (T[]) new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        initialized = true;

    }

// Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a list whose capacity exceeds allowed maximum.");
        }
    } // end checkCapacity

    public void add(T newEntry) {
        checkInitialization();
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity(); // Ensure enough room for next add      
    } // end add

    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("AList object is not initialized properly.");
        }
    } // end checkInitialization

    private void ensureCapacity() {
        int capacity = list.length - 1;
        if (numberOfEntries == capacity) {
            int newCapacity = 2 * capacity;
            //Is capacity too big?
            checkCapacity(newCapacity);
            list = Arrays.copyOf(list, newCapacity + 1);
        } // end if
    } // end ensureCapacity

    public void add(int newPosition, T newEntry) {
        checkInitialization();
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            if (newPosition <= numberOfEntries) {
                makeRoom(newPosition);
            }

            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity(); // Ensure enough room for next add
        } else {
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
        }
    } // end add

    private void makeRoom(int newPosition) {
        assert (newPosition >= 1)
                && (newPosition <= numberOfEntries + 1);

        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            list[index + 1] = list[index];
        }

    } // end makeRoom

    public T remove(int givenPosition) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T result = list[givenPosition]; // Get entry to be removed
            // Move subsequent entries towards entry to be removed,
            // unless it is last in list
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    } // end remove

    private void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++) {
            list[index] = list[index + 1];
        }
    } // end removeGa

    public void clear() {
        checkInitialization();
        // Clear entries but retain array; no need to create a new array
        for (int index = 1; index <= numberOfEntries; index++) {
            list[index] = null;
        }
        numberOfEntries = 0;
    } // end clear

    public T replace(int givenPosition, T newEntry) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }
    } // end replace

    public T getEntry(int givenPosition) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return list[givenPosition];
        } else {
            throw new IndexOutOfBoundsException(
                    "Illegal position given to getEntry operation.");
        }
    } // end getEntry

    public int getLength() {
        return numberOfEntries;
    } // end getLength

    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    public T[] toArray() {
        checkInitialization();
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast

        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index + 1];
        } // end for

        return result;
    } // end toArray

    public boolean contains(T anEntry) {
        checkInitialization();
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries)) {
            if (anEntry.equals(list[index])) {
                found = true;
            }
            index++;
        } // end while

        return found;

    } // end contains

    public int getPosition(T anEntry) {
        checkInitialization();
        int index = 1;
        while (index <= numberOfEntries) {
            if (anEntry.equals(list[index])) {
                return index;
            }
            index++;
        } // end while

        return 0;

    } // end getPostion
    
      public Iterator<T> iterator(){
        return new IteratorForAList();
    } // end iterator
    
    public Iterator<T> getIterator() {
        return new IteratorForAList();
    } // end getIterator

    private class IteratorForAList implements Iterator<T> {

        private int nextIndex;          // Index of next entry
        private boolean wasNextCalled;  // Needed by remove
        
        private IteratorForAList() {
            nextIndex = 1;
            wasNextCalled = false;
        } // end default constructor

        public T next() {
            if (hasNext()) {
                wasNextCalled = true;
                T nextEntry = list[nextIndex];
                nextIndex++; // Advance iterator
                return nextEntry;
            } else {
                throw new NoSuchElementException("Illegal call to next(); "
                        + "iterator is after end of list.");
            }
        } // end next

        public boolean hasNext() {
            return nextIndex <= numberOfEntries;
        } // end hasNext

        public void remove() {
            if (wasNextCalled) {
                // nextIndex was incremented by the call to next, so it
                // is 1 larger than the position number of the entry to be removed
                AList.this.remove(nextIndex - 1);
                nextIndex--; // Index of next entry in iteration
                wasNextCalled = false; // Reset flag
            } else {
                throw new IllegalStateException("Illegal call to remove(); "
                        + "next() was not called.");
            }
        } // end remove
    } // end IteratorForAList
}
