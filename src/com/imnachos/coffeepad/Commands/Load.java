package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Editor;
import com.imnachos.coffeepad.Engine.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class Load extends Command {

    public void actionPerformed(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if(Main.editor.isDocumentEmpty()){

            }

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    public void undoAction(ActionEvent event){

    }


}
