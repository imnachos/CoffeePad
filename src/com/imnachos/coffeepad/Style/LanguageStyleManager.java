package com.imnachos.coffeepad.Style;

import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LanguageStyleManager {

    private static Map<String, LanguageStyle> styledLanguages; //TODO REFACTOR THIS SYSTEM

    /**
     * Starts the parsing of the files and returns a Map that consists of Language-LanguageStyle
     * @return
     */
    public static Map<String, LanguageStyle> loadStyles(){

        styledLanguages = new HashMap<String, LanguageStyle>();
        try {
            Files.newDirectoryStream(Paths.get("data/styles"),
                    path -> path.toString().endsWith(".ini"))
                    .forEach(LanguageStyleManager::processStyleFile);
        }catch(IOException e){
            JOptionPane.showMessageDialog(new JFrame(), Settings.ERROR_IO_EXCEPTION, Settings.ERROR_WINDOW_TITLE, JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            e.printStackTrace();
        }
        return styledLanguages;

    }


    /**
     * Parses all of the styles in the /data/styles/ folder.
     * It creates a key-color map.
     * @param filePath
     */
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

        }catch(IOException e){
            JOptionPane.showMessageDialog(new JFrame(), Settings.ERROR_IO_EXCEPTION, Settings.ERROR_WINDOW_TITLE, JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            e.printStackTrace();

        }

        generateLanguageStyle(languageName, keywordColors);

    }

    /**
     * Creates the language style based on the key-color and adds the LanguageStyle to styledLanguages.
     * @param language
     * @param keywordColors
     */
    private static void generateLanguageStyle(String language, Map<String, Color> keywordColors){
        LanguageStyle languageStyle = new LanguageStyle(language, keywordColors);
        styledLanguages.putIfAbsent(language, languageStyle);
    }


}
