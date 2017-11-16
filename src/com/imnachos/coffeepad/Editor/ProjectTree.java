package com.imnachos.coffeepad.Editor;

import com.imnachos.coffeepad.Engine.Settings;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;

class ProjectTree extends JPanel {
    private JTree tree;
    private DefaultMutableTreeNode root;

    public ProjectTree() {
        root = new DefaultMutableTreeNode("root", true);
        getList(root, new File(System.getProperty("user.dir")));
        setLayout(new BorderLayout());
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setCellRenderer(new FileTreeCellRenderer());
        add(new JScrollPane(tree), BorderLayout.LINE_START);
    }

    private void getList(DefaultMutableTreeNode node, File file) {
        if(!file.isDirectory()) {

            if (file.getName().endsWith("java")) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(file);
                node.add(child);
            }
        }else {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(file);

            node.add(child);

            File fList[] = file.listFiles();
            for (File aFList : fList) {
                getList(child, aFList);
            }
        }
    }

    protected class FileTreeCellRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            if (value instanceof DefaultMutableTreeNode) {
                value = ((DefaultMutableTreeNode)value).getUserObject();
                if (value instanceof File) {
                    value = ((File) value).getName();
                }
            }
            return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        }

    }

}

