package com.plurasight;

public class Product {
        private int id;                                                         //class private variables
        private String name;
        private float price;

        public Product(int id, String name, float price) {                      //pre-constructed constructor
            this.id = id;
            this.name = name;
            this.price = price;
        }
        public Product(){                                                       //empty constructor
            this.id = 0;
            this.name = "default";
            this.price = 0;
        }
        public void displayInfo(){
            System.out.printf(" %s - Price: $%.2f \n",
                    getName(), getPrice());
        }
        public int getId() {                                                    //list of getters and setters for each class variable
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return this.price;
        }

        public void setPrice(float price) {
            this.price = price;
        }


    }
