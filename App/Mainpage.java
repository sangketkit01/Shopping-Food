package App;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Mainpage implements ActionListener, MouseListener {

    private JFrame frame;
    private CardLayout pictureCard, restuarantCard, bigCard;
    private JPanel showRestuarantPanel, dessertPanel, dropDownPanel, headPanel, mapPanel, picPanel, categoryPanel,
            selectPicPanel;
    private String username;
    private JLabel profileLabel, editLabel;
    private JButton cartButton, deliveryButton;
    private RoundedButton healtyButton, dessertButton, mainDishButton, drinkButton, otherButton, exitButton;
    private Timer timerVideo, progressTimer;
    private ActionListener updateTime;
    private JProgressBar loadBar, loadBarmap;
    private JLabel img;
    private int progress, progress1, progress2;
    private ActionListener updateProgress2;
    private JTextArea ta;

    /**
     * Launch the application.
     */

    /**
     * Create the application.
     * 
     * @throws SQLException
     */
    public Mainpage(String username) {
        this.username = username;
        initialize();
        frame.setVisible(true);
    }

    public Mainpage() {

    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws SQLException
     */
    private void initialize() {

        GuiCreater creater = new GuiCreater();
        EditProfile editer = new EditProfile();

        bigCard = new CardLayout(0, 0);
        frame = new JFrame();
        frame.setBounds(100, 100, 976, 609);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(bigCard);
        frame.setResizable(false);
        frame.setTitle("Delivery App");

        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, "mainPanel");
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 255, 255));

        mapPanel = new JPanel();
        mapPanel.setLayout(null);
        mapPanel.setBounds(0, 88, 976, 609);
        mainPanel.add(mapPanel);
        mapPanel.setVisible(false);


        dropDownPanel = new JPanel();
        dropDownPanel.setBounds(810, 79, 142, 193);
        mainPanel.add(dropDownPanel);
        dropDownPanel.setVisible(false);
        mainPanel.setComponentZOrder(dropDownPanel, 0);

        String query = String.format("Select * From user_data.users WHERE username = '%s'", username);
        ResultSet rs = ConnectData.getData(query);

        JLabel nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        nameLabel.setBounds(616, 14, 230, 29);
        try {
            if (rs.next()) {
                nameLabel.setText(rs.getString("name"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        cartButton = new JButton();
        cartButton.setContentAreaFilled(false);
        cartButton.setFocusable(false);
        cartButton.setBorder(BorderFactory.createEmptyBorder());
        cartButton.setIcon(creater.createImage("FoodPic/Cart.png", 40, 30));
        cartButton.addActionListener(this);
        cartButton.setCursor(new Cursor(12));

        headPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                int length = 0;
                if (nameLabel.getText().length() > 10) {
                    length = 13;
                } else {
                    length = 12;
                }
                Graphics2D g2d = (Graphics2D) g;
                super.paintComponent(g);
                g2d.setStroke(new BasicStroke((float) 2));
                g2d.drawLine(849 - nameLabel.getText().length() * length, 45, 846, 45);
                cartButton.setBounds(750 - nameLabel.getText().length() * (length - 2), 23, 85, 45);
            }
        };
        headPanel.setLayout(null);
        headPanel.setBounds(0, 0, 962, 88);
        headPanel.add(nameLabel);
        headPanel.add(cartButton);

        mainPanel.add(headPanel);

        pictureCard = new CardLayout(0, 0);

        picPanel = new JPanel();
        picPanel.setBounds(35, 115, 342, 383);
        mainPanel.add(picPanel);
        picPanel.setLayout(pictureCard);
        picPanel.setBackground(new Color(255, 255, 255));

        // Create main page picture
        JPanel pic1Panel = new JPanel();
        picPanel.add(pic1Panel, "pic1");
        pic1Panel.add(creater.createLabel("MainPagePic/foodpic1.jpg", 0, 0, 400, 450));
        pic1Panel.setBackground(new Color(255, 255, 255));

        JPanel pic2Panel = new JPanel();
        picPanel.add(pic2Panel, "pic2");
        pic2Panel.add(creater.createLabel("MainPagePic/foodpic2.jpg", 0, 0, 342,
                383));
        pic2Panel.setBackground(new Color(255, 255, 255));

        JPanel pic3Panel = new JPanel();
        pic3Panel.add(creater.createLabel("MainPagePic/foodpic3.jpg", 0, 0, 342,
                383));
        picPanel.add(pic3Panel, "pic3");
        pic3Panel.setBackground(new Color(255, 255, 255));

        JPanel pic4Panel = new JPanel();
        pic4Panel.add(creater.createLabel("MainPagePic/foodpic4.jpg", 0, 0, 342,
                383));
        picPanel.add(pic4Panel, "pic4");
        pic4Panel.setBackground(new Color(255, 255, 255));

        JPanel pic5Panel = new JPanel();
        pic5Panel.add(creater.createLabel("MainPagePic/foodpic5.jpg", 0, 0, 342,
                383));
        picPanel.add(pic5Panel, "pic5");
        pic5Panel.setBackground(new Color(255, 255, 255));

        JPanel pic6Panel = new JPanel();
        pic6Panel.add(creater.createLabel("MainPagePic/foodpic6.jpg", 0, 0, 342,
                383));
        picPanel.add(pic6Panel, "pic6");
        pic6Panel.setBackground(new Color(255, 255, 255));

        JPanel pic7Panel = new JPanel();
        pic7Panel.add(creater.createLabel("MainPagePic/foodpic7.jpg", 0, 0, 342,
                383));
        picPanel.add(pic7Panel, "pic7");
        pic7Panel.setBackground(new Color(255, 255, 255));

        selectPicPanel = new JPanel();
        selectPicPanel.setBounds(35, 518, 345, 28);
        mainPanel.add(selectPicPanel);
        selectPicPanel.setBackground(new Color(255, 255, 255));
        selectPicPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        categoryPanel = new JPanel();
        categoryPanel.setBounds(413, 105, 498, 88);
        mainPanel.add(categoryPanel);
        categoryPanel.setLayout(null);
        categoryPanel.setBackground(new Color(255, 255, 255));

        otherButton = new RoundedButton(300);
        otherButton.setBounds(430, 0, 63, 63);
        categoryPanel.add(otherButton);
        otherButton.setColor(Color.PINK);
        otherButton.setColorOver(new Color(225, 145, 145));
        otherButton.setColorExit(Color.PINK);
        otherButton.addActionListener(this);
        otherButton.setIcon(creater.createImage("ShopPic/meal.png", 58, 58));

        mainDishButton = new RoundedButton(300);
        mainDishButton.setBounds(30, 0, 63, 63);
        categoryPanel.add(mainDishButton);
        mainDishButton.addActionListener(this);
        mainDishButton.setColor(Color.PINK);
        mainDishButton.setColorOver(new Color(225, 145, 145));
        mainDishButton.setColorExit(Color.PINK);
        mainDishButton.addActionListener(this);
        mainDishButton.setIcon(creater.createImage("ShopPic/main_dish.png", 60, 60));

        healtyButton = new RoundedButton(300);
        healtyButton.setBounds(130, 0, 63, 63);
        categoryPanel.add(healtyButton);
        healtyButton.setColor(Color.PINK);
        healtyButton.setColorOver(new Color(225, 145, 145));
        healtyButton.setColorExit(Color.PINK);
        healtyButton.addActionListener(this);
        healtyButton.setIcon(creater.createImage("ShopPic/health.png", 63, 63));

        dessertButton = new RoundedButton(300);
        dessertButton.setBounds(231, 0, 63, 63);
        categoryPanel.add(dessertButton);
        dessertButton.setColor(Color.PINK);
        dessertButton.setColorOver(new Color(225, 145, 145));
        dessertButton.setColorExit(Color.PINK);
        dessertButton.addActionListener(this);
        dessertButton.setIcon(creater.createImage("ShopPic/dessert.png", 63, 63));

        drinkButton = new RoundedButton(300);
        drinkButton.setBounds(332, 0, 63, 63);
        categoryPanel.add(drinkButton);
        drinkButton.setColor(Color.PINK);
        drinkButton.setColorOver(new Color(225, 145, 145));
        drinkButton.setColorExit(Color.PINK);
        drinkButton.addActionListener(this);
        drinkButton.setIcon(creater.createImage("ShopPic/drink.png", 63, 63));

        exitButton = new RoundedButton(15);
        exitButton.setBounds(10, 23, 80, 50);
        headPanel.add(exitButton);
        exitButton.addActionListener(this);
        exitButton.setColor(new Color(0, 0, 0));
        exitButton.setColorOver(Color.GRAY);
        exitButton.setColorExit(Color.BLACK);
        exitButton.setIcon(creater.createImage("ShopPic/Logout.png", 35, 25));

        deliveryButton = new JButton();
        deliveryButton.setContentAreaFilled(false);
        deliveryButton.setFocusable(false);
        deliveryButton.setBorder(BorderFactory.createEmptyBorder());
        deliveryButton.addActionListener(this);
        deliveryButton.setCursor(new Cursor(12));
        deliveryButton.setIcon(creater.createImage("FoodPic/Delivery.png", 50, 40));
        deliveryButton.setBounds(10, 23, 85, 45);
        headPanel.add(deliveryButton);
        deliveryButton.setVisible(false);

        creater.buttonDecorate(mainDishButton, healtyButton, dessertButton, drinkButton, otherButton, exitButton);

        JLabel mainDishLabel = new JLabel("Main Dish");
        mainDishLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mainDishLabel.setBounds(36, 65, 55, 13);
        categoryPanel.add(mainDishLabel);

        JLabel healtyLabel = new JLabel("Healty Food");
        healtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        healtyLabel.setBounds(133, 65, 65, 13);
        categoryPanel.add(healtyLabel);

        JLabel dessertLabel = new JLabel("Dessert");
        dessertLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dessertLabel.setBounds(242, 65, 47, 13);
        categoryPanel.add(dessertLabel);

        JLabel teaCoffeeLabel = new JLabel("Tea & Coffee");
        teaCoffeeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        teaCoffeeLabel.setBounds(326, 65, 75, 13);
        categoryPanel.add(teaCoffeeLabel);

        JLabel otherLabel = new JLabel("All");
        otherLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        otherLabel.setBounds(456, 65, 41, 13);
        categoryPanel.add(otherLabel);

        restuarantCard = new CardLayout(0, 0);

        showRestuarantPanel = new JPanel();
        showRestuarantPanel.setBounds(413, 195, 550, 450);
        mainPanel.add(showRestuarantPanel);
        showRestuarantPanel.setLayout(restuarantCard);
        creater.setPreferredSizeByButton(showRestuarantPanel, mainDishButton, healtyButton, dessertButton, drinkButton,
                otherButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(413, 195, 550, 372); // Adjust the bounds as needed
        scrollPane.setViewportView(showRestuarantPanel);
        mainPanel.add(scrollPane);

        JPanel otherPanel = new JPanel();
        showRestuarantPanel.add(otherPanel, "all");
        otherPanel.setLayout(null);
        otherPanel.setBackground(new Color(30, 142, 95));

        JPanel mainDishPanel = new JPanel();
        showRestuarantPanel.add(mainDishPanel, "mainDish");
        mainDishPanel.setLayout(null);
        creater.createCategory(restuarantCard, showRestuarantPanel, mainDishPanel, "main_dish");
        mainDishPanel.setBackground(new Color(30, 142, 95));

        dessertPanel = new JPanel();
        showRestuarantPanel.add(dessertPanel, "dessert");
        dessertPanel.setLayout(null);
        creater.createCategory(restuarantCard, showRestuarantPanel, dessertPanel, "dessert");
        dessertPanel.setBackground(new Color(30, 142, 95));

        JPanel healtyPanel = new JPanel();
        showRestuarantPanel.add(healtyPanel, "healty");
        healtyPanel.setLayout(null);
        creater.createCategory(restuarantCard, selectPicPanel, healtyPanel, "healty");
        healtyPanel.setBackground(new Color(30, 142, 95));

        JPanel drinkPanel = new JPanel();
        showRestuarantPanel.add(drinkPanel, "drink");
        drinkPanel.setLayout(null);
        creater.createCategory(restuarantCard, showRestuarantPanel, drinkPanel, "drink");
        drinkPanel.setBackground(new Color(30, 142, 95));

        creater.createCategory(restuarantCard, showRestuarantPanel, otherPanel, "all");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, "other");

        Timer timer = new Timer(3000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pictureCard.next(picPanel);
            }

        });
        timer.start();

        profileLabel = new JLabel("Haha");
        profileLabel.setBounds(860, 10, 68, 68);
        try {
            editer.setProfile(profileLabel, username, 68, 68);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        headPanel.add(profileLabel);
        headPanel.setBackground(new Color(30, 142, 95));
        profileLabel.addMouseListener(this);
        profileLabel.setCursor(new Cursor(12));

        editLabel = new JLabel("Edit Profile");
        editLabel.setBounds(780, 55, 90, 13);
        headPanel.add(editLabel);
        editLabel.addMouseListener(this);
        editLabel.setCursor(new Cursor(12));

        creater.createCard(showRestuarantPanel, username, headPanel);

        JPanel Header = new JPanel();
        Header.setBounds(0, 0, 946, 75);
        Header.setBackground(new Color(128, 128, 128));
        frame.getContentPane().add(Header, "Header");
        Header.setLayout(null);

        JLabel ProgramName = new JLabel("Program’s Name");
        ProgramName.setBounds(75, 20, 150, 30);
        Header.add(ProgramName);
        ProgramName.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel PartnerName = new JLabel("Partner’s Name");
        PartnerName.setBounds(730, 13, 115, 20);
        PartnerName.setHorizontalAlignment(SwingConstants.RIGHT);
        PartnerName.setFont(new Font("SansSerif", Font.PLAIN, 16));
        Header.add(PartnerName);

        JPanel Profile = new JPanel();
        Profile.setBounds(855, 13, 50, 50);
        Header.add(Profile);
        Profile.setLayout(null);

        JPanel UnderString = new JPanel();
        UnderString.setBounds(745, 40, 100, 1);
        Header.add(UnderString);
        UnderString.setBackground(new Color(0, 0, 0));

        JLabel EditProfile = new JLabel("Edit Profile");
        EditProfile.setBounds(785, 43, 60, 15);
        EditProfile.setFont(new Font("SansSerif", Font.PLAIN, 12));
        Header.add(EditProfile);

        JPanel BackIcon = new JPanel();
        BackIcon.setBounds(25, 17, 40, 40);
        Header.add(BackIcon);

        JPanel box1 = new JPanel();
        box1.setBounds(10, 0, 234, 95);
        mapPanel.add(box1);
        box1.setLayout(null);

        JLabel Time = new JLabel("Time");
        Time.setBounds(10, 10, 68, 13);
        Time.setFont(new Font("Tahoma", Font.BOLD, 10));
        box1.add(Time);

        JLabel Load = new JLabel("สถาณะการจัดส่ง");
        Load.setHorizontalAlignment(SwingConstants.CENTER);
        Load.setToolTipText("");
        Load.setLabelFor(frame);
        Load.setBounds(38, 20, 143, 24);
        Load.setFont(new Font("Tahoma", Font.BOLD, 14));
        box1.add(Load);

        loadBar = new JProgressBar();
        loadBar.setBounds(10, 75, 204, 10);
        loadBar.setStringPainted(false);
        loadBar.setBackground(new Color(0, 255, 0));
        loadBar.setForeground(new Color(0, 166, 0));
        box1.add(loadBar);

        img = new JLabel("");
        img.setIcon(new ImageIcon("Img/p1.png"));
        img.setBounds(10, 45, 68, 32);
        box1.add(img);

        JPanel googlemap = new JPanel();
        googlemap.setBounds(281, 32, 619, 411);
        mapPanel.add(googlemap);
        googlemap.setLayout(null);

        JLabel img_1 = new JLabel("");
        img_1.setIcon(new ImageIcon("Img/p2.png"));
        img_1.setBounds(251, 379, 68, 32);
        googlemap.add(img_1);

        JProgressBar loadBar_2 = new JProgressBar();
        loadBar_2.setStringPainted(false);
        loadBar_2.setForeground(new Color(0, 166, 0));
        loadBar_2.setBackground(Color.GREEN);
        loadBar_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        loadBar_2.setBounds(100, 156, 186, 10);
        googlemap.add(loadBar_2);

        loadBarmap = new JProgressBar();
        loadBarmap.setStringPainted(false);
        loadBarmap.setForeground(new Color(0, 166, 0));
        loadBarmap.setBackground(Color.GREEN);
        loadBarmap.setOrientation(SwingConstants.VERTICAL);
        loadBarmap.setBounds(275, 156, 11, 255);
        googlemap.add(loadBarmap);

        JLabel PhotoEarth = new JLabel("");
        PhotoEarth.setIcon(new ImageIcon("Img/Erth.png"));
        PhotoEarth.setBounds(0, 0, 619, 411);
        googlemap.add(PhotoEarth);

        JPanel box2 = new JPanel();
        box2.setLayout(null);
        box2.setBounds(10, 100, 234, 95);
        mapPanel.add(box2);

        JPanel Profile_2 = new JPanel();
        Profile_2.setBackground(new Color(240, 240, 240));
        Profile_2.setLayout(null);
        Profile_2.setBounds(10, 21, 50, 50);
        box2.add(Profile_2);

        JLabel driverPicLabel1 = new JLabel();
        driverPicLabel1.setBounds(0, 0, 50, 50);
        driverPicLabel1.setIcon(creater.createImage("MainPagePic/driver1.png", 50, 50));
        Profile_2.add(driverPicLabel1);

        JLabel Load_1 = new JLabel("คนขับกำลังจัดส่ง");
        Load_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        Load_1.setBounds(74, 41, 120, 13);
        box2.add(Load_1);

        ta = new JTextArea();
        ta.setFont(new Font("Tahoma", Font.BOLD, 14));
        ta.setEditable(false);

        JScrollPane scrollPaneVideo = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneVideo.setBounds(10, 217, 234, 184);
        mapPanel.add(scrollPaneVideo);

        RoundedButton homeLabel = new RoundedButton(10);
        homeLabel.setContentAreaFilled(false);
        homeLabel.setFocusable(false);
        homeLabel.setText("Home");
        homeLabel.setForeground(Color.WHITE);
        homeLabel.setBorder(BorderFactory.createEmptyBorder());
        homeLabel.setColor(Color.BLACK);
        homeLabel.setColorOver(Color.gray);
        homeLabel.setColorExit(Color.black);
        homeLabel.setBounds(75, 420, 100, 25);
        mapPanel.add(homeLabel);
        homeLabel.setCursor(new Cursor(12));
        homeLabel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mapPanel.setVisible(false);
                categoryPanel.setVisible(true);
                showRestuarantPanel.setVisible(true);
                picPanel.setVisible(true);
                selectPicPanel.setVisible(true);
                exitButton.setVisible(true);
                cartButton.setVisible(true);
                deliveryButton.setBounds(100, 23, 85, 45);
            }

        });

        JLabel Load_1_1 = new JLabel("รายการอาหาร");
        Load_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        Load_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        scrollPaneVideo.setColumnHeaderView(Load_1_1);

        updateTime = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Time.setText(sdf.format(new java.util.Date()));
            }
        };
        timerVideo = new Timer(1000, updateTime);

        progress = 13;
        ActionListener updateProgress = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (progress < 100) {
                    progress++;
                    loadBar.setValue(progress);
                    int x = img.getX();
                    x += 2;
                    img.setLocation(x, img.getY());
                } else {
                    progress = 13;
                    loadBar.setValue(progress);
                    img.setLocation(10, img.getY());
                }
            }
        };
        progressTimer = new Timer(217, updateProgress);
        progress1 = 0;
        progress2 = 17;
        updateProgress2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (progress1 < 100) {
                    progress1++;
                    loadBar_2.setValue(0);
                    loadBarmap.setValue(progress1);
                    int y = img_1.getY();
                    y -= 2;
                    img_1.setLocation(251, y);
                } else if (progress1 == 100) {
                    progress2++;
                    loadBar_2.setValue(progress2);
                    int x = img_1.getX();
                    x -= 2;
                    img_1.setLocation(x, 144);
                    if (x <= 85) {
                        img_1.setLocation(251, 379);
                        progress1 = 0;
                        progress2 = 17;
                    }
                }
            }
        };

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dessertButton) {
            restuarantCard.show(showRestuarantPanel, "dessert");
        } else if (e.getSource() == mainDishButton) {
            restuarantCard.show(showRestuarantPanel, "mainDish");
        } else if (e.getSource() == otherButton) {
            restuarantCard.show(showRestuarantPanel, "all");
        } else if (e.getSource() == healtyButton) {
            restuarantCard.show(showRestuarantPanel, "healty");
        } else if (e.getSource() == drinkButton) {
            restuarantCard.show(showRestuarantPanel, "drink");
        } else if (e.getSource() == exitButton) {
            int sure = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Logout", JOptionPane.YES_NO_OPTION);
            if (sure == JOptionPane.YES_OPTION) {
                new AccountAndRegister().showFrame();
                frame.dispose();
            }
        } else if (e.getSource() == cartButton) {
            CartAndPayPage cartAndPayPage = new CartAndPayPage(username);
            cartAndPayPage.setListener(new CloseDisplayListener() {

                @Override
                public void onClose() {
                    mapPanel.setVisible(true);
                    categoryPanel.setVisible(false);
                    showRestuarantPanel.setVisible(false);
                    picPanel.setVisible(false);
                    selectPicPanel.setVisible(false);
                    exitButton.setVisible(false);
                    cartButton.setVisible(false);
                    deliveryButton.setVisible(true);
                    progressTimer.start();
                    Timer progressTimer2 = new Timer(100, updateProgress2);
                    progressTimer2.start();
                    timerVideo.start();

                    String query = String.format("Select * From user_data.cart where username = '%s'", username);
                    ResultSet rs = ConnectData.getData(query);

                    String orderMenu = "\n";
                    int count = 1;
                    try {
                        while (rs.next()) {
                            orderMenu += count + ". " + rs.getString("food_name") + "\n";
                            count++;
                        }
                        ta.setText(orderMenu);
                        new CartAndPayPage().successfulPayment(username);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            });
        } else if (e.getSource() == deliveryButton) {
            mapPanel.setVisible(true);
            categoryPanel.setVisible(false);
            showRestuarantPanel.setVisible(false);
            picPanel.setVisible(false);
            selectPicPanel.setVisible(false);
            exitButton.setVisible(false);
            cartButton.setVisible(false);
            deliveryButton.setVisible(true);
            deliveryButton.setBounds(10, 23, 85, 45);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == editLabel) {
            ProfilePage profilePage = new ProfilePage(username);
            profilePage.setListener(new CloseDisplayListener() {

                @Override
                public void onClose() {
                    frame.dispose();
                    new Mainpage(username);
                }

            });
        } else if (e.getSource() == profileLabel && profileLabel.getIcon() != null) {
            new ImageDisplay(username);
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
}
