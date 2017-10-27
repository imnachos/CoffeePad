package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Paste extends Command {


    int caretPosition;
    String clipboard;

    public void actionPerformed(ActionEvent event){

        /*caretPosition = Main.editor.textContainer.getCaretPosition();
        clipboard = Main.editor.clipboard;
        MutableAttributeSet inputAttributes = Main.editor.textContainer.getInputAttributes();

        StyleConstants.setForeground(inputAttributes, Settings.DEFAULT_COLOR);
        StyleConstants.setFontFamily(inputAttributes, Font.MONOSPACED);


        try{
            Main.editor.textContainer.getDocument().insertString(caretPosition, clipboard, inputAttributes);
        }catch (Exception exception){
            exception.printStackTrace();
            //TODO EXCEPTION
        }
        */
        Main.editor.textContainer.paste();



    }

    public void undoAction(ActionEvent event){


    }

}
