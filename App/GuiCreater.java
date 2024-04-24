package App;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class GuiCreater {

    private CloseDisplayListener listener, editListener;
    int ymenu = 50, y_restaurant = 4;
    ArrayList<Integer> arr;

    public void setListener(CloseDisplayListener listener) {
        this.listener = listener;
    }

    public void setEditListener(CloseDisplayListener editListener) {
        this.editListener = editListener;
    }

    public ImageIcon createImage(String url, int width, int height) {
        ImageIcon imageicon = new ImageIcon(url);
        Image image = imageicon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon newImageIcon = new ImageIcon(image);

        return newImageIcon;
    }

    public JLabel createLabel(String url, int x, int y, int width, int height) {
        JLabel label = new JLabel(createImage(url, width, height));
        label.setBounds(x, y, width, height);

        return label;
    }

    public JPanel createFoodPanel() {
        return new JPanel();
    }

    // สร้างหน้าของแต่ละร้านค่า ว่าร้านค้านั้นๆมีอไรอยู่ข้างในบ้าง
    public void createCard(JPanel card, String username, JPanel headPanel) {
        GuiCreater creater = new GuiCreater();
        String query = String.format("Select * FROM store.store_user");
        ResultSet rs = ConnectData.getData(query);

        JPanel AddMenuPanel = new JPanel();
        AddMenuPanel.setBackground(new Color(0, 0, 0));
        AddMenuPanel.setBounds(347, 23, 151, 29);
        headPanel.add(AddMenuPanel);
        AddMenuPanel.setLayout(null);

        JLabel AddMenuLabel = new JLabel("Add menu successfully");
        AddMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        AddMenuLabel.setForeground(new Color(255, 255, 255));
        AddMenuLabel.setBounds(10, 0, 131, 29);
        AddMenuPanel.add(AddMenuLabel);

        AddMenuPanel.setVisible(false);
        arr = new ArrayList<>();
        try {
            for (; rs.next();) {
                String foodQuery = String.format("Select * FROM store.food WHERE shop = '%s'",
                        rs.getString("store_name"));
                ResultSet foodRs = ConnectData.getData(foodQuery);
                JLabel label = new JLabel(rs.getString("store_name"));
                label.setForeground(new Color(255, 255, 255));
                label.setFont(new Font("Tahoma", Font.BOLD, 20));
                JPanel panel = new JPanel();
                panel.setLayout(null);
                label.setBounds(10, 5, 200, 50);
                panel.add(label);
                panel.setBackground(new Color(30, 142, 95));

                int x = 10;
                for (int i = 0; foodRs.next(); i++) {
                    final String shopName = foodRs.getString("shop"), food_name = foodRs.getString("food_name");
                    final String url = foodRs.getString("url");
                    final int price = foodRs.getInt("price");

                    if (i == 0) {
                        ymenu = 50;
                    }
                    if (i % 3 == 0 && i != 0) {
                        x = 10;
                        ymenu += 230;
                    }
                    JPanel picPanel = new JPanel();
                    picPanel.setBounds(x, ymenu, 138, 148);
                    panel.add(picPanel);
                    picPanel.setLayout(null);

                    JLabel picLabel = new JLabel();
                    picLabel.setBounds(0, 0, 138, 148);
                    picLabel.setIcon(creater.createImage(foodRs.getString("url"), 139, 148));
                    picPanel.add(picLabel);

                    JLabel nameLabel = new JLabel(foodRs.getString("food_name"));
                    nameLabel.setBounds(x, ymenu + 150, 100, 20);
                    nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                    panel.add(nameLabel);

                    JLabel priceLabel = new JLabel(String.valueOf(foodRs.getInt("price") + " " + "฿"));
                    priceLabel.setBounds(x + 100, ymenu + 150, 38, 20);
                    priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel.add(priceLabel);

                    RoundedButton addButton = new RoundedButton(20);
                    addButton.setIcon(createImage("FoodPic/Cart.png", 30, 30));
                    addButton.setFont(new Font("Tahoman", Font.PLAIN, 10));
                    addButton.setColor(Color.BLACK);
                    addButton.setColorOver(Color.GRAY);
                    addButton.setColorExit(Color.BLACK);
                    buttonDecorate(addButton);
                    addButton.setBounds(x, ymenu + 180, 138, 35);

                    int delay = 2000;

                    Timer timer = new Timer(delay, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AddMenuPanel.setVisible(false);
                        }
                    });

                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (timer.isRunning()) {
                                AddMenuPanel.setVisible(true);
                                timer.restart();
                            }
                            try {
                                String checkQuery = String.format(
                                        "Select * From user_data.cart WHERE username = '%s' and shop = '%s' and food_name = '%s'",
                                        username, shopName, food_name);
                                ResultSet checkRs = ConnectData.getData(checkQuery);
                                if (checkRs.next()) {
                                    String updatePieceQuery = "Update user_data.cart Set piece = ? Where username = ? and shop = ? and food_name = ?";
                                    ConnectData.UpdateData(updatePieceQuery,
                                            String.valueOf(checkRs.getInt("piece") + 1), username, shopName, food_name);

                                    // JOptionPane.showMessageDialog(null, "Add menu successfully");
                                } else {
                                    String addMenuQuery = String.format(
                                            "INSERT INTO user_data.cart (shop, food_name, price, url, piece, username) VALUES ('%s', '%s', %d, '%s', %d, '%s')",
                                            shopName, food_name, price, url, 1, username);
                                    ConnectData.setData(addMenuQuery, "");
                                    // JOptionPane.showMessageDialog(null, "Add menu successfully");
                                }
                                AddMenuPanel.setVisible(true);
                                timer.start();

                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }

                    });
                    panel.add(addButton);
                    x += 180;
                    arr.add(ymenu);
                }

                card.add(panel, rs.getString("store_name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method made for client side
    public void createCategory(CardLayout card, JPanel parent, JPanel panel, String category) {
        // คำสั่ง sql
        String query = "";
        if (category.equals("all")) {
            query = String.format("Select * FROM store.store_user");
        } else {
            query = String.format("Select * FROM store.store_user WHERE category = '%s'", category);
        }
        // Result from query
        ResultSet rs = ConnectData.getData(query);

        // Loop that show what restuarant we have
        int x = 10, previous_size = 0;
        try {
            for (int i = 0; rs.next(); i++) {
                if (previous_size > y_restaurant) {
                    y_restaurant = previous_size;
                }
                if (i == 0) {
                    y_restaurant = 4;
                }
                if (i % 3 == 0 && i != 0) {
                    x = 10;
                    y_restaurant += 188;
                }

                String store_name = rs.getString("store_name");
                String picture_path = rs.getString("url") == null ? "ShopPic/restaurantIcon.png" : rs.getString("url");

                // create panel that show picture of a restaurant
                JPanel picPanel = new JPanel();
                picPanel.setBounds(x, y_restaurant, 138, 148);
                picPanel.add(createLabel(picture_path, 0, 0, 138, 148));
                picPanel.setBackground(new Color(30, 142, 95));

                panel.add(picPanel);

                // Create label that show name of a restaurant
                JLabel label = new JLabel(store_name);
                label.setBounds(x, y_restaurant + 158, 138, 15);
                label.setFont(new Font("Roboto", Font.BOLD, 15));
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.setHorizontalAlignment(SwingConstants.CENTER);

                // showCard(label, card, parent, store_name);

                // Decorate restaurant's name
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int max = 0;
                        for (int i : arr) {
                            if (i > max) {
                                max = i;
                            }
                        }
                        card.show(parent, store_name);
                        parent.setPreferredSize(new Dimension(518, max + 250));
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        label.setForeground(new Color(128, 128, 128));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        label.setForeground(new Color(0, 0, 0));
                    }

                });
                panel.add(label);
                x += 180;
                previous_size = y_restaurant;
                parent.setPreferredSize(new Dimension(518, y_restaurant + 250));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CreateStoreMenu(JPanel parent, String username, JFrame frame) {
        String shopQuery = String.format("Select * From store.store_user WHERE username = '%s'", username);
        ResultSet shopRS = ConnectData.getData(shopQuery);
        GuiCreater creater = new GuiCreater();

        int x = 10;
        try {
            if (shopRS.next()) {
                String foodQuery = String.format("Select * FROM store.food WHERE shop = '%s'",
                        shopRS.getString("store_name"));
                ResultSet foodRS = ConnectData.getData(foodQuery);

                int y = 10;
                for (int i = 1; foodRS.next(); i++) {
                    final String shopName = foodRS.getString("shop");
                    final String food_name = foodRS.getString("food_name");
                    final String url = foodRS.getString("url");

                    JPanel panel = new JPanel();
                    panel.setBounds(x, y, 125, 195);

                    JPanel picPanel = new JPanel();
                    picPanel.setBounds(5, 5, 115, 125);
                    panel.setLayout(null);
                    panel.add(picPanel);

                    JLabel picLabel = new JLabel();
                    picLabel.setBounds(0, 0, 115, 125);
                    picLabel.setIcon(creater.createImage(foodRS.getString("url"), 115, 125));
                    picPanel.add(picLabel);

                    JLabel shopNameLabel = new JLabel(foodRS.getString("food_name"));
                    shopNameLabel.setBounds(5, 135, 100, 13);
                    panel.add(shopNameLabel);

                    JLabel priceLabel = new JLabel(String.valueOf(foodRS.getInt("price") + " " + "฿"));
                    priceLabel.setBounds(5, 155, 40, 13);
                    panel.add(priceLabel);

                    RoundedButton editButton = new RoundedButton(0);
                    editButton.setColor(Color.BLACK);
                    editButton.setColorOver(Color.gray);
                    editButton.setColorExit(Color.BLACK);
                    editButton.setContentAreaFilled(false);
                    editButton.setCursor(new Cursor(12));
                    editButton.setBorder(BorderFactory.createEmptyBorder());
                    editButton.setText("Edit");
                    editButton.setForeground(Color.white);
                    editButton.setBounds(0, 175, 58, 20);
                    panel.add(editButton);
                    editButton.setFocusable(false);
                    editButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EditMenu editMenu = new EditMenu(shopName, food_name);
                            editMenu.setListener(new CloseDisplayListener() {

                                @Override
                                public void onClose() {
                                    if (editListener != null) {
                                        editListener.onClose();
                                    }
                                }

                            });

                        }

                    });

                    RoundedButton deleteButton = new RoundedButton(10);
                    deleteButton.setColor(Color.WHITE);
                    deleteButton.setColorOver(new Color(255, 127, 127));
                    deleteButton.setColorExit(Color.WHITE);
                    deleteButton.setForeground(Color.RED);
                    deleteButton.setText("Delete");
                    deleteButton.setContentAreaFilled(false);
                    deleteButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    deleteButton.setCursor(new Cursor(12));
                    deleteButton.setFocusable(false);
                    deleteButton.setBounds(60, 175, 52, 20);
                    panel.add(deleteButton);

                    deleteButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int accept = JOptionPane.showConfirmDialog(null, "Are you sure to delete this menu?",
                                    "Delete menu", JOptionPane.YES_NO_OPTION);
                            if (accept == JOptionPane.YES_OPTION) {
                                String deleteQuery = String.format(
                                        "Delete From store.food WHERE shop = '%s' And food_name = '%s'", shopName,
                                        food_name);
                                ConnectData.setData(deleteQuery, "");

                                File file = new File(url);
                                if (file.exists()) {
                                    file.delete();
                                }
                                JOptionPane.showMessageDialog(null, "Delete menu successfully");

                                if (listener != null) {
                                    listener.onClose();
                                }
                            }
                        }

                    });

                    parent.add(panel);

                    x += 140;
                    if (i % 5 == 0 && i != 0) {
                        y += 205;
                        x = 10;
                    }

                }
                RoundedButton addButton = new RoundedButton(10);
                addButton.setText("+");
                addButton.setColor(Color.white);
                addButton.setColorOver(new Color(240, 240, 240));
                addButton.setColorExit(Color.white);
                addButton.setContentAreaFilled(false);
                // addButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                addButton.setForeground(new Color(128, 128, 128));
                addButton.setCursor(new Cursor(12));
                addButton.setBounds(x + 5, y + 10, 110, 120);
                addButton.setFont(new Font("Tahoma", Font.BOLD, 40));
                addButton.setFocusable(false);
                parent.add(addButton);
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            AddMenu addMenu = new AddMenu(shopRS.getString("store_name"));
                            addMenu.setListener(new CloseDisplayListener() {

                                @Override
                                public void onClose() {
                                    listener.onClose();
                                }
                            });
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }

                });
                parent.setPreferredSize(new Dimension(700, y + 200));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showCard(JLabel label, CardLayout card, JPanel panel, String name) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(panel, name);
                panel.setPreferredSize(new Dimension(518, ymenu));
            }
        });
    }

    public void buttonDecorate(JButton... button) {
        for (int i = 0; i < button.length; i++) {
            button[i].setCursor(new Cursor(12));
            button[i].setContentAreaFilled(false);
            button[i].setBorder(BorderFactory.createEmptyBorder());
            button[i].setFocusable(false);
        }
    }

    public void setPreferredSizeByButton(JPanel panel, JButton... buttons) {
        if (panel != null) {
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        panel.setPreferredSize(new Dimension(518, y_restaurant + 180));
                    }
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "Panel is null");
        }
    }

    public JPanel createMenuPanel(int x, int y, int width, int height) {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, width, height);

        JLabel imagelabel = createLabel("MainPagePic/foodpic4.jpg", 10, 10, 138, 148);
        panel.add(imagelabel);
        panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        return panel;
    }

}
