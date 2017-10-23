package com.imnachos.coffeepad.Style;

import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Util.LogManager;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class LanguageStyleManager {

    public static void loadStyles(){

        try {
            Files.newDirectoryStream(Paths.get("/data/styles/."),
                    path -> path.toString().endsWith(".ini"))
                    .forEach(System.out::println);
        }catch(Exception exception){
            LogManager.printLog("IO exception");
            //TODO CREATE EXCEPTION

        }

    }

    public static void setStyleForLanguage(TextContainer textContainer){

            Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

            Style style = textContainer.addStyle("keyword", def);
            StyleConstants.setForeground(style, Color.RED);
            StyleConstants.setFontFamily(style, "monospace");
    }
}
