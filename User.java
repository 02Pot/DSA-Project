package LibrarySystemVSCODE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
	private String userID;
	private String passID;
	
	public User(String uid,String pid) {
		this.userID = uid;
		this.passID = pid;
	}
	
	public String getUID() {
		return userID;
	}
	public String getPID() {
		return passID;
	}
	
	
	public void saveUserToDatabase() {
		try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO users (UserName, UserPass) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, this.userID);
                preparedStatement.setString(2, this.passID);
                preparedStatement.executeUpdate();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
