package LibrarySystemVSCODE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
		private String title;
		private String author;
		private int ISBN;
		private String publisher;
	
		public Book(String title,String author,int ISBN,String publisher ) {
			this.title = title;
			this.author = author;
			this.ISBN = ISBN;
			this.publisher = publisher;
		}
	 	
		public void setBookTitle(String bt) {
			this.title = bt;
		}
		
		public void setBookAuthor(String author) {
			this.author = author;
		}
		
		public void setISBN(int ISBN) {
			this.ISBN = ISBN;
		}
		
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		public String getBookTitle() {
			return title;
		}
		public String getAuthor() {
			return author;
		}
		public int getISBN() {
			return ISBN;
		}
		public String getPublisher() {
			return publisher;
		}
		
		public static boolean isBookAvailable(String bookTitle) {
			try (Connection connection = DBConnection.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(
		                     "SELECT * FROM books WHERE BookTitle = ? AND Availability IS null")) {
							bookTitle = bookTitle.trim();
		            		preparedStatement.setString(1, bookTitle);

		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
						if (resultSet.next()) {
							boolean bookAvailable = (resultSet.getString("Availability") == null);
							return bookAvailable;
						} else {
							System.out.println("Book not found.");
						}
					}
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return false;
		        }
		
		public static boolean isBookBorrowed(String bookTitle) {
			try (Connection connection = DBConnection.getConnection()) {
				if (connection != null) {						
					try (PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT BookTitle, ReturnDate FROM books WHERE BookTitle = ? AND ReturnDate IS NULL")) {
						preparedStatement.setString(1, bookTitle);
						bookTitle = bookTitle.trim();
			
						ResultSet resultSet = preparedStatement.executeQuery();
						resultSet.next();	
					}
					
				} else {
							System.out.println("No database connection.Book is not borrowed.");
							return false;    
					}
						} catch (SQLException e) {
							e.printStackTrace();
					}
						return false;
					}
				
				
		public void saveBooktoDatabase() {
			try (Connection connection = DBConnection.getConnection()) {
	            String query = "INSERT INTO books (BookTitle, BookAuthor, BookISBN, BookPublisher) VALUES (?, ?, ?, ?)";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, this.title);
	                preparedStatement.setString(2, this.author);
	                preparedStatement.setInt(3, this.ISBN);
	                preparedStatement.setString(4, this.publisher);
	                preparedStatement.executeUpdate();
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
			
		}
	

}
