package com.imnachos.coffeepad.Listener;


import Memento.Caretaker;
import Memento.Originator;
import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Style.LanguageStyle;
import com.imnachos.coffeepad.Util.FormatManager;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

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

        List lastWord;
        lastWord = checkLastWord();
        applyFormat(lastWord);
        processAutoComplete(lastWord);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        //checkLastWord();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
    }

    private void applyFormat(List lastWord){
        if(!lastWord.isEmpty() && lastWord.size() == 3) {
            String word = (String) lastWord.get(0);
            int wordStart = (int) lastWord.get(1);
            int wordEnd = (int) lastWord.get(2);

            Color wordStyle = currentStyle.getStyleForKey(word);
            //AttributeSet styleAttributes = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, wordStyle);
            //styledDocument.setCharacterAttributes(wordStart, wordEnd, styleAttributes, false);

        }
    }
    
    private List checkLastWord() {

        String lastWord = "";
        int wordStart;
        int wordEnd;
        int caretPosition = canvas.getCaretPosition();
        List returnVal = new ArrayList();

        try {
            wordStart = Utilities.getWordStart(canvas, caretPosition);
            wordEnd = Utilities.getWordEnd(canvas, caretPosition);
            lastWord = canvas.getDocument().getText(wordStart, wordEnd - wordStart);

            if(lastWord.equals(" ") && caretPosition != 0){
                wordStart = Utilities.getWordStart(canvas, caretPosition -1);
                wordEnd = Utilities.getWordEnd(canvas, caretPosition -1 );
                lastWord = canvas.getDocument().getText(wordStart, wordEnd - wordStart);
                returnVal.add(lastWord);
                returnVal.add(wordStart);
                returnVal.add(wordEnd);

                return returnVal;
            }

        } catch (Exception e) {
            //TODO EXCEPTION
            e.printStackTrace();
        }

        return returnVal;
    }

    private void processAutoComplete(List lastWord){
        if(!lastWord.isEmpty()){
            String word = (String) lastWord.get(0);

            if(word.length() == 1){
                if(word.equals("{")){
                    canvas.setCaretPosition(1);
                }

                if(word.equals("(")){
                    canvas.setCaretPosition(canvas.getCaretPosition()+1);
                }
            }
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
