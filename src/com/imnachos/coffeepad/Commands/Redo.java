package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Editor;

import java.awt.event.ActionEvent;

public class Redo extends Command {

    public void actionPerformed(ActionEvent event){
        if(Editor.undoManager.canRedo()){
            Editor.undoManager.redo();
        }
    }

    public void undoAction(ActionEvent event){

    }
}
