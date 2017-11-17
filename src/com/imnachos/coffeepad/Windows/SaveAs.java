package com.imnachos.coffeepad.Windows;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Util.FileManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.imnachos.coffeepad.Engine.Main.editor;

public class SaveAs extends JFrame implements ActionListener {


    public SaveAs(){

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int choice = fileChooser.showSaveDialog(this);

        if(choice == JFileChooser.APPROVE_OPTION) {
        	File outputFile = fileChooser.getSelectedFile();
            boolean wasFileSaved = false;
        	try{
                 wasFileSaved = FileManager.saveFile(outputFile,
                        editor.canvas.getStyledDocument().getText(0, Main.editor.canvas.getStyledDocument().getLength()));
            }catch(Exception e){
        	    e.printStackTrace();
        	    //TODO EXCEPTION
            }

        	if(wasFileSaved){
        		editor.setFileSaved(true);
        		editor.setTitle(outputFile.getName());
        		editor.setCurrentFile(outputFile);
        	}
        }

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
