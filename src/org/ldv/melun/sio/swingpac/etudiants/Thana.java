package org.ldv.melun.sio.swingpac.etudiants;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import org.ldv.melun.sio.swingpac.Bidule;

public class Thana extends Bidule {

  
  public Thana() {
	
    super("Thana");
   
    setBackground(Color.ORANGE);  
  }

  @Override
  public void doMove() {  
    super.doMove(); 
  }
  
  
  @Override
  protected void doAfterImpactByOther() {
    super.doAfterImpactByOther();
    setBackground(Color.BLACK);
    
  }
  
  @Override
  public void tuEstouchePar(Bidule biduleQuiATouche) { 
	  for (Component obj : getParent().getComponents()) {
	  if (obj instanceof Bidule && obj != this){
		  	super.stop();
	  }
	  else 
		  super.start();
	  }  
		  
  }
 

  
}
