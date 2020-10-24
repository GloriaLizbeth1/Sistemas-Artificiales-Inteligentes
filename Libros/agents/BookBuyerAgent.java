/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glori
 */
package agents;

import behaviours.OfferRequestServer;
import behaviours.PurchaseOrderServer;
import jade.core.Agent;
import behaviours.RequestPerformer;
import gui.BookBuyerGui;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookBuyerAgent extends Agent {
  private String bookTitle;
  
  private AID[] sellerAgents;
  private int ticker_timer = 10000;
  private BookBuyerAgent this_agent = this;
  private BookBuyerGui gui;
  protected void setup() {
      
    gui = new BookBuyerGui(this_agent);
    gui.show();
    
      try {
          Thread.sleep(10000);
      } catch (InterruptedException ex) {
          Logger.getLogger(BookBuyerAgent.class.getName()).log(Level.SEVERE, null, ex);
      }
    bookTitle=gui.title;
    gui.TextLibro.setText("");
     System.out.println("Buyer agent " + getAID().getName() + " is ready");
    if(bookTitle != null && bookTitle.length()> 0) {
      
      System.out.println("Book: " + bookTitle);
      
      addBehaviour(new TickerBehaviour(this, ticker_timer) {
        protected void onTick() {
          System.out.println("Trying to buy " + bookTitle);
          
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("book-selling");
          template.addServices(sd);
          
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            System.out.println("Found the following seller agents:");
            sellerAgents = new AID[result.length];
            for(int i = 0; i < result.length; i++) {
              sellerAgents[i] = result[i].getName();
              System.out.println(sellerAgents[i].getName());
            }
            
          }catch(FIPAException fe) {
            fe.printStackTrace();
          }
          
          myAgent.addBehaviour(new RequestPerformer(this_agent));
        }
      });
    } else {
      System.out.println("No target book title specified");
      doDelete();
    }
  }
  
  protected void takeDown() {
    
	  gui.dispose();
      
    System.out.println("Buyer agent " + getAID().getName() + " terminating");
  }
  
  public AID[] getSellerAgents() {
    return sellerAgents;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
}
	  
      
