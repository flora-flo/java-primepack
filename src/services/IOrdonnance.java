/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Ordonnance;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ennou
 */
public interface IOrdonnance<T> {
    
    public void ajoutOrdonnance(T o) throws SQLException;
    public List<T> afficherListeO() throws SQLException;
    public void  supprimerOrdonnance(int id);
//    public List<T> afficherListeO() throws SQLException;
}
