package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Filter.TextFilter;
import com.imnachos.coffeepad.Listener.FooterListener;
import com.imnachos.coffeepad.Listener.FormatKeyListener;
import com.imnachos.coffeepad.Listener.TextListener;
import com.imnachos.coffeepad.Memento.Caretaker;
import com.imnachos.coffeepad.Memento.Originator;
import com.imnachos.coffeepad.Style.LanguageStyle;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
    Editor canvas
 */
public class TextContainer extends JTextPane{

    private TextListener textListener;
    private TextFilter textFilter;
    private FooterListener footerListener;
    private LanguageStyle currentStyle;
    private StyleContext styleContext;
    private LanguageStyle defaultStyle;
    private MutableAttributeSet defaultAttrs;

    private Originator originator;
    private Caretaker caretaker;
    private int currentState;
    private int savedStates;


    public TextContainer() {

        setContentType("text/html");
        setBorder(null);

        originator = new Originator();
        caretaker = new Caretaker();
        styleContext = new StyleContext();
        savedStates = 0;
        currentState = 0;

        textListener = new TextListener(this);
        getStyledDocument().addDocumentListener(textListener);
        textFilter = new TextFilter(this);
        ((AbstractDocument) getDocument()).setDocumentFilter(textFilter);
        footerListener = new FooterListener();
        addCaretListener(footerListener);

        defaultAttrs = getInputAttributes();

        StyleConstants.setForeground(defaultAttrs, Settings.DEFAULT_COLOR);
        StyleConstants.setFontFamily(defaultAttrs, Font.MONOSPACED);
        getStyledDocument().setCharacterAttributes(0, getStyledDocument().getLength() + 1, defaultAttrs, false);

        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), new FormatKeyListener(this));

        Map<String, Color> emptyColorMap = new HashMap<String, Color>();
        defaultStyle = new LanguageStyle("default", emptyColorMap);
        setCurrentStyle(defaultStyle);

    }


    /**
     * Manages the format of the last word or character.
     */
    public void applyFormat() throws BadLocationException{

        List lastWord = checkLastWord();

        if(!lastWord.isEmpty() && lastWord.size() == 3) {

            String word = (String) lastWord.get(0);
            int wordStart = (int) lastWord.get(1);
            int wordEnd = (int) lastWord.get(2);

            if(word.length() != 1){
                if(currentStyle.hasStyleForKey(word)){
                    Color wordStyle = currentStyle.getStyleForKey(word);
                    AttributeSet styleAttributes = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, wordStyle);
                    getStyledDocument().setCharacterAttributes(wordStart, wordEnd, styleAttributes, false);
                }else{
                    processContextFormat(lastWord);
                }
            }else{
                processAutoComplete(lastWord);
            }

        }
    }

    /**
     * Adds format to a word based on the context
     * @param lastword
     * @throws BadLocationException
     */
    private void processContextFormat(List lastword) throws BadLocationException{
        /*int previousWordStart = Utilities.getPreviousWord(this, getCaretPosition());
        String previousWord = getText(previousWordStart, (int)lastword.get(2) -1);
        System.out.println("previousWord: " + previousWord);*/

    }

    /**
     * Returns a list with the last word and its start and end positions
     */
    private List checkLastWord() {

        String lastWord;
        int wordStart;
        int wordEnd;
        int caretPosition = Main.editor.canvas.getCaretPosition();
        List returnVal = new ArrayList();

        try {
            wordStart = Utilities.getWordStart(Main.editor.canvas, caretPosition);
            wordEnd = Utilities.getWordEnd(Main.editor.canvas, caretPosition);
            lastWord = Main.editor.canvas.getDocument().getText(wordStart, wordEnd - wordStart);

            if(!lastWord.equals(" ") && caretPosition != 0){
                wordStart = Utilities.getWordStart(Main.editor.canvas, caretPosition -1);
                wordEnd = Utilities.getWordEnd(Main.editor.canvas, caretPosition -1 );
                lastWord = Main.editor.canvas.getDocument().getText(wordStart, wordEnd - wordStart);

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

    /**
     * Adds characters based on last input
     * @param lastWord
     */
    private void processAutoComplete(List lastWord) throws BadLocationException{
            if (!lastWord.isEmpty()) {
                String word = (String) lastWord.get(0);

                if (word.length() == 1) {
                    if (word.equals("{")) {
                        //getStyledDocument().insertString(getCaretPosition() + 1, "}", null);
                    }

                    if (word.equals("(")) {
                        //setCaretPosition(getCaretPosition() + 1);
                    }
                }
            }

    }

    /**
     * Sets the canvas style
     * @param currentStyle
     */
    public void setCurrentStyle(LanguageStyle currentStyle) {
        this.currentStyle = currentStyle;
        styleContext = new StyleContext();

        System.out.println("setCurrentStyle to: " + currentStyle.languageName);

        currentStyle.keywordColors.forEach((key, value) -> {

            Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
            Style style = addStyle(key, defaultStyle);
            StyleConstants.setForeground(style, value);
            StyleConstants.setFontFamily(style, Font.MONOSPACED);
            StyleConstants.setFontSize(style, 14);
            styleContext.addStyle(key, style);

            AttributeSet styleAttributes = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, value);
            style.setResolveParent(styleAttributes);

        });
    }

    public boolean isDocumentEmpty(){
        return this.getText().isEmpty();
    }

    /**
     * Undo function. Better to expose this function than all of the memento utils.
     */
    public void undo(){

        if(currentState >= 1){
            currentState--;
            String textContent = originator.restoreFromMemento( caretaker.getMemento(currentState) );
            setText(textContent);
        }
    }

    /**
     * Redo function. Better to expose this function than all of the memento utils.
     */
    public void redo(){

        if((savedStates - 1) > currentState){
            currentState++;
            String textContent = originator.restoreFromMemento( caretaker.getMemento(currentState) );
            setText(textContent);
        }
    }

    /**
     * Create memento. Better to expose this function than all of the memento utils.
     */
    public void createMementoState(){
        String canvasText = getText();
        originator.set(canvasText);
        caretaker.addMemento(originator.storeInMemento());
        savedStates++;
        currentState++;
    }


    public void updateCaretPosition(int line, int column){
        Main.editor.updateFooter(line, column);

    }


    /**
     *  Get offset
     * @param offset
     * @return
     * @throws BadLocationException
     */
    public int getLineOfOffset(int offset) throws BadLocationException {
        Document doc = getDocument();
        if (offset < 0) {
            throw new BadLocationException("Can't translate offset to line", -1);
        } else if (offset > doc.getLength()) {
            throw new BadLocationException("Can't translate offset to line", doc.getLength() + 1);
        } else {
            Element map = doc.getDefaultRootElement();
            return map.getElementIndex(offset);
        }
    }

    /**
     *  Start of line offset
     * @param line
     * @return
     * @throws BadLocationException
     */
    public int getLineStartOffset(int line) throws BadLocationException {
        Element map = this.getDocument().getDefaultRootElement();
        if (line < 0) {
            throw new BadLocationException("Negative line", -1);
        } else if (line >= map.getElementCount()) {
            throw new BadLocationException("No such line", getDocument().getLength() + 1);
        } else {
            Element lineElem = map.getElement(line);
            return lineElem.getStartOffset();
        }
    }





}
