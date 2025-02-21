/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 *
 * @author DELL
 */

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    // Panel Agregar Cuenta
    private JTextField accountUsernameField;
    private JTextField accountTipoField;
    private JButton addAccountButton;
    
    // Panel Agregar Amigo
    private JTextField friendMainField;
    private JTextField friendToAddField;
    private JButton addFriendButton;
    
    // Panel Publicar Post
    private JTextField postUserField;
    private JTextField postContentField;
    private JButton addPostButton;
    
    // Panel Agregar Comentario
    private JTextField commentAuthorField;
    private JTextField commentUserField;
    private JTextField commentPostIdField;
    private JTextField commentContentField;
    private JButton addCommentButton;
    
    // Panel Ver Perfil
    private JTextArea profileDisplayArea;
    private JButton viewProfileButton;
    
    private JTabbedPane tabbedPane;
    private UberSocial uberSocial;
    private String currentUser; 

    public MainGUI() {
        setTitle("Social App");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        uberSocial = new UberSocial();
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Agregar Cuenta", createAddAccountPanel());
        tabbedPane.addTab("Agregar Amigo", createAddFriendPanel());
        tabbedPane.addTab("Publicar Post", createAddPostPanel());
        tabbedPane.addTab("Agregar Comentario", createAddCommentPanel());
        tabbedPane.addTab("Ver Perfil", createProfilePanel());
        
        add(tabbedPane);
    }
    
    // Panel para Agregar Cuenta
    private JPanel createAddAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre de Usuario:"));
        accountUsernameField = new JTextField();
        panel.add(accountUsernameField);
        
        panel.add(new JLabel("Tipo de Cuenta (Facebook/Twitter):"));
        accountTipoField = new JTextField();
        panel.add(accountTipoField);
        
        addAccountButton = new JButton("Agregar Cuenta");
        panel.add(addAccountButton);
        addAccountButton.addActionListener(e -> addAccount());
        return panel;
    }
    
    // Panel para Agregar Amigo
    private JPanel createAddFriendPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Cuenta Principal:"));
        friendMainField = new JTextField();
        panel.add(friendMainField);
        
        panel.add(new JLabel("Amigo a Agregar:"));
        friendToAddField = new JTextField();
        panel.add(friendToAddField);
        
        addFriendButton = new JButton("Agregar Amigo");
        panel.add(addFriendButton);
        addFriendButton.addActionListener(e -> addFriend());
        return panel;
    }
    
    // Panel para Publicar Post
    private JPanel createAddPostPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Usuario:"));
        postUserField = new JTextField();
        panel.add(postUserField);
        
        panel.add(new JLabel("Contenido del Post:"));
        postContentField = new JTextField();
        panel.add(postContentField);
        
        addPostButton = new JButton("Publicar Post");
        panel.add(addPostButton);
        addPostButton.addActionListener(e -> addPost());
        return panel;
    }
    
    // Panel para Agregar Comentario
    private JPanel createAddCommentPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Autor del comentario:"));
        commentAuthorField = new JTextField();
        panel.add(commentAuthorField);
        
        panel.add(new JLabel("Usuario del Post:"));
        commentUserField = new JTextField();
        panel.add(commentUserField);
        
        panel.add(new JLabel("Post ID:"));
        commentPostIdField = new JTextField();
        panel.add(commentPostIdField);
        
        panel.add(new JLabel("Comentario:"));
        commentContentField = new JTextField();
        panel.add(commentContentField);
        
        addCommentButton = new JButton("Agregar Comentario");
        panel.add(addCommentButton);
        addCommentButton.addActionListener(e -> addComment());
        return panel;
    }
    
    // Panel para Ver Perfil
    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Perfil de Usuario"), BorderLayout.NORTH);
        
        profileDisplayArea = new JTextArea(10, 30);
        profileDisplayArea.setEditable(false);
        panel.add(new JScrollPane(profileDisplayArea), BorderLayout.CENTER);
        
        viewProfileButton = new JButton("Ver Perfil");
        panel.add(viewProfileButton, BorderLayout.SOUTH);
        viewProfileButton.addActionListener(e -> viewProfile());
        return panel;
    }
    
    // Método para Agregar Cuenta
    private void addAccount() {
        String username = accountUsernameField.getText().trim();
        String tipo = accountTipoField.getText().trim().toUpperCase();
        
        if (username.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }
        
        if (!tipo.equals("FACEBOOK") && !tipo.equals("TWITTER")) {
            JOptionPane.showMessageDialog(this, "Ingrese un tipo de cuenta válido (Facebook o Twitter).");
            return;
        }
        
        uberSocial.agregarCuenta(username, tipo);
        currentUser = username; // Se asigna el usuario actual
        JOptionPane.showMessageDialog(this, "Cuenta agregada exitosamente.");
    }
    
    // Método para Agregar Amigo
    private void addFriend() {
        String mainUser = friendMainField.getText().trim();
        String friendUser = friendToAddField.getText().trim();
        
        if (mainUser.isEmpty() || friendUser.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ambos nombres de usuario.");
            return;
        }
        
        uberSocial.agregarAmigo(mainUser, friendUser);
        JOptionPane.showMessageDialog(this, "Se intentó agregar la amistad (verifique que ambas cuentas sean de Facebook).");
    }
    
    // Método para Publicar Post
    private void addPost() {
        String user = postUserField.getText().trim();
        String post = postContentField.getText().trim();
        
        if (user.isEmpty() || post.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario y contenido.");
            return;
        }
        
        uberSocial.agregarPost(user, post);
        JOptionPane.showMessageDialog(this, "Post agregado exitosamente.");
    }
    
    // Método para Agregar Comentario
    private void addComment() {
        String author = commentAuthorField.getText().trim();
        String user = commentUserField.getText().trim();
        String postIdStr = commentPostIdField.getText().trim();
        String commentText = commentContentField.getText().trim();
        
        if (author.isEmpty() || user.isEmpty() || postIdStr.isEmpty() || commentText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }
        
        try {
            int postID = Integer.parseInt(postIdStr);
            uberSocial.agregarComment(user, postID, author, commentText);
            JOptionPane.showMessageDialog(this, "Comentario agregado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un Post ID válido.");
        }
    }
    
    // Método para Ver Perfil
    private void viewProfile() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "No hay usuario actual. Agregue una cuenta primero.");
            return;
        }
        
        SocialClass userAccount = uberSocial.buscar(currentUser);
        if (userAccount == null) {
            JOptionPane.showMessageDialog(this, "El usuario no existe.");
            return;
        }
        
        StringBuilder profileText = new StringBuilder();
        profileText.append("Perfil de ").append(currentUser).append("\n\n");
        
        // Si la cuenta es de Facebook, mostramos el timeline completo (posts y comentarios)
        if (userAccount instanceof Facebook) {
            profileText.append(((Facebook) userAccount).getTimeLineAsString());
        } else {
            profileText.append("Posts:\n");
            for (String post : userAccount.posts) {
                profileText.append("- ").append(post).append("\n");
            }
        }
        
        profileText.append("\nAmigos:\n");
        for (String friend : userAccount.friends) {
            profileText.append("- ").append(friend).append("\n");
        }
        
        profileDisplayArea.setText(profileText.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
