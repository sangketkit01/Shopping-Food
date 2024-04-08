package App;

import java.io.*;
import java.nio.file.*;
import javax.swing.JFileChooser;

public class FileManager {
    private boolean isSelected = false;

    // เลือกรูปจากเครื่อง แล้ว copy รูปนั้นลงโปรเจ็ค
    public String fileDestination(String absolutePath, String... destination) {
        String username = "";
        try {

            // ที่อยู่ในเครื่องของรูปที่เลือก
            Path sourcePath = Paths.get(absolutePath);

            // Folder ในโปรเจ็คที่จะให้รูปไปอยู่
            Path destinationFolder = Paths.get(destination[0]);

            // ตั้งชื่อไฟล์รูปภาพเป็นชื่อของผู้ใช้ ||
            // ถ้าไม่มีผู้ใช้ล็อกอินอยู่ก็จะตั้งชื่อไฟล์ตามชื่อเดิมของมัน
            if (destination[1] != null) {
                username = destination[1] + "." + sourcePath.getFileName().toString().split("\\.")[1];
            } else {
                username = sourcePath.getFileName().toString();
            }

            // ที่อยู่ใหม่ของรูปภาพ
            Path destinationPath = destinationFolder.resolve(username);

            // Copy ไฟล์จากตำแหน่งเดิมในเครื่องลงในโฟลเดอร์ในโปรเจ็ค
            Files.copy(sourcePath, destinationPath);

            return destinationPath.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            System.out.println("Please select file motherfucker");
        }
        return null;

    }

    public String FileChooserSelection() {

        // เด้ง file explorer(Pop-up ที่ให้เรากดเลือกไฟล์นั่นแหละ)
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));

        int result = fileChooser.showOpenDialog(null);

        // ผู้ใช้กดเลือกรูปภาพหรือไม่
        if (result == JFileChooser.APPROVE_OPTION) {
            isSelected = true;
            return fileChooser.getSelectedFile().getAbsolutePath();
        }

        return null;
    }

    // ส่งค่า boolean ว่าผู้ใช้กดเลือกรูปภาพหรือไม่
    public boolean getSelected() {
        return isSelected;
    }
}
