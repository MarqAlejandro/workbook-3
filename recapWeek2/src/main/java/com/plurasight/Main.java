package com.plurasight;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Book> bookList = new ArrayList<>();

        Book book1 = new Book("48 Laws of Power", "Robert Greene", "123456789");
        Book book2 = new Book("90 Rules for Entrepreneurs the codex hustle", "Marnus Broodryk", "123456987");
        Book book3 = new Book("The Heroine Diaries","Nikki Six","123465879");

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        for(Book book : bookList){
            book.displayBookDetails();
            System.out.println();
        }

    }
}