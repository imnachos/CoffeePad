package com.imnachos.coffeepad.Editor;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.awt.*;

public class Document implements StyledDocument {

    public Document(){

    }

    @Override
    public Style addStyle(String s, Style style) {
        return null;
    }

    @Override
    public void removeStyle(String s) {

    }

    @Override
    public Style getStyle(String s) {
        return null;
    }

    @Override
    public void setCharacterAttributes(int i, int i1, AttributeSet attributeSet, boolean b) {

    }

    @Override
    public void setParagraphAttributes(int i, int i1, AttributeSet attributeSet, boolean b) {

    }

    @Override
    public void setLogicalStyle(int i, Style style) {

    }

    @Override
    public Style getLogicalStyle(int i) {
        return null;
    }

    @Override
    public Element getParagraphElement(int i) {
        return null;
    }

    @Override
    public Element getCharacterElement(int i) {
        return null;
    }

    @Override
    public Color getForeground(AttributeSet attributeSet) {
        return null;
    }

    @Override
    public Color getBackground(AttributeSet attributeSet) {
        return null;
    }

    @Override
    public Font getFont(AttributeSet attributeSet) {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public void addDocumentListener(DocumentListener documentListener) {

    }

    @Override
    public void removeDocumentListener(DocumentListener documentListener) {

    }

    @Override
    public void addUndoableEditListener(UndoableEditListener undoableEditListener) {

    }

    @Override
    public void removeUndoableEditListener(UndoableEditListener undoableEditListener) {

    }

    @Override
    public Object getProperty(Object o) {
        return null;
    }

    @Override
    public void putProperty(Object o, Object o1) {

    }

    @Override
    public void remove(int i, int i1) throws BadLocationException {

    }

    @Override
    public void insertString(int i, String s, AttributeSet attributeSet) throws BadLocationException {

    }

    @Override
    public String getText(int i, int i1) throws BadLocationException {
        return null;
    }

    @Override
    public void getText(int i, int i1, Segment segment) throws BadLocationException {

    }

    @Override
    public Position getStartPosition() {
        return null;
    }

    @Override
    public Position getEndPosition() {
        return null;
    }

    @Override
    public Position createPosition(int i) throws BadLocationException {
        return null;
    }

    @Override
    public Element[] getRootElements() {
        return new Element[0];
    }

    @Override
    public Element getDefaultRootElement() {
        return null;
    }

    @Override
    public void render(Runnable runnable) {

    }
}
