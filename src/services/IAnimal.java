/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Animal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ennou
 */
public interface IAnimal<T> {
    
    public void ajoutAnimal(T a) throws SQLException;
    public List<T> afficherListeA() throws SQLException;
    public void  supprimerAnimal(int id);
}
