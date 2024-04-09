package App;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ShopPage {
    private String username;
    private JFrame frame;
    private GuiCreater creater;

    /**
     * Launch the application.
     */

    /**
     * Create the application.
     */
    public ShopPage(String username) {
        this.username = username;
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        creater = new GuiCreater();

        JPanel Header = new JPanel();
        Header.setBounds(0, 0, 800, 75);
        Header.setBackground(new Color(30,142,95));
        frame.getContentPane().add(Header);
        Header.setLayout(null);

        JLabel PartnerName = new JLabel("Partner's Name");
        PartnerName.setHorizontalAlignment(SwingConstants.RIGHT);
        PartnerName.setBounds(605, 10, 115, 20);
        PartnerName.setFont(new Font("SansSerif", Font.PLAIN, 16));
        Header.add(PartnerName);

        JPanel Profile = new JPanel();
        Profile.setBounds(725, 13, 50, 50);
        Header.add(Profile);
        Profile.setLayout(null);

        JLabel profileLabel = new JLabel();
        profileLabel.setBounds(0, 0, 50, 50);
        Profile.add(profileLabel);

        JPanel UnderString = new JPanel();
        UnderString.setBounds(621, 35, 100, 1);
        Header.add(UnderString);
        UnderString.setBackground(new Color(0, 0, 0));

        JLabel EditProfile = new JLabel("Edit Profile");
        EditProfile.setBounds(660, 40, 60, 15);
        EditProfile.setFont(new Font("SansSerif", Font.PLAIN, 12));
        Header.add(EditProfile);
        EditProfile.setCursor(new Cursor(12));
        EditProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditShopProfile editPartnerProfile = new EditShopProfile(username);
                editPartnerProfile.setListener(new CloseDisplayListener() {

                    @Override
                    public void onClose() {
                        frame.dispose();
                        new ShopPage(username);
                    }

                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                EditProfile.setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                EditProfile.setForeground(Color.black);
            }
        });

        RoundedButton BackIcon = new RoundedButton(15);
        BackIcon.setBounds(10, 15, 80, 50);
        BackIcon.setColor(Color.black);
        BackIcon.setColorOver(Color.gray);
        BackIcon.setColorExit(Color.black);
        BackIcon.setIcon(creater.createImage("ShopPic/Logout.png", 35, 25));
        BackIcon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Exit program ? ", "Exit program",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new ShopLoginAndRegister();
                }
            }

        });
        Header.add(BackIcon);
        creater.buttonDecorate(BackIcon);

        JPanel Base = new JPanel();
        Base.setBounds(50, 100, 750, 500);
        Base.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(Base);
        Base.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 700, 50);
        Base.add(panel);
        panel.setLayout(null);

        JLabel StoreName = new JLabel("Store’s Name");
        StoreName.setFont(new Font("SansSerif", Font.BOLD, 24));
        StoreName.setBounds(5, 5, 155, 40);
        panel.add(StoreName);

        JPanel AreaFood = new JPanel();
        AreaFood.setBackground(new Color(240, 240, 240));
        AreaFood.setBounds(0, 90, 700, 1500);
        AreaFood.setLayout(null);
        creater.CreateStoreMenu(AreaFood, username, frame);
        creater.setListener(new CloseDisplayListener() {

            @Override
            public void onClose() {
                frame.dispose();
                new ShopPage(username);
            }

        });
        creater.setEditListener(new CloseDisplayListener() {

            @Override
            public void onClose() {
                frame.dispose();
                new ShopPage(username);
            }

        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 90, 720, 500);
        scrollPane.setViewportView(AreaFood);
        Base.add(scrollPane);

        String query = String.format("Select * From store.store_user WHERE username = '%s'", username);
        ResultSet rs = ConnectData.getData(query);
        try {
            if (rs.next()) {
                String url = rs.getString("url") == null ? "ShopPic/restaurantIcon.png" : rs.getString("url");
                PartnerName.setText(rs.getString("name"));
                StoreName.setText(rs.getString("store_name"));
                profileLabel.setIcon(creater.createImage(url, 50, 50));
            }
        } catch (SQLException e1) {

            e1.printStackTrace();
        }

    }
}
