package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Editor;

import java.awt.event.ActionEvent;

public class Copy extends Command {

    public void actionPerformed(ActionEvent event){
        Editor.clipboard = Editor.canvas.getSelectedText();
    }

    public void undoAction(ActionEvent event){

    }
}
