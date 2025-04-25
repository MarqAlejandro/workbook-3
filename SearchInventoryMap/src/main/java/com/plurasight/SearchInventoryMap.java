package com.plurasight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SearchInventoryMap {


    private HashMap<String, Product> inventory = new HashMap<String, Product>();
    private Scanner scanner = new Scanner(System.in);

    public void mainControlMethod(){
        loadInventory();
        System.out.println("loading inventory");
        displayInventory();
        displayAnItem();


    }


    public void displayAnItem(){
        boolean productFound = false;
        System.out.print("Enter a product ID: ");
        String productName = scanner.nextLine();
        try{
            for (String key: inventory.keySet()) {
                if(inventory.get(key).getName().equalsIgnoreCase(productName)) {
                    productFound = true;
                    System.out.println("Product Found: ");
                    inventory.get(key).displayInfo();

                }
            }
            if(!productFound){
                System.out.println("product not found");                        //if productFound remains false then it will prompt the user that it is not in the inventory
            }
            System.out.println("Would you like to search for another product?");
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("yes")){
                displayAnItem();
            }
            else if(userInput.equalsIgnoreCase("no")){
                System.out.println("No has been detected. Have a nice day.");
            }
            else{
                System.out.println("input was neither yes or no. defaulting to exiting the program. Have a nice day.");
            }


        }
        catch (NumberFormatException e){
            System.out.println("input was not a number try again");
            displayAnItem();                                              //recursion to the top of the method if there was an error with the input
        }


    }


    public void displayInventory() {                                               //displays the whole inventory with a formatted text
        System.out.println("We carry the following inventory: ");
        for (String key: inventory.keySet()) {
            System.out.print(key + ": ");
            inventory.get(key).displayInfo();
        }
    }



    public void loadInventory(){
        try {
            System.out.println("Loading Product Information");
            BufferedReader bufReader = new BufferedReader(new FileReader("Inventory.csv"));     //BufferedReader variable that takes a FileReader as arguement that takes a .csv file arguement
            String productFileInput;                                                                   //String Variable to hold product info

            // bufReader.readLine();      //commented out because current file doesn't have garbage     //skip the first line, assumes that the first line is headers and garbage data

            while ((productFileInput = bufReader.readLine()) != null) {                                //in the midst of while loop read a line from .csv file and load it onto String Variable and check if it comes out null
                String[] tokens = productFileInput.split(Pattern.quote("|"));                   //load the line onto a String array so that it can be partitioned by the pattern "|"
                Product product = new Product();                                                 //create an empty Product object
                if(tokens.length == 3) {
                    product.setId(Integer.parseInt(tokens[0]));
                    product.setName(tokens[1].toLowerCase());
                    product.setPrice(Float.parseFloat(tokens[2]));                       //will load and set all product's information only if there is exactly 3 elements in the String Array
                }
                else{
                    System.out.println("error: missing or too much information on a given product");
                }

                inventory.put(tokens[1].toLowerCase(), product);                                                         //load the Product object onto the Hashmap with product name as key
            }
            bufReader.close();                                                                          //bufferedReader close

        } catch (IOException e) {                                                                       //in case of an error with I/O
            System.out.println("error. no file exists.");                              //catch to loop back to beginning
        }




    }
    
}
