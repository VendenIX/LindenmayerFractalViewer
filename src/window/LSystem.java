package window;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
 
import geometric.*;
import lsystem.*;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.*;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Cette classe permet d'initialiser la fenêtre de L-System et tous ses composants.
 * Elle est commune à la version 2D et 3D
 */
public class LSystem extends JFrame {

	public static ArrayList<Line> map;
	private java.awt.Color awtColor;
	
	private JButton boutonRetour = new JButton();
	private JButton boutonGenerate = new JButton();
    private JTextField axiome = new JTextField("F");
    private JTextField regle1 = new JTextField("F=FF+[2+F-F-F]-[1-F+F+F]");
	private JTextField regle2 = new JTextField("X=FF");
	private JTextField regleOptionnelle = new JTextField(" ");
	private JRadioButton check1 = new JRadioButton();
	private JRadioButton check2 = new JRadioButton();
	private ButtonGroup group = new ButtonGroup();
	private Integer[] option = { 10,20,30,40,50,60,70,80,90,100};  
	private JComboBox pourcentage = new JComboBox(option);  
	private javax.swing.JSlider angle = new JSlider(0,0,180,22);
	private javax.swing.JSlider longueurPixel = new JSlider(0,0,200,8);
	private javax.swing.JSlider iterations = new JSlider(0,0,10,2);
	private JFXPanel zoneDessin3D;
	private Rewrite rewrite1;
	private boolean choiceDisplay, isChecked1, isChecked2;

