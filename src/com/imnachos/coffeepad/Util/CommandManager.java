package com.imnachos.coffeepad.Util;

import com.imnachos.coffeepad.Commands.Command;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/*
    Class that manages the stacks of commands.
 */
public class CommandManager {

        private ArrayList<Command> undoStack = new ArrayList<Command>();
        private ArrayList<Command> redoStack = new ArrayList<Command>();

        public CommandManager(){

        }

        public void undo() {
            if (undoStack.size() > 0) {
                Command command = undoStack.remove(undoStack.size() - 1);
                System.out.println("Undo command: " + command.toString());
                command.undoAction(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        }

        public void redo() {
            if (redoStack.size() > 0) {
                Command command = redoStack.remove(redoStack.size() - 1);
                System.out.println("Redo command: " + command.toString());
                command.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        }

        public void addToUndoStack(Command command) {
            undoStack.add(command);
        }

        public void addToRedoStack(Command command) {
            redoStack.add(command);
        }

}
