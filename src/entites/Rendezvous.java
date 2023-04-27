/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author expert
 */
public class Rendezvous {
     private int id,duree;
     private Date date;

    public Rendezvous() {
    }

    public Rendezvous(int id, int duree, Date date) {
        this.id = id;
        this.duree = duree;
        this.date = date;
    }

    public Rendezvous(int duree, Date date) {
        this.duree = duree;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "id=" + id + ", duree=" + duree + ", date=" + date + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
     
   
}