	/**
	 * Constructeur qui prend en paramètre un booleen qui détermine si l'on souhaite un affichage 2d ou 3d dans le panel de droite
	 */
	public LSystem(boolean choiceDisplay) {

		super("Generateur de flore video-ludique");

		Container page = this.getContentPane();
		JPanel controlMenu = new JPanel();
		isChecked1 = false;
		isChecked2 = false;

		page.setBackground(awtColor.black);
		page.setLayout(new BorderLayout());

		/*Gestion du menu (ou barre des tâches)*/
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width = (int)dimension.getWidth();
		controlMenu.setLayout(new GridLayout(17,1));
		controlMenu.setBackground(awtColor.GRAY);
		controlMenu.setPreferredSize(new Dimension(width/6, height));

    	boutonRetour.setText("Retour");
		boutonRetour.setBackground(new java.awt.Color(255,102,102));
		boutonRetour.setFont(new Font("Chilanka", Font.BOLD, 16));
		boutonRetour.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						new Menu();

					}
				});

		boutonGenerate.setBackground(new java.awt.Color(102,255,102));
		boutonGenerate.setText("Generer ");
		boutonGenerate.setFont(new Font("Chilanka", Font.BOLD, 16));
		boutonGenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
				// Traitement de la premiere regle:

				ArrayList<Rule> rules = new ArrayList<>();
				String axiom = "" + regle1.getText().charAt(0); //Le caractère avant le '=' est l'axiome
				String regle1Sub = regle1.getText();

				regle1Sub = regle1Sub.substring(2);
				rules.add(new Rule(axiom,regle1Sub));

				// Traitement de la deuxieme regle:
				String regle2Sub = regle2.getText();
				regle2Sub = regle2Sub.substring(2);
				rules.add(new Rule(regle2.getText().charAt(0)+"",regle2Sub));
				
				
				if(isChecked1 == false && isChecked2 == false) {
					rewrite1 = new Rewrite(rules, axiome.getText());
					System.out.println("Pas de regle optionnelle");
				} 
			
				if(isChecked1 == true && isChecked2 == false) {
					rewrite1 = new Rewrite(rules, axiome.getText(), new Rule(regleOptionnelle.getText().charAt(0)+"",regleOptionnelle.getText().substring(2)), getPourcentage(),true);
				}

				if(isChecked1 == false && isChecked2 == true) {
					rewrite1 = new Rewrite(rules, axiome.getText(), new Rule(regleOptionnelle.getText().charAt(0)+"",regleOptionnelle.getText().substring(2)), getPourcentage(),false);
				}
				
				String resultat = rewrite1.replacement(iterations.getValue());
				Parser parsing1 = new Parser(resultat, longueurPixel.getValue(), angle.getValue());
				if (parsing1.isValid() && regle1.getText().charAt(1) == '=') {
					ArrayList<Line> res = parsing1.parseStringtoLines();
					LSystem.map = res;
					if(choiceDisplay) {
						page.add(new Display3D(getMap()),BorderLayout.CENTER);
						
					}
					else {
						page.add(new Display2D(getMap()),BorderLayout.CENTER);
						
					}
					refreshPage();

					regle1.setBackground(java.awt.Color.white);
					regle2.setBackground(java.awt.Color.white);
				} 
				else {
					System.out.println("Y a un probleme de syntaxe !");
					regle1.setBackground(java.awt.Color.red);
					regle2.setBackground(java.awt.Color.red);
				}
				refreshPage();
				
				
            }
        });
		
		controlMenu.add(boutonRetour);
		JLabel axiomLabel = new JLabel("Axiome de départ:");
		axiomLabel.setFont(new Font("Chilanka",Font.BOLD, 16));
		controlMenu.add(axiomLabel);
		controlMenu.add(axiome);

		JLabel longueurLabel = new JLabel("Nombre d'iterations : 2");
		longueurLabel.setFont(new Font("Chilanka",Font.BOLD, 16));
		
		iterations.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			   longueurLabel.setText("Nombre d'iterations : " + ((JSlider)e.getSource()).getValue());
			}
		});

		JLabel longueurPixelLabel = new JLabel("Longueur : 8");
		longueurPixelLabel.setFont(new Font("Chilanka",Font.BOLD, 16));
		longueurPixel.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				longueurPixelLabel.setText("Longueur : " + ((JSlider)e.getSource()).getValue());
			}
		});

		JLabel angleLabel = new JLabel("Angle : 22");
		angleLabel.setFont(new Font("Chilanka",Font.BOLD, 16));
		angle.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				angleLabel.setText("Angle : " + ((JSlider)e.getSource()).getValue());
			}
		});

		JLabel regle1Label = new JLabel("Regle1 : ");
		regle1Label.setFont(new Font("Chilanka",Font.BOLD, 16));
		JLabel regle2Label = new JLabel("Regle2 : ");
		regle2Label.setFont(new Font("Chilanka",Font.BOLD, 16));
		controlMenu.add(regle1Label);

		check1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				regleOptionnelle.setText("F=");
				regleOptionnelle.setBackground(java.awt.Color.white);
				
				if(isChecked1) {
					group.clearSelection();
					isChecked1 = false;
					regleOptionnelle.setBackground(java.awt.Color.black);
				} else {
					isChecked1 = true;
				}
				
			}
		});

		check2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				regleOptionnelle.setText("X=");
				regleOptionnelle.setBackground(java.awt.Color.white);
				
				if(isChecked2) {
					group.clearSelection();
					isChecked2 = false;
					regleOptionnelle.setBackground(java.awt.Color.black);
				} else {
					isChecked2 = true;
				}
				
			}
		});

		group.add(check1);
		group.add(check2);
		JPanel rule1 = new JPanel();
		rule1.setLayout(new BorderLayout());
		rule1.setBackground(awtColor.GRAY);
		rule1.add(regle1,BorderLayout.CENTER);
		rule1.add(check1,BorderLayout.EAST);

		JPanel rule2 = new JPanel();
		rule2.setLayout(new BorderLayout());
		rule2.setBackground(awtColor.GRAY);
		rule2.add(regle2,BorderLayout.CENTER);
		rule2.add(check2,BorderLayout.EAST);

		JPanel rule3 = new JPanel();
		rule3.setLayout(new BorderLayout());
		regleOptionnelle.setBackground(java.awt.Color.black);
		rule3.add(regleOptionnelle,BorderLayout.CENTER);
		rule3.add(pourcentage,BorderLayout.EAST);

		JLabel ruleOptionalLabel = new JLabel("Regle optionnelle : (SOL-System)");
		ruleOptionalLabel.setFont(new Font("Chilanka",Font.BOLD, 16));
	
		controlMenu.add(rule1);
		controlMenu.add(regle2Label);
		controlMenu.add(rule2);
		controlMenu.add(ruleOptionalLabel);
		controlMenu.add(rule3);

		controlMenu.add(longueurLabel);
		controlMenu.add(iterations);
		controlMenu.add(angleLabel);
		controlMenu.add(angle);
		controlMenu.add(longueurPixelLabel);
		controlMenu.add(longueurPixel);
		controlMenu.add(boutonGenerate);
		page.add(controlMenu,BorderLayout.WEST);
		refreshPage();
	}

	public static ArrayList<Line> getMap() {
		return LSystem.map;
	}

	/**
	 * Fait un pack() pour actualiser et resize la page, reparametre
	 */
	public void refreshPage() {

		pack();
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();

		setSize(width,height); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static int getMapSize(){
		return map.size();
	}

	/**
     * Compte le nombre de F de la règle
     * @param axiome
     * @return nombre
     */
    public int getNbAxiomes() {
        int cpt = 0;
        for (int i = 0; i < regle1.getText().length(); i++) {
            if (regle1.getText().charAt(i) == 'F') {
                cpt++;
            }
        }
        return cpt;
    }

	
	public int getPourcentage() {
		return (int) pourcentage.getSelectedItem();
	}
	
	
	

    /**
     * @return java.awt.Color return the awtColor
     */
    public java.awt.Color getAwtColor() {
        return awtColor;
    }

    /**
     * @param awtColor the awtColor to set
     */
    public void setAwtColor(java.awt.Color awtColor) {
        this.awtColor = awtColor;
    }

    /**
     * @return JButton return the boutonRetour
     */
    public JButton getBoutonRetour() {
        return boutonRetour;
    }

    /**
     * @param boutonRetour the boutonRetour to set
     */
    public void setBoutonRetour(JButton boutonRetour) {
        this.boutonRetour = boutonRetour;
    }

    /**
     * @return JButton return the boutonGenerate
     */
    public JButton getBoutonGenerate() {
        return boutonGenerate;
    }

    /**
     * @param boutonGenerate the boutonGenerate to set
     */
    public void setBoutonGenerate(JButton boutonGenerate) {
        this.boutonGenerate = boutonGenerate;
    }

    /**
     * @return JTextField return the axiome
     */
    public JTextField getAxiome() {
        return axiome;
    }

    /**
     * @param axiome the axiome to set
     */
    public void setAxiome(JTextField axiome) {
        this.axiome = axiome;
    }

    /**
     * @return JTextField return the regles
     */
    public JTextField getRegles() {
        return regle1;
    }

    /**
     * @param regles the regles to set
     */
    public void setRegles(JTextField regles) {
        this.regle1 = regles;
    }

    /**
     * @return JTextField return the regles1
     */
    public JTextField getRegles1() {
        return regle2;
    }

    /**
     * @param regles1 the regles1 to set
     */
    public void setRegles1(JTextField regles1) {
        this.regle2 = regles1;
    }

    /**
     * @return JTextField return the regleOptionnelle
     */
    public JTextField getRegleOptionnelle() {
        return regleOptionnelle;
    }

    /**
     * @param regleOptionnelle the regleOptionnelle to set
     */
    public void setRegleOptionnelle(JTextField regleOptionnelle) {
        this.regleOptionnelle = regleOptionnelle;
    }

    /**
     * @return JRadioButton return the check1
     */
    public JRadioButton getCheck1() {
        return check1;
    }

    /**
     * @param check1 the check1 to set
     */
    public void setCheck1(JRadioButton check1) {
        this.check1 = check1;
    }

    /**
     * @return JRadioButton return the check2
     */
    public JRadioButton getCheck2() {
        return check2;
    }

    /**
     * @param check2 the check2 to set
     */
    public void setCheck2(JRadioButton check2) {
        this.check2 = check2;
    }

    /**
     * @return ButtonGroup return the group
     */
    public ButtonGroup getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(ButtonGroup group) {
        this.group = group;
    }

    /**
     * @return javax.swing.JSlider return the angle
     */
    public javax.swing.JSlider getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(javax.swing.JSlider angle) {
        this.angle = angle;
    }

    /**
     * @return javax.swing.JSlider return the longueurPixel
     */
    public javax.swing.JSlider getLongueurPixel() {
        return longueurPixel;
    }

    /**
     * @param longueurPixel the longueurPixel to set
     */
    public void setLongueurPixel(javax.swing.JSlider longueurPixel) {
        this.longueurPixel = longueurPixel;
    }

    /**
     * @return javax.swing.JSlider return the iterations
     */
    public javax.swing.JSlider getIterations() {
        return iterations;
    }

    /**
     * @param iterations the iterations to set
     */
    public void setIterations(javax.swing.JSlider iterations) {
        this.iterations = iterations;
    }

}
