package com.imnachos.coffeepad.Editor;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;

class ProjectTree extends JPanel {
    private JTree tree;
    private DefaultMutableTreeNode root;
    private JFrame window;

    private JScrollPane scrollPane;

    public ProjectTree() {
        getRootPane();
        window = (JFrame) SwingUtilities.windowForComponent(this);
        root = new DefaultMutableTreeNode("root", true);
        getList(root, new File(System.getProperty("user.dir")));
        setLayout(new BorderLayout());
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setCellRenderer(new FileTreeCellRenderer());
        scrollPane = new JScrollPane(tree);

        add(scrollPane, BorderLayout.LINE_START);
    }


    /**
     * Fills the JTree
     * @param node
     * @param file
     */
    private void getList(DefaultMutableTreeNode node, File file) {
        if(!file.isDirectory()) {

            if (file.getName().endsWith("java")) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(file);
                node.add(child);
            }
        }else {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(file);
            node.add(child);

            File fileList[] = file.listFiles();
            for (File aFList : fileList) {
                getList(child, aFList);
            }
        }
    }

    /**
     * Method to change the folder names
     */
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

