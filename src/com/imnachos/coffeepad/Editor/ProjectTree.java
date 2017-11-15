package com.imnachos.coffeepad.Editor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;

public class ProjectTree extends JPanel {
    JTree tree;
    DefaultMutableTreeNode root;

    public ProjectTree() {
        root = new DefaultMutableTreeNode("root", true);
        getList(root, new File(System.getProperty("user.dir")));
        setLayout(new BorderLayout());
        tree = new JTree(root);
        setSize(tree.getPreferredSize());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red));
        tree.setRootVisible(false);
        add(new JScrollPane(tree), BorderLayout.LINE_START);
    }

    private void getList(DefaultMutableTreeNode node, File f) {
        if(!f.isDirectory()) {

            if (f.getName().endsWith("java")) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
                node.add(child);
            }
        }else {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
            node.add(child);
            File fList[] = f.listFiles();
            for(int i = 0; i  < fList.length; i++) {
                getList(child, fList[i]);
            }
        }
    }
}