package org.ldv.melun.sio.swingpac.etudiants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



import org.ldv.melun.sio.swingpac.Bidule;

public class Thana extends Bidule {

  
  public Thana() {
	
    super("Thana");/*
    JPanel panel=new JPanel(new GridLayout(2,2)){
    	ImageIcon image = new ImageIcon( "/home/sio/Bureau/Coloriage-Hello-Kitty.png" );
    	public void paint( Graphics g ) {
    	Dimension d = getSize();
    	for( int x = 0; x < d.width; x += image.getIconWidth() )
    	for( int y = 0; y < d.height; y += image.getIconHeight() )
    	g.drawImage( image.getImage(), x, y, null, null );
    	super.paint(g);
    	}
    }*/
    /*setBackground(Color.ORANGE);  */  
  }

  @Override
  public void doMove() {  
    super.doMove();    
  }

  @Override
  protected void doAfterImpactByOther() {
    super.doAfterImpactByOther();
  }

  
}
