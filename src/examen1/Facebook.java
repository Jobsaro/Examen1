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
        int postId = comment.getPostId() - 1; // Ajuste de índice
        if (postId < 0 || postId >= posts.size()) {
            System.out.println("Error: postId inválido. No se puede agregar el comentario.");
            return false;
        }
        comments.add(comment);
        return true;
    }
    
    @Override
    public void timeLine() {
        if (posts.isEmpty()) {
            System.out.println("No hay posts en la cuenta de " + username);
            return;
        }
        for (int i = 0; i < posts.size(); i++) {
            System.out.println("POST " + (i + 1) + ":");
            System.out.println(posts.get(i));
            boolean hasComments = false;
            for (Comment c : comments) {
                if (c.getPostId() == (i + 1)) {
                    if (!hasComments) {
                        System.out.println("-- Comentarios --");
                        hasComments = true;
                    }
                    c.print();
                }
            }
            System.out.println("----------------------");
        }
    }
    
    // Método para obtener el timeline completo como String
    public String getTimeLineAsString() {
        StringBuilder sb = new StringBuilder();
        if (posts.isEmpty()) {
            sb.append("No hay posts en la cuenta de ").append(username).append("\n");
        } else {
            for (int i = 0; i < posts.size(); i++) {
                sb.append("POST ").append(i + 1).append(":\n");
                sb.append(posts.get(i)).append("\n");
                boolean hasComments = false;
                for (Comment c : comments) {
                    if (c.getPostId() == (i + 1)) {
                        if (!hasComments) {
                            sb.append("-- Comentarios --\n");
                            hasComments = true;
                        }
                        sb.append(c.toString()).append("\n");
                    }
                }
                sb.append("----------------------\n");
            }
        }
        return sb.toString();
    }
}
