package com.imnachos.coffeepad.Util;

import com.imnachos.coffeepad.Functions.Function;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CommandManager {

        private ArrayList<Function> undoStack = new ArrayList<Function>();
        private ArrayList<Function> redoStack = new ArrayList<Function>();

        public CommandManager(){

        }

        public void undo() {
                if (undoStack.size() > 0) {
                        Function command = undoStack.remove(undoStack.size() - 1);
                        command.undoAction(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
        }

        public void redo() {
                if (redoStack.size() > 0) {
                        Function command = redoStack.remove(redoStack.size() - 1);
                        command.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
        }

        public void addToUndoStack(Function command) {
                undoStack.add(command);
        }

        public void addToRedoStack(Function command) {
                redoStack.add(command);
        }

}
