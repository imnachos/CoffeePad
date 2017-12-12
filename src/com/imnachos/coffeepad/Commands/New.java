package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class New extends Command {

    public void actionPerformed(ActionEvent event){

        if(Main.editor.isFileSaved()){
            Main.editor.canvas = new TextContainer();
        }else{
            String objButtons[] = {"Exit", "Save"};
            int choice = JOptionPane.showOptionDialog(null, "Exit without saving?", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, objButtons, objButtons[1]);

            if(choice == JOptionPane.YES_OPTION){
                Main.editor.canvas.setText("");
            }
        }
    }

}
