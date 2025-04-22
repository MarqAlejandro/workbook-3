package com.plurasight;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BedTimeStory {



    public void storyMenu(){
        Scanner scanner = new Scanner(System.in);                                       //scanner object just for userInput

        boolean exitWhileLoop = false;                                                  //boolean factor to control the while loop
        System.out.println("We have quite a few stories to read from");

            while (!exitWhileLoop) {                                                         //while loop menu

                    System.out.println("\nPlease select a number to have it read to you" +
                            "\n1 - Goldilocks" +
                            "\n2 - Hansel and Gretel" +
                            "\n3 - Mary had a Little Lamb" +
                            "\n\n4 - Exit the Application");

                    String storyTitle = "";
                try {
                    int userInput = scanner.nextInt();
                    scanner.nextLine();

                    switch (userInput) {                                                        //switch case to allow user control on decisions
                        case 1:
                            StoryBook("goldilocks.txt");                               //will print out the story goldilocks.txt
                            break;
                        case 2:
                            StoryBook("hansel_and_gretel.txt");                        //^                      ^ hansel_and_gretel.txt
                            break;
                        case 3:
                            StoryBook("mary_had_a_little_lamb.txt");                   //^                      ^ mary_had_a_little_lamb.txt
                            break;
                        case 4:
                            exitWhileLoop = true;
                            System.out.println("Exiting the Application, Have a Nice Day");//exit the menu and the application
                            break;
                        default:
                            System.out.println("input was not between 1 - 4. Please Try Again"); //if a number outside of 1 -4 is chosen it will loop back
                    }

                }
                catch(InputMismatchException e){
                    System.out.println("Input was not a number, Please try again");   //if a non-number is chosen it will exit the program
                     scanner.nextLine();                                              //needed to absorb whatever is in the next line
                                                                                      //loops back to the beginning



                }


            }//end of while loop
    }
    public void StoryBook(String storyTitle){                                           //method copied and modified from the workbook
        try{
                // create a FileInputStream object pointing to
                // a specific file
            System.out.println();
            System.out.println("\n Reading from: " + storyTitle);                       //takes String arguement
                FileInputStream fis = new FileInputStream(storyTitle);                  //will find the txt.file and assign it variable "fis"
                // create a Scanner to reference the file to be read
                Scanner scanner = new Scanner(fis);                                     //scanner will start scanning fis
                String input;                                                           //variables initialized
                int linenumber = 1;
                // read until there is no more data
                while(scanner.hasNextLine()) {                                          //while scanner is reading next line is true
                    input = scanner.nextLine();                                         //set 1 line from the txt.file into String variable
                    Thread.sleep(1000);
                    System.out.println(linenumber + ". " + input);                      //print out the line number and String variables
                    Thread.sleep(1000);
                    linenumber++;                                                       //update line number variable
                }
                // close the scanner and release the resources
                scanner.close();                                                        //once out of loop. close the loop
            }
            catch(IOException | InterruptedException e) {                               //standard IO catch statement
                // display stack trace if there was an error
                e.printStackTrace();
            }
    }
}


