package com.imnachos.coffeepad.Style;

import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Util.LogManager;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LanguageStyleManager {

    public static void loadStyles(){

        try {
            Files.newDirectoryStream(Paths.get("data/styles"),
                    path -> path.toString().endsWith(".ini"))
                    .forEach(LanguageStyleManager::processStyleFile);
        }catch(Exception exception){
            LogManager.printLog("IO exception");
            //TODO CREATE EXCEPTION
            exception.printStackTrace();
        }

    }

    private static Map<String, String> processStyleFile(Path filePath){
        LogManager.printLog("enter: " + filePath.getFileName());
        Map<String, String> keywordColors = new HashMap<String, String>();
        int equalPos;
        try{
            Scanner scanner = new Scanner(filePath.toFile());
            LogManager.printLog("toString:" +scanner.toString());
            while(scanner.hasNext()){

                String line = scanner.next();
                LogManager.printLog("line:" +line);
                equalPos = line.indexOf("=");
                keywordColors.put(line.substring(0,equalPos), line.substring(equalPos + 1, line.length()));
                System.out.println("key:" + line.substring(0,equalPos) + ", val: " + line.substring(equalPos + 1, line.length()) );

            }

        }catch(Exception exception){
            LogManager.printLog("Scanner exception");
            exception.printStackTrace();
            //TODO CREATE EXCEPTION

        }


        return keywordColors;
    }

    public static void setStyleForLanguage(TextContainer textContainer){

            Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

            Style style = textContainer.addStyle("keyword", def);
            StyleConstants.setForeground(style, Color.RED);
            StyleConstants.setFontFamily(style, "monospace");
    }
}
