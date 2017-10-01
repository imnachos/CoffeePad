package com.imnachos.coffeepad.Engine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Settings {

    public static final JFileChooser userDir = new JFileChooser(System.getProperty("user.dir"));
    public static final String DEFAULT_TITLE = "Untitled";
    public static final int DEFAULT_WINDOW_HEIGHT = 600;
    public static final int DEFAULT_WINDOW_WIDTH = 800;

    public static final String LABEL_FILE = "File";
    public static final String LABEL_EDIT = "Edit";

    public static final String LABEL_SAVE = "Save";
    public static final String LABEL_LOAD = "Load";

    public static final String LABEL_COPY = "Copy";
    public static final String LABEL_CUT = "Cut";
    public static final String LABEL_PASTE = "Paste";
    public static final String LABEL_SELECT = "Select all";

    public static final String LABEL_EXIT = "Exit";

    public static final String WINDOW_ICON = "data/images/icon.png";

    /*
        File menu function shortcuts.
    */
    public static final Map<String, Integer> FUNCTIONS_FILE = new HashMap<String, Integer>() {{
        put(LABEL_SAVE, KeyEvent.VK_S);
        put(LABEL_LOAD, KeyEvent.VK_L);
        put(LABEL_EXIT, KeyEvent.VK_ESCAPE);
    }};

    /*
        Edit menu function shortcuts.
     */
    public static final Map<String, Integer> FUNCTIONS_EDIT = new HashMap<String, Integer>() {{
        put(LABEL_COPY, KeyEvent.VK_C);
        put(LABEL_CUT, KeyEvent.VK_X);
        put(LABEL_PASTE, KeyEvent.VK_V);
        put(LABEL_SELECT, KeyEvent.VK_A);
    }};

}
