package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;

public class EditMenu {

    private JFrame frame;
    private JTextField productNameField;
    private JTextField priceField;
    private String shopName, food_name;
    private CloseDisplayListener listener;

    /**
     * Launch the application.
     */

    /**
     * Create the application.
     */
    public EditMenu(String shopName, String food_name) {
        this.shopName = shopName;
        this.food_name = food_name;
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

        ArrayList<String> picUrl = new ArrayList<>();
        GuiCreater creater = new GuiCreater();
        String query = String.format("Select * From store.food WHERE shop = '%s' And food_name = '%s'", shopName,
                food_name);
        ResultSet rs = ConnectData.getData(query);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 424, 563);
        frame.getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        JLabel picLabel = new JLabel("");
        picLabel.setBounds(122, 20, 180, 180);
        mainPanel.add(picLabel);

        JLabel editPictureLabel = new JLabel("Edit Picture");
        editPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editPictureLabel.setBounds(154, 214, 116, 13);
        mainPanel.add(editPictureLabel);

        JLabel productNameLabel = new JLabel("Product Name");
        productNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        productNameLabel.setBounds(59, 236, 100, 15);
        mainPanel.add(productNameLabel);

        productNameField = new JTextField();
        productNameField.setBounds(58, 260, 293, 26);
        mainPanel.add(productNameField);
        productNameField.setColumns(10);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(58, 329, 293, 26);
        priceField.setDocument(new NumericDocument());
        mainPanel.add(priceField);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        priceLabel.setBounds(59, 305, 100, 15);
        mainPanel.add(priceLabel);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        descriptionLabel.setBounds(59, 371, 100, 15);
        mainPanel.add(descriptionLabel);

        JTextArea descriptionField = new JTextArea();
        descriptionField.setBounds(59, 396, 292, 79);
        mainPanel.add(descriptionField);
        try {
            while (rs.next()) {
                picLabel.setIcon(creater.createImage(rs.getString("url"), 180, 141));
                productNameField.setText(rs.getString("food_name"));
                priceField.setText(String.valueOf(rs.getInt("price")));
                descriptionField.setText(rs.getString("description"));
                picUrl.add(rs.getString("url"));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        editPictureLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setCurrentDirectory(new File("C:/Users/thira/OneDrive/รูปภาพ"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    picLabel.setIcon(creater.createImage(fileChooser.getSelectedFile().getAbsolutePath(), 180, 180));
                    picUrl.add(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editPictureLabel.setForeground(Color.gray);
            }

            public void mouseExited(MouseEvent e) {
                editPictureLabel.setForeground(new Color(0, 0, 0));
            }
        });

        RoundedButton confirmButton = new RoundedButton(0);
        confirmButton.setColor(new Color(30, 142, 95));
        confirmButton.setColorOver(new Color(10, 122, 75));
        confirmButton.setColorExit(new Color(30, 142, 95));
        confirmButton.setContentAreaFilled(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder());
        confirmButton.setFocusable(false);
        confirmButton.setCursor(new Cursor(12));
        confirmButton.setText("Save Change");
        confirmButton.setForeground(Color.WHITE);

        confirmButton.setBounds(266, 511, 100, 26);
        mainPanel.add(confirmButton);
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = "";
                if (productNameField.getText().isEmpty() || priceField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have to fullfill in all field");
                } else {
                    int accept = JOptionPane.showConfirmDialog(null, "Confirm update menu", "Confirm update menu",
                            JOptionPane.YES_NO_OPTION);
                    if (accept == JOptionPane.YES_OPTION) {
                        if (picUrl.size() > 1) {
                            File file = new File(picUrl.getFirst());
                            if (file.exists()) {
                                file.delete();
                            }

                            Path sourcePath = Paths.get(picUrl.getLast());
                            Path destinationFolder = Paths.get("FoodPic");
                            Path destionationPath = destinationFolder
                                    .resolve(shopName + "_" + sourcePath.getFileName().toString());
                            filePath = destionationPath.toString().replace("\\", "/");

                            try {
                                Files.copy(sourcePath, destionationPath);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            filePath = picUrl.getFirst().replace("\\", "/");
                        }

                        String updateQuery = String.format(
                                "Update store.food Set food_name = '%s' , price = %d , url = '%s' , description = '%s' Where shop = '%s' And food_name = '%s'",
                                productNameField.getText(), Integer.parseInt(priceField.getText()),
                                filePath,
                                descriptionField.getText(), shopName, food_name);
                        ConnectData.setData(updateQuery, "");
                        JOptionPane.showMessageDialog(null, "Edit menu successfully");
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
