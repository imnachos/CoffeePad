package com.imnachos.coffeepad.Windows;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Util.FileManager;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveAs extends JFrame implements ActionListener {


    public SaveAs(){

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int choice = fileChooser.showSaveDialog(this);

        if(choice == JFileChooser.APPROVE_OPTION) {
        	File outputFile = fileChooser.getSelectedFile();
        	boolean wasFileSaved = FileManager.saveFile(outputFile, Main.editor.canvas.getText());
        	
        	if(wasFileSaved){
        		Main.editor.setFileSaved(true);
        		Main.editor.setTitle(outputFile.getName());
        		Main.editor.setCurrentFile(outputFile);
        	}
        }

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
