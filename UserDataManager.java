package LibrarySystemVSCODE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    private List<User> userList;
    private static UserDataManager instance;

    private UserDataManager() {
        userList = new ArrayList<>();
    }

    public static UserDataManager getInstance() {
        if (instance == null) {
            instance = new UserDataManager();
        }
        return instance;
    }
   
    public void setUserList(List<User> userList) {
    	this.userList = userList;
    }
        
    public List<User> getUserList() {
    	
    	try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String username = resultSet.getString("UserName");
                        String password = resultSet.getString("UserPass");
                        User user = new User(username, password);
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
    
   
}
