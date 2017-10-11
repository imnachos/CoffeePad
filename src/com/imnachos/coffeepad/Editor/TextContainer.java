package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TextContainer extends JTextPane {

    private Document textContent;

    public TextContainer(){
        textContent = new Document();

        setContentType("text/html");
        setBorder(null);

        StyledDocument doc = getStyledDocument();

        Style style = addStyle("Red", null);
        StyleConstants.setForeground(style, Color.red);

        SimpleAttributeSet background = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Settings.DEFAULT_BACKGROUND);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), background, false);


    }

    public Document getTextContent () {
        return textContent;
    }

    public void setTextContent(Document textContent) {
        this.textContent = textContent;
    }

}
