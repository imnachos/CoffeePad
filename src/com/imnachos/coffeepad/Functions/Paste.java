package com.imnachos.coffeepad.Functions;

import com.imnachos.coffeepad.Engine.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Paste extends Function{

    public void actionPerformed(ActionEvent event){
        Editor.canvas.insert(Editor.clipboard, Editor.canvas.getCaretPosition());
    }

    public void undoAction(ActionEvent event){

    }

}
