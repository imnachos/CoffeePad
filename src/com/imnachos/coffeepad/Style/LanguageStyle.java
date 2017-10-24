package com.imnachos.coffeepad.Style;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LanguageStyle {

    public Map<String, Color> keywordColors = new HashMap<String, Color>();
    public String languageName;

    public LanguageStyle(String languageName, Map<String, Color> keywordColors){

        this.languageName = languageName;
        this.keywordColors = keywordColors;
    }

    public Color getStyleForKey(String key){

        return this.keywordColors.get(key);

    }

    public boolean hasStyleForKey(String key){

        return this.keywordColors.containsKey(key);

    }
}
