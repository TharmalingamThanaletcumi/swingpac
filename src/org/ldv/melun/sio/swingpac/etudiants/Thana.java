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
    
    List <Bidule> proches =  this.getBidulesProches(10);
    	//for (int i = 0; i<=9 ; i++){ 
    		if (proches.size()>0) {
    			if (proches.get(0).getDELAY() < this.getDELAY()) {
    				if (proches.get(0).isGoLeft()) {
    					this.isGoDown();
    					if(proches.get(0).isGoUp()){
    						this.isGoUp();
    					}
    				}

    			}

    		}
    	//}
    
    	
    		
    }
  	  

  
  
  
  @Override
  protected void doAfterImpactByOther() {
    super.doAfterImpactByOther();
    setBackground(Color.BLACK);

    
  }
  
 
  
}
