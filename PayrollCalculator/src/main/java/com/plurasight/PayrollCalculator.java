/*
finish the rest of section 2-1 exercise 1

Enter the name of the payroll file to create: payroll-sept-2023.csv
When your program finishes running, open the new file in Notepad to view the
results.
BONUS:  If the user chooses specifies a .json extension write the data as JSON
instead of csv.

this part from the workbook
 */


package com.plurasight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PayrollCalculator {
    private List<Employee> employeeList = new ArrayList<>();                                    //ArrayList for Employee objects
    private String fileInput = "";
    private String writeTo = "";

    public void findLoadAndPrint(){                                                                 //main control method
        fileInputSelection();                                                                       //only works if in this order
        readFileLoadEmployees();
        printEmployeeInfo();
    }

    public void fileInputSelection(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the file employee file to process: ");
        fileInput = scanner.nextLine();
    }


    public void readFileLoadEmployees() {                                                       //method to load employees info from .csv file onto ArrayList
        try {
            // create a FileReader object connected to the File
            System.out.println("Loading Employees' Information");
            // create a BufferedReader to manage input stream
            BufferedReader bufReader = new BufferedReader(new FileReader(fileInput));     //BufferedReader variable that takes a FileReader as arguement that takes a .csv file arguement
            String employeeFileInput;                                                                   //String Variable to hold employee info
            // read until there is no more data
            while ((employeeFileInput = bufReader.readLine()) != null) {                                //in the midst of while loop read a line from .csv file and load it onto String Variable and check if it comes out null
                try {
                    String[] tokens = employeeFileInput.split(Pattern.quote("|"));                   //load the line onto a String array so that it can be partitioned by the pattern "|"

                    Employee employee = new Employee(Integer.parseInt(tokens[0]),tokens[1],Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3])); //create an Employee object that passes each String array element as an arguement

                    employeeList.add(employee);                                                         //load the Employee object onto the ArrayList for Employee
                }
                catch (NumberFormatException e2){                                                       //catch statement to skip the first line of the .csv file
                    System.out.println("");
                }
            }
            // close the stream and release the resources
            bufReader.close();                                                                          //bufferedReader close
        } catch (IOException e) {                                                                       //in case of an error with I/O
            System.out.println("error. no file exists. coming from: readFileLoadEmployees method");                                                                       //skip it
        }
    }

    public void printEmployeeInfo(){                                                                    //method to print employee info in a specific format
        System.out.println("Printing Employee Information:");
        System.out.println();
        for(Employee employee: employeeList){                                                           //for-each loop to iterate through
            System.out.printf("ID: %d|\tName: %s|\nGross Pay: %.2f\n\n",employee.getEmployeeID(),employee.getName(),employee.getGrossPay()); //print out with format so doubles are formatted as floats
        }

    }
}
