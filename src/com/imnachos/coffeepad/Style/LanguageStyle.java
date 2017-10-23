package com.imnachos.coffeepad.Style;

import java.util.HashMap;
import java.util.Map;

public class LanguageStyle {

    public Map<String, String> keywordColors = new HashMap<String, String>();
    public String languageName;

    public LanguageStyle(String languageName, Map<String, String> keywordColors){

        this.languageName = languageName;
        this.keywordColors = keywordColors;
    }
}
