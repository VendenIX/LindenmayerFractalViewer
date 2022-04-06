import java.util.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import geometric.Line;
import window.Menu;

public class Main {

        /**
         * main function, execute the program
         * @param args String write in the shell
         * @throws UnsupportedLookAndFeelException
         * Ce throw est uile pour enlever l'ererur du look n' fill (un pack de texture)
         */
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
                UIManager.setLookAndFeel(new NimbusLookAndFeel()); //Theme
                new Menu();
	}
}
