package com.imnachos.coffeepad.Memento;

public class Originator{

    private String textContent;


    public void set(String newContent) {
        this.textContent = newContent;
    }

    public Memento storeInMemento() {
        return new Memento(textContent);
    }


    public String restoreFromMemento(Memento memento) {
        textContent = memento.getSavedContent();
        return textContent;
    }

}