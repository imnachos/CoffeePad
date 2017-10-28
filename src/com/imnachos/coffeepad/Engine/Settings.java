package com.imnachos.coffeepad.Engine;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Settings {

    public static final JFileChooser userDir = new JFileChooser(System.getProperty("user.dir"));

    public static final String DEFAULT_TITLE = "Untitled";

    public static final int DEFAULT_WINDOW_HEIGHT = 600;
    public static final int DEFAULT_WINDOW_WIDTH = 800;

    public static int WINDOW_HEIGHT;
    public static int WINDOW_WIDTH;

    public static final String LABEL_FILE = "File";
    public static final String LABEL_EDIT = "Edit";
    public static final String LABEL_STYLE = "Style";

    private static final String LABEL_NEW = "New";
    private static final String LABEL_SAVE = "Save";
    public static final String LABEL_SAVE_AS = "Save as";
    private static final String LABEL_OPEN = "Open";

    private static final String LABEL_COPY = "Copy";
    private static final String LABEL_CUT = "Cut";
    private static final String LABEL_PASTE = "Paste";
    private static final String LABEL_SELECT = "Select all";

    private static final String LABEL_UNDO = "Undo";
    private static final String LABEL_REDO = "Redo";

    private static final String LABEL_EXIT = "Exit";

    public static final Color DEFAULT_BACKGROUND = new Color(43, 43,43);
    public static final Color DEFAULT_COLOR = new Color(168, 182,173);
    public static final Color GUI_COLOR = new Color(49, 51,53);
    public static final Color GUI_FONT_COLOR = new Color(96, 99,102);
    public static final Color TOOLBAR_FONT_COLOR = new Color(147, 186,166);

    public static final String WINDOW_ICON = "data/images/icon.png";

    /*
        File menu function shortcuts.
    */
    public static final Map<String, Integer> FUNCTIONS_FILE = new LinkedHashMap<String, Integer>() {{
        put(LABEL_NEW, KeyEvent.VK_N);
        put(LABEL_SAVE, KeyEvent.VK_S);
        put(LABEL_SAVE_AS, KeyEvent.VK_S);
        put(LABEL_OPEN, KeyEvent.VK_L);
        put(LABEL_EXIT, KeyEvent.VK_ESCAPE);
    }};

    /*
        Edit menu function shortcuts.
     */
    public static final Map<String, Integer> FUNCTIONS_EDIT = new LinkedHashMap<String, Integer>() {{
        put(LABEL_UNDO, KeyEvent.VK_Z);
        put(LABEL_REDO, KeyEvent.VK_Y);

        put(LABEL_COPY, KeyEvent.VK_C);
        put(LABEL_CUT, KeyEvent.VK_X);
        put(LABEL_PASTE, KeyEvent.VK_V);
        put(LABEL_SELECT, KeyEvent.VK_A);
    }};


}
