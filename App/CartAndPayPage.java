package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CartAndPayPage extends Promotion {

    private JFrame frame;
    private JTextField VoucherField;
    private JLabel PriceTotal, billPriceTotal, billFullPrice, billDiscount;
    private String username;
    private CloseDisplayListener listener;

    /**
     * Launch the application.
     */

    /**
     * Create the application.
     */
    public CartAndPayPage(String username) {
        this.username = username;
        initialize();
        frame.setVisible(true);
    }

    public void setListener(CloseDisplayListener listener) {
        this.listener = listener;
    }

    public CartAndPayPage() {

    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initialize() {

        CardLayout card = new CardLayout(0, 0);
        frame = new JFrame();
        frame.setForeground(new Color(30, 142, 95));
        frame.setBounds(400, 100, 976, 609);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(card);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame.dispose();
            }
        });

        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, "MainPanel");
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(30, 142, 95));

        JPanel paneltang = new JPanel();
        paneltang.setBounds(293, 10, 375, 552);
        mainPanel.add(paneltang);
        paneltang.setLayout(null);

        JLabel allitems = new JLabel("Check out");
        allitems.setFont(new Font("Tahoma", Font.BOLD, 20));
        allitems.setBounds(130, 23, 105, 25);
        paneltang.add(allitems);

        JLabel voucher = new JLabel("Voucher:");
        voucher.setFont(new Font("Tahoma", Font.PLAIN, 14));
        voucher.setBounds(19, 354, 72, 19);
        paneltang.add(voucher);

        VoucherField = new JTextField();
        VoucherField.setBounds(83, 356, 182, 19);
        paneltang.add(VoucherField);
        VoucherField.setColumns(10);

        JButton submit = new JButton("Submit");
        submit.setFont(new Font("Tahoma", Font.PLAIN, 9));
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        submit.setBounds(268, 353, 65, 25);
        paneltang.add(submit);

        JLabel VoucherDiscount = new JLabel("Voucher Discount:");
        VoucherDiscount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        VoucherDiscount.setBounds(19, 431, 132, 19);
        paneltang.add(VoucherDiscount);

        JLabel PaymentOption = new JLabel("Payment Option:");
        PaymentOption.setFont(new Font("Tahoma", Font.PLAIN, 14));
        PaymentOption.setBounds(19, 385, 118, 19);
        paneltang.add(PaymentOption);

        JComboBox<String> PaymentOptionComboBox = new JComboBox<>();
        PaymentOptionComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        PaymentOptionComboBox.setModel(new DefaultComboBoxModel(
                new String[] { "Cash on Delivery", "QR Promtpay", "Credit / Debit Card", "Mobile Banking" }));
        PaymentOptionComboBox.setBounds(135, 385, 197, 21);
        paneltang.add(PaymentOptionComboBox);
        PaymentOptionComboBox.setFocusable(false);

        JLabel PriceDiscount = new JLabel();
        PriceDiscount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        PriceDiscount.setBounds(295, 432, 63, 19);
        paneltang.add(PriceDiscount);
        PriceDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
        PriceDiscount.setVisible(false);

        JLabel noPromotionFound = new JLabel("No such a code");
        noPromotionFound.setFont(new Font("Tahoma", Font.PLAIN, 12));
        noPromotionFound.setBounds(275, 432, 100, 19);
        paneltang.add(noPromotionFound);
        noPromotionFound.setVisible(false);
        noPromotionFound.setForeground(Color.RED);
        noPromotionFound.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel paneltang2 = new JPanel();
        paneltang2.setBackground(new Color(64, 128, 128));
        paneltang2.setForeground(new Color(128, 128, 192));
        paneltang2.setBounds(10, 458, 355, 84);
        paneltang.add(paneltang2);
        paneltang2.setLayout(null);

        RoundedButton cancle = new RoundedButton(0);
        cancle.setColor(new Color(220, 220, 220));
        cancle.setColorOver(Color.WHITE);
        cancle.setColorExit(new Color(240, 240, 240));
        cancle.setContentAreaFilled(false);
        cancle.setBorder(BorderFactory.createEmptyBorder());
        cancle.setFocusable(false);
        cancle.setCursor(new Cursor(12));
        cancle.setText("Back");
        cancle.setForeground(Color.black);
        cancle.setBounds(7, 38, 107, 32);
        paneltang2.add(cancle);
        cancle.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cancle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }

        });

        JLabel total = new JLabel("Total Payment : ");
        total.setForeground(new Color(255, 255, 255));
        total.setBounds(10, 8, 132, 19);
        paneltang2.add(total);
        total.setFont(new Font("Tahoma", Font.BOLD, 15));

        PriceTotal = new JLabel("฿ price");
        PriceTotal.setForeground(new Color(255, 255, 255));
        PriceTotal.setBounds(263, 8, 86, 19);
        PriceTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        paneltang2.add(PriceTotal);
        PriceTotal.setFont(new Font("Tahoma", Font.BOLD, 15));

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(0, 76, 345, 268);
        paneltang.add(itemPanel);
        itemPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 76, 375, 268);
        paneltang.add(scrollPane);
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(itemPanel);

        billPriceTotal = new JLabel("฿price");
        billPriceTotal.setForeground(new Color(0, 0, 0));
        billPriceTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        billPriceTotal.setBounds(268, 489, 100, 19);

        billFullPrice = new JLabel();
        billFullPrice.setForeground(new Color(0, 0, 0));
        billFullPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        billFullPrice.setBounds(278, 444, 100, 19);

        billDiscount = new JLabel("฿ 0");
        billDiscount.setForeground(new Color(0, 0, 0));
        billDiscount.setFont(new Font("Tahoma", Font.PLAIN, 13));
        billDiscount.setBounds(278, 466, 100, 19);

        String query = String.format("Select * From user_data.cart WHERE username = '%s'", username);
        ResultSet rs = ConnectData.getData(query);
        int x_value = 10, y_value = 10, total_price = 0;
        GuiCreater creater = new GuiCreater();
        try {
            while (rs.next()) {
                JPanel panel = new JPanel();
                panel.setBounds(x_value, y_value, 323, 74);
                itemPanel.add(panel);
                panel.setLayout(null);

                JLabel picLabel = new JLabel();
                picLabel.setBounds(10, 10, 48, 55);
                picLabel.setIcon(creater.createImage(rs.getString("url"), 48, 55));
                panel.add(picLabel);

                JLabel nameLabel = new JLabel();
                nameLabel.setBounds(66, 16, 176, 19);
                nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                nameLabel.setText(rs.getString("food_name"));
                panel.add(nameLabel);

                JLabel shopLabel = new JLabel();
                shopLabel.setBounds(66, 37, 166, 19);
                shopLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
                shopLabel.setText(rs.getString("shop"));
                panel.add(shopLabel);

                JLabel timesLabel = new JLabel("x");
                timesLabel.setBounds(302, 22, 13, 13);
                timesLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
                panel.add(timesLabel);

                JLabel pieceLabel = new JLabel();
                pieceLabel.setBounds(312, 20, 32, 13);
                pieceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                pieceLabel.setText(String.valueOf(rs.getString("piece")));
                panel.add(pieceLabel);

                JLabel priceLabel = new JLabel(rs.getString("price"));
                priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                priceLabel.setBounds(210, 20, 85, 13);
                panel.add(priceLabel);

                total_price += rs.getInt("price") * rs.getInt("piece");

                PriceTotal.setText("฿ " + total_price);
                itemPanel.setPreferredSize(new Dimension(345, y_value + 100));
                y_value += 82;

            }
            final int first_price = total_price;
            billFullPrice.setText("฿ " + String.valueOf(first_price));

            submit.setFocusable(false);
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Promotion promo = new Promotion();
                    for (Object[] i : promo.getAllCode()) {
                        int discount = (int) (first_price * ((double) i[1]));
                        if (VoucherField.getText().isEmpty()) {
                            noPromotionFound.setVisible(false);
                        } else if (VoucherField.getText().equals(i[0])) {
                            if (i.length > 2) {
                                if (i[2] != null) {
                                    if (discount > (int) i[2]) {
                                        discount = (int) i[2];
                                    }
                                }
                            }
                            PriceTotal.setText("฿ " + (int) (first_price - discount));
                            PriceDiscount.setText("- ฿ " + discount);
                            PriceDiscount.setVisible(true);
                            billPriceTotal.setText(PriceTotal.getText());
                            billDiscount.setText("฿ " + String.valueOf(discount));
                            noPromotionFound.setVisible(false);
                            break;
                        } else {
                            PriceTotal.setText("฿ " + (int) (first_price));
                            PriceDiscount.setText("- ฿ " + 0);
                            billPriceTotal.setText(PriceTotal.getText());
                            PriceDiscount.setVisible(false);
                            noPromotionFound.setVisible(true);
                            billDiscount.setText("฿ " + 0);
                        }
                    }
                }

            });
        } catch (SQLException e1) {

        }
        JPanel mainBillPanel = new JPanel();
        frame.getContentPane().add(mainBillPanel, "billPanel");
        mainBillPanel.setLayout(null);
        mainBillPanel.setBackground(new Color(30, 142, 95));

        JPanel billPanel = new JPanel();
        billPanel.setBounds(293, 10, 350, 552);
        mainBillPanel.add(billPanel, "billPanel");
        billPanel.setLayout(null);

        JLabel picNotPicFood = new JLabel();
        picNotPicFood.setBackground(new Color(192, 192, 192));
        picNotPicFood.setBounds(140, 52, 79, 79);
        billPanel.add(picNotPicFood);
        picNotPicFood.setLayout(null);
        picNotPicFood.setIcon(creater.createImage("FoodPic/success.png", 80, 45));

        JLabel ThankYouText = new JLabel("Thank you!");
        ThankYouText.setFont(new Font("Tahoma", Font.BOLD, 20));
        ThankYouText.setBounds(117, 126, 116, 27);
        billPanel.add(ThankYouText);

        JLabel successText = new JLabel("Your purchase order has been successful.");
        successText.setForeground(SystemColor.windowBorder);
        successText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        successText.setBounds(60, 165, 230, 13);
        billPanel.add(successText);

        JScrollPane line = new JScrollPane();
        line.setBounds(15, 200, 313, 2);
        billPanel.add(line);

        JLabel fullPriceLabel = new JLabel("Price: ");
        fullPriceLabel.setForeground(new Color(0, 0, 0));
        fullPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        billPanel.add(fullPriceLabel);
        fullPriceLabel.setBounds(31, 444, 132, 19);

        JLabel discoundLabel = new JLabel("Discount: ");
        discoundLabel.setForeground(new Color(0, 0, 0));
        discoundLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        discoundLabel.setBounds(31, 466, 132, 19);
        billPanel.add(discoundLabel);

        JLabel lblTotal = new JLabel("Total: ");
        lblTotal.setForeground(new Color(0, 0, 0));
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTotal.setBounds(31, 489, 132, 19);
        billPanel.add(lblTotal);

        billPanel.add(billPriceTotal);
        billPanel.add(billFullPrice);
        billPanel.add(billDiscount);

        RoundedButton back = new RoundedButton(0);
        back.setColor(new Color(192, 192, 192));
        back.setColorOver(Color.WHITE);
        back.setColorExit(new Color(192, 192, 192));
        back.setContentAreaFilled(false);
        back.setFocusable(false);
        back.setCursor(new Cursor(12));
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setText("Back");
        back.setForeground(Color.black);
        back.setFont(new Font("Tahoma", Font.PLAIN, 15));
        back.setBounds(119, 515, 102, 27);
        billPanel.add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 216, 345, 204);
        billPanel.add(menuPanel);
        menuPanel.setLayout(null);

        JScrollPane menuScroll = new JScrollPane();
        menuScroll.setBounds(0, 230, 350, 204);
        billPanel.add(menuScroll);
        menuScroll.setViewportView(menuPanel);
        menuScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        String billQuery = String.format("Select * From user_data.cart WHERE username = '%s'", username);
        ResultSet billRS = ConnectData.getData(billQuery);
        int x_bill = 10, y_bill = 10;

        JLabel billProductName = new JLabel("Product name");
        billProductName.setBounds(20, 207, 100, 13);
        billPanel.add(billProductName);

        JLabel billPieceLabel = new JLabel("Piece");
        billPieceLabel.setBounds(180, 207, 100, 13);
        billPanel.add(billPieceLabel);

        JLabel billTotalPriceLabel = new JLabel("Total");
        billTotalPriceLabel.setBounds(272, 207, 50, 13);
        billPanel.add(billTotalPriceLabel);

        RoundedButton comfirm = new RoundedButton(0);
        comfirm.setColor(Color.WHITE);
        comfirm.setColorOver(Color.lightGray);
        comfirm.setColorExit(Color.WHITE);
        comfirm.setContentAreaFilled(false);
        comfirm.setBorder(BorderFactory.createEmptyBorder());
        comfirm.setCursor(new Cursor(12));
        comfirm.setFocusable(false);
        comfirm.setText("Confirm");
        comfirm.setForeground(Color.black);
        comfirm.setBounds(120, 38, 220, 32);
        paneltang2.add(comfirm);
        paneltang2.setBackground(new Color(30, 142, 95));
        comfirm.setEnabled(false);

        try {
            int count = 0;
            while (billRS.next()) {
                count++;
                if (count > 0) {
                    comfirm.setEnabled(true);
                }
                JPanel panel = new JPanel();
                panel.setBounds(x_bill, y_bill, 318, 27);
                panel.setLayout(null);
                menuPanel.add(panel);

                int piece = billRS.getInt("piece");
                int price = billRS.getInt("price");

                JLabel bill_productName = new JLabel(billRS.getString("food_name"));
                bill_productName.setBounds(10, 3, 176, 19);
                panel.add(bill_productName);

                JLabel timesLabel = new JLabel(String.valueOf(piece));
                timesLabel.setBounds(180, 7, 20, 13);
                panel.add(timesLabel);

                JLabel priceLabel = new JLabel(String.valueOf(price * piece));
                priceLabel.setBounds(265, 7, 30, 13);
                panel.add(priceLabel);

                y_bill += 30;
                menuPanel.setPreferredSize(new Dimension(345, y_bill));
            }
            billPriceTotal.setText(PriceTotal.getText());
            billPriceTotal.setHorizontalAlignment(SwingConstants.LEFT);
        } catch (SQLException e1) {

        }
        comfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (PaymentOptionComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Hope you'll enjoy your meal");
                    card.show(frame.getContentPane(), "billPanel");
                    changeDisplay();
                } else if (PaymentOptionComboBox.getSelectedIndex() == 1) {
                    ImageDisplay imageDisplay = new ImageDisplay("ShopPic/qrcode.jpg", 0);
                    imageDisplay.setListener(new CloseDisplayListener() {
                        @Override
                        public void onClose() {
                            card.show(frame.getContentPane(), "billPanel");
                            changeDisplay();
                        }
                    });
                }
            }
        });
        comfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));

    }

    public void successfulPayment(String username) {
        String deleteQuery = String.format(
                "DELETE FROM user_data.cart WHERE username = '%s'",
                username);
        ConnectData.setData(deleteQuery, "");
    }

    public void changeDisplay() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame.dispose();
                if (listener != null) {
                    listener.onClose();
                }
            }
        });
    }
}
