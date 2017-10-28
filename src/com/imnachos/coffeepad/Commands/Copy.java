package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Copy extends Command {

    public void actionPerformed(ActionEvent event){
        Main.editor.canvas.copy();
    }

    public void undoAction(ActionEvent event){

    }
}
