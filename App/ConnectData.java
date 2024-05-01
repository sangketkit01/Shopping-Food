package App;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectData {
    private final String query = "jdbc:mysql://localhost:3306/user_data?user=root&password=0627457454New";
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    public static ResultSet getData(String Query) {

        ConnectData data = new ConnectData();
        try {
            data.con = DriverManager.getConnection(data.query);
            data.st = data.con.createStatement();
            data.rs = data.st.executeQuery(Query);
            return data.rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static void setData(String Query, String msg) {
        ConnectData data = new ConnectData();
        try {

            data.con = DriverManager.getConnection(data.query);
            data.st = data.con.createStatement();
            data.st.executeUpdate(Query);
            if (!msg.equals(""))
                JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                data.con.close();
                data.st.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public static void UpdateData(String query, String... s) throws SQLException {
        ConnectData data = new ConnectData();
        try (Connection con = DriverManager.getConnection(data.query)) {
            try (PreparedStatement prepare = con.prepareStatement(query)) {
                for (int i = 1; i <= s.length; i++) {
                    prepare.setString(i, s[i - 1]);
                }
                prepare.executeUpdate();
            }

        }
    }

}