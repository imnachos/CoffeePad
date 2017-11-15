package com.imnachos.coffeepad.Commands;

import java.awt.event.ActionEvent;

import com.imnachos.coffeepad.Engine.Main;

public class Redo extends Command {

    public void actionPerformed(ActionEvent event){
        Main.editor.canvas.redo();
    }

}
