/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Comment {
    private int id,idPost,idMembre;
    private String text;
    private Date date;

    public Comment(int id, int idPost, int idMembre, String text, Date date) {
        this.id = id;
        this.idPost = idPost;
        this.idMembre = idMembre;
        this.text = text;
        this.date = date;
    }

    public Comment(int idPost, int idMembre, String text, Date date) {
        this.idPost = idPost;
        this.idMembre = idMembre;
        this.text = text;
        this.date = date;
    }
    
    public Comment(int idPost, String text, Date date) {
        this.idPost = idPost;
        this.text = text;
        this.date = date;
    }

    public Comment(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Comment(String text) {
        this.text = text;
    }

    
    public Comment(){
        
    }
    
    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", idPost=" + idPost + ", idMembre=" + idMembre + ", text=" + text + ", date=" + date + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    public int getId() {
        return id;
    }

    public int getIdPost() {
        return idPost;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
    
}
