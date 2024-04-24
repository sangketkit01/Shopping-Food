package App;

import java.io.File;
import java.sql.*;
import javax.swing.*;

public class EditProfile {

    // Set รูปโปรไฟล์ผู้ใช้
    public void setProfile(JLabel label, String username, int width, int height) throws SQLException {
        GuiCreater creater = new GuiCreater();

        // คำสัง sql ทีจะไปเอา url ของรูปโปรไฟล์ของผู้ใช้
        String query = String.format(
                "Select * FROM user_data.users WHERE username = '%s'",
                username);
        ResultSet rs = ConnectData.getData(query);

        // Set รูปโปรไฟล์จาก url ของรูปภาพที่ได้จาก database
        while (rs.next()) {
            String url = rs.getString("url") == null ? "profile/userIcon.png" : rs.getString("url");
            File file = new File(rs.getString("url"));
            if(!file.exists()){
                url = "profile/userIcon.png";
            }
            label.setIcon(creater.createImage(url, width, height));
        }

    }

    // แก้ไขรูปโปรไฟล์ของ user
    public String editPicture(JLabel label, String username, int width, int height) {
        GuiCreater creater = new GuiCreater();
        FileManager manager = new FileManager();
        ProfilePage profilePage = new ProfilePage();

        // เปิดให้ผู้ใช้หารูปภาพจากในเครื่อง
        String path = manager.FileChooserSelection();
        label.setIcon(creater.createImage(path, width, height));

        // ถ้าผู้ใช้กดเลือกรูปสักรูปแล้ว...
        if (profilePage.getSelected()) {
            // ที่อยู่ของรูปภาพที่ผู้ใช้กดเลือก (รูปใหม่)
            String destination = manager.fileDestination(path, "profile", username);

            label.setIcon(creater.createImage(destination, width, height));

            // บันทึกที่อยู่ของรูปที่ผู้ใช้กดเลือก(รูปใหม่) ลง database
            return destination;
        }
        

        return null;

    }

    // ลบภาพโปรไฟล์เก่าของผู้ใช้ออก
    public void deleteOldProfile(String path) {
        try {
            File file = new File(path);
            if (file.exists() && !file.getName().equals("UserIcon.png")) {
                file.delete();
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
    }
}
