package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Copy extends Command {

    public void actionPerformed(ActionEvent event){
        if(Main.editor.canvas.getSelectedText() != null){
        	Main.editor.clipboard = Main.editor.canvas.getSelectedText();
        }
    }

    public void undoAction(ActionEvent event){

    }
}
