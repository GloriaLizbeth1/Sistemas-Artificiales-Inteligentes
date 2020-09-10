/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1sai;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
/**
 *
 * @author glori
 */
class comportamiento extends Behaviour
{
    
        public void action()
        {
            Agent nombre=this.getAgent();
            nombre.getAID();
           System.out.println("Hola mundo de Agentes yo soy el agente -> " + nombre.getName());

        }
        public boolean done()
        {
            return true;
        }
    
}

public class agente extends Agent
{
    
    comportamiento compo = new comportamiento(); 
    protected void setup() 
    {
        
           this.addBehaviour(compo);
           this.getAID();
          
    }
}

