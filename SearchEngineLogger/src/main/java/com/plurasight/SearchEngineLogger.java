package com.plurasight;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class SearchEngineLogger {

        private final LocalDateTime todayDateTime = LocalDateTime.now();
        private DateTimeFormatter dateTimeFormatter;
        private String formatDateTime;
        private Scanner scanner = new Scanner(System.in);



        public void writeToLog(){                                                               //method to prompt user to input a search term and logs it on a text file
            try {
                FileWriter writer = new FileWriter("logs.txt");                         //initial log dated and written on a text file
                formatDateTime();
                writer.write(formatDateTime + " launch\n");

                boolean keepWriting = false;
                while(!keepWriting) {

                    System.out.print("Enter a search term (X to exit: ");                       //keeps asking the user for input until x has been inputted
                    String userInput = scanner.nextLine();
                    if(userInput.equalsIgnoreCase("x")){
                        keepWriting = true;
                    }
                    else {
                        formatDateTime();
                        writer.write(formatDateTime + " search : " + userInput + "\n");     //log the search term on text file
                    }
                }

                formatDateTime();                                                           //final log dated and written
                writer.write(formatDateTime + " exit");
                writer.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void formatDateTime(){
                dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");         //format the date and time for the log to print out

                formatDateTime = todayDateTime.format(dateTimeFormatter);
        }
}
