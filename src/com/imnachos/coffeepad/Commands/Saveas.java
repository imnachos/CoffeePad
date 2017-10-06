package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Windows.SaveAs;

import java.awt.event.ActionEvent;

public class Saveas extends Command {

    public void actionPerformed(ActionEvent event){

        new SaveAs();
    }

    public void undoAction(ActionEvent event){

    }
}