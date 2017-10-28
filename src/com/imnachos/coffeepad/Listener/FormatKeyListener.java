package com.imnachos.coffeepad.Listener;

import com.imnachos.coffeepad.Engine.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FormatKeyListener extends AbstractAction {


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("Key space.");

        /*
        String[] wordsArray = Main.editor.textContainer.getText().split("\\s+");
        String lastWord = wordsArray[wordsArray.length - 4];

        System.out.println("last word: " + lastWord);

        if(Main.editor.textContainer.textListener.currentStyle.keywordColors.containsKey(lastWord)){

            System.out.println("map contains: " + lastWord);
        }
    */
    }
}
