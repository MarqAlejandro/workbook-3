package com.plurasight;

import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        FamousQuote famousQuotes = new FamousQuote();
        boolean exitWhileLoop = false;

        while(!exitWhileLoop) {
            System.out.print("Hello User!," +
                    "\nPlease choose either 1 to receive a random famous quote, 2 to select a famous quote, or 3 to exit the program:");
            int userChoice = scanner.nextInt();


            if (userChoice == 1) {
                int random = (int) (Math.random() * 10);

                System.out.println(random);

                System.out.println(famousQuotes.getQuotes(random));
            }

            else if(userChoice == 2) {
                //2nd option
                System.out.print("Hello, User! " +
                        "\nPlease enter a number from 1-10 to get a famous quote: ");

                int userInput = scanner.nextInt();
                userInput--;

                try {
                    System.out.println(famousQuotes.getQuotes(userInput));

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("number selected is outside of scope. Please try again");
                }
            }
            //3rd option
            else if(userChoice == 3){
                System.out.println("Thank you for checking out our famous quotes. See you again.");
                    exitWhileLoop = true;
            }

        }

    }
}