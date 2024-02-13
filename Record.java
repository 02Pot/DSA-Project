package LibrarySystemVSCODE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Record {
	private String bookIssued;
	private Date ReturnDate;
	private String Username;	

	public Record(String bookIssued,String Username,Date ReturnDate) {
		this.bookIssued = bookIssued;
		this.Username = Username;
		this.ReturnDate = ReturnDate;
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
	
	
	public String getBookIssued() {
		return bookIssued;
	}
	public Date getReturnDate() {
		return ReturnDate;
	}
	public String getUsername() {
		return Username;
	}

	public void saveToRecord(String username) {
    try (Connection connection = DBConnection.getConnection()) {
        connection.setAutoCommit(false);
        
        String checkQuery = "SELECT * FROM record WHERE UserName = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setString(1, username);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Username already exists in the record table.");
                    return; 
                }
            }
        }

        String insertQuery = "INSERT INTO record (UserName, BookIssued, ReturnDate) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, this.bookIssued);
            preparedStatement.setDate(3, this.ReturnDate);
            preparedStatement.executeUpdate();
        }

        connection.commit();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
