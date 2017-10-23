package com.imnachos.coffeepad.Engine;

import com.imnachos.coffeepad.Editor.Editor;
import com.imnachos.coffeepad.Style.LanguageStyleManager;

import java.awt.*;

public class Main{

    public static Editor editor;
    
    public static void main(String[] args) {
        calculateWindowSize();
        editor = new Editor();
        LanguageStyleManager.loadStyles();
    }

    private static void calculateWindowSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double resolutionWidth = screenSize.getWidth();
        double resolutionHeight = screenSize.getHeight();

        if(resolutionWidth / 2 != 0){
            Settings.WINDOW_WIDTH = (int) resolutionWidth / 2;
        }else{
            Settings.WINDOW_WIDTH = Settings.DEFAULT_WINDOW_WIDTH;
        }

        if(resolutionWidth / 2 != 0){
            Settings.WINDOW_HEIGHT = (int) (resolutionHeight * 0.7);
        }else{
            Settings.WINDOW_HEIGHT = Settings.DEFAULT_WINDOW_HEIGHT;
        }
    }

}