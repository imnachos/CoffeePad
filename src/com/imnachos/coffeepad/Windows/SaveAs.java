package com.imnachos.coffeepad.Windows;

import com.imnachos.coffeepad.Engine.Main;
import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveAs extends JFrame implements ActionListener {


    public SaveAs(){
        super(Settings.LABEL_SAVE_AS);
        ImageIcon img = new ImageIcon(Settings.WINDOW_ICON);
        this.setIconImage(img.getImage());
        setSize(150, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        setVisible(true);


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);


        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File());
            pw.println(ta.getText());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            if(pw != null){
                pw.close();
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
