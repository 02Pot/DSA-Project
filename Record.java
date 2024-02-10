package LibrarySystemVSCODE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Record {
	private String bookIssued;
	private Date ReturnDate;
	private String Username;
	private int UserID;
	

	public Record(String bookIssued,String Username,Date ReturnDate,int UserID) {
		this.bookIssued = bookIssued;
		this.Username = Username;
		this.ReturnDate = ReturnDate;
		this.UserID = UserID;
	}
	
	public void setBookIssued(String bt) {
		this.bookIssued = bt;
	}
	
	public void setReturnDate(Date date) {
		this.ReturnDate = date;
	}
	
	public void setUsername(String username) {
		this.Username = username;
	}
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	
	public String getBookIssued() {
		return bookIssued;
	}
	public Date getReturnDate() {
		return ReturnDate;
	}
	public String getUsername() {
		return Username;
	}
	public int getUserID(){
		return UserID;
	}

	public static int generateNewID(){
		int maxUserID = getMaxUIDfromdatabase();
		int newUserID = maxUserID + 1;

		return newUserID;
	}

	private static int getMaxUIDfromdatabase(){
		int maxUserID = 1;
		return maxUserID;
	}

	public void saveToRecord(String username) {


		try (Connection connection = DBConnection.getConnection()) {
			connection.setAutoCommit(false);

			String query = "INSERT INTO record (UserID, UserName, BookIssued, ReturnDate) VALUES (?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, this.UserID);
				preparedStatement.setString(2, username);
				preparedStatement.setString(3, this.bookIssued);
				preparedStatement.setDate(4, this.ReturnDate);
				preparedStatement.executeUpdate();
				
			}

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
