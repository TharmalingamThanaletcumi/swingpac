package org.ldv.melun.sio.swingpac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.ldv.melun.sio.swingpac.utils.PackageUtil;

/**
 * Définition de la scene du jeu et instanciation des objets. 
 * @author lycée Léonard de Vinci - Melun - SIO-SLAM
 */
public class FenetreMain extends JFrame implements ActionListener ,MouseListener {
  // une constante (mot clé final)
  // c'est un moyen très pratique d'associer un écouteur d'événement
  // à un générateur d'événement.
  static final String ACTION_QUITTER = "Quitter";

  static final String ACTION_GO = "Go";

  private static final String PACKAGE_BIDULES = "org.ldv.melun.sio.swingpac.etudiants";

  private static final int TAILLE_BIDULE = 50;

  private final String ACTION_PAUSE = "Pause";
  private JMenuItem mnPause;
  
  private JPanel laScene;
   private JLabel infos;
   
  // constructeur
  public FenetreMain() {
    // appel un constructeur de son parent
    super("SwingPac");
    // effet : donne un titre à la fenêtre

    // l'application s'arrête lorsque cette fenêtre sera fermée.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 // pas de gestionnaire de positionnement
        setLayout(new BorderLayout());
        
        laScene = new JPanel(true);
        // pas de gestionnaire de positionnement pour la sence
        laScene.setLayout(null);
        
        infos = new JLabel();
        infos.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        infos.setText("test");
    
        this.add(laScene, BorderLayout.CENTER);
        this.add(infos, BorderLayout.SOUTH);

    // initialisation de la fenêtre
    init();
  }

  private void init() {
    // on a besoin de créer une barre de menus
    JMenuBar menuBar;
    // et un menu
    JMenu menuFichier;

    // création dela barre de menus
    menuBar = new JMenuBar();
    // construisons le premier menu
    menuFichier = new JMenu("Fichier");
    menuFichier.setMnemonic(KeyEvent.VK_F);
    menuFichier.getAccessibleContext().setAccessibleDescription(
        "Menu permettant d'accéder à une commande pour quitter");

    // création de la commande "quitter"
    JMenuItem mnItemQuitter = new JMenuItem(ACTION_QUITTER, KeyEvent.VK_Q);
    mnItemQuitter.getAccessibleContext().setAccessibleDescription(
        "Quitter le programme");

    // mnItemQuitter.setActionCommand(ACTION_QUITTER);

    // le menu Fichier contient la commande Quitter
    menuFichier.add(mnItemQuitter);
    // menu.addSeparator();
    // la barre de menus contient le menu Fichier
    menuBar.add(menuFichier);
    JMenu jeu = new JMenu("Jeu");
    jeu.setMnemonic(KeyEvent.VK_J);
    
    JMenuItem mn = new JMenuItem("go", KeyEvent.VK_G);
    mn.setActionCommand(ACTION_GO);
    
    //JMenuItem pause = new JMenuItem("Pause", KeyEvent.VK_P);
    //pause.setActionCommand(ACTION_PAUSE );
    mnPause = new JMenuItem("Start", KeyEvent.VK_P);
    mnPause.setActionCommand(ACTION_PAUSE); 
    
    // l'instance de cette fenêtre est à l'écoute d'une action sur ce menu
    mn.addActionListener(this);
    jeu.add(mn);
    //pause.addActionListener(this);
    //jeu.add(pause);
    mnPause.addActionListener(this);
    jeu.add(mnPause);
    
    menuBar.add(jeu);

    // TODO : ajouter une commande Pause qui stoppe le timer de tous les objets
    // Bidule.

    // on ajoute la barre de menu à la fenêtre
    setJMenuBar(menuBar);

    // l'instance de cette fenêtre est à l'écoute d'une action sur ce menu
    mnItemQuitter.addActionListener(this);
    
    //getContentPane().setBackground(Color.WHITE); 
    //fond d'ecran
    laScene.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    laScene.setBackground(Color.WHITE);

    // TODO : définir une taille en fonction de la taille de l'écran
    // par exemple le 1/4 de l'écran pour des grands écrans, ou 1/2 ...
    setSize(500, 500);

  }

