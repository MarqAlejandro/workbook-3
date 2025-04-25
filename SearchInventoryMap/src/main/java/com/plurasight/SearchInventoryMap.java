package com.plurasight;

import java.io.BufferedReader;                                                          //imports used in this application
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SearchInventoryMap {

    private HashMap<String, Product> inventory = new HashMap<String, Product>();       //Hashmap using String as Key and Product object as value, initialized
    private Scanner scanner = new Scanner(System.in);                                   //Scanner object initialized

    public void mainControlMethod(){                                                    //main control method, calls other methods
        loadInventory();
        System.out.println("loading inventory");
        displayInventory();
        displayAnItem();
    }

    public void displayAnItem(){                                                       //method prompts user for product name, if key equals user input display the products info
        boolean productFound = false;                                                  // boolean initially false will change if the product is found
        System.out.print("Enter a product name: ");
        String productName = scanner.nextLine();                                        //prompts the user for product name and stores it into a string
        try{
            for (String key: inventory.keySet()) {                                      //for-each loop to iterate through the hashmap keys
                if(inventory.get(key).getName().equalsIgnoreCase(productName)) {        //only triggers if the name (ignoring case) is equal to the user's input
                    productFound = true;                                                //boolean switch to indicate that the product is found
                    System.out.println("Product Found: ");
                    inventory.get(key).displayInfo();                                   //display method to show the product's info

                }
            }
            if(!productFound){
                System.out.println("product not found");                        //if productFound remains false then it will prompt the user that it is not in the inventory
            }
            System.out.println("Would you like to search for another product? (yes/no)");    //prompts the user if they want to search for another item
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("yes")){                      //if user input (ignoring case) is yes...
                displayAnItem();                                                    //then recurse back to the top of the method and do it again
            }
            else if(userInput.equalsIgnoreCase("no")){                  //if no then the program will fall off and terminate
                System.out.println("No has been detected. Thank you for using our services. Have a nice day.");  //don't forget to thank the customer for using our services
            }
            else{
                System.out.println("input was neither yes or no. defaulting to exiting the program. Have a nice day."); //other response incase other response was inputted
            }
        }
        catch (NumberFormatException e){
            System.out.println("input was");
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
                inventory.put(tokens[1].toLowerCase(), product);                            //load the Product object onto the Hashmap with product name as key
            }
            bufReader.close();                                                                          //bufferedReader close

        } catch (IOException e) {                                                                       //in case of an error with I/O
            System.out.println("error. no file exists.");                              //catch to loop back to beginning
        }
    }
}
