/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Rendezvous;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ennou
 */
public interface IRendezvous<T> {
    
    public void ajoutRendezvous(T r) throws SQLException;
  
    public List<T> afficherListeR() throws SQLException;
}

