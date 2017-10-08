package com.imnachos.coffeepad.Windows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.imnachos.coffeepad.Commands.Command;
import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;
import com.imnachos.coffeepad.Util.FontComboBoxRenderer;
import com.imnachos.coffeepad.Util.LogManager;

public class TextToolbar extends JPanel {
    
	public JToolBar toolbar;
    public SpinnerModel fontSizeElement;
    public JSpinner fontSizeSelector;
    
	public TextToolbar(JFrame frame){
		
		toolbar = new JToolBar(0);

        //Font size selector
        fontSizeElement = new SpinnerNumberModel(12, 1, 72, 2);
        fontSizeSelector = new JSpinner(fontSizeElement);
        fontSizeSelector.setSize(20, 2);
        fontSizeSelector.setToolTipText("font size");
        fontSizeElement.addChangeListener(e -> fontSizeChange());
        
        JLabel fontSizeLabel = new JLabel(Settings.LABEL_FONT_SIZE);
        toolbar.add(fontSizeLabel, BorderLayout.CENTER);
        toolbar.add(fontSizeSelector);
  
        //Font selector
	    JComboBox fontComboBox = new JComboBox(Main.fonts);
	    fontComboBox.setRenderer(new FontComboBoxRenderer());
	    fontComboBox.setSelectedIndex(4);
	    fontComboBox.addActionListener(e -> fontChange((Font) fontComboBox.getSelectedItem()));
        
	    JLabel fontListLabel = new JLabel(Settings.LABEL_FONT);
        toolbar.add(fontListLabel, BorderLayout.CENTER);
	    toolbar.add(fontComboBox);
	    
	    
	    //Text options builder
    	for (String option: Settings.TEXT_OPTIONS) {
    		try{
	    		JButton optionButton = new JButton();
	            String classPath = "com.imnachos.coffeepad.Commands." + option;
	            classPath = classPath.replaceAll("\\s+","");
	    		Command action = (Command) Class.forName(classPath).newInstance();
	    		optionButton.setAction(action);
	    	    Image optionImage = new ImageIcon("data/images/" + option + ".png").getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT);
	    	    Icon optionIcon = new ImageIcon(optionImage);
	    	    optionButton.setIcon(optionIcon);
	    	    toolbar.add(optionButton);
    
	    	}catch(Exception e){
	            LogManager.printLog("Unhandled exception. Todo.");
	            e.printStackTrace();
	        }
        }
	   	
		
	}
	
    /*
     * Manage the font size change functionality
     */
    private void fontSizeChange(){
        Font font = new Font(Main.editor.canvas.getFont().getName(), Main.editor.getCursorStyle(), Main.editor.fontSize);
        Main.editor.canvas.setFont(font);
        Main.editor.font = font;

    }
    
    /*
     * Manage the font change functionality
     */
    private void fontChange(Font selectedFont){
    	Main.editor.canvas.setFont(new Font(selectedFont.getName(), Main.editor.getCursorStyle(), Main.editor.fontSize));
    }


}
