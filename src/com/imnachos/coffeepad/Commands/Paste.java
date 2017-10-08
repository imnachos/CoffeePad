package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Paste extends Command {

    int caretPosition;
    String text;
    public void actionPerformed(ActionEvent event){
        text = Main.editor.clipboard;
        caretPosition = Main.editor.canvas.getCaretPosition();
        Main.editor.canvas.insert(text, caretPosition);

        System.out.println("DO PASTE " + text + " , pos: "+ caretPosition);
        Main.editor.commandManager.addToUndoStack(this);
    }

    public void undoAction(ActionEvent event){
        System.out.println("RunUndo PASTE " + caretPosition + "-" + caretPosition + text.length());
        Main.editor.canvas.replaceRange("", caretPosition, caretPosition + text.length());

    }

}
