package com.imnachos.coffeepad.Memento;

public class Memento {

    private String content;

    public Memento(String savedContent){
        content = savedContent;
    }

    public String getSavedContent(){
        return content;
    }
}
