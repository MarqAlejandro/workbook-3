package com.plurasight;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Inventory {

    private List<Product> inventory = new ArrayList<Product>();
    private Scanner scanner = new Scanner(System.in);

    public void openingMenu(){                                                          //menu which allows user to control what decisions are being made
        readFileLoadProducts();
        sortInventoryByName();
        boolean exitWhileloop = false;
        while (!exitWhileloop) {                                                        //while loop that keeps user in the menu
                System.out.print("\nWelcome to the Inventory" +
                        "\n\tPress 1 to display Product Inventory" +
                        "\n\tPress 2 to look up a product by its id" +
                        "\n\tPress 3 to find all products within a price range" +
                        "\n\tPress 4 to add a new product" +
                        "\n\tPress 5 to quite the application" +
                        "\nEnter Command: ");
                String userInput = scanner.nextLine();
                int userInt = 0;

                if (userInput.equals("1")) {                                            //input validation
                    userInt = 1;
                }
                else if (userInput.equals("2")) {
                    userInt = 2;
                }
                else if (userInput.equals("3")) {
                    userInt = 3;
                }
                else if(userInput.equals("4")){
                    userInt = 4;
                }
                else if(userInput.equals("5")) {
                    userInt = 5;
                }
                else{
                    System.out.println();
                }

                switch (userInt) {                                                      //switch controls that changes depending on userInput
                    case 1:                                                             //display inventory
                        displayInventory();
                        break;
                    case 2:                                                             //look up a product by id and display info
                        searchInventoryByID();
                        break;
                    case 3:                                                             //find all products of within a price range
                        searchInventoryByPriceRange();
                        break;
                    case 4:                                                             //add a new product
                        addNewProduct();
                        break;
                    case 5:                                                             //quit the application
                        exitWhileloop = true;
                        System.out.println("Thank you! See you next time.");
                        break;
                    default:
                        System.out.println("User Input is invalid please try again.");
                }


        }
    }

    public void searchInventoryByID(){                                              //search by product ID, if found in the inventory it will

            boolean productFound = false;                                           //bool variable, if product is found it will flip to true
            System.out.println("Enter a product ID.");
            String userinput = scanner.nextLine();
            try {
                int productIDTarget = Integer.parseInt(userinput);                  //parse to int from String variable, will catch if input is incorrect
                for(Product product : inventory){                                   //for-each loop to iterate through the whole inventory
                    if(product.getId() == productIDTarget){                         //only triggers if the product ID matches the user's input
                        productFound = true;
                        product.displayInfo();                                      //trigger the product objects display method
                    }
                }
                if(!productFound){
                    System.out.println("product not found");                        //if productFound remains false then it will prompt the user that it is not in the inventory
                }

            } catch (NumberFormatException e) {
                System.out.println("input was not a number try again");
                searchInventoryByID();                                              //recursion to the top of the method if there was an error with the input
            }

    }

    private void sortInventoryByName(){                                             //sort inventory by alphabetical order
        inventory.sort((product1, product2) -> product1.getName().compareTo(product2.getName()));
    }

    private void displayInventory() {                                               //displays the whole inventory with a formatted text
        System.out.println("We carry the following inventory: ");
        for (Product product: inventory) {
            product.displayInfo();
        }

    }

    private void preloadInventory() {                                             // this method loads product objects into inventory
        inventory.add(new Product(1,"egg", 6.5F));
        inventory.add(new Product(2,"milk",4.0F));
        inventory.add(new Product(3,"bacon",7.5F));
        inventory.add(new Product(4,"bread",5.5F));
        inventory.add(new Product(5,"coffee",4.3F));
    }

    public void readFileLoadProducts() {                                                       //method to load products info from .csv file onto ArrayList
        boolean exitWhileLoop = false;
        while(!exitWhileLoop){
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
                        product.setName(tokens[1]);
                        product.setPrice(Float.parseFloat(tokens[2]));                       //will load and set all product's information only if there is exactly 3 elements in the String Array
                    }
                    else{
                        System.out.println("error: missing or too much information on a given product");
                    }

                    inventory.add(product);                                                         //load the Product object onto the ArrayList for product
                }
                bufReader.close();                                                                          //bufferedReader close
                exitWhileLoop = true;
            } catch (IOException e) {                                                                       //in case of an error with I/O
                System.out.println("error. no file exists.");                              //catch to loop back to beginning
            }
        }
    }

    public void searchInventoryByPriceRange() {
        System.out.println("Finding a product by Price.");
        System.out.print("Input a minimum price and maximum price," +
                "\nMinimum price: ");
        try {
            float minimumPrice = scanner.nextFloat();                                                       //getting min and max for the price range
            scanner.nextLine();

            System.out.print("Maximum price: ");
            float maximumPrice = scanner.nextFloat();
            scanner.nextLine();

            for (Product product : inventory) {                                                          //for-each loop that iterates through the object array
                try {
                    float priceCheck = product.getPrice();                                                  //instance variable so that getter isn't called 2 times per loop but instead just once
                    if ((priceCheck >= minimumPrice) && (priceCheck <= maximumPrice)) {                      //if statement that triggers if the vehicle price is within the price range
                        product.displayInfo();
                    }
                } catch (
                        NullPointerException e) {                                                              //if there is no object then it will shoot out an error, this allows the loop to continue
                    continue;
                }
            }
        }
        catch(NumberFormatException e){
            System.out.println("1 or more Input(s) is incorrect please try again");
            searchInventoryByPriceRange();
        }
    }

    public void addNewProduct() {                                                                       //method prompts user to input values for product info and adds it to inventory as a new product object
        System.out.println("Adding a New Product");
        int productID = inventory.size() + 1;
        System.out.print("Enter the product's name: ");                                                 //prompts the user for product info
        String productName = scanner.nextLine();
        System.out.print("Enter the product's price: ");
        String productPrice = scanner.nextLine();
        try {
            float productPriceFormatted = Float.parseFloat(productPrice);                               //float parse the string variable, if there's an issue catch statement will recurse

            inventory.add(new Product(productID,productName,productPriceFormatted));                    // assuming all the prompts are valid, add a new product to inventory using the inputs as parameters
            sortInventoryByName();                                                                      //use sort method to reorganize the list
        }
        catch(NumberFormatException e){
            System.out.println("1 or more Input(s) is incorrect please try again");
            addNewProduct();                                                                            //recursion to beginning of method and get all the information correct
        }

    }
}//end of class
