package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Engine.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;

public class Open extends Command {

    public void actionPerformed(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if(Main.editor.isDocumentEmpty() && selectedFile.isFile()){

                try{
                    byte[] contents = Files.readAllBytes(selectedFile.toPath());
                    String fileContent = new String(contents, "ISO-8859-1");
                    Main.editor.canvas.setText(fileContent);
                    Main.editor.setTitle(selectedFile.getName());
                }catch(Exception e){
                    //TODO not gonna happen
                }
            }

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    public void undoAction(ActionEvent event){

    }


}
