/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author laraj
 */
public class Examen1 {
private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;
    private JTextField usernameField;
    private JComboBox<String> accountTypeComboBox;
    private JTextArea outputTextArea;

    public SocialMediaGUI() {
        frame = new JFrame("UberSocial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        JPanel mainMenuPanel = createMainMenuPanel();
        JPanel createAccountPanel = createCreateAccountPanel();
        JPanel addPostPanel = createAddPostPanel();
        JPanel addFriendPanel = createAddFriendPanel();
        JPanel addCommentPanel = createAddCommentPanel();
        JPanel profilePanel = createProfilePanel();

        panel.add(mainMenuPanel, "MainMenu");
        panel.add(createAccountPanel, "CreateAccount");
        panel.add(addPostPanel, "AddPost");
        panel.add(addFriendPanel, "AddFriend");
        panel.add(addCommentPanel, "AddComment");
        panel.add(profilePanel, "Profile");

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel(new GridLayout(6, 1));
        JButton createAccountButton = new JButton("Crear Cuenta");
        JButton addPostButton = new JButton("Agregar Post");
        JButton addFriendButton = new JButton("Agregar Amigo");
        JButton addCommentButton = new JButton("Agregar Comentario");
        JButton viewProfileButton = new JButton("Ver Perfil");
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "CreateAccount");
            }
        });

        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "AddPost");
            }
        });

        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "AddFriend");
            }
        });

        addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "AddComment");
            }
        });

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "Profile");
            }
        });

        mainMenuPanel.add(createAccountButton);
        mainMenuPanel.add(addPostButton);
        mainMenuPanel.add(addFriendButton);
        mainMenuPanel.add(addCommentButton);
        mainMenuPanel.add(viewProfileButton);
        mainMenuPanel.add(new JScrollPane(outputTextArea));

        return mainMenuPanel;
    }

    private JPanel createCreateAccountPanel() {
        JPanel createAccountPanel = new JPanel(new GridLayout(4, 1));
        usernameField = new JTextField();
        accountTypeComboBox = new JComboBox<>(new String[]{"FACEBOOK", "TWITTER"});
        JButton createButton = new JButton("Crear Cuenta");
        JButton backButton = new JButton("Atrás");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para crear cuenta
                String username = usernameField.getText();
                String accountType = (String) accountTypeComboBox.getSelectedItem();
                outputTextArea.append("Cuenta creada: " + username + " (" + accountType + ")\n");
                cardLayout.show(panel, "MainMenu");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "MainMenu");
            }
        });

        createAccountPanel.add(new JLabel("Nombre de Usuario:"));
        createAccountPanel.add(usernameField);
        createAccountPanel.add(new JLabel("Tipo de Cuenta:"));
        createAccountPanel.add(accountTypeComboBox);
        createAccountPanel.add(createButton);
        createAccountPanel.add(backButton);

        return createAccountPanel;
    }

    private JPanel createAddPostPanel() {
        JPanel addPostPanel = new JPanel(new GridLayout(3, 1));
        JTextField userField = new JTextField();
        JTextField postField = new JTextField();
        JButton addButton = new JButton("Agregar Post");
        JButton backButton = new JButton("Atrás");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar post
                String user = userField.getText();
                String post = postField.getText();
                outputTextArea.append("Post agregado para " + user + ": " + post + "\n");
                cardLayout.show(panel, "MainMenu");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "MainMenu");
            }
        });

        addPostPanel.add(new JLabel("Usuario:"));
        addPostPanel.add(userField);
        addPostPanel.add(new JLabel("Post:"));
        addPostPanel.add(postField);
        addPostPanel.add(addButton);
        addPostPanel.add(backButton);

        return addPostPanel;
    }

    private JPanel createAddFriendPanel() {
        JPanel addFriendPanel = new JPanel(new GridLayout(3, 1));
        JTextField user1Field = new JTextField();
        JTextField user2Field = new JTextField();
        JButton addButton = new JButton("Agregar Amigo");
        JButton backButton = new JButton("Atrás");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar amigo
                String user1 = user1Field.getText();
                String user2 = user2Field.getText();
                outputTextArea.append(user1 + " agregó a " + user2 + " como amigo\n");
                cardLayout.show(panel, "MainMenu");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "MainMenu");
            }
        });

        addFriendPanel.add(new JLabel("Usuario 1:"));
        addFriendPanel.add(user1Field);
        addFriendPanel.add(new JLabel("Usuario 2:"));
        addFriendPanel.add(user2Field);
        addFriendPanel.add(addButton);
        addFriendPanel.add(backButton);

        return addFriendPanel;
    }

    private JPanel createAddCommentPanel() {
        JPanel addCommentPanel = new JPanel(new GridLayout(5, 1));
        JTextField userField = new JTextField();
        JTextField postIdField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField commentField = new JTextField();
        JButton addButton = new JButton("Agregar Comentario");
        JButton backButton = new JButton("Atrás");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar comentario
                String user = userField.getText();
                int postId = Integer.parseInt(postIdField.getText());
                String author = authorField.getText();
                String comment = commentField.getText();
                outputTextArea.append("Comentario agregado para " + user + " en el post " + postId + " por " + author + ": " + comment + "\n");
                cardLayout.show(panel, "MainMenu");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "MainMenu");
            }
        });

        addCommentPanel.add(new JLabel("Usuario:"));
        addCommentPanel.add(userField);
        addCommentPanel.add(new JLabel("Post ID:"));
        addCommentPanel.add(postIdField);
        addCommentPanel.add(new JLabel("Autor:"));
        addCommentPanel.add(authorField);
        addCommentPanel.add(new JLabel("Comentario:"));
        addCommentPanel.add(commentField);
        addCommentPanel.add(addButton);
        addCommentPanel.add(backButton);

        return addCommentPanel;
    }

    private JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel(new BorderLayout());
        JTextField userField = new JTextField();
        JButton viewButton = new JButton("Ver Perfil");
        JButton backButton = new JButton("Atrás");

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para ver perfil
                String user = userField.getText();
                outputTextArea.append("Perfil de " + user + ":\n");
                // Aquí se mostraría la información del perfil
                cardLayout.show(panel, "MainMenu");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "MainMenu");
            }
        });

        profilePanel.add(new JLabel("Usuario:"), BorderLayout.NORTH);
        profilePanel.add(userField, BorderLayout.CENTER);
        profilePanel.add(viewButton, BorderLayout.EAST);
        profilePanel.add(backButton, BorderLayout.SOUTH);

        return profilePanel;
    }

    public static void main(String[] args) {
        new Examen1();
    }
}