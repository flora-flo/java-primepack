/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entities.Post;
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
}