  /**
   * Injecte des objets Bidule dans cette instance de fenêtre
   */
  private void go() {
    // récupère la liste des classes du package en question
    String[] classes = PackageUtil.getClasses(PACKAGE_BIDULES);
    List<String> classesShuffles = Arrays.asList(classes);
    
    // change l'ordre des éléments dans le tableau
    Collections.shuffle(classesShuffles);
    System.out.println(classesShuffles);
    
    // on instancie les classes (un objet par class)
    // et l'ajoute à la scene (fenetre)
    String erreurs = "";
    int margeBidule = 4;
        int largeurCadreBidulle = TAILLE_BIDULE + margeBidule;
    
       // mettre les bidules dans le cadre en tentant d'�viter les
       // chevauchements...
        int xDansScene = 0;
        int yDansScene = 0;
        System.out.println(getWidth());
    
    for (int i = 0; i < classesShuffles.size(); i++) {
      try {
        Bidule bidule = (Bidule) Class.forName(
            PACKAGE_BIDULES + "." + classesShuffles.get(i)).newInstance();
       // bidule.setLocation(20 + i * TAILLE_BIDULE, +i * TAILLE_BIDULE);
        bidule.addMouseListener(this);
        bidule.stop();
        
               //if (xDansScene + TAILLE_BIDULE > getWidth()) {
        	if (xDansScene + TAILLE_BIDULE > laScene.getWidth()) { 
                  xDansScene = 0;
                  yDansScene += largeurCadreBidulle;
                }
        
                bidule.setLocation(xDansScene, yDansScene);
                // bidule.setLocation(20 + i * TAILLE_BIDULE, +i * TAILLE_BIDULE);
        
                xDansScene += largeurCadreBidulle; 
        // ajout l'objet à la fenêtre
        //this.add(bidule);
        laScene.add(bidule);
      } catch (Exception e) {
        erreurs = e.getMessage();
      }
    }
    //this.getContentPane().invalidate();
    //this.repaint(); 
    
    if (!"".equals(erreurs))
      JOptionPane.showMessageDialog(null, erreurs);
    
    this.getContentPane().invalidate();
     this.repaint();
  }
  
 
  private void pause() {
	  //for (Component obj : getContentPane().getComponents()) {
	      //if (obj instanceof Bidule)
	    	 //((Bidule) obj).stop();}
	  
	 
	     System.out.println("nb compos : "
	        // + this.getContentPane().getComponentCount());
	    	 + this.laScene.getComponentCount());
	     Bidule b = null;
	       //for (Component obj : this.getContentPane().getComponents()) {
	     for (Component obj : this.laScene.getComponents()) {     
	     if (obj instanceof Bidule) {
	 
	          b = (Bidule) obj;
	          if (b.isRunning()) {
	            b.stop();
	          } else {
	            b.start();
	          }
	         }
	       }
	      if (b != null) {
	        if (b.isRunning())
	          mnPause.setText("Stop");
	        else
	          mnPause.setText("Start");
	      }
  }
  /**
   * Appelé par les commandes du menu
   */
  public void actionPerformed(ActionEvent evt) {
    String action = evt.getActionCommand();

    if (action.equals(ACTION_QUITTER)) {
      System.exit(0);
    } 
    else if (action.equals(ACTION_GO)) {
      go();
    }
    else if (action.equals(ACTION_PAUSE)) {
    	
    	pause();
    			
      }
  }
  
  /**
   * Les bidules sont �cout�s par this
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    JPanel bidule = (JPanel) e.getSource();
    infos.setText(bidule.toString());
    // ou, tout simplement :
    //  infos.setText(e.getSource().toString());    
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }  


}// FentreMain
