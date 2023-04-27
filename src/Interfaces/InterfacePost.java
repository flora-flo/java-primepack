/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entities.Post;
import Entities.Rating;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public interface InterfacePost {
    
    public void addPost(Post p);
    public void deletePost(int id);
    public void updatePost(Post p);
    public ObservableList<Post> displayPosts();
    public int nbrComments(int id);
    public void addRate(int idp,int idm,int rate,java.util.Date date);
    public void deleteRate(int idp, int idm );
    public List<Rating> rates (int id);
    public List<Rating> isRatedByUser(int idp,int idm) ;
    public int getLikeCount(int postId);
    public int getDislikeCount(int postId) ;
    public List<Post> searchByTitle(String query) ;
             
}
