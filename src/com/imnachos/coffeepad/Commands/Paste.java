package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Editor;

import java.awt.event.ActionEvent;

public class Paste extends Command {

    int caretPosition;
    String text;
    public void actionPerformed(ActionEvent event){
        text = Editor.clipboard;
        caretPosition = Editor.canvas.getCaretPosition();
        Editor.canvas.insert(text, caretPosition);

        System.out.println("DO PASTE " + text + " , pos: "+ caretPosition);
        Editor.commandManager.addToUndoStack(this);
    }

    public void undoAction(ActionEvent event){
        System.out.println("RunUndo PASTE " + caretPosition + "-" + caretPosition + text.length());
        Editor.canvas.replaceRange("", caretPosition, caretPosition + text.length());
    }

}
