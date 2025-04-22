package com.plurasight;



public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, User!");

        PayrollCalculator prCalculator = new PayrollCalculator();   //PayRollCalculator object instantiated

        prCalculator.findLoadAndPrint();                                //call main method of PayRollCalculator


    }
}