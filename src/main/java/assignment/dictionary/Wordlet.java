/**
 * This class holds a fragment (presumed to be a word) of text.  It includes a flag to indicate if the word
 * is believed to be spelled incorrectly.
 * 
 */
package assignment.dictionary;
public class Wordlet
{
    String myWord;
    boolean spelledCorrectly;

    /**
     * Constructor for objects of class Wordlet.
     */
    public Wordlet(String word, boolean spelling)
    {
        myWord = word;
        spelledCorrectly = spelling;
    }
    
    /**
     * An accessor for the word held by the class.
     * @return The word.
     */
    public String getWord()
    {
        return myWord;
    }
    
    /**
     * Is the word spelled correctly?
     * @return The flag held by the class indicating if the word is spelled correctly.
     */

    public boolean isSpelledCorrectly()
    {
        return spelledCorrectly;
    }
    
}
