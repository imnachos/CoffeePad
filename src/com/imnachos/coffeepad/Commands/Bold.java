package com.imnachos.coffeepad.Commands;

import com.imnachos.coffeepad.Util.TextUtil;

import java.awt.Font;
import java.awt.event.ActionEvent;

public class Bold extends Command {

    public void actionPerformed(ActionEvent event){
    	
    	TextUtil.changeStyle(Font.BOLD);
    }

    public void undoAction(ActionEvent event){

    }
}
