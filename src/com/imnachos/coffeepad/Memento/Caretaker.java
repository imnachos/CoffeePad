package com.imnachos.coffeepad.Memento;

import java.util.ArrayList;

public class Caretaker {

    private ArrayList<Memento> states = new ArrayList<Memento>();

    public void addMemento(Memento memento){
        states.add(memento);
    }

    public Memento getMemento(int index){
        return states.get(index);
    }
}