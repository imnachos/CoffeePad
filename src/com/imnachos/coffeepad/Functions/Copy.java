package com.imnachos.coffeepad.Functions;

import com.imnachos.coffeepad.Engine.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Copy extends Function {

    public void actionPerformed(ActionEvent event){
        Editor.clipboard = Editor.canvas.getSelectedText();
    }

    public void undoAction(ActionEvent event){

    }
}
