package App;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;

public class ImageDisplay {
    private JLabel imageLabel;
    private JFrame frame;
    private boolean isExit = false;
    private CloseDisplayListener listener;
    public ImageDisplay(String username) {
        String query = String.format("Select url From user_data.users WHERE username = '%s'", username);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        try {
            ResultSet rs = ConnectData.getData(query);
            if (rs.next()) {
                String url = rs.getString("url") == null ? "profile/userIcon.png" : rs.getString("url");
                BufferedImage image = ImageIO.read(new File(url));
                ImageIcon imageIcon = new ImageIcon(image);
                imageLabel = new JLabel(imageIcon);
                frame.add(imageLabel, BorderLayout.CENTER);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                frame.dispose();
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
  
    public ImageDisplay(String url, int x) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        BufferedImage image;
        try {
            image = ImageIO.read(new File(url));
            ImageIcon imageIcon = new ImageIcon(image);
            imageLabel = new JLabel(imageIcon);
            frame.add(imageLabel, BorderLayout.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (listener != null) {
                    listener.onClose();
                }
                frame.dispose();
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setListener(CloseDisplayListener listener) {
        this.listener = listener;
    }
    public boolean getExit(){
        return isExit;
    }
}
