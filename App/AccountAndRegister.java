package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.sql.SQLException;

public class AccountAndRegister extends Action implements ActionListener, FocusListener, MouseListener {

    private JFrame frame;
    private CardLayout cardLayout, pictureCard;
    private JTextField usernameLoginField, usernameRegisterField, phoneField, emailRegisterField, usernameforgetField;
    private JPasswordField passwordRegisterField, confirmPasswordField, confirmResetPassfield, resetPassField,
            passwordField;
    private JLabel createAccountLabel, forgetpassLabel;
    private JButton backButton1,
            backForgetPassButton, backResetPassButton;
    private RoundedButton signInButton, signUpButton, resetPassButton, confirmResetPassbutton;
    private JPanel bigLoginPanel, bigRegisterPanel, picturePanel1, forgetpassPanel;
    private JPanel resetPassPanel;
    private String username;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { 
                    AccountAndRegister window = new AccountAndRegister();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AccountAndRegister() {
        initialize();
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        GuiCreater create = new GuiCreater();

        frame = new JFrame();
        frame.setBounds(100, 100, 976, 609);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setTitle("Delivery App");

        cardLayout = new CardLayout(0, 0);
        pictureCard = new CardLayout(0, 0);

        bigLoginPanel = new JPanel();
        bigLoginPanel.setBounds(582, 0, 380, 572);
        frame.getContentPane().add(bigLoginPanel);
        bigLoginPanel.setLayout(cardLayout);

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(255, 255, 255));
        loginPanel.setForeground(new Color(128, 128, 128));
        loginPanel.setBounds(88, 54, 234, 443);
        bigLoginPanel.add(loginPanel, "loginPanel");
        loginPanel.setLayout(null);

        JPanel smallLoginPanel = new JPanel();
        smallLoginPanel.setBounds(88, 54, 234, 443);
        loginPanel.add(smallLoginPanel);
        smallLoginPanel.setLayout(null);

        JLabel loginLabel = new JLabel("Login\r\n");
        loginLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        loginLabel.setBounds(90, 30, 60, 27);
        smallLoginPanel.add(loginLabel);

        JLabel usernameLoginLabel = new JLabel("Username");
        usernameLoginLabel.setBounds(42, 90, 85, 13);
        smallLoginPanel.add(usernameLoginLabel);

        usernameLoginField = new JTextField();
        usernameLoginField.setBorder(new RoundedBorder(8));
        usernameLoginField.setColumns(10);
        usernameLoginField.setBounds(40, 108, 153, 32);
        smallLoginPanel.add(usernameLoginField);

        JLabel userIconLabel = new JLabel(create.createImage("LoginPic/User.png", 25, 25));
        userIconLabel.setBounds(0, 108, 45, 27);
        smallLoginPanel.add(userIconLabel);

        JLabel passwordLoginLabel = new JLabel("Password");
        passwordLoginLabel.setBounds(40, 158, 85, 13);
        smallLoginPanel.add(passwordLoginLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 177, 153, 30);
        smallLoginPanel.add(passwordField);
        passwordField.setBorder(new RoundedBorder(8));
        passwordField.setEchoChar('\u25cf');
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));

        signInButton = new RoundedButton(10);
        signInButton.setText("SIGN IN");
        signInButton.setBounds(40, 237, 153, 30);
        signInButton.addActionListener(this);
        signInButton.setCursor(new Cursor(12));
        signInButton.setContentAreaFilled(false);
        signInButton.setForeground(Color.WHITE);
        signInButton.setColor(Color.BLACK);
        signInButton.setColorOver(new Color(128, 128, 128));
        signInButton.setColorExit(new Color(0, 0, 0));
        signInButton.setBorder(BorderFactory.createEmptyBorder());
        smallLoginPanel.add(signInButton);

        JLabel dontHaveAccountLabel = new JLabel("Don't have account yet? ");
        dontHaveAccountLabel.setBounds(58, 330, 176, 13);
        smallLoginPanel.add(dontHaveAccountLabel);

        createAccountLabel = new JLabel("Create an account");
        createAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createAccountLabel.setBounds(74, 353, 139, 13);
        createAccountLabel.addMouseListener(this);
        createAccountLabel.setForeground(new Color(65, 105, 255));
        smallLoginPanel.add(createAccountLabel);

        forgetpassLabel = new JLabel("<html><u>Forgot Password</html></u>");
        forgetpassLabel.setBounds(117, 210, 100, 20);
        forgetpassLabel.addMouseListener(this);
        forgetpassLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        smallLoginPanel.add(forgetpassLabel);
        forgetpassLabel.setForeground(new Color(65, 105, 255));
        forgetpassLabel.setFont(new Font("Tahoma", Font.BOLD, 9));

        bigRegisterPanel = new JPanel();
        bigRegisterPanel.setBackground(new Color(255, 255, 255));
        bigLoginPanel.add(bigRegisterPanel, "registerPanel");
        bigRegisterPanel.setLayout(null);

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(new Color(240, 240, 240));
        registerPanel.setForeground(new Color(128, 128, 128));
        registerPanel.setBounds(88, 54, 234, 443);
        bigRegisterPanel.add(registerPanel);
        registerPanel.setLayout(null);

        JLabel createRegisterLabel = new JLabel("Create an account");
        createRegisterLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        createRegisterLabel.setBounds(26, 30, 200, 27);
        registerPanel.add(createRegisterLabel);

        usernameRegisterField = new JTextField();
        usernameRegisterField.setBorder(new RoundedBorder(8));
        usernameRegisterField.setColumns(10);
        usernameRegisterField.setBounds(40, 108, 153, 32);
        registerPanel.add(usernameRegisterField);

        JLabel usernameRegisterLabel = new JLabel("Username");
        usernameRegisterLabel.setBounds(42, 90, 85, 13);
        registerPanel.add(usernameRegisterLabel);

        emailRegisterField = new JTextField();
        emailRegisterField.setBorder(new RoundedBorder(8));
        emailRegisterField.setColumns(10);
        emailRegisterField.setBounds(40, 172, 153, 32);
        registerPanel.add(emailRegisterField);

        JLabel emailRegisterLabel = new JLabel("Email");
        emailRegisterLabel.setBounds(40, 157, 85, 13);
        registerPanel.add(emailRegisterLabel);

        JLabel passwordRegisterLabel = new JLabel("Password");
        passwordRegisterLabel.setBounds(41, 279, 85, 13);
        registerPanel.add(passwordRegisterLabel);

        passwordRegisterField = new JPasswordField();
        passwordRegisterField.setBorder(new RoundedBorder(8));
        passwordRegisterField.setColumns(10);
        passwordRegisterField.setBounds(40, 296, 153, 32);
        registerPanel.add(passwordRegisterField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(42, 339, 120, 13);
        registerPanel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(new RoundedBorder(8));
        confirmPasswordField.setColumns(10);
        confirmPasswordField.setBounds(40, 356, 153, 32);
        registerPanel.add(confirmPasswordField);

        signUpButton = new RoundedButton(10);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setText("SIGN UP");
        signUpButton.setContentAreaFilled(false);
        signUpButton.setColorOver(new Color(128, 128, 128));
        signUpButton.setColorExit(new Color(0, 0, 0));
        signUpButton.setBorder(BorderFactory.createEmptyBorder());
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setColor(Color.BLACK);
        signUpButton.setBounds(40, 404, 153, 30);
        signUpButton.addActionListener(this);
        signUpButton.setCursor(new Cursor(12));
        registerPanel.add(signUpButton);

        phoneField = new JTextField();
        phoneField.setBorder(new RoundedBorder(8));
        phoneField.setColumns(10);
        phoneField.setBounds(40, 236, 153, 32);
        phoneField.setDocument(new NumericDocument());
        registerPanel.add(phoneField);

        JLabel phoneNumberLabel = new JLabel("Phone");
        phoneNumberLabel.setBounds(42, 219, 85, 13);
        registerPanel.add(phoneNumberLabel);

        picturePanel1 = new JPanel();
        picturePanel1.setBackground(new Color(0, 0, 0));
        picturePanel1.setBounds(0, 0, 582, 572);
        frame.getContentPane().add(picturePanel1);
        picturePanel1.setLayout(pictureCard);

        picturePanel1.add(create.createLabel("MainPagePic/picture1.png", 0, 0, 582, 572), "picture1");
        picturePanel1.add(create.createLabel("MainPagePic/picture2.png", 0, 0, 582, 572), "picture2");

        forgetpassPanel = new JPanel();
        forgetpassPanel.setBackground(new Color(255, 255, 255));
        bigLoginPanel.add(forgetpassPanel, "forgetpass");
        forgetpassPanel.setLayout(null);

        JPanel smallForgetPanel = new JPanel();
        smallForgetPanel.setLayout(null);
        smallForgetPanel.setForeground(Color.GRAY);
        smallForgetPanel.setBackground(new Color(240, 240, 240));
        smallForgetPanel.setBounds(80, 59, 234, 443);
        forgetpassPanel.add(smallForgetPanel);

        JLabel forgetPassLabel = new JLabel("Forgot Password");
        forgetPassLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        forgetPassLabel.setBounds(35, 30, 180, 27);
        smallForgetPanel.add(forgetPassLabel);

        backForgetPassButton = new JButton("\u276E");
        backForgetPassButton.setBounds(5, 75, 100, 50);
        backForgetPassButton.setBorder(BorderFactory.createEmptyBorder());
        backForgetPassButton.setContentAreaFilled(false);
        backForgetPassButton.setCursor(new Cursor(12));
        backForgetPassButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
        forgetpassPanel.add(backForgetPassButton);
        backForgetPassButton.addActionListener(this);

        usernameforgetField = new JTextField();
        usernameforgetField.setColumns(10);
        usernameforgetField.setBorder(new RoundedBorder(8));
        usernameforgetField.setColumns(10);
        usernameforgetField.setBounds(40, 112, 153, 32);
        smallForgetPanel.add(usernameforgetField);

        JLabel usernameForget = new JLabel("Username");
        usernameForget.setBounds(41, 85, 85, 13);
        smallForgetPanel.add(usernameForget);

        resetPassButton = new RoundedButton(10);
        resetPassButton.setColor(Color.black);
        resetPassButton.setColorOver(Color.gray);
        resetPassButton.setColorExit(Color.BLACK);
        resetPassButton.setText("Get Reset Password");
        resetPassButton.setForeground(Color.WHITE);
        resetPassButton.setContentAreaFilled(false);
        resetPassButton.setBorder(BorderFactory.createEmptyBorder());
        resetPassButton.setBackground(new Color(0, 0, 0));
        resetPassButton.setBounds(40, 159, 153, 27);
        resetPassButton.addActionListener(this);
        smallForgetPanel.add(resetPassButton);

        resetPassPanel = new JPanel();
        bigLoginPanel.add(resetPassPanel, "resetPass");
        resetPassPanel.setLayout(null);
        resetPassPanel.setBackground(new Color(255, 255, 255));

        backResetPassButton = new JButton("\u276E");
        backResetPassButton.setBounds(5, 75, 100, 50);
        backResetPassButton.setBorder(BorderFactory.createEmptyBorder());
        backResetPassButton.setContentAreaFilled(false);
        backResetPassButton.setCursor(new Cursor(12));
        backResetPassButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
        resetPassPanel.add(backResetPassButton);
        backResetPassButton.addActionListener(this);

        JPanel smallForgetPanel_1 = new JPanel();
        smallForgetPanel_1.setLayout(null);
        smallForgetPanel_1.setForeground(Color.GRAY);
        smallForgetPanel_1.setBackground(new Color(240, 240, 240));
        smallForgetPanel_1.setBounds(77, 61, 234, 443);
        resetPassPanel.add(smallForgetPanel_1);

        JLabel forgetPassLabel_1 = new JLabel("Reset Password");
        forgetPassLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        forgetPassLabel_1.setBounds(44, 30, 172, 27);
        smallForgetPanel_1.add(forgetPassLabel_1);

        confirmResetPassbutton = new RoundedButton(10);
        confirmResetPassbutton.setForeground(Color.WHITE);
        confirmResetPassbutton.setColor(Color.black);
        confirmResetPassbutton.setColorOver(Color.gray);
        confirmResetPassbutton.setColorExit(Color.black);
        confirmResetPassbutton.setContentAreaFilled(false);
        confirmResetPassbutton.setBorder(BorderFactory.createEmptyBorder());
        confirmResetPassbutton.setCursor(new Cursor(12));
        confirmResetPassbutton.setBounds(45, 209, 150, 27);
        confirmResetPassbutton.addActionListener(this);
        confirmResetPassbutton.setText("Reset Password");
        smallForgetPanel_1.add(confirmResetPassbutton);

        resetPassField = new JPasswordField();
        resetPassField.setBounds(44, 97, 153, 27);
        smallForgetPanel_1.add(resetPassField);
        resetPassField.setBorder(new RoundedBorder(8));

        JLabel resetpassLabel = new JLabel("Password");
        resetpassLabel.setBounds(44, 79, 85, 13);
        smallForgetPanel_1.add(resetpassLabel);

        confirmResetPassfield = new JPasswordField();
        confirmResetPassfield.setBounds(44, 159, 153, 27);
        smallForgetPanel_1.add(confirmResetPassfield);
        confirmResetPassfield.setBorder(new RoundedBorder(8));

        JLabel confirmResetPassLabel = new JLabel("Confirm Password");
        confirmResetPassLabel.setBounds(44, 141, 120, 13);
        smallForgetPanel_1.add(confirmResetPassLabel);

        backButton1 = new JButton();
        backButton1.setText("\u276E");
        backButton1.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
        backButton1.setBounds(40, 57, 40, 47);
        bigRegisterPanel.add(backButton1);
        backButton1.addActionListener(this);
        create.buttonDecorate(backButton1);
        backButton1.setContentAreaFilled(false);
        backButton1.setBackground(new Color(240, 240, 240));

        usernameLoginField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signInButton.doClick();
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signInButton.doClick();
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            super.signIn(usernameLoginField.getText(), String.valueOf(passwordField.getPassword()));
            if (super.loginCheck == true) {
                new Mainpage(usernameLoginField.getText());
                frame.dispose();
            }
        }
        if (e.getSource() == signUpButton) {
            super.singUp(usernameRegisterField.getText(), emailRegisterField.getText(), phoneField.getText(),
                    String.valueOf(passwordRegisterField.getPassword()),
                    String.valueOf(confirmPasswordField.getPassword()));

            if (super.isSignUp()) {
                cardLayout.show(bigLoginPanel, "loginPanel");
                pictureCard.show(picturePanel1, "picture1");
            }
        }
        if (e.getSource() == resetPassButton) {
            try {
                if (super.forget(usernameforgetField.getText())) {
                    cardLayout.show(bigLoginPanel, "resetPass");
                }
            } catch (SQLException forgetException) {
                forgetException.printStackTrace();
            }
        }
        if (e.getSource() == confirmResetPassbutton) {
            try {
                if (super.resetPassword(usernameforgetField.getText(), String.valueOf(resetPassField.getPassword()),
                        String.valueOf(confirmResetPassfield.getPassword()))) {
                    cardLayout.show(bigLoginPanel, "loginPanel");
                    pictureCard.show(picturePanel1, "picture1");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == backButton1) {
            cardLayout.show(bigLoginPanel, "loginPanel");
            pictureCard.show(picturePanel1, "picture1");
        }
        if (e.getSource() == backForgetPassButton) {
            cardLayout.show(bigLoginPanel, "loginPanel");
        } else if (e.getSource() == backResetPassButton) {
            cardLayout.show(bigLoginPanel, "forgetpass");
        }

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == createAccountLabel) {
            cardLayout.show(bigLoginPanel, "registerPanel");
            pictureCard.show(picturePanel1, "picture2");
        } else if (e.getSource() == forgetpassLabel) {
            cardLayout.show(bigLoginPanel, "forgetpass");
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
        if (e.getSource() == createAccountLabel) {
            createAccountLabel.setForeground(Color.blue);
        } else if (e.getSource() == forgetpassLabel) {
            forgetpassLabel.setForeground(Color.blue);
        } else if (e.getSource() == backButton1) {
            backButton1.setForeground(Color.gray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createAccountLabel) {
            createAccountLabel.setForeground(new Color(65, 105, 255));
        } else if (e.getSource() == forgetpassLabel) {
            forgetpassLabel.setForeground(new Color(65, 105, 255));
        } else if (e.getSource() == backButton1) {
            backButton1.setForeground(Color.BLACK);
        }
    }

    public String getUsername() {
        return username;
    }

    public class NumericDocument extends PlainDocument {
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null || str.isEmpty()) {
                return;
            }

            StringBuilder builder = new StringBuilder(getText(0, getLength()));
            builder.insert(offset, str);

            if (isNumeric(builder.toString())) {
                super.insertString(offset, str, attr);
                phoneField.getDocument().addDocumentListener(new DocumentListener() {

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
                        String phoneIndex = phoneField.getText();
                        if (phoneField.getText().length() == 10) {
                            phoneField.setEditable(false);
                            phoneField.addKeyListener(new KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                        phoneField.setEditable(true);
                                    }
                                }
                            });

                        } else {
                            phoneField.setEditable(true);
                        }
                        if (phoneField.getText().length() < 10 || !String.valueOf(phoneIndex.charAt(0)).equals("0")
                                || (!String.valueOf(phoneIndex.charAt(1)).equals("6")
                                        && !String.valueOf(phoneIndex.charAt(1)).equals("8")
                                        && !String.valueOf(phoneIndex.charAt(1)).equals("9"))) {

                        }
                    }
                });
            }
        }

        private boolean isNumeric(String str) {
            return str.matches("\\d+");
        }
    }
}

class RoundedBorder implements Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

}
