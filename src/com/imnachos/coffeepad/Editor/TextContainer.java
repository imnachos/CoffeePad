package com.imnachos.coffeepad.Editor;

import Memento.Caretaker;
import Memento.Originator;
import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Filter.TextFilter;
import com.imnachos.coffeepad.Listener.FormatKeyListener;
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
public class TextContainer extends JTextPane{

    public TextListener textListener;
    public TextFilter textFilter;
    private LanguageStyle defaultStyle;

    public Originator originator;
    public Caretaker caretaker;
    public int currentState;
    public int savedStates;

    public TextContainer() {

        originator = new Originator();
        caretaker = new Caretaker();
        savedStates = 0;
        currentState = 0;

        setContentType("text/html");
        setBorder(null);
        textListener = new TextListener(this);
        textFilter = new TextFilter(this);

        setForeground(Settings.DEFAULT_COLOR);
        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), new FormatKeyListener());

        //TODO REFACTOR THIS, MOVE TO LANGUAGESTYLEMANAGER
        Map<String, Color> emptyColorMap = new HashMap<String, Color>();
        defaultStyle = new LanguageStyle("default", emptyColorMap);
        textListener.setCurrentStyle(defaultStyle);


        //((AbstractDocument) getStyledDocument()).addDocumentListener(textListener);
        ((AbstractDocument) getDocument()).setDocumentFilter(textFilter);

        /*SimpleAttributeSet background = new SimpleAttributeSet();
        SimpleAttributeSet foreground = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Settings.DEFAULT_BACKGROUND);
        StyleConstants.setForeground(foreground, Settings.DEFAULT_BACKGROUND);
        setCaretColor(Settings.DEFAULT_COLOR);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), background, false);
        getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), foreground, false);
        setOpaque(false);*/


    }

    public boolean isDocumentEmpty(){
        return this.getText().isEmpty();
    }

    public void undo(){

        if(currentState >= 1){
            currentState--;
            String textContent = originator.restoreFromMemento( caretaker.getMemento(currentState) );
            setText(textContent);
        }
    }

    public void redo(){

        if((savedStates - 1) > currentState){

            currentState++;
            String textContent = originator.restoreFromMemento( caretaker.getMemento(currentState) );
            setText(textContent);
        }
    }

    public void createMementoState(){
        String canvasText = getText();
        originator.set(canvasText);
        caretaker.addMemento(originator.storeInMemento());
        savedStates++;
        currentState++;
    }


}
