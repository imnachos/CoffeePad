package com.imnachos.coffeepad.Listener;


import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Style.LanguageStyle;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;

public class TextListener implements DocumentListener {

    private TextContainer canvas;
    private StyledDocument styledDocument;

    public LanguageStyle currentStyle;
    private StyleContext styleContext;

    public TextListener(TextContainer container) {

        canvas = container;
        styledDocument = canvas.getStyledDocument();
        MutableAttributeSet inputAttributes = canvas.getInputAttributes();

        StyleConstants.setForeground(inputAttributes, Settings.DEFAULT_COLOR);
        StyleConstants.setFontFamily(inputAttributes, Font.MONOSPACED);
        styledDocument.setCharacterAttributes(0, styledDocument.getLength() + 1, inputAttributes, false);

        styleContext = new StyleContext();

    }


    //TODO EXCEPTIONS

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        String lastWord;
        lastWord = checkLastWord();
        processAutoComplete(lastWord);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        checkLastWord();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        String lastWord = checkLastWord();
        //int startIndex = offset - key.length();

        if("hola".equals("cas")){
            if(!lastWord.equals(" ")){
                Color wordStyle = currentStyle.getStyleForKey(lastWord);
                AttributeSet styleAttributes = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, wordStyle);
                // styledDocument.setCharacterAttributes(startIndex, startIndex + key.length(), styleAttributes, false);

            }
        }

    }

    private String checkLastWord() {

        String lastWord = "";
        int wordStart;
        int wordEnd;

        try {
            wordStart = Utilities.getWordStart(canvas, canvas.getCaretPosition());
            wordEnd = Utilities.getWordEnd(canvas, canvas.getCaretPosition());
            lastWord = canvas.getDocument().getText(wordStart, wordEnd - wordStart);


            if(lastWord.equals(" ")){
                wordStart = Utilities.getWordStart(canvas, canvas.getCaretPosition() -1);
                wordEnd = Utilities.getWordEnd(canvas, canvas.getCaretPosition() -1 );
                lastWord = canvas.getDocument().getText(wordStart, wordEnd - wordStart);
                System.out.println("Last word: " + lastWord);
            }

        } catch (Exception e) {
            //TODO EXCEPTION
            e.printStackTrace();
        }

        return lastWord;
    }

    private void processAutoComplete(String lastCharacter){

        if(lastCharacter.equals("{")){
            canvas.setCaretPosition(1);
        }

        if(lastCharacter.equals("(")){

            canvas.setCaretPosition(1);
        }

    }

    public void setCurrentStyle(LanguageStyle currentStyle) {
        this.currentStyle = currentStyle;
        styleContext = new StyleContext();

        System.out.println("setCurrentStyle to: " + currentStyle.languageName);

        currentStyle.keywordColors.forEach((key, value) -> {

            Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
            Style style = canvas.addStyle(key, defaultStyle);
            StyleConstants.setForeground(style, value);
            StyleConstants.setFontFamily(style, Font.MONOSPACED);
            StyleConstants.setFontSize(style, 14);
            styleContext.addStyle(key, style);

            AttributeSet styleAttributes = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, value);
            style.setResolveParent(styleAttributes);

        });
    }

}
