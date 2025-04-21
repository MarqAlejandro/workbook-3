package com.plurasight;

public class FamousQuote {


    private String[] listOfQuotes = {"I think, therefore I am",                             //String Array to hold all the string quotes
            "The only thing we have to fear is fear itself",
            "Knowledge is power",
            "Not all those who wander is lost",
            "Change your thoughts and you change your world",
            "The journey of a thousand miles begins with a single step",
            "This too shall pass",
            "In the end, we will remember not the words of our enemies, but the silence of our friends",
            "tis better to have loved and lost than never to have loved at all",
            "All the world's a stage, and all the men and women merely players"
    };

    public String getQuotes(int pageNumber) {                                       //requires int as argument, getter for quotes in the String Arrray
        return listOfQuotes[pageNumber];
    }


}
