/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class UberSocial {
    private ArrayList<SocialClass> accounts;

    public UberSocial() {
        accounts = new ArrayList<>();
    }

    // Búsqueda recursiva de una cuenta por username
    public SocialClass buscar(String username) {
        return buscarRec(accounts, username, 0);
    }

    private SocialClass buscarRec(ArrayList<SocialClass> list, String username, int index) {
        if (index >= list.size()) {
            return null;
        }
        SocialClass account = list.get(index);
        if (account.getUsername().equals(username)) {
            return account;
        }
        return buscarRec(list, username, index + 1);
    }

    public void agregarCuenta(String username, String tipo) {
        if (buscar(username) != null) {
            System.out.println("La cuenta " + username + " ya existe.");
            return;
        }
        if (tipo.equalsIgnoreCase("FACEBOOK")) {
            accounts.add(new Facebook(username));
        } else if (tipo.equalsIgnoreCase("TWITTER")) {
            accounts.add(new Twitter(username));
        } else {
            System.out.println("Tipo de red social no soportado: " + tipo);
        }
    }

    public void agregarPost(String user, String post) {
        SocialClass account = buscar(user);
        if (account == null) {
            System.out.println("La cuenta " + user + " no existe.");
            return;
        }
        account.addPost(post);
    }

    public void agregarAmigo(String user1, String user2) {
        SocialClass account1 = buscar(user1);
        SocialClass account2 = buscar(user2);
        if (account1 == null || account2 == null) {
            System.out.println("Una de las cuentas no existe.");
            return;
        }
        if (account1 instanceof Facebook && account2 instanceof Facebook) {
            boolean a1 = account1.addFriend(user2);
            boolean a2 = account2.addFriend(user1);
            if (a1 && a2) {
                System.out.println("Amistad agregada entre " + user1 + " y " + user2 + ".");
            } else {
                System.out.println("No se pudo agregar la amistad (posible duplicado o error).");
            }
        } else {
            System.out.println("Ambas cuentas deben ser de Facebook para agregar amigos.");
        }
    }

    public void agregarComment(String user, int postID, String autor, String commentText) {
        SocialClass account = buscar(user);
        if (account == null) {
            System.out.println("La cuenta " + user + " no existe.");
            return;
        }
        if (!(account instanceof Facebook)) {
            System.out.println("La cuenta " + user + " no es de Facebook, no se puede agregar comentario.");
            return;
        }
        Facebook fb = (Facebook) account;
        Comment newComment = new Comment(postID, autor, commentText);
        boolean added = fb.addComment(newComment);
        if (!added) {
            System.out.println("No se pudo agregar el comentario. PostID inválido para " + user + ".");
        }
    }

    public void profileFrom(String user) {
        SocialClass account = buscar(user);
        if (account == null) {
            System.out.println("La cuenta " + user + " no existe.");
            return;
        }
        account.myProfile();
    }
}
