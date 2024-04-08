package App;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

public class AddMenu {
    private JFrame frame;
    private JTextField InputFoodName;
    private JTextField InputPrice;
    private String shopName;
    private boolean isSelected = false;
    private CloseDisplayListener listener;

    /**
     * Launch the application.
     */

    /**
     * Create the application.
     */
    public AddMenu(String shopName) {
        initialize();
        this.shopName = shopName;
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
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
        frame.getContentPane().setBackground(new Color(64, 128, 128));
        frame.setBounds(559, 114, 438, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame.dispose();
            }
        });

        ArrayList<String> arr = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(217, 217, 217));
        panel.setBounds(0, 0, 424, 600);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel picLabel = new JLabel();
        picLabel.setBounds(125, 40, 180, 150);
        picLabel.setVisible(false);
        panel.add(picLabel);

        RoundedButton AddPicture = new RoundedButton(0);
        AddPicture.setColor(Color.WHITE);
        AddPicture.setColorOver(Color.lightGray);
        AddPicture.setColorExit(Color.white);
        AddPicture.setContentAreaFilled(false);
        AddPicture.setForeground(Color.BLACK);
        AddPicture.setText("Add Photo");
        AddPicture.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        AddPicture.setFocusable(false);
        AddPicture.setCursor(new Cursor(12));
        AddPicture.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.setProperty("file.encoding", "UTF-8");

                SwingUtilities.invokeLater(() -> {
                    JFileChooser fileChooser = new JFileChooser() {
                        @Override
                        public void updateUI() {
                            super.updateUI();
                            FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                            setFileSystemView(fileSystemView);
                        }
                    };
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    fileChooser.setCurrentDirectory(new File("C:\\Users\\thira\\OneDrive\\รูปภาพ\\eie"));
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION ) {
                        picLabel.setIcon(
                                creater.createImage(fileChooser.getSelectedFile().getAbsolutePath(), 180, 150));
                        picLabel.setVisible(true);
                        AddPicture.setVisible(false);
                        arr.add(fileChooser.getSelectedFile().getAbsolutePath());
                        isSelected = true;
                    }
                });
            }
        });
        AddPicture.setFont(new Font("Tahoma", Font.PLAIN, 24));
        AddPicture.setBounds(125, 40, 180, 150);
        panel.add(AddPicture);

        JLabel FoodNameTitle = new JLabel("Food Name");
        FoodNameTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        FoodNameTitle.setBounds(52, 200, 235, 25);
        panel.add(FoodNameTitle);

        InputFoodName = new JTextField();
        InputFoodName.setBounds(37, 225, 350, 35);
        panel.add(InputFoodName);
        InputFoodName.setColumns(10);
        InputFoodName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InputFoodName.setText(" Enter food’s name...");
        InputFoodName.setForeground(new Color(111, 111, 111));
        InputFoodName.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (InputFoodName.getText().equals(" Enter food’s name...")) {
                    InputFoodName.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (InputFoodName.getText().isEmpty()) {
                    InputFoodName.setText(" Enter food’s name...");
                }
            }
        });

        JLabel PriceTitle = new JLabel("Price");
        PriceTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        PriceTitle.setBounds(52, 270, 235, 25);
        panel.add(PriceTitle);

        InputPrice = new JTextField();
        InputPrice.setColumns(10);
        InputPrice.setBounds(37, 295, 350, 30);
        InputPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InputPrice.setText(" Enter food’s price...");
        InputPrice.setForeground(new Color(111, 111, 111));
        panel.add(InputPrice);
        InputPrice.setDocument(new NumericDocument());
        InputPrice.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (InputPrice.getText().equals(" Enter food’s price...")) {
                    InputPrice.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (InputPrice.getText().isEmpty()) {
                    InputPrice.setText(" Enter food’s price...");
                }
            }
        });
        InputPrice.setDocument(new NumericDocument());

        JLabel DescriptionTitle = new JLabel("Description");
        DescriptionTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        DescriptionTitle.setBounds(52, 335, 235, 25);
        panel.add(DescriptionTitle);

        JLabel ImportantFoodNameTitle = new JLabel("*");
        ImportantFoodNameTitle.setForeground(new Color(255, 0, 0));
        ImportantFoodNameTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ImportantFoodNameTitle.setBounds(37, 200, 10, 25);
        panel.add(ImportantFoodNameTitle);

        JLabel ImportantPriceTitle = new JLabel("*");
        ImportantPriceTitle.setForeground(Color.RED);
        ImportantPriceTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ImportantPriceTitle.setBounds(37, 270, 10, 25);
        panel.add(ImportantPriceTitle);

        JTextArea InputDescription = new JTextArea();
        InputDescription.setBounds(37, 360, 350, 150);
        InputDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InputDescription.setText(" Enter description...");
        InputDescription.setForeground(new Color(111, 111, 111));
        panel.add(InputDescription);
        InputDescription.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (InputDescription.getText().equals(" Enter description...")) {
                    InputDescription.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (InputDescription.getText().isEmpty()) {
                    InputDescription.setText(" Enter description...");
                }
            }
        });
        RoundedButton AddButton = new RoundedButton(10);
        AddButton.setColor(new Color(30, 142, 95));
        AddButton.setColorExit(new Color(30, 142, 95));
        AddButton.setColorOver(new Color(10, 122, 75));
        AddButton.setContentAreaFilled(false);
        AddButton.setText("Add");
        AddButton.setForeground(Color.white);
        AddButton.setBorder(BorderFactory.createEmptyBorder());
        AddButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AddButton.setFocusable(false);
        AddButton.setCursor(new Cursor(12));
        AddButton.setBounds(302, 525, 86, 25);
        AddButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (InputFoodName.getText().isEmpty() || InputPrice.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You need to fullfill Food name and price");
                } else if (!isInteger(InputPrice.getText()) || Integer.parseInt(InputPrice.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect price entered");
                } else if (isSelected == false) {
                    JOptionPane.showMessageDialog(null, "You have to add food image");
                } else {
                    Path sourcePath = Paths.get(arr.getLast());
                    Path destinationFolder = Paths.get("FoodPic");
                    Path destinationPath = destinationFolder
                            .resolve(shopName + "_" + sourcePath.getFileName().toString());
                    try {
                        Files.copy(sourcePath, destinationPath);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    String insertQuery = String.format(
                            "Insert into store.food (shop,food_name,price,url,description) values('%s','%s','%s','%s','%s')",
                            shopName, InputFoodName.getText(),
                            Integer.parseInt(InputPrice.getText()),
                            "FoodPic/" + shopName + "_" + sourcePath.getFileName().toString().replace("\\", "/"),
                            InputDescription.getText());
                    ConnectData.setData(insertQuery, "");

                    JOptionPane.showMessageDialog(null, "Add menu successfully");
                    if (listener != null) {
                        frame.dispose();
                        listener.onClose();
                    }
                }

            }

        });
        panel.add(AddButton);

        RoundedButton CancelButton = new RoundedButton(10);
        CancelButton.setColor(new Color(240, 240, 240));
        CancelButton.setColorOver(Color.WHITE);
        CancelButton.setColorExit(new Color(240, 240, 240));
        CancelButton.setContentAreaFilled(false);
        CancelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        CancelButton.setText("Cancel");
        CancelButton.setForeground(Color.BLACK);
        CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CancelButton.setBounds(37, 525, 85, 25);
        CancelButton.setCursor(new Cursor(12));
        panel.add(CancelButton);

    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e1) {
            return false;
        }
    }
}
