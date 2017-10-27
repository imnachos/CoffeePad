package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import java.awt.event.ActionEvent;

public class Cut extends Command {


    public void actionPerformed(ActionEvent event){

       /* if(Main.editor.textContainer.getSelectedText() != null){
            Main.editor.clipboard = Main.editor.textContainer.getSelectedText();
            int startingPos = Main.editor.textContainer.getCaretPosition() - Main.editor.textContainer.getSelectedText().length();

            try{
                Main.editor.textContainer.getDocument().insertString(caretPosition, clipboard, null);
            }catch (Exception exception){
                exception.printStackTrace();
                //TODO EXCEPTION
            }

        }
*/
       Main.editor.textContainer.cut();
    }

    public void undoAction(ActionEvent event){

    }
}
