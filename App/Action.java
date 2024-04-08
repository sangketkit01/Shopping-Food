package App;

import java.sql.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class Action {

    private boolean isSignUp = false;
    public boolean loginCheck = false;

    public void signIn(String username, String password) {
        String queryLogin = String.format(
                "Select * FROM user_data.users WHERE username = '%s' AND user_password = '%s'",
                username, password);
        ResultSet loginRS = ConnectData.getData(queryLogin);
        try {
            if (loginRS.next()) {
                loginCheck = true;
            }
        } catch (Exception loginException) {
            JOptionPane.showMessageDialog(null, loginException);
        }
        if (!loginCheck) {
            JOptionPane.showMessageDialog(null, "Username or Password is invalid");
        }
    }

    public void singUp(String username, String email, String phone, String password, String confirmPassword) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        ResultSet userRS = ConnectData.getData(String.format("Select * FROM user_data.users WHERE username = '%s'",
                username));

        ResultSet emailRS = ConnectData.getData(String.format("Select * FROM user_data.users WHERE email = '%s'",
                email));

        ResultSet phoneRS = ConnectData.getData(
                String.format("Select * FROM user_data.users WHERE phone = '%s'", phone));

        boolean userCheck = false;
        boolean emailCheck = false;
        boolean phoneCheck = false;
        try {
            userCheck = userRS.next();
            emailCheck = emailRS.next();
            phoneCheck = phoneRS.next();
        } catch (Exception duplicateException) {
            JOptionPane.showMessageDialog(null, duplicateException);
        }

        if (username.isEmpty() || email.isEmpty()
                || phone.isEmpty() ||
                password.isEmpty()
                || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fullfill in all field");
        } else if (!password
                .equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password does not match");
        } else if (phone.length() != 10) {
            JOptionPane.showMessageDialog(null, "You entered an incorrect phone number");
        } else if (userCheck) {
            JOptionPane.showMessageDialog(null, "Username is already in use");
        } else if (emailCheck) {
            JOptionPane.showMessageDialog(null, "Email is already in use");
        } else if (phoneCheck) {
            JOptionPane.showMessageDialog(null, "Phone number is already in use");
        } else if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "You entered an incorrect email");
        } else {
            ConnectData.setData(String.format(
                    "INSERT INTO user_data.users (username,email,phone,user_password,name) VALUES('%s','%s','%s','%s','%s')",
                    username, email, phone,
                    password,username), "Create account successfully");

            isSignUp = true;
        }
    }

    public boolean forget(String username) throws SQLException {
        ResultSet rs = ConnectData
                .getData(String.format("Select user_password from user_data.users WHERE username = '%s'", username));
        if (rs.next()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "User not found");
            return false;
        }

    }

    public boolean resetPassword(String username, String password, String confirmPassword) throws SQLException {
        if (password.equals(confirmPassword)) {
            ConnectData.UpdateData("UPDATE user_data.users SET user_password = ? WHERE username = ?", password,
                    username);
            JOptionPane.showMessageDialog(null, "Update Password Successfully");
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Password does not match");
            return false;
        }
    }

    public boolean isSignUp() {
        if (isSignUp) {
            return true;
        }
        return false;
    }

    public boolean getstore() {
        String storeQuery = String.format("Select * FROM store.store_user");
        ResultSet stores = ConnectData.getData(storeQuery);
        try {
            return stores.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public ArrayList<String> getStoreName() {
        String query = String.format("Select * FROM store.store_user");
        ResultSet name = ConnectData.getData(query);
        ArrayList<String> arr = new ArrayList<>();

        try {
            while (name.next()) {
                arr.add(name.getString("store_name"));
            }
            Collections.sort(arr);
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getCategory(String category) {
        String query = String.format("Select * FROM store.store_user WHERE category = '%s'", category);
        ResultSet stores = ConnectData.getData(query);
        ArrayList<String> arr = new ArrayList<>();

        try {
            while (stores.next()) {
                arr.add(stores.getString("store_name"));
            }
            Collections.sort(arr);
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getAllCategory() {
        String query = String.format("Select * FROM store.store_user");
        ResultSet stores = ConnectData.getData(query);
        ArrayList<String> arr = new ArrayList<>();

        try {
            while (stores.next()) {
                arr.add(stores.getString("store_name"));
            }
            Collections.sort(arr);
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Action action = new Action();
        for (int i = 0; i < action.getCategory("all").size(); i++) {
            System.out.println(action.getCategory("all").get(i));
        }
    }

}
