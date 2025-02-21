/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1;

import java.util.ArrayList;

/**
 *
 * @author laraj
 */


public class Facebook extends SocialClass implements Commentable {
    private ArrayList<Comment> comments;

    public Facebook(String username) {
        super(username);
        comments = new ArrayList<>();
    }

    @Override
    public boolean addComment(Comment comment) {
        // Validar que el postId (1-indexado) sea correcto
        if (comment.getPostId() < 1 || comment.getPostId() > posts.size()) {
            return false;
        }
        comments.add(comment);
        return true;
    }

    @Override
    public void timeline() {
        for (int i = 0; i < posts.size(); i++) {
            System.out.println("POST " + (i + 1) + ":");
            System.out.println(posts.get(i));
            // Mostrar comentarios asociados a este post
            for (Comment c : comments) {
                if (c.getPostId() == (i + 1)) {
                    c.print();
                }
            }
            System.out.println("----------------------");
        }
    }
}