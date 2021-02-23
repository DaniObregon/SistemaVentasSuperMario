package modelo;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Eventos {

	
	//Permite sólo caracteres
	public void textKeyPress(KeyEvent evt) {		

		char car = evt.getKeyChar();// declaramos una variable y le asignamos un evento
		
		if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_BACK_SPACE)
				&& (car != (char) KeyEvent.VK_SPACE)) {
			
			evt.consume();
			
		}
	}

	//Permite sólo números (Al presionar una tecla sólo permitirá que sea numerica)
	public void numberKeyPress(KeyEvent evt) {		

		char car = evt.getKeyChar();// declaramos una variable y le asignamos el objeto evento
		
		if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
			
			evt.consume();
			
		}
	}

	//Sólo permite decimales
	public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) {		

		char car = evt.getKeyChar();// declaramos una variable y le asignamos un evento
		
		if ((car < '0' || car > '9') && textField.getText().contains(".") && (car != (char) KeyEvent.VK_BACK_SPACE)) {
			
			evt.consume();
			
		} else if ((car < '0' || car > '9') && (car != '.') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
			
			evt.consume();
			
		}
	}

}
