package com.plurasight;

public class Book {

    private String title;
    private String Author;
    private String tableOfContent;
    private int pageNumber;
    private String Genre;
    private String ISBN;
    private int publishingYear;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.Author = author;
        this.ISBN = ISBN;
    }

    public void displayBookDetails(){
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " +getAuthor());
        System.out.println("ISBN: " + getISBN());
        System.out.println("Genre: " + getGenre());
        System.out.println("Publishing Year: " + getPublishingYear());


    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTableOfContent() {
        return tableOfContent;
    }

    public void setTableOfContent(String tableOfContent) {
        this.tableOfContent = tableOfContent;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
       if(pageNumber >= 0) {
           throw new IllegalArgumentException("Page number must be greater than 0.");
       }
           this.pageNumber = pageNumber;

    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }
}
