package com.imnachos.coffeepad.Util;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.imnachos.coffeepad.Engine.Main;

public class FontComboBoxRenderer extends JLabel implements ListCellRenderer {

	public FontComboBoxRenderer() {

	}

	@Override
	public Component getListCellRendererComponent(JList arg0, Object arg1,
			int arg2, boolean arg3, boolean arg4) {
		Font font = (Font) arg1;
		setText(font.getName());
		return this;
	}
}
