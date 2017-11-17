package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Commands.ChangeStyle;
import com.imnachos.coffeepad.Commands.Command;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Style.LanguageStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class Toolbar extends JMenuBar {

    //Menu
    private JMenu MENU_FILE;
    private JMenu MENU_EDIT;
    private JMenu MENU_STYLE;

    public Toolbar(Map<String, LanguageStyle> styledLanguages){

        //Menu items
        MENU_FILE = new JMenu(Settings.LABEL_FILE);
        buildMenu(MENU_FILE, Settings.FUNCTIONS_FILE);
        MENU_EDIT = new JMenu(Settings.LABEL_EDIT);
        buildMenu(MENU_EDIT, Settings.FUNCTIONS_EDIT);
        MENU_STYLE = new JMenu(Settings.LABEL_STYLE);
        buildStyleMenu(MENU_STYLE, styledLanguages);

        add(MENU_FILE);
        add(MENU_EDIT);
        add(MENU_STYLE);

    }


    /**
     Construct menus from a given map for a JMenu
     */
    private void buildMenu(JMenu menu, Map<String, Integer> functionMap){
        functionMap.forEach((key, value) -> {
            JMenuItem item = new JMenuItem(key);
            String classPath = "com.imnachos.coffeepad.Commands." + key;
            classPath = classPath.replaceAll("\\s+","");
            try{
                Command action = (Command) Class.forName(classPath).newInstance();
                item.setAction(action);
                item.setAccelerator(KeyStroke.getKeyStroke(value, ActionEvent.CTRL_MASK));
                item.setName(key);
                item.setText(key);
                Image iconImage = new ImageIcon("data/images/" + key + ".png").getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(iconImage);
                item.setIcon(icon);

            }catch(Exception e){
                System.out.println("Unhandled exception. Todo."); //TODO EXCEPTION
                e.printStackTrace();
            }
            menu.add(item);
        });
    }


    /**
     Construct menus from a given map for a JMenu
     */
    private void buildStyleMenu(JMenu menu, Map<String, LanguageStyle> languageList){
        languageList.forEach((value, key) -> {
            JMenuItem item = new JMenuItem(key.languageName);
            try{
                Command action = ChangeStyle.class.newInstance();
                item.setAction(action);
                item.setName(key.languageName);
                item.setText(key.languageName);

            }catch(Exception e){
                System.out.println("Unhandled exception. Todo."); //TODO EXCEPTION
                e.printStackTrace();
            }
            menu.add(item);
        });
    }


}
