package com.imnachos.coffeepad.Style;

import com.imnachos.coffeepad.Editor.Editor;
import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Util.LogManager;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class LanguageStyleManager {

    private static Map<String, LanguageStyle> styledLanguages; //TODO REFACTOR THIS SYSTEM

    public static Map<String, LanguageStyle> loadStyles(){

        styledLanguages = new HashMap<String, LanguageStyle>();
        try {
            Files.newDirectoryStream(Paths.get("data/styles"),
                    path -> path.toString().endsWith(".ini"))
                    .forEach(LanguageStyleManager::processStyleFile);
        }catch(Exception exception){
            //TODO CREATE EXCEPTION
            exception.printStackTrace();
        }


        return styledLanguages;

    }

    private static void processStyleFile(Path filePath){
        String languageName = filePath.getFileName().toString().substring(0, filePath.getFileName().toString().indexOf("."));
        Map<String, Color> keywordColors = new HashMap<String, Color>();
        int equalPos;
        String textLine;

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toFile()));
            textLine = bufferedReader.readLine();

            while (textLine != null){
                equalPos = textLine.indexOf("=");

                if(equalPos != -1){
                    String keyword = (textLine.substring(0, equalPos)).trim();
                    String colorString = (textLine.substring(equalPos + 1, textLine.length())).trim();

                    if(!colorString.isEmpty()){
                        String[] separated = colorString.split("-");
                        if(separated.length == 3){
                            int red = Integer.parseInt(separated[0]);
                            int green = Integer.parseInt(separated[1]);
                            int blue = Integer.parseInt(separated[2]);
                            Color styleColor = new Color(red, green, blue);
                            keywordColors.put(keyword, styleColor);
                        }

                    }
                }
                textLine = bufferedReader.readLine();
            }

        }catch(Exception exception){
            exception.printStackTrace();
            //TODO CREATE EXCEPTION

        }

        generateLanguageStyle(languageName, keywordColors);

    }

    private static void generateLanguageStyle(String language, Map<String, Color> keywordColors){

        LanguageStyle languageStyle = new LanguageStyle(language, keywordColors);
        styledLanguages.putIfAbsent(language, languageStyle);

    }

    public static void setStyleForLanguage(TextContainer textContainer){


    }
}
