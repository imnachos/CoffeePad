package com.imnachos.coffeepad.Engine;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Document extends JTextPane {

    public Document(JFrame container){
        super();
        setContentType("text/html");
        setForeground(Settings.DEFAULT_BACKGROUND);

        setSize(container.getSize());

        SimpleAttributeSet background = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Settings.DEFAULT_BACKGROUND);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), background, false);



    }
}
