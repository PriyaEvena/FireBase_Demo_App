package com.example.firebasedemoapp;

public class Quote{
    private String quote;
    private String author;

    public Quote(){

    }

    public Quote(String quote,String author){
        this.author = author;
        this.quote = quote;
    }

    public String getQuote(){
        return quote;
    }
    public String getAuthor(){
        return author;
    }
}