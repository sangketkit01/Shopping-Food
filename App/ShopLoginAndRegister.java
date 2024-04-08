package App;

import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;

public class ShopLoginAndRegister implements ActionListener {

    private JFrame frame;
    private JTextField Username_TextField, Store_Username_TextField, Email_Textfield, Phone_number_textField,
            fullnameTextField, usernameLoginTextField, idTextField, Bank_account_number_Field;
    private JPasswordField passwordField, confirmPasswordField, passwordLoginTextField;
    private JComboBox<String> bankComboBox, categoryComboBox;
    private RoundedButton createAccountButton, Next_Button, loginButton, Back_Button, Back_Button_Forget,
            Back_Button_Reset;
    private CardLayout cl_register_partner;
    private JPanel register_partner, register_partner2, loginPanel;
    private JTextField usernameForgotTextField;
    private JPasswordField passwordField_1;
    private JPasswordField confirmPassField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ShopLoginAndRegister();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ShopLoginAndRegister() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initialize() {
        GuiCreater creater = new GuiCreater();
        frame = new JFrame();
        frame.setBounds(100, 100, 960, 609);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        cl_register_partner = new CardLayout(0, 0);

        register_partner = new JPanel();
        register_partner.setBounds(0, 0, 956, 583);
        frame.getContentPane().add(register_partner);
        register_partner.setLayout(cl_register_partner);

        loginPanel = new JPanel();
        register_partner.add(loginPanel, "Login");
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(255, 255, 255));

        JPanel smallLoginPanel = new JPanel();
        smallLoginPanel.setLayout(null);
        smallLoginPanel.setBackground(new Color(240, 240, 240));
        smallLoginPanel.setBounds(553, 30, 334, 519);
        loginPanel.add(smallLoginPanel);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        loginLabel.setBounds(23, 89, 287, 48);
        smallLoginPanel.add(loginLabel);

        JLabel usernameLoginLabel = new JLabel("Username");
        usernameLoginLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        usernameLoginLabel.setBounds(25, 144, 88, 31);
        smallLoginPanel.add(usernameLoginLabel);

        usernameLoginTextField = new JTextField();
        usernameLoginTextField.setFont(new Font("Dialog", Font.PLAIN, 11));
        usernameLoginTextField.setColumns(10);
        usernameLoginTextField.setBounds(25, 173, 287, 32);
        smallLoginPanel.add(usernameLoginTextField);
        usernameLoginTextField.setBorder(new RoundedBorder(8));

        JLabel passwordLoginLabel = new JLabel("Password");
        passwordLoginLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        passwordLoginLabel.setBounds(25, 217, 88, 31);
        smallLoginPanel.add(passwordLoginLabel);

        passwordLoginTextField = new JPasswordField();
        passwordLoginTextField.setFont(new Font("Dialog", Font.PLAIN, 11));
        passwordLoginTextField.setBounds(25, 250, 287, 30);
        smallLoginPanel.add(passwordLoginTextField);
        passwordLoginTextField.setBorder(new RoundedBorder(8));

        loginButton = new RoundedButton(10);
        loginButton.setColor(Color.black);
        loginButton.setColorOver(Color.gray);
        loginButton.setColorExit(Color.black);
        loginButton.setText("Login");
        loginButton.addActionListener(this);
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginButton.setBounds(25, 315, 287, 31);
        smallLoginPanel.add(loginButton);

        JLabel forgotPasswordLabel = new JLabel("Forgot password");
        forgotPasswordLabel.setBounds(187, 285, 164, 20);
        forgotPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        forgotPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        smallLoginPanel.add(forgotPasswordLabel);
        forgotPasswordLabel.setCursor(new Cursor(12));
        forgotPasswordLabel.setForeground(new Color(65, 105, 255));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cl_register_partner.show(register_partner, "forgetpass");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                forgotPasswordLabel.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgotPasswordLabel.setForeground(new Color(65, 105, 255));
            }
        });

        JLabel donthaveAccountLabel = new JLabel("Don't have an account?");
        donthaveAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        donthaveAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        donthaveAccountLabel.setBounds(85, 412, 164, 13);
        smallLoginPanel.add(donthaveAccountLabel);

        JLabel createAccountLabel = new JLabel("Create an account");
        createAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        createAccountLabel.setBounds(85, 430, 164, 13);
        createAccountLabel.setForeground(new Color(65, 105, 255));
        smallLoginPanel.add(createAccountLabel);
        createAccountLabel.setCursor(new Cursor(12));
        createAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cl_register_partner.show(register_partner, "register1");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createAccountLabel.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createAccountLabel.setForeground(new Color(65, 105, 255));
            }
        });

        JPanel register_partner1 = new JPanel();
        register_partner.add(register_partner1, "register1");
        register_partner1.setLayout(null);
        register_partner1.setBackground(new Color(255, 255, 255));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setBounds(553, 30, 334, 519);
        register_partner1.add(panel);
        panel.setLayout(null);

        Next_Button = new RoundedButton(10);
        Next_Button.setColor(Color.black);
        Next_Button.setColorOver(Color.gray);
        Next_Button.setColorExit(Color.black);
        Next_Button.setBounds(107, 471, 195, 37);
        Next_Button.setText("Next");
        Next_Button.setFont(new Font("Kanit", Font.BOLD, 17));
        panel.add(Next_Button);
        Next_Button.addActionListener(this);
        Next_Button.setFocusable(false);

        Back_Button = new RoundedButton(10);
        Back_Button.setFont(new Font("Kanit", Font.BOLD, 17));
        Back_Button.setColor(new Color(240, 240, 240));
        Back_Button.setColorOver(Color.white);
        Back_Button.setColorExit(new Color(240, 240, 240));
        Back_Button.setText("Back");
        Back_Button.setBounds(26, 471, 75, 37);
        panel.add(Back_Button);
        Back_Button.setContentAreaFilled(false);
        Back_Button.setBorder(BorderFactory.createLineBorder(Color.black));
        Back_Button.setForeground(Color.BLACK);
        Back_Button.addActionListener(this);
        Back_Button.setFocusable(false);

        JLabel lblNewLabel = new JLabel("Create Partner Account");
        lblNewLabel.setBounds(26, 11, 287, 48);
        lblNewLabel.setFont(new Font("Kanit", Font.BOLD, 24));
        panel.add(lblNewLabel);

        JLabel Name_Label = new JLabel("Username");
        Name_Label.setBounds(26, 58, 88, 31);
        Name_Label.setFont(new Font("Kanit", Font.PLAIN, 16));
        panel.add(Name_Label);

        Username_TextField = new JTextField();
        Username_TextField.setFont(new Font("Kanit", Font.PLAIN, 11));
        Username_TextField.setBounds(26, 84, 287, 31);
        panel.add(Username_TextField);
        Username_TextField.setColumns(10);
        Username_TextField.addActionListener(this);
        Username_TextField.setBorder(new RoundedBorder(8));

        Store_Username_TextField = new JTextField();
        Store_Username_TextField.setFont(new Font("Kanit", Font.PLAIN, 11));
        Store_Username_TextField.setBounds(26, 152, 287, 31);
        Store_Username_TextField.setColumns(10);
        panel.add(Store_Username_TextField);
        Store_Username_TextField.addActionListener(this);
        Store_Username_TextField.setBorder(new RoundedBorder(8));

        JLabel Store_name_Label = new JLabel("Store name");
        Store_name_Label.setBounds(26, 126, 88, 31);
        Store_name_Label.setFont(new Font("Kanit", Font.PLAIN, 16));
        panel.add(Store_name_Label);

        Email_Textfield = new JTextField();
        Email_Textfield.setFont(new Font("Kanit", Font.PLAIN, 11));
        Email_Textfield.setColumns(10);
        Email_Textfield.setBounds(26, 288, 287, 31);
        panel.add(Email_Textfield);
        Email_Textfield.addActionListener(this);
        Email_Textfield.setBorder(new RoundedBorder(8));

        JLabel Email_Label = new JLabel("Email");
        Email_Label.setFont(new Font("Kanit", Font.PLAIN, 16));
        Email_Label.setBounds(26, 262, 88, 31);
        panel.add(Email_Label);

        Phone_number_textField = new JTextField();
        Phone_number_textField.setFont(new Font("Kanit", Font.PLAIN, 11));
        Phone_number_textField.setColumns(10);
        Phone_number_textField.setBounds(26, 220, 287, 31);
        panel.add(Phone_number_textField);
        Phone_number_textField.setDocument(new NumericDocument());
        Phone_number_textField.setBorder(new RoundedBorder(8));
        Phone_number_textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
            }

            public void checkLength() {
                String phoneNumber = Phone_number_textField.getText();
                if (phoneNumber.length() == 10) {
                    Phone_number_textField.setEditable(false);
                    Phone_number_textField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                Phone_number_textField.setEditable(true);
                            }
                        }
                    });
                } else {
                    Phone_number_textField.setEditable(true);
                }

            }

        });
        Phone_number_textField.addActionListener(this);

        JLabel Phone_number_Label_1 = new JLabel("Phone number");
        Phone_number_Label_1.setFont(new Font("Kanit", Font.PLAIN, 16));
        Phone_number_Label_1.setBounds(26, 194, 162, 31);
        panel.add(Phone_number_Label_1);

        JLabel Password_Label_2 = new JLabel("Confirm password");
        Password_Label_2.setFont(new Font("Kanit", Font.PLAIN, 16));
        Password_Label_2.setBounds(26, 391, 150, 31);
        panel.add(Password_Label_2);

        JLabel Password_Label_1 = new JLabel("Password");
        Password_Label_1.setFont(new Font("Kanit", Font.PLAIN, 16));
        Password_Label_1.setBounds(26, 323, 88, 31);
        panel.add(Password_Label_1);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Kanit", Font.PLAIN, 11));
        passwordField.setBounds(26, 353, 287, 30);
        panel.add(passwordField);
        passwordField.addActionListener(this);
        passwordField.setBorder(new RoundedBorder(8));

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Kanit", Font.PLAIN, 11));
        confirmPasswordField.setBounds(26, 422, 287, 30);
        panel.add(confirmPasswordField);
        confirmPasswordField.addActionListener(this);
        confirmPasswordField.setBorder(new RoundedBorder(8));

        register_partner2 = new JPanel();
        register_partner.add(register_partner2, "register2");
        register_partner2.setLayout(null);
        register_partner2.setBackground(new Color(255, 255, 255));

        JLabel img = new JLabel();
        img.setBounds(0, 0, 486, 583);
        img.setIcon(creater.createImage("LoginPic/driver.jpg", 486, 583));
        loginPanel.add(img);

        JLabel img_2 = new JLabel();
        img_2.setBounds(0, 0, 486, 583);
        register_partner1.add(img_2);
        img_2.setIcon(creater.createImage("LoginPic/image.png", 486, 583));

        JLabel img_3 = new JLabel();
        img_3.setBounds(0, 0, 486, 583);
        register_partner2.add(img_3);
        img_3.setIcon(creater.createImage("LoginPic/image.png", 486, 583));

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(553, 30, 334, 519);
        register_partner2.add(panel_1);
        panel_1.setBackground(new Color(240, 240, 240));

        RoundedButton Back_Button_1 = new RoundedButton(10);
        Back_Button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl_register_partner.show(register_partner, "register1");
            }
        });
        Back_Button_1.setFont(new Font("Kanit", Font.BOLD, 17));
        Back_Button_1.setColor(new Color(240, 240, 240));
        Back_Button_1.setColorOver(Color.WHITE);
        Back_Button_1.setColorExit(new Color(240, 240, 240));
        Back_Button_1.setText("Back");
        Back_Button_1.setBounds(26, 471, 75, 37);
        panel_1.add(Back_Button_1);

        JLabel lblNewLabel_1 = new JLabel("Create Partner  Account");
        lblNewLabel_1.setFont(new Font("Kanit", Font.BOLD, 24));
        lblNewLabel_1.setBounds(26, 11, 287, 48);
        panel_1.add(lblNewLabel_1);

        JLabel ID_card_number_Label = new JLabel("ID card number");
        ID_card_number_Label.setFont(new Font("Kanit", Font.PLAIN, 16));
        ID_card_number_Label.setBounds(26, 136, 162, 31);
        panel_1.add(ID_card_number_Label);

        idTextField = new JTextField();
        idTextField.setFont(new Font("Kanit", Font.PLAIN, 11));
        idTextField.setColumns(10);
        idTextField.setBounds(26, 162, 287, 31);
        idTextField.setBorder(new RoundedBorder(8));
        panel_1.add(idTextField);
        idTextField.setDocument(new NumericDocument());
        idTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
            }

            public void checkLength() {
                String id = idTextField.getText();
                if (id.length() == 13) {
                    idTextField.setEditable(false);
                    idTextField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                idTextField.setEditable(true);
                            }
                        }
                    });
                } else {
                    idTextField.setEditable(true);
                }
            }

        });
        idTextField.addActionListener(this);

        JLabel Select_your_bank_Label = new JLabel("Select your bank");
        Select_your_bank_Label.setFont(new Font("Kanit", Font.PLAIN, 16));
        Select_your_bank_Label.setBounds(26, 204, 162, 31);
        panel_1.add(Select_your_bank_Label);

        Bank_account_number_Field = new JTextField();
        Bank_account_number_Field.setFont(new Font("Kanit", Font.PLAIN, 11));
        Bank_account_number_Field.setColumns(10);
        Bank_account_number_Field.setBounds(26, 298, 287, 31);
        panel_1.add(Bank_account_number_Field);
        Bank_account_number_Field.setDocument(new NumericDocument());
        Bank_account_number_Field.addActionListener(this);
        Bank_account_number_Field.setBorder(new RoundedBorder(8));

        JLabel Bank_account_number_Label_1_1 = new JLabel("Bank account number");
        Bank_account_number_Label_1_1.setFont(new Font("Kanit", Font.PLAIN, 16));
        Bank_account_number_Label_1_1.setBounds(26, 272, 269, 31);
        panel_1.add(Bank_account_number_Label_1_1);

        bankComboBox = new JComboBox();
        bankComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
        bankComboBox
                .setModel(new DefaultComboBoxModel(new String[] { "กรุงไทย", "กรุงเทพ", "กรุงศรี", "บางระจันทร์" }));
        bankComboBox.setBounds(26, 235, 287, 30);
        panel_1.add(bankComboBox);
        bankComboBox.addActionListener(this);

        createAccountButton = new RoundedButton(10);
        createAccountButton.setFont(new Font("Kanit", Font.BOLD, 17));
        createAccountButton.setColor(Color.black);
        createAccountButton.setColorOver(Color.gray);
        createAccountButton.setColorExit(Color.black);
        createAccountButton.setText("Create an account");
        createAccountButton.setBounds(107, 471, 195, 37);
        createAccountButton.addActionListener(this);
        panel_1.add(createAccountButton);

        categoryComboBox = new JComboBox();
        categoryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
        categoryComboBox.setBounds(26, 370, 287, 30);
        categoryComboBox
                .setModel(new DefaultComboBoxModel(new String[] { "main_dish", "healty", "dessert", "drink" }));
        panel_1.add(categoryComboBox);

        JLabel categoryLabel = new JLabel("Food Category");
        categoryLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        categoryLabel.setBounds(26, 339, 162, 31);
        panel_1.add(categoryLabel);

        JLabel fullNameLabel = new JLabel("Full name");
        fullNameLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        fullNameLabel.setBounds(26, 69, 162, 31);
        panel_1.add(fullNameLabel);

        fullnameTextField = new JTextField();
        fullnameTextField.setFont(new Font("Dialog", Font.PLAIN, 11));
        fullnameTextField.setColumns(10);
        fullnameTextField.setBounds(26, 96, 287, 31);
        panel_1.add(fullnameTextField);
        fullnameTextField.setBorder(new RoundedBorder(8));

        loginButton.setCursor(new Cursor(12));
        Next_Button.setCursor(new Cursor(12));
        Back_Button_1.setCursor(new Cursor(12));
        createAccountButton.setCursor(new Cursor(12));

        loginButton.setContentAreaFilled(false);
        Next_Button.setContentAreaFilled(false);
        createAccountButton.setContentAreaFilled(false);
        Back_Button_1.setContentAreaFilled(false);

        loginButton.setForeground(Color.WHITE);
        createAccountButton.setForeground(Color.WHITE);
        Next_Button.setForeground(Color.WHITE);
        Back_Button_1.setForeground(Color.BLACK);

        loginButton.setBorder(BorderFactory.createEmptyBorder());
        createAccountButton.setBorder(BorderFactory.createEmptyBorder());
        Next_Button.setBorder(BorderFactory.createEmptyBorder());
        Back_Button_1.setBorder(BorderFactory.createLineBorder(Color.black));

        loginButton.setFocusable(false);
        createAccountButton.setFocusable(false);
        Next_Button.setFocusable(false);
        Back_Button_1.setFocusable(false);

        JPanel forgotPassPanel = new JPanel();
        forgotPassPanel.setBackground(new Color(255, 255, 255));
        register_partner.add(forgotPassPanel, "forgetpass");
        forgotPassPanel.setLayout(null);

        JLabel img_1_1 = new JLabel();
        img_1_1.setBounds(0, 0, 486, 583);
        forgotPassPanel.add(img_1_1);
        img_1_1.setIcon(creater.createImage("LoginPic/image.png", 486, 583));

        JPanel forgotPassPanel_1_1 = new JPanel();
        forgotPassPanel_1_1.setBounds(553, 30, 334, 519);
        forgotPassPanel.add(forgotPassPanel_1_1);
        forgotPassPanel_1_1.setLayout(null);

        JLabel forgetPassLabel = new JLabel("Forgot Password");
        forgetPassLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        forgetPassLabel.setBounds(23, 10, 202, 48);
        forgotPassPanel_1_1.add(forgetPassLabel);

        JLabel usernameForgotLabel = new JLabel("Username");
        usernameForgotLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        usernameForgotLabel.setBounds(23, 57, 162, 31);
        forgotPassPanel_1_1.add(usernameForgotLabel);

        usernameForgotTextField = new JTextField();
        usernameForgotTextField.setFont(new Font("Dialog", Font.PLAIN, 11));
        usernameForgotTextField.setColumns(10);
        usernameForgotTextField.setBounds(24, 97, 287, 31);
        forgotPassPanel_1_1.add(usernameForgotTextField);
        usernameForgotTextField.setBorder(new RoundedBorder(8));

        Back_Button_Forget = new RoundedButton(10);
        Back_Button_Forget.setFont(new Font("Kanit", Font.BOLD, 17));
        Back_Button_Forget.setColor(new Color(240, 240, 240));
        Back_Button_Forget.setColorOver(Color.white);
        Back_Button_Forget.setColorExit(new Color(240, 240, 240));
        Back_Button_Forget.setText("Back");
        Back_Button_Forget.setBounds(23, 141, 75, 37);
        forgotPassPanel_1_1.add(Back_Button_Forget);
        Back_Button_Forget.setContentAreaFilled(false);
        Back_Button_Forget.setBorder(BorderFactory.createLineBorder(Color.black));
        Back_Button_Forget.setForeground(Color.BLACK);
        Back_Button_Forget.addActionListener(this);
        Back_Button_Forget.setFocusable(false);
        Back_Button_Forget.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl_register_partner.show(register_partner, "Login");
            }

        });

        RoundedButton getPasswordButton = new RoundedButton(10);
        getPasswordButton.setBounds(105, 141, 205, 37);
        getPasswordButton.setContentAreaFilled(false);
        getPasswordButton.setBorder(BorderFactory.createEmptyBorder());
        getPasswordButton.setColor(Color.BLACK);
        getPasswordButton.setColorOver(Color.gray);
        getPasswordButton.setColorExit(Color.black);
        getPasswordButton.setText("Get Password");
        getPasswordButton.setForeground(Color.WHITE);
        forgotPassPanel_1_1.add(getPasswordButton);
        getPasswordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String query = String.format("Select * From store.store_user Where username = '%s'",
                        usernameForgotTextField.getText());
                ResultSet rs = ConnectData.getData(query);
                try {
                    if (rs.next()) {
                        cl_register_partner.show(register_partner, "resetpass");
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        JPanel resetPassPanel = new JPanel();
        resetPassPanel.setBackground(new Color(255, 255, 255));
        register_partner.add(resetPassPanel, "resetpass");
        resetPassPanel.setLayout(null);

        JLabel img_2_1 = new JLabel();
        img_2_1.setBounds(0, 0, 486, 583);
        resetPassPanel.add(img_2_1);
        img_2_1.setIcon(creater.createImage("LoginPic/image.png", 486, 583));

        JPanel resetPassPanel_1 = new JPanel();
        resetPassPanel_1.setLayout(null);
        resetPassPanel_1.setBounds(553, 30, 334, 519);
        resetPassPanel.add(resetPassPanel_1);

        JLabel resetPassLabel = new JLabel("Reset Password");
        resetPassLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        resetPassLabel.setBounds(73, 10, 188, 48);
        resetPassPanel_1.add(resetPassLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        passwordLabel.setBounds(23, 57, 162, 31);
        resetPassPanel_1.add(passwordLabel);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(24, 98, 287, 33);
        resetPassPanel_1.add(passwordField_1);
        passwordField_1.setBorder(new RoundedBorder(8));

        JLabel confirmPassLabel = new JLabel("Confirm Password");
        confirmPassLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        confirmPassLabel.setBounds(23, 141, 162, 31);
        resetPassPanel_1.add(confirmPassLabel);

        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(24, 182, 287, 33);
        resetPassPanel_1.add(confirmPassField);
        confirmPassField.setBorder(new RoundedBorder(8));

        RoundedButton resetPassButton = new RoundedButton(15);
        resetPassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = "Update store.store_user Set password = ? Where username = ?";
                try {
                    if (!String.valueOf(passwordField_1.getPassword())
                            .equals(String.valueOf(confirmPassField.getPassword()))) {
                        JOptionPane.showMessageDialog(null, "Password does not match");
                    } else {
                        ConnectData.UpdateData(query, String.valueOf(passwordField_1.getPassword()),
                                usernameForgotTextField.getText());
                        JOptionPane.showMessageDialog(null, "Update password successfully");
                        cl_register_partner.show(register_partner, "Login");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        resetPassButton.setBounds(105, 243, 205, 37);
        resetPassPanel_1.add(resetPassButton);
        resetPassButton.setContentAreaFilled(false);
        resetPassButton.setBorder(BorderFactory.createEmptyBorder());
        resetPassButton.setColor(Color.BLACK);
        resetPassButton.setColorOver(Color.gray);
        resetPassButton.setColorExit(Color.black);
        resetPassButton.setText("Reset Password");
        resetPassButton.setForeground(Color.WHITE);

        Back_Button_Reset = new RoundedButton(10);
        Back_Button_Reset.setFont(new Font("Kanit", Font.BOLD, 17));
        Back_Button_Reset.setColor(new Color(240, 240, 240));
        Back_Button_Reset.setColorOver(Color.white);
        Back_Button_Reset.setColorExit(new Color(240, 240, 240));
        Back_Button_Reset.setText("Back");
        Back_Button_Reset.setBounds(23, 243, 75, 37);
        resetPassPanel_1.add(Back_Button_Reset);
        Back_Button_Reset.setContentAreaFilled(false);
        Back_Button_Reset.setBorder(BorderFactory.createLineBorder(Color.black));
        Back_Button_Reset.setForeground(Color.BLACK);
        Back_Button_Reset.addActionListener(this);
        Back_Button_Reset.setFocusable(false);
        Back_Button_Reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl_register_partner.show(register_partner, "forgetpass");
            }

        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean userExist = false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(Email_Textfield.getText());
        if (e.getSource() == loginButton) {
            String loginQuery = String.format(
                    "Select * From store.store_user WHERE username = '%s' And password = '%s'",
                    usernameLoginTextField.getText(), String.valueOf(passwordLoginTextField.getPassword()));
            ResultSet loginRS = ConnectData.getData(loginQuery);
            try {
                if (loginRS.next()) {
                    new ShopPage(usernameLoginTextField.getText());
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == Next_Button) {
            String userCheckQuery = String.format("Select * From store.store_user WHERE username = '%s'",
                    Username_TextField.getText());
            ResultSet userCheckRS = ConnectData.getData(userCheckQuery);
            try {
                if (userCheckRS.next()) {
                    userExist = true;
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            if (Username_TextField.getText().isEmpty() || Store_Username_TextField.getText().isEmpty()
                    || Phone_number_textField.getText().isEmpty() || Email_Textfield.getText().isEmpty()
                    || String.valueOf(passwordField.getPassword()).isBlank()
                    || String.valueOf(confirmPasswordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have to fullfill in all textfield");
            } else if (!String.valueOf(passwordField.getPassword())
                    .equals(String.valueOf(confirmPasswordField.getPassword()))) {
                JOptionPane.showMessageDialog(null, "Password doesn't match");
            } else if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "Incorrect email");
            } else if (Phone_number_textField.getText().length() != 10) {
                JOptionPane.showMessageDialog(null, "Incorrect phone number");
            } else if (userExist) {
                JOptionPane.showMessageDialog(null, "Username already exist");
            } else {
                cl_register_partner.show(register_partner, "register2");
            }
        } else if (e.getSource() == createAccountButton) {
            if (fullnameTextField.getText().isEmpty() || idTextField.getText().isEmpty()
                    || Bank_account_number_Field.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "You have to fullfill in all textfield");
            } else if (idTextField.getText().length() != 13) {
                JOptionPane.showMessageDialog(null, "Incorrect ID number");
            } else {
                try {
                    String insertQuery = String
                            .format("Insert into store.store_user (username,password,name,surname,store_name,email,phone,bank,bank_account,category)"
                                    +
                                    "values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                                    Username_TextField.getText(), String.valueOf(passwordField.getPassword()),
                                    fullnameTextField.getText().split(" ")[0],
                                    fullnameTextField.getText().split(" ")[1],
                                    Store_Username_TextField.getText(), Email_Textfield.getText(),
                                    Phone_number_textField.getText(),
                                    String.valueOf(bankComboBox.getSelectedItem()), Bank_account_number_Field.getText(),
                                    String.valueOf(categoryComboBox.getSelectedItem()));
                    ConnectData.setData(insertQuery, "");
                    JOptionPane.showMessageDialog(null, "Create account successfully");
                    cl_register_partner.show(register_partner, "Login");
                } catch (ArrayIndexOutOfBoundsException e3) {
                    JOptionPane.showMessageDialog(null, "Please enter your surname");
                }
            }
        } else if (e.getSource() == Back_Button) {
            cl_register_partner.show(register_partner, "Login");
        }
    }
}
