package assignment.dictionary;

import java.util.Iterator;


/**
 * A class that will be used to display the lines of text that are corrected.
 *
 */

public class LinesToDisplay {

    public static final int LINES = 20; // Was originally 10, I changed it
    private AList<Wordlet>[] lines;
    private int currentLine;

    /**
     * Constructor for objects of class LinesToDisplay
     */
    public LinesToDisplay() {
        //ADD CODE FOR THE CONSTRUCTOR
        //>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>
        lines = (AList<Wordlet>[]) new AList[LINES]; // Initialize the lines variable
        for (int i = 0; i < LINES; i++) {
            lines[i] = new AList<Wordlet>(); // Initialize the AList's
        }
        currentLine = 0;
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    /**
     * Add a new wordlet to the current line.
     *
     */
    public void addWordlet(Wordlet w) {
        //ADD CODE HERE TO ADD A WORDLET TO THE CURRENT LINE

        //>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>
        lines[currentLine].add(w); // add the wordlet
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift
     * them all up by one
     *
     */
    public void nextLine() {
        //ADD CODE TO HANDLE THE NEXT LINE
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>        
        if(currentLine + 1 >= LINES) { // if we're going to overflow,
            for(int i = 0; i < LINES-1; i++) {
                lines[i] = lines[i + 1]; // move all lines up one
            }
            lines[currentLine].clear(); // since the current line is the upper limit,
                                        // stay on it and clear it to make it new
        } else currentLine++;
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

      
    public int getCurrentLine(){
        return currentLine;
    }
    
    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
