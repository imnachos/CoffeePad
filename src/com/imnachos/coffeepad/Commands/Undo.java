package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Editor;

import java.awt.event.ActionEvent;

public class Undo extends Command {

    public void actionPerformed(ActionEvent event){
        Editor.commandManager.undo();
    }

    public void undoAction(ActionEvent event){

    }
}
