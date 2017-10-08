package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Cut extends Command {

    private String selectedText;
    private int selectionStart;
    private int selectionEnd;

    public void actionPerformed(ActionEvent event){
        if(Main.editor.canvas.getSelectedText() != null){
            selectedText = Main.editor.canvas.getSelectedText();
            Main.editor.clipboard = selectedText;
            selectionStart = Main.editor.canvas.getSelectionStart();
            selectionEnd = Main.editor.canvas.getSelectionEnd();

            Main.editor.canvas.replaceRange("", selectionStart, selectionEnd);
            System.out.println("Do CUT:  " + selectedText + " \n  Start: " +selectionStart + "\n  End: " + selectionEnd);
            Main.editor.commandManager.addToUndoStack(this);
        }
    }

    public void undoAction(ActionEvent event){
    	System.out.println("Undo CUT:  " + selectedText + " \n  Start: " +selectionStart + "\n  End: " + selectionEnd);
    	Main.editor.canvas.replaceRange(selectedText, selectionStart, selectionEnd);
    }
}
