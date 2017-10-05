package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Editor;

import java.awt.event.ActionEvent;

public class Selectall extends Command {

    public void actionPerformed(ActionEvent event){
        Editor.canvas.selectAll();
    }

    public void undoAction(ActionEvent event){

    }
}
