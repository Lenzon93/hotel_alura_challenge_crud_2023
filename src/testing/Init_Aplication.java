package testing;

import javax.swing.JFrame;

import views.MenuPrincipal;

public class Init_Aplication {

	public static void main(String[] args) {
		MenuPrincipal inicio=new MenuPrincipal();
		inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicio.setVisible(true);
	}

}
