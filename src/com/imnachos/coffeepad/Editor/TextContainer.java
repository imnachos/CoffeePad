package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Listener.TextListener;
import com.imnachos.coffeepad.Style.LanguageStyle;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/*
    Simple JTextPane child class
 */
public class TextContainer extends JTextPane {

    public TextListener textListener;

    public TextContainer(){

        setContentType("text/html");
        setBorder(null);
        textListener = new TextListener(this);

        //TODO REFACTOR THIS, MOVE TO LANGUAGESTYLEMANAGER
        Map<String, Color> emptyColorMap = new HashMap<String, Color>();
        LanguageStyle defaultStyle = new LanguageStyle("default", emptyColorMap);
        textListener.setCurrentStyle(defaultStyle);

        ((AbstractDocument) this.getStyledDocument()).setDocumentFilter(textListener);
        SimpleAttributeSet background = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Settings.DEFAULT_BACKGROUND);
        setCaretColor(Settings.DEFAULT_COLOR);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), background, false);


    }





}
