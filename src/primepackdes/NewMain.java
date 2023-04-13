/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primepackdes;

import entites.Evenement;
import entites.Ticket;
import java.sql.Timestamp;
import services.EvenementService;
import services.TicketService;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //evenement//////////////////////////////
        EvenementService evenementService = new EvenementService();
        Evenement evenement = new Evenement("test", "test", new Timestamp(1235), "test", 0);
//        evenementService.insert(evenement);
        evenement.setNbPlace(10);
        evenement.setId(19);
        evenementService.update(evenement);
//
//        System.out.println(evenementService.getAll());
//
//        System.out.println(evenementService.getOne(19));
//
        evenementService.delete(evenement);
        //ticket///////////////////////
        TicketService ticketService = new TicketService();
        Ticket ticket = new Ticket("12", 1, new Timestamp(123), new Timestamp(12558), 12, evenement, 5);
//        ticketService.insert(ticket);
        ticket.setId(29);
        ticket.setPrix(1234);
//        ticketService.update(ticket);
//        ticketService.delete(ticket);

//        System.out.println(ticketService.getAll());
//        System.out.println(ticketService.getOne(30));
    }

}
