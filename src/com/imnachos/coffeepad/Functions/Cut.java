package com.imnachos.coffeepad.Functions;

import com.imnachos.coffeepad.Engine.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Cut extends Function{

    String selectedText;

    public void actionPerformed(ActionEvent event){
        Editor.clipboard = Editor.canvas.getSelectedText();
        Editor.canvas.replaceRange("", Editor.canvas.getSelectionStart(), Editor.canvas.getSelectionEnd());
        Editor.commandManager.addToUndoStack(this);
    }

    public void undoAction(ActionEvent event){

    }
}
