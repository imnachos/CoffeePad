package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Undo extends Command {

    public void actionPerformed(ActionEvent event){
    	Main.editor.commandManager.undo();
    }

    public void undoAction(ActionEvent event){

    }
}
