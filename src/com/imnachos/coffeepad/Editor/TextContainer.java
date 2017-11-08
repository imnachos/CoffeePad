package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Listener.FormatKeyListener;
import com.imnachos.coffeepad.Listener.TextListener;
import com.imnachos.coffeepad.Style.LanguageStyle;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


/*
    Simple JTextPane child class
 */
public class TextContainer extends JTextPane{

    public TextListener textListener;
    private LanguageStyle defaultStyle;

    public TextContainer() {

        setContentType("text/html");
        setBorder(null);
        textListener = new TextListener(this);

        setForeground(Settings.DEFAULT_COLOR);
        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), new FormatKeyListener());

        //TODO REFACTOR THIS, MOVE TO LANGUAGESTYLEMANAGER
        Map<String, Color> emptyColorMap = new HashMap<String, Color>();
        defaultStyle = new LanguageStyle("default", emptyColorMap);
        textListener.setCurrentStyle(defaultStyle);


        ((AbstractDocument) getStyledDocument()).addDocumentListener(textListener);
        SimpleAttributeSet background = new SimpleAttributeSet();
        SimpleAttributeSet foreground = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Settings.DEFAULT_BACKGROUND);
        StyleConstants.setForeground(foreground, Settings.DEFAULT_BACKGROUND);
        setCaretColor(Settings.DEFAULT_COLOR);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), background, false);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), foreground, false);
        setOpaque(false);


    }

    public boolean isDocumentEmpty(){
        return this.getText().isEmpty();
    }

    public void insertStringAt(String text, int position){
        StyledDocument styledDocument = getStyledDocument();
        try{
            styledDocument.insertString(position, text, null);
        }catch(Exception exception){
            exception.printStackTrace();
            //TODO EXCEPTION
        }


    }


}
