package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Util.FileManager;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.io.File;

public class Open extends Command {

    public void actionPerformed(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int choice = fileChooser.showOpenDialog(null);

        if(choice == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if(Main.editor.canvas.isDocumentEmpty() && selectedFile.isFile()){
            	Main.editor.canvas.setText(FileManager.openFile(selectedFile));
                Main.editor.setTitle(selectedFile.getName());
            }else{
            	
            }

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    public void undoAction(ActionEvent event){

    }


}
