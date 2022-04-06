package window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette clase permet d'initialiser le menu de notre application
 */

public class Menu {

    private JFrame baseFrame = new JFrame("Generateur de flore-video ludique (1S3)");
    private final JButton button3 = new JButton("Quitter");
    private final JButton button3D = new JButton("L-System generation 3D");
    private final JButton button2D = new JButton("L-System generation 2D");
    private final JButton buttonAide = new JButton("AIDE");

    /**
     * Initialise la fenêtre menu (le GUI orignal)
     */
    public Menu() {

		JPanel contentPane = new JPanel();
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon background = new ImageIcon("lib/pictures/background.jpg");
        JLabel back = new JLabel(background);
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();

		baseFrame.setSize(width,height);
        baseFrame.setLocationRelativeTo(null);
        baseFrame.setResizable(false);
        baseFrame.setLayout(new BorderLayout());
        baseFrame.setContentPane(back);
        baseFrame.setLayout(new FlowLayout());
        baseFrame.add(BorderLayout.CENTER,buttonAide);
        baseFrame.add(BorderLayout.CENTER,button2D);
        baseFrame.add(BorderLayout.CENTER,button3D);
        baseFrame.add(BorderLayout.CENTER,button3);
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*initialisation des boutons*/
        button3.setPreferredSize(new Dimension(100, 70));
        button3.setHorizontalAlignment(SwingConstants.CENTER);
        button3.setVerticalAlignment(SwingConstants.CENTER);
        button3.setFont(new Font("Chilanka", Font.BOLD, 18));
        button3.setBackground(Color.PINK);

        button3D.setPreferredSize(new Dimension(250, 70));
        button3D.setHorizontalAlignment(SwingConstants.CENTER);
        button3D.setVerticalAlignment(SwingConstants.CENTER);
        button3D.setFont(new Font("Chilanka", Font.BOLD, 18));
        button3D.setBackground(new Color(140, 237, 236));

        button2D.setPreferredSize(new Dimension(250, 70));
        button2D.setHorizontalAlignment(SwingConstants.CENTER);
        button2D.setVerticalAlignment(SwingConstants.CENTER);
        button2D.setFont(new Font("Chilanka", Font.BOLD, 18));
        button2D.setBackground(new Color(255, 255, 0));

        buttonAide.setPreferredSize(new Dimension(100, 70));
        buttonAide.setHorizontalAlignment(SwingConstants.CENTER);
        buttonAide.setVerticalAlignment(SwingConstants.CENTER);
        buttonAide.setFont(new Font("Chilanka", Font.BOLD, 18));
        buttonAide.setBackground(new Color(205, 92, 92));

        contentPane.setOpaque(false);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        /*ajout et paramétrage des evenements souris pour les boutons*/
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        button3D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	baseFrame.dispose();
                new LSystem(true);
            }
        });

        button2D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	baseFrame.dispose();
                new LSystem(false);
            }
        });

        buttonAide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                UIManager.put("OptionPane.messageFont", new Font("Chilanka", Font.PLAIN, 18));
                UIManager.put("OptionPane.titleFont", new Font("Chilanka", Font.PLAIN, 18));
            	JOptionPane.showMessageDialog(baseFrame,"La génération de flore vidéo-ludique en 2D et 3D fonctionnent de la même manière.\n"+
                "Voici les étapes de génération sont : \n"+
                "   1- Choisir son axiome de départ;\n"+
                "   2- Entrer la règle de réécriture\n"+
                "       Il faut respecter l'utilisation de l'alphabet ci-contre sinon pas de réécriture.\n"+
                "           '+': l'angle est positif\n"+
                "           '-' : l'angle est negatif\n"+
                "           'F' et 'X' : tracé de ligne et marquent les axiomes\n"+
                "           '[' : debut de branche\n"+
                "           ']' : fin de branche\n"+
                "           '0' : couleur marron\n"+
                "           '1' : couleur vert foncé\n"+
                "           '2' : couleur vert clair\n"+
                "       On peut effectuer la réécriture selon le 'F' ou le 'X' d' où règle 1 ou règle 2. C'est le DOL-System.\n"+
                "       On peut avoir un SOL-System en paramètrant la règle optionnelle et en choisissant le pourcentage de son action.\n"+
                "   3- Choisir le nombre d'itérations de la règle;\n"+
                "   4- Choisir l'angle de rotation des branches;\n"+
                "   5- Choisir la longueur souhaitée pour les branches\n"+
                "   6- Il ne reste plus qu'à générer votre flore vidéo-ludique. :)\n"+
                "Controle de la camera dans la version 3D:\n"+
                "     (v) flèche du bas: aller vers le bas  \n"+
                "     (^) flèche du haut: aller vers le haut  \n"+
                "     (<-) flèche de gauche: aller vers la gauche  \n"+
                "     (->) flèche de droite: aller vers la droite  \n"+
                "     (molette) molette/pavé tactile: aller vers l'avant ou l'arriere \n"+
                "     NUM8 du pavé tactile: incliner la caméra vers l'avant  \n"+
                "     NUM2 du pavé tactile: incliner la caméra vers l'arrière  \n"
                , "Manuel d'utilisation", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        baseFrame.setVisible(true);
    }

    public JButton getButton3() {
        return button3;
    }

    public JFrame getBaseFrame() {
        return this.baseFrame;
    }
}
