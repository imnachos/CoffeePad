package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Cut extends Command {


    public void actionPerformed(ActionEvent event){
       Main.editor.canvas.cut();
    }

    public void undoAction(ActionEvent event){

    }
}
