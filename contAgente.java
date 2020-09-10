
package ejercicio2sai;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glori
 */
class contador100 extends Behaviour
{
    int contador = 1;
    
    public void action() 
    {
        System.out.println(contador );
        contador++;
    }
    public boolean done() 
    {
        return contador > 100;
        
    }
    
}

public class contAgente extends Agent {
    contador100 cont = new contador100();  
   protected void setup() 
    {  
        addBehaviour(cont);
      
    } 
    
}
