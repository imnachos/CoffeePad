package com.imnachos.coffeepad.Style;

import com.imnachos.coffeepad.Editor.TextContainer;
import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


/**
 * Class that managed the UI themes.
 */
class ThemeManager {

    private TextContainer canvas;

    public ThemeManager(TextContainer container){
        canvas = container;
    }


    public void setTheme(){

        SimpleAttributeSet background = new SimpleAttributeSet();
        SimpleAttributeSet foreground = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Settings.DEFAULT_BACKGROUND);
        StyleConstants.setForeground(foreground, Settings.DEFAULT_BACKGROUND);
        canvas.setCaretColor(Settings.DEFAULT_COLOR);
        canvas.getStyledDocument().setParagraphAttributes(0, canvas.getDocument().getLength(), background, false);
        canvas.getStyledDocument().setParagraphAttributes(0, canvas.getDocument().getLength(), foreground, false);

    }
}
