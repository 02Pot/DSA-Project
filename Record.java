package LibrarySystemVSCODE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Record {
	private String bookIssued;
	private Date returnDate;
	private String username;	

	public Record(String bookIssued,String username,Date returnDate) {
		this.bookIssued = bookIssued;
		this.username = username;
		this.returnDate = returnDate;
	}
	
	public void setBookIssued(String bt) {
		this.bookIssued = bt;
	}
	
	public void setReturnDate(Date date) {
		this.returnDate = date;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getBookIssued() {
		return bookIssued;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public String getUsername() {
		return username;
	}

	public void saveToRecord(String username) {
    try (Connection connection = DBConnection.getConnection()) {
        connection.setAutoCommit(false);
        

        String checkQuery = "SELECT * FROM record WHERE UserName = ? AND BookIssued = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setString(1, username);
			checkStatement.setString(2, this.bookIssued);
			try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
					JOptionPane.showMessageDialog(null, "Already borrowed");
                    return;
                }
            }
        }

        String insertQuery = "INSERT INTO record (UserName, BookIssued, ReturnDate) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, this.bookIssued);
            preparedStatement.setDate(3, this.returnDate);
			JOptionPane.showMessageDialog(null, "Book Issued");
            preparedStatement.executeUpdate();
        }

        	connection.commit();
    	} catch (SQLException e) {
        	e.printStackTrace();
    	}
	}
	public void deleteRecord(String username, String bookTitle) {
    	try (Connection connection = DBConnection.getConnection()) {
        	String query = "DELETE FROM record WHERE UserName = ? AND BookIssued = ?";
        	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            	preparedStatement.setString(1, username);
            	preparedStatement.setString(2, bookTitle);
            	preparedStatement.executeUpdate();
        	}
    	} catch (SQLException e) {
        	e.printStackTrace();
    	}
	}



}
