package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Editor;

import java.awt.event.ActionEvent;

public class Cut extends Command {

    private String selectedText;
    private int selectionStart;
    private int selectionEnd;

    public void actionPerformed(ActionEvent event){
        if(Editor.canvas.getSelectedText() != null){
            selectedText = Editor.canvas.getSelectedText();
            Editor.clipboard = selectedText;
            selectionStart = Editor.canvas.getSelectionStart();
            selectionEnd = Editor.canvas.getSelectionEnd();

            Editor.canvas.replaceRange("", selectionStart, selectionEnd);
            System.out.println("DO CUT " + selectedText + selectionStart + selectionEnd);
            Editor.commandManager.addToUndoStack(this);
        }
    }

    public void undoAction(ActionEvent event){
        System.out.println("RunUndo CUT " + selectedText + selectionStart + selectionEnd);
        Editor.canvas.replaceRange(selectedText, selectionStart, selectionEnd);
    }
}
