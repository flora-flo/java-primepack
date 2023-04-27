/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primepack;

import entites.Animal;
import entites.Ordonnance;
import entites.Rendezvous;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.AnimalService;
import services.OrdonnanceService;
import services.RendezvousService;
import utils.MyDB;

/**
 *
 * @author expert
 */
public class PrimePack {

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) throws SQLException {
        // TODO code application logic here
                  Date d =Date.valueOf(LocalDate.of(2022, Month.MARCH, 17));
                  
        MyDB db = MyDB.getInstance() ;
        
//  Animal a = new Animal("BEN FOULEN", "Foulen","dtgv","vgb","hjgj");
//  Animal a1 = new Animal("amira", "amira","dtgv","vgb","hjgj");
//        
//        AnimalService ps = new AnimalService();
//      // ps.ajoutAnimal(a);
//        ps.supprimerAnimal(23) ;  
//        ps.modifier(22, a1);
         Ordonnance o = new Ordonnance("amira", "abdrabou",d);
         OrdonnanceService ps = new OrdonnanceService();
        // ps.ajoutOrdonnance(o);

         //System.out.println(ps.afficherListeO() );
         //ps.supprimerOrdonnance(9) ;
       ps.modifier(13, o);
       Rendezvous r =new Rendezvous(12,d);
       RendezvousService rs =new RendezvousService();
       rs.ajoutRendezvous(r);
    }
    
}
