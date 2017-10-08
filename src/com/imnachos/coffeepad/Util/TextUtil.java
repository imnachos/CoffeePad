package com.imnachos.coffeepad.Util;

import java.awt.Font;

import com.imnachos.coffeepad.Engine.Main;

public class TextUtil {

	public static void changeStyle(int textStyle){
		
    	if(Main.editor.getCursorStyle() == textStyle){
    		Main.editor.setCursorStyle(Font.PLAIN);
    	}else{
    		Main.editor.setCursorStyle(textStyle);
    	}
		
		if(Main.editor.canvas.getSelectedText() != ""){
			
			
		}else{
			System.out.println("nada");
	
		}
	}
	
}
