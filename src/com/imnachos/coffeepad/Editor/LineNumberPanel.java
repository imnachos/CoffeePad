package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Engine.Settings;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class LineNumberPanel extends JPanel implements CaretListener, DocumentListener {
    public final static float RIGHT = 1.0f;

    private final static Border OUTER = new MatteBorder(0, 0, 0, 2, Color.GRAY);

    private final static int HEIGHT = Integer.MAX_VALUE - 1000000;

    private TextContainer canvas;

    private int borderGap;
    private Color currentLineForeground;
    private float digitAlignment;
    private int minimumDisplayDigits;

    private int lastDigits;
    private int lastHeight;
    private int lastLine;

    private HashMap<String, FontMetrics> fonts;

    public LineNumberPanel(TextContainer component) {
        this.canvas = component;

        setFont(component.getFont());
        //setForeground(Settings.GUI_COLOR);
        //setBackground(Settings.GUI_COLOR);
        setBorderGap(5);
        setCurrentLineForeground(Settings.GUI_FONT_COLOR);
        setDigitAlignment(RIGHT);
        setMinimumDisplayDigits(3);

        component.getDocument().addDocumentListener(this);
        component.addCaretListener(this);
    }


    public int getBorderGap() {
        return borderGap;
    }


    public void setBorderGap(int borderGap) {
        this.borderGap = borderGap;
        Border inner = new EmptyBorder(0, borderGap, 0, borderGap);
        setBorder( new CompoundBorder(OUTER, inner) );
        lastDigits = 0;
        setPreferredWidth();
    }


    public Color getCurrentLineForeground() {
        return currentLineForeground == null ? getForeground() : currentLineForeground;
    }

    public void setCurrentLineForeground(Color currentLineForeground) {
        this.currentLineForeground = currentLineForeground;
    }


    public float getDigitAlignment() {
        return digitAlignment;
    }

    public void setDigitAlignment(float digitAlignment) {
        this.digitAlignment = digitAlignment > 1.0f ? 1.0f : digitAlignment < 0.0f ? -1.0f : digitAlignment;
    }

    public int getMinimumDisplayDigits() {
        return minimumDisplayDigits;
    }

    public void setMinimumDisplayDigits(int minimumDisplayDigits) {
        this.minimumDisplayDigits = minimumDisplayDigits;
        setPreferredWidth();
    }

    private void setPreferredWidth() {
        Element root = canvas.getDocument().getDefaultRootElement();
        int lines = root.getElementCount();
        int digits = Math.max(String.valueOf(lines).length(), minimumDisplayDigits);

        if (lastDigits != digits) {
            lastDigits = digits;
            FontMetrics fontMetrics = getFontMetrics( getFont() );
            int width = fontMetrics.charWidth( '0' ) * digits;
            Insets insets = getInsets();
            int preferredWidth = insets.left + insets.right + width;

            Dimension d = getPreferredSize();
            d.setSize(preferredWidth, HEIGHT);
            setPreferredSize( d );
            setSize( d );
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        FontMetrics fontMetrics = canvas.getFontMetrics( canvas.getFont() );
        Insets insets = getInsets();
        int availableWidth = getSize().width - insets.left - insets.right;

        Rectangle clip = g.getClipBounds();
        int rowStartOffset = canvas.viewToModel( new Point(0, clip.y) );
        int endOffset = canvas.viewToModel( new Point(0, clip.y + clip.height) );

        while (rowStartOffset <= endOffset) {
            try {
                if (isCurrentLine(rowStartOffset)){
                    g.setColor(getCurrentLineForeground());
                }else{
                     g.setColor(getForeground());
                }
                String lineNumber = getTextLineNumber(rowStartOffset);
                int stringWidth = fontMetrics.stringWidth(lineNumber);
                int x = getOffsetX(availableWidth, stringWidth) + insets.left;
                int y = getOffsetY(rowStartOffset, fontMetrics);
                System.out.println("lineNumber " + lastLine);
                int lineCount = Integer.valueOf(lastLine);
                int yCount = y;
                while(lineCount > 0){
                    g.drawString(String.valueOf(lineCount), x, yCount);
                    System.out.println("y: " + yCount);
                    yCount = yCount - 20;
                    lineCount = lineCount - 1;
                }



                rowStartOffset = Utilities.getRowEnd(canvas, rowStartOffset) + 1;
            }
            catch(Exception e) {
                break;
            }
        }
    }

    private boolean isCurrentLine(int rowStartOffset) {
        int caretPosition = canvas.getCaretPosition();
        Element root = canvas.getDocument().getDefaultRootElement();

        if (root.getElementIndex( rowStartOffset ) == root.getElementIndex(caretPosition)) {
            return true;
        }else {
            return false;
        }
    }

    protected String getTextLineNumber(int rowStartOffset) {

        if (lastLine == rowStartOffset) {
            return String.valueOf(lastLine + 1);
        }else {
            return "";
        }
    }


    private int getOffsetX(int availableWidth, int stringWidth) {
        return (int)((availableWidth - stringWidth) * digitAlignment);
    }

    private int getOffsetY(int rowStartOffset, FontMetrics fontMetrics) throws BadLocationException {

        Rectangle r = canvas.modelToView( rowStartOffset );
        int lineHeight = fontMetrics.getHeight();
        int y = r.y + r.height;
        int descent = 0;

        if (r.height == lineHeight){
            descent = fontMetrics.getDescent();
        }else{
            if (fonts == null)
                fonts = new HashMap<String, FontMetrics>();

            Element root = canvas.getDocument().getDefaultRootElement();
            int index = root.getElementIndex( rowStartOffset );
            Element line = root.getElement( index );

            for (int i = 0; i < line.getElementCount(); i++) {
                Element child = line.getElement(i);
                AttributeSet as = child.getAttributes();
                String fontFamily = (String)as.getAttribute(StyleConstants.FontFamily);
                Integer fontSize = (Integer)as.getAttribute(StyleConstants.FontSize);
                String key = fontFamily + fontSize;

                FontMetrics fm = fonts.get( key );

                if (fm == null)
                {
                    Font font = new Font(fontFamily, Font.PLAIN, fontSize);
                    fm = canvas.getFontMetrics( font );
                    fonts.put(key, fm);
                }

                descent = Math.max(descent, fm.getDescent());
            }
        }

        return y - descent;
    }

    /*
        Caret listener, revisar
     */
    @Override
    public void caretUpdate(CaretEvent e) {

        int currentLine = getCaretRowPosition();

        if (lastLine != currentLine) {
            repaint();
            lastLine = currentLine;
        }
    }

    public int getCaretRowPosition() {
        try {
            int y = canvas.modelToView(canvas.getCaretPosition()).y;

            int line = (y -1)/20;
            return ++line;
        } catch (BadLocationException e) {
        }
        return -1;
    }


    @Override
    public void changedUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        documentChanged();
    }


    private void documentChanged() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    int endPos = canvas.getDocument().getLength();
                    Rectangle rect = canvas.modelToView(endPos);

                    if (rect != null && rect.y != lastHeight) {
                        setPreferredWidth();
                        repaint();
                        lastHeight = rect.y;
                    }
                }
                catch (BadLocationException ex) {
                    //TODO EXCEPTION
                }
            }
        });
    }

}