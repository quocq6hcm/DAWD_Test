package com.example.quocq.dawd_test;

/**
 * Created by quocq on 04/17/2018.
 */

public class Book {
    int code;
    String title;
    int price;
    String author;

    public Book() {
    }

    public Book(int code, String title, int price, String author) {

        this.code = code;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public Book(String title, int price, String author) {
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return code + " - " + title + " - " + price;
    }
}
