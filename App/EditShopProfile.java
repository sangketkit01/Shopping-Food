package App;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class EditShopProfile {

    private JFrame frame;
    private JTextField nameField;
    private JTextField shopNameField;
    private String username;
    private CloseDisplayListener listener;

    /**
     * Launch the application.
     */

    /**
     * Create the application.
     */
    public EditShopProfile(String username) {
        this.username = username;
        initialize();
        frame.setVisible(true);
    }

    public void setListener(CloseDisplayListener listener) {
        this.listener = listener;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        GuiCreater creater = new GuiCreater();
        frame = new JFrame();
        frame.setBounds(100, 100, 438, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame.dispose();
            }
        });

        String query = String.format("Select * From store.store_user WHERE username = '%s'", username);
        ResultSet rs = ConnectData.getData(query);

        ArrayList<String> arr = new ArrayList<>();

        JLabel picLabel = new JLabel("New label");
        picLabel.setHorizontalAlignment(SwingConstants.CENTER);
        picLabel.setBounds(123, 51, 178, 138);
        frame.getContentPane().add(picLabel);

        JLabel changePicLabel = new JLabel("Change Picture");
        changePicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        changePicLabel.setBounds(158, 218, 108, 13);
        frame.getContentPane().add(changePicLabel);
        changePicLabel.setCursor(new Cursor(12));
        changePicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:\\Users\\thira\\OneDrive\\รูปภาพ\\Food Pic"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    picLabel.setIcon(creater.createImage(fileChooser.getSelectedFile().getAbsolutePath(), 178, 138));
                    arr.add(fileChooser.getSelectedFile().getAbsolutePath());
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                changePicLabel.setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changePicLabel.setForeground(Color.black);
            }
        });

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(45, 250, 76, 13);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(45, 269, 265, 26);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        shopNameField = new JTextField();
        shopNameField.setColumns(10);
        shopNameField.setBounds(45, 328, 265, 26);
        frame.getContentPane().add(shopNameField);

        JLabel shopNameLabel = new JLabel("Shop Name");
        shopNameLabel.setBounds(45, 309, 76, 13);
        frame.getContentPane().add(shopNameLabel);

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(45, 368, 76, 13);
        frame.getContentPane().add(categoryLabel);

        JComboBox<String> categoryComboBox = new JComboBox<>();
        categoryComboBox
                .setModel(new DefaultComboBoxModel<>(new String[] { "main_dish", "dessert", "healty", "drink" }));
        categoryComboBox.setBounds(45, 391, 130, 26);
        frame.getContentPane().add(categoryComboBox);

        String shopNameOut = "";
        try {
            while (rs.next()) {
                String url = rs.getString("url") == null ? "ShopPic/restaurantIcon.png" : rs.getString("url");
                shopNameField.setText(rs.getString("store_name"));
                nameField.setText(rs.getString("name"));
                picLabel.setIcon(creater.createImage(url, 178, 138));
                categoryComboBox.setSelectedItem(rs.getString("category"));
                arr.add(url);
                shopNameOut = rs.getString("store_name");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        final String shopName = shopNameOut;

        RoundedButton backButton = new RoundedButton(10);
        backButton.setColor(new Color(240, 240, 240));
        backButton.setColorOver(Color.WHITE);
        backButton.setColorExit(new Color(240, 240, 240));
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        backButton.setCursor(new Cursor(12));
        backButton.setText("Cancel");
        backButton.setForeground(Color.black);
        backButton.setBounds(45, 496, 120, 26);
        frame.getContentPane().add(backButton);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }

        });

        RoundedButton saveChangeButton = new RoundedButton(10);
        saveChangeButton.setColor(new Color(30, 142, 95));
        saveChangeButton.setColorOver(new Color(10, 122, 75));
        saveChangeButton.setColorExit(new Color(30, 142, 95));
        saveChangeButton.setContentAreaFilled(false);
        saveChangeButton.setBorder(BorderFactory.createEmptyBorder());
        saveChangeButton.setFocusable(false);
        saveChangeButton.setCursor(new Cursor(12));
        saveChangeButton.setText("Save Change");
        saveChangeButton.setForeground(Color.white);
        saveChangeButton.setBounds(266, 496, 120, 26);
        frame.getContentPane().add(saveChangeButton);
        saveChangeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = "";
                if (nameField.getText().isEmpty() || shopNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have to fullfill in all textfill");
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Save change?", "Save change",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        if (arr.size() > 1) {
                            File file = new File(arr.getFirst());
                            if (file.exists() && !file.getName().equals("restaurantIcon.png")
                                    && file.getName().startsWith(shopName)) {
                                file.delete();
                            }
                            Path sourcePath = Paths.get(arr.getLast());
                            Path destinatioFolder = Paths.get("ShopPic");
                            Path destinationPath = destinatioFolder
                                    .resolve(shopName + "_" + sourcePath.getFileName().toFile());
                            filePath = destinationPath.toString().replace("\\", "/");

                            try {
                                Files.copy(sourcePath, destinationPath);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            filePath = arr.getFirst().replace("\\", "/");
                        }
                        String updateQuery = String.format(
                                "Update store.store_user set name = '%s' , store_name  = '%s' , category = '%s' , url = '%s' Where username = '%s'",
                                nameField.getText(), shopNameField.getText(),
                                String.valueOf(categoryComboBox.getSelectedItem()), filePath, username);
                        ConnectData.setData(updateQuery, "");
                        System.out.println("Save change successfully");
                        if (listener != null) {
                            frame.dispose();
                            listener.onClose();
                        }
                    }
                }
            }

        });
    }
}
