package com.imnachos.coffeepad.Listener;


import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Style.LanguageStyle;

import javax.swing.text.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TextListener extends DocumentFilter {

    private TextContainer textPane;
    private StyledDocument styledDocument;
    
    private LanguageStyle currentStyle;
    private StyleContext styleContext;

    public TextListener (TextContainer container){

        textPane = container;
        styledDocument =  textPane.getStyledDocument();
        MutableAttributeSet inputAttributes = textPane.getInputAttributes();

        StyleConstants.setForeground(inputAttributes, Settings.DEFAULT_COLOR);
        StyleConstants.setFontFamily(inputAttributes, Font.MONOSPACED);
        styledDocument.setCharacterAttributes(0, styledDocument.getLength() + 1, inputAttributes, false);

        styleContext = new StyleContext();

    }


    //TODO EXCEPTIONS

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        super.insertString(fb, offset, text, attr);
        System.out.println("text: " + text);
        if(text.equals("{")){
            super.insertString(fb, offset, "}", attr);
        }
    }

    //TODO EXCEPTIONS

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
        System.out.println("remove.");
    }

    //TODO EXCEPTIONS

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        super.replace(fb, offset, length, text, attrs);

        processAutoComplete(fb, offset, length, text, attrs);

        setTextStyle(fb, offset, length, text, attrs);

    }

    private void setTextStyle(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws  BadLocationException{

        currentStyle.keywordColors.forEach((key, color) -> {
            int startIndex = offset - key.length();
            if (startIndex >= 0) {

                try {
                    String last = fb.getDocument().getText(startIndex, key.length()).trim();

                    //TODO EXCEPTIONS
                    if (currentStyle.hasStyleForKey(last)) {

                        //TODO USE STYLES
                        AttributeSet styleAttributes = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, color);
                        styledDocument.setCharacterAttributes(startIndex, startIndex + key.length(), styleAttributes, false);
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                    //TODO EXCEPTION
                }
            }

        });

    }

    private void processAutoComplete(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws  BadLocationException{

        if(text.equals("{")){
            super.replace(fb, offset + 1, length, "}", attrs);
            textPane.setCaretPosition(offset + 1);
        }
        if(text.equals("(")){
            super.replace(fb, offset + 1, length, ")", attrs);
            textPane.setCaretPosition(offset + 1);
        }

    }
    
    public void setCurrentStyle(LanguageStyle currentStyle) {
        this.currentStyle = currentStyle;
        styleContext = new StyleContext();

        System.out.println("setCurrentStyle to: " + currentStyle.languageName);
        currentStyle.keywordColors.forEach((key, value) -> {

        Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
        Style style = textPane.addStyle(key, defaultStyle);
        StyleConstants.setForeground(style, value);
        StyleConstants.setFontFamily(style, Font.MONOSPACED);
        StyleConstants.setFontSize(style, 14);
        styleContext.addStyle(key, style);

        AttributeSet styleAttributes = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, value);
        style.setResolveParent(styleAttributes);

        });


    }


    

}
