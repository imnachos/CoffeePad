package com.imnachos.coffeepad.Listener;


import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.text.*;
import java.awt.*;

public class TextListener extends DocumentFilter {

    private TextContainer textPane;
    private StyledDocument styledDocument;

    StyleContext styleContext;
    AttributeSet testColor;

    public TextListener (TextContainer container){

        textPane = container;
        styledDocument =  textPane.getStyledDocument();
        MutableAttributeSet inputAttributes = textPane.getInputAttributes();

        StyleConstants.setForeground(inputAttributes, Settings.DEFAULT_COLOR);
        StyleConstants.setFontFamily(inputAttributes, Font.MONOSPACED);
        styledDocument.setCharacterAttributes(0, styledDocument.getLength() + 1, inputAttributes, false);

        styleContext = new StyleContext();
        testColor = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.RED);


    }


    //TODO EXCEPTIONS

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        super.insertString(fb, offset, text, attr);
    }

    //TODO EXCEPTIONS

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    //TODO EXCEPTIONS

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

        String match = "void";

        super.replace(fb, offset, length, text, attrs);

        int startIndex = offset - match.length();
        if (startIndex >= 0) {

            String last = fb.getDocument().getText(startIndex, match.length()).trim();

            if (last.equalsIgnoreCase(match)) {

                styledDocument.setCharacterAttributes(startIndex, startIndex + match.length(), testColor, false);

            }
        }
    }

}
