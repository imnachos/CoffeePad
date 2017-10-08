package com.imnachos.coffeepad.Commands;

import java.awt.event.ActionEvent;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Util.FileManager;

public class Save extends Command {

    public void actionPerformed(ActionEvent event){
    	
    	if(!Main.editor.isFileSaved()){
    		new Saveas();
    	}else{
    		FileManager.saveFile(Main.editor.getCurrentFile(), Main.editor.canvas.getText());
    	}
    }

    public void undoAction(ActionEvent event){

    }
}
