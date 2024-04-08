package App;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class ProfilePage implements MouseListener, ActionListener {

    private JFrame frame;
    private JLabel editLabel;
    private JTextField nameField;
    private JLabel emailLabel, profileLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private String username;
    private RoundedButton saveButton;
    private FileManager manager;
    private EditProfile edit;
    private GuiCreater creater;
    private String newProfile = "";
    private String url = "";
    private boolean selected = false;
    private CloseDisplayListener listener;

    /**
     * Launch the application.
     */

    /**
     * Create the application.
     */
    public ProfilePage(String username) {
        this.username = username;
        initialize();
        frame.setVisible(true);
    }

    public ProfilePage() {

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        edit = new EditProfile();
        manager = new FileManager();
        creater = new GuiCreater();
        String query = String.format("Select * From user_data.users WHERE username = '%s'", username);
        ResultSet rs = ConnectData.getData(query);

        frame = new JFrame();
        frame.setBounds(100, 100, 424, 568);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().addMouseListener(this);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        profileLabel = new JLabel();
        profileLabel.setBackground(new Color(0, 0, 128));
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileLabel.setBounds(145, 25, 120, 120);
        frame.getContentPane().add(profileLabel);
        try {
            edit.setProfile(profileLabel, username, 120, 120);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        editLabel = new JLabel("Edit profile");
        editLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        editLabel.setBounds(169, 167, 70, 13);
        frame.getContentPane().add(editLabel);
        editLabel.setCursor(new Cursor(12));
        editLabel.addMouseListener(this);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nameLabel.setBounds(53, 197, 58, 13);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(53, 220, 304, 28);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        emailLabel.setBounds(55, 275, 58, 13);
        frame.getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setBounds(54, 298, 301, 28);
        frame.getContentPane().add(emailField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        phoneLabel.setBounds(55, 359, 58, 13);
        frame.getContentPane().add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setColumns(10);
        phoneField.setBounds(54, 382, 301, 28);
        frame.getContentPane().add(phoneField);
        phoneField.setDocument(new NumericDocument());

        saveButton = new RoundedButton(0);

        saveButton.setBounds(147, 448, 120, 28);
        saveButton.setColor(new Color(30, 142, 95));
        saveButton.setColorOver(new Color(10, 122, 75));
        saveButton.setColorExit(new Color(30, 142, 95));
        saveButton.setContentAreaFilled(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setCursor(new Cursor(12));
        saveButton.setFocusable(false);
        saveButton.setText("Save Change");
        saveButton.setForeground(Color.white);
        frame.getContentPane().add(saveButton);
        saveButton.addActionListener(this);
        try {
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                emailField.setText(rs.getString("email"));
                phoneField.setText(rs.getString("phone"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public boolean getSelected() {
        return selected;
    }

    public String getNewProfile() {
        return newProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            int accept = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Save change",
                    JOptionPane.YES_NO_OPTION);

            if (accept == JOptionPane.YES_OPTION) {
                String saveQuery = String.format(
                        "Update user_data.users Set name = ? , email = ? , phone = ? , url = ? WHERE username = ?");

                String queryDelete = String.format("Select * FROM user_data.users WHERE username = '%s'", username);
                ResultSet rs = ConnectData.getData(queryDelete);

                try {
                    if (rs.next() && selected) {
                        edit.deleteOldProfile(rs.getString("url"));
                        url = manager.fileDestination(newProfile, "profile", username);
                    } else {
                        url = rs.getString("url");
                    }
                    ConnectData.UpdateData(saveQuery, nameField.getText(), emailField.getText(), phoneField.getText(),
                            url, username);
                    // JOptionPane.showMessageDialog(null, "Please restart application to update new
                    // profile");
                    if (listener != null) {
                        frame.dispose();
                        listener.onClose();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == editLabel) {
            url = manager.FileChooserSelection();
            newProfile = url;
            if (manager.getSelected()) {
                selected = true;
                profileLabel.setIcon(creater.createImage(url, 120, 120));
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == editLabel) {
            editLabel.setForeground(new Color(128, 128, 128));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == editLabel) {
            editLabel.setForeground(new Color(0, 0, 0));
        }
    }

    public void setListener(CloseDisplayListener listener) {
        this.listener = listener;
    }

}
