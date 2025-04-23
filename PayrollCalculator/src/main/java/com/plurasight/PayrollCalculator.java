

package com.plurasight;

import java.io.*;
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
        //printEmployeeInfo();
        fileWriteTo();
    }

    public void fileInputSelection(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the employee file to process: ");
        fileInput = scanner.nextLine();
    }


    public void readFileLoadEmployees() {                                                       //method to load employees info from .csv file onto ArrayList
        boolean exitWhileLoop = false;
        while(!exitWhileLoop){
        try {
            System.out.println("Loading Employees' Information");

            BufferedReader bufReader = new BufferedReader(new FileReader(fileInput));     //BufferedReader variable that takes a FileReader as arguement that takes a .csv file arguement
            String employeeFileInput;                                                                   //String Variable to hold employee info

            bufReader.readLine();                                                                       //skip the first line, assumes that the first line is headers and garbage data

            while ((employeeFileInput = bufReader.readLine()) != null) {                                //in the midst of while loop read a line from .csv file and load it onto String Variable and check if it comes out null
                    String[] tokens = employeeFileInput.split(Pattern.quote("|"));                   //load the line onto a String array so that it can be partitioned by the pattern "|"
                    Employee employee = new Employee();                                                 //create an empty Employee object
                    if(tokens.length == 4) {
                        employee.setEmployeeID(Integer.parseInt(tokens[0]));
                        employee.setName(tokens[1]);
                        employee.setHoursWorked(Double.parseDouble(tokens[2]));
                        employee.setPayRate(Double.parseDouble(tokens[3]));                             //will load and set all employees information only if there is exactly 4 elements in the String Array
                    }
                    else{
                        System.out.println("error: missing or too much information on a given employee");
                    }

                    employeeList.add(employee);                                                         //load the Employee object onto the ArrayList for Employee
            }

            bufReader.close();                                                                          //bufferedReader close
            exitWhileLoop = true;
            } catch (IOException e) {                                                                       //in case of an error with I/O
            System.out.println("error. no file exists. please try again");                              //catch to loop back to beginning
            fileInputSelection();
            }
        }
    }

    public void fileWriteTo(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of the file to write to: ");
            writeTo = scanner.nextLine();


            if(writeTo.contains(".csv")) {

                FileWriter writer = new FileWriter(writeTo);


                writer.write("Employee Payroll Information:\n");
                for (Employee employee : employeeList) {                                                           //for-each loop to iterate through
                    writer.write("ID: " + employee.getEmployeeID() + " |\tName: " + employee.getName() + " |\n Gross Pay: " + employee.getGrossPay() + "\n");
                }


                writer.close();
            }
            else if(writeTo.contains(".json")){

                FileWriter writer = new FileWriter(writeTo);


                writer.write("[\n");

                for (Employee employee : employeeList) {                                                           //for-each loop to iterate through
                    writer.write("   {\"ID\": " + employee.getEmployeeID() + ", \"Name\" : \"" + employee.getName() + "\", \"Gross Pay\" : " + employee.getGrossPay());
                    if(employee.getEmployeeID() != employeeList.get(employeeList.size()-1).getEmployeeID()){
                        writer.write("},\n");
                    }
                    else{
                        writer.write("}\n");
                    }
                }
                writer.write("]");

                writer.close();
            }
        }
        catch (IOException e) {
            System.out.println("ERROR:  An unexpected error occurred");
            e.printStackTrace();
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
