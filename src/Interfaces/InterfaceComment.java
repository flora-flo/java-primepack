/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entities.Comment;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfaceComment {
    
     public void addComment(Comment c);
    public void deleteComment(int id);
    public void updateComment(Comment c);
    public List<Comment> displayComments();
}
