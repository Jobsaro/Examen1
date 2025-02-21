/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

/**
 *
 * @author laraj
 */
public class Examen1 extends JFrame {
    private JTextField usernameField, tipoField, user1Field, user2Field, commentField;
    private JTextArea postArea, postsDisplayArea;
    private JButton addAccountButton, addFriendButton, addPostButton, addCommentButton, nextPostButton, prevPostButton, viewProfileButton;
    private JTabbedPane tabbedPane;

    private ArrayList<String> posts; // Lista de posts
    private ArrayList<ArrayList<String>> comments; // Lista de comentarios por post
    private int currentPostIndex = 0; // Índice del post actual en la vista

    public Examen1() {
        setTitle("Social App");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        posts = new ArrayList<>();
        comments = new ArrayList<>();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Agregar Cuenta", createAddAccountPanel());
        tabbedPane.addTab("Agregar Amigo", createAddFriendPanel());
        tabbedPane.addTab("Publicar Post", createAddPostPanel());
        tabbedPane.addTab("Visualizar Posts", createViewPostsPanel());
        tabbedPane.addTab("Perfil", createProfilePanel());

        add(tabbedPane);
    }

    // Panel para agregar cuentas
    private JPanel createAddAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre de Usuario:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        panel.add(new JLabel("Tipo de Cuenta (Facebook/Twitter):"));
        tipoField = new JTextField();
        panel.add(tipoField);
        addAccountButton = new JButton("Agregar");
        panel.add(addAccountButton);
        addAccountButton.addActionListener(e -> addAccount());
        return panel;
    }

    // Panel para agregar amigos
    private JPanel createAddFriendPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Cuenta Principal:"));
        user1Field = new JTextField();
        panel.add(user1Field);
        panel.add(new JLabel("Amigo a Agregar:"));
        user2Field = new JTextField();
        panel.add(user2Field);
        addFriendButton = new JButton("Agregar");
        panel.add(addFriendButton);
        addFriendButton.addActionListener(e -> addFriend());
        return panel;
    }

    // Panel para agregar posts
    private JPanel createAddPostPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Escriba su post:"), BorderLayout.NORTH);
        postArea = new JTextArea(5, 30);
        panel.add(new JScrollPane(postArea), BorderLayout.CENTER);
        addPostButton = new JButton("Publicar");
        panel.add(addPostButton, BorderLayout.SOUTH);
        addPostButton.addActionListener(e -> addPost());
        return panel;
    }

    // Panel para visualizar los posts con opción de comentar
    private JPanel createViewPostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Área de texto para mostrar los posts
        postsDisplayArea = new JTextArea(10, 30);
        postsDisplayArea.setEditable(false);
        panel.add(new JScrollPane(postsDisplayArea), BorderLayout.CENTER);

        // Botones para cambiar entre posts
        JPanel navigationPanel = new JPanel(new FlowLayout());
        prevPostButton = new JButton("← Anterior");
        nextPostButton = new JButton("Siguiente →");
        prevPostButton.addActionListener(e -> showPreviousPost());
        nextPostButton.addActionListener(e -> showNextPost());

        navigationPanel.add(prevPostButton);
        navigationPanel.add(nextPostButton);

        panel.add(navigationPanel, BorderLayout.NORTH);

        // Campo y botón para agregar comentarios
        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.add(new JLabel("Escriba un comentario:"), BorderLayout.NORTH);
        commentField = new JTextField();
        commentPanel.add(commentField, BorderLayout.CENTER);
        addCommentButton = new JButton("Comentar");
        commentPanel.add(addCommentButton, BorderLayout.SOUTH);
        addCommentButton.addActionListener(e -> addComment());

        panel.add(commentPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Panel para ver el perfil del usuario
    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Perfil del Usuario"), BorderLayout.NORTH);
        viewProfileButton = new JButton("Ver Perfil");
        panel.add(viewProfileButton, BorderLayout.CENTER);
        viewProfileButton.addActionListener(e -> viewProfile());
        return panel;
    }

    // Método para agregar cuentas
    private void addAccount() {
        String username = usernameField.getText();
        String tipo = tipoField.getText().toLowerCase();
        if (tipo.equals("facebook") || tipo.equals("twitter")) {
            JOptionPane.showMessageDialog(this, "Cuenta agregada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un tipo de cuenta válido (Facebook o Twitter).");
        }
    }

    // Método para agregar amigos
    private void addFriend() {
        String user1 = user1Field.getText();
        String user2 = user2Field.getText();
        JOptionPane.showMessageDialog(this, "Amigo agregado exitosamente.");
    }

    // Método para agregar posts
    private void addPost() {
        String post = postArea.getText();
        if (!post.isEmpty()) {
            posts.add(post);
            comments.add(new ArrayList<>()); // Se crea una lista vacía de comentarios para este post
            currentPostIndex = posts.size() - 1;
            updatePostDisplay();
            JOptionPane.showMessageDialog(this, "Post publicado exitosamente.");
            postArea.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un post antes de publicar.");
        }
    }

    // Método para agregar comentarios al post actual
    private void addComment() {
        if (posts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay posts para comentar.");
            return;
        }

        String comment = commentField.getText();
        if (!comment.isEmpty()) {
            comments.get(currentPostIndex).add(comment);
            updatePostDisplay();
            JOptionPane.showMessageDialog(this, "Comentario agregado exitosamente.");
            commentField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un comentario antes de enviarlo.");
        }
    }

    // Método para mostrar el post actual con sus comentarios
    private void updatePostDisplay() {
        if (posts.isEmpty()) {
            postsDisplayArea.setText("No hay posts disponibles.");
            return;
        }

        StringBuilder displayText = new StringBuilder();
        displayText.append("Post ").append(currentPostIndex + 1).append(" de ").append(posts.size()).append("\n");
        displayText.append(posts.get(currentPostIndex)).append("\n\n");

        // Mostrar los comentarios asociados al post
        ArrayList<String> postComments = comments.get(currentPostIndex);
        if (!postComments.isEmpty()) {
            displayText.append("--- Comentarios ---\n");
            for (String comment : postComments) {
                displayText.append("- ").append(comment).append("\n");
            }
        }

        postsDisplayArea.setText(displayText.toString());
    }

    // Método para mostrar el post anterior
    private void showPreviousPost() {
        if (currentPostIndex > 0) {
            currentPostIndex--;
            updatePostDisplay();
        }
    }

    // Método para mostrar el siguiente post
    private void showNextPost() {
        if (currentPostIndex < posts.size() - 1) {
            currentPostIndex++;
            updatePostDisplay();
        }
    }

    // Método para ver perfil (simulación)
    private void viewProfile() {
        JOptionPane.showMessageDialog(this, "Perfil del usuario mostrado.");
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Examen1().setVisible(true);
        });
    }
}