/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Usuario
 */
public final class Comment {
    private int postId;
    private String autor;
    private String contenido;
    private Calendar fecha;

    public Comment(int postId, String autor, String contenido) {
        this.postId = postId;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = Calendar.getInstance();
    }

    
    public void print() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(autor + " - " + sdf.format(fecha.getTime()));
        System.out.println();
        System.out.println(contenido);
        System.out.println();
    }

    
    public int getPostId() {
        return postId;
    }
    @Override
     public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return autor + " - " + sdf.format(fecha.getTime()) + "\n" + contenido;
    }
}
