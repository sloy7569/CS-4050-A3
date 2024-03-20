package assignment.dictionary;

import java.io.*;
import java.util.*;
import javafx.application.Platform;

/**
 * A Thread that contains the application we are going to animate
 *
 */

public class MisSpellActionThread implements Runnable {

    DictionaryController controller;
    private final String textFileName;
    private final String dictionaryFileName;

    private LinesToDisplay myLines;
    private DictionaryInterface<String, String> myDictionary;
    private boolean dictionaryLoaded;

    /**
     * Constructor for objects of class MisspellActionThread
     *
     * @param controller
     */
    public MisSpellActionThread(DictionaryController controller) {
        super();

        this.controller = controller;
        textFileName = "src/main/resources/assignment/dictionary/check.txt";
        dictionaryFileName = "src/main/resources/assignment/dictionary/sampleDictionary.txt";

        myDictionary = new HashedMapAdaptor<String, String>();
        myLines = new LinesToDisplay();
        dictionaryLoaded = false;

    }

    @Override
    public void run() {

        loadDictionary(dictionaryFileName, myDictionary);


        Platform.runLater(() -> {
            if (dictionaryLoaded) {
               controller.SetMsg("The Dictionary has been loaded"); 
            } else {
               controller.SetMsg("No Dictionary is loaded"); 
            }
        });
        
        checkWords(textFileName, myDictionary);

    }

    /**
     * Load the words into the dictionary.
     *
     * @param theFileName The name of the file holding the words to put in the
     * dictionary.
     * @param theDictionary The dictionary to load.
     */
    public void loadDictionary(String theFileName, DictionaryInterface<String, String> theDictionary) {
        Scanner input;
        try {
            // ADD CODE HERE
            // Goal: read words from file theFileName and add them into theDictionary
// >>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            File file = new File(theFileName); // open the file
            input = new Scanner(file); // scan through the dictionary words file

            while(input.hasNextLine()) {
                String line = input.nextLine();
                theDictionary.add(line, line); // read through and add the words to theDictionary
            }

            input.close();

            dictionaryLoaded = true;
            //IOException e
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//            System.out.println("TESTTESt");

        } catch (FileNotFoundException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }

    }



    /**
     * Get the words to check, check them, then put Wordlets into myLines. When
     * a single line has been read do an animation step to wait for the user.
     *
     */
    public void checkWords(String theFileName, DictionaryInterface<String, String> theDictionary) {
        Scanner input;
        try {
 
// ADD CODE HERE
// >>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            File file = new File(theFileName); // open the test file
            input = new Scanner(file);

            while(input.hasNextLine()) { // while there are more lines to read
                String line = input.nextLine();
                String[] words = line.split(" "); // split line into words

                for(String word : words) { // for each word in the line
                    // Create a wordlet holding the word and if it's in the dictionary
                    boolean spelling = checkWord(word, theDictionary);
                    Wordlet wl = new Wordlet(word, spelling);
                    myLines.addWordlet(wl); // add the wordlet to LinesToDisplay object
                }

                // Do an animation to wait for the user
                showLines(myLines);

                myLines.nextLine(); // wait for the next line

            }

            input.close();
            //IOException e
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//            System.out.println("TESTTESt");


        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }

    }

    /**
     * Check the spelling of a single word.
     *
     */
    public boolean checkWord(String word, DictionaryInterface<String, String> theDictionary) {
        boolean result = false;

        // ADD CODE HERE
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // Trim the word to remove punctuation marks like commas and periods
        String trimmedWord = word.replaceAll("^[\\p{Punct}]+|[\\p{Punct}]+$", "");

        // Check the dictionary
        result = theDictionary.contains(trimmedWord);

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        return result;

    }

    private void showLines(LinesToDisplay lines) {
        try {
            Thread.sleep(500);
            Platform.runLater(() -> {
                if (myLines != null) {
                    controller.UpdateView(lines);
                }
            });
        } catch (InterruptedException ex) {
        }
    }

} // end class MisspellActionThread

