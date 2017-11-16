package com.imnachos.coffeepad.Filter;

import com.imnachos.coffeepad.Editor.TextContainer;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class TextFilter extends DocumentFilter{

    private TextContainer canvas;

    public TextFilter(TextContainer container) {
        canvas = container;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {


        super.insertString(fb,offset,text,attr);

    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        canvas.createMementoState();
        super.replace(fb, offset, length, text, attrs);
    }



    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);

    }



}
