package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeStyle  extends Command {

    public void actionPerformed(ActionEvent event) {

        JMenuItem source = (JMenuItem) event.getSource();
        if (Main.editor.styledLanguages.containsKey(source.getName())) {

            Main.editor.setCurrentLanguageStyle(Main.editor.styledLanguages.get(source.getName()));
            System.out.println("Language: " + source.getName());
        }
    }

}