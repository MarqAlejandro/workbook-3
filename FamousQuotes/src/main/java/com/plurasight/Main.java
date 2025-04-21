package com.plurasight;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);                                               //scanner for users input
        FamousQuote famousQuotes = new FamousQuote();                                           //object to call on famous quotes
        boolean exitWhileLoop = false;                                                          //used to exit the while loop menu

        while(!exitWhileLoop) {                                                                 //said while loop menu
            System.out.print("Hello User!," +                                                   //prompts user for choice from menu
                    "\nPlease choose either:" +
                    "\n1 to receive a random famous quote," +
                    "\n2 to select a famous quote," +
                    "\nor 3 to exit the program:");
            int userChoice = scanner.nextInt();


            if (userChoice == 1) {                                                              //userChoice for random quote
                int random = (int) (Math.random() * 10);

                System.out.println(random);

                System.out.println(famousQuotes.getQuotes(random));
            }

            else if(userChoice == 2) {                                                          //userChoice prompts user for secondary input to select a quote to print out
                //2nd option
                System.out.print("Hello, User! " +
                        "\nPlease enter a number from 1-10 to get a famous quote: ");

                int userInput = scanner.nextInt();
                userInput--;

                try {
                    System.out.println(famousQuotes.getQuotes(userInput));

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("number selected is outside of scope. Please try again");        //if user's selection is outside the String Array's scope then it will print out a warning
                }
            }
            //3rd option
            else if(userChoice == 3){                                                                   //userChoice exits the while loop menu effective ending the program
                System.out.println("Thank you for checking out our famous quotes. See you again.");     //Thanks the user for coming by
                    exitWhileLoop = true;
            }

        }

    }
}