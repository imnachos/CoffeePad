package com.imnachos.coffeepad.Style;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents a style for a specific language.
 * Structure is a map of Keys(word) and colors.
 */
public class LanguageStyle {

    public Map<String, Color> keywordColors = new HashMap<String, Color>();
    public String languageName;

    public LanguageStyle(String languageName, Map<String, Color> keywordColors){
        this.languageName = languageName;
        this.keywordColors = keywordColors;
    }

    /**
     * Returns the style for a particular key
     * @param key
     * @return
     */
    public Color getStyleForKey(String key){
        return this.keywordColors.get(key);
    }

    /**
     * Returns if a LanguageStyle has a particular key
     * @param key
     * @return
     */
    public boolean hasStyleForKey(String key){
        return this.keywordColors.containsKey(key);
    }
}
