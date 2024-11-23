package com.proyecto.Otros;

import javax.swing.UIManager;
import java.awt.Color;

public class ColoresPanel {
	public static void colorPanel() {
		UIManager.put("Panel.background", Color.BLACK);
		UIManager.put("OptionPane.background", Color.black);
		// Cambiar el color del texto
		UIManager.put("OptionPane.messageForeground", Color.GREEN);
		UIManager.put("Button.background", Color.BLACK);
		UIManager.put("Button.foreground", Color.WHITE);
	}

}
