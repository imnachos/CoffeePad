package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Paste extends Command {

    public void actionPerformed(ActionEvent event){
        Main.editor.canvas.createMementoState();
        Main.editor.canvas.paste();
    }
}
