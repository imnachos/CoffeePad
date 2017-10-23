package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Listener.TextListener;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;


/*
    Simple JTextPane child class
 */
public class TextContainer extends JTextPane {

    public TextContainer(){

        setContentType("text/html");
        setBorder(null);

        ((AbstractDocument) this.getStyledDocument()).setDocumentFilter(new TextListener(this));


        SimpleAttributeSet background = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Settings.DEFAULT_BACKGROUND);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), background, false);


    }

}
