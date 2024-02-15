package LibrarySystemVSCODE;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AdminInterface {

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextField bTtextField;
    List<Record> transactionlist = new ArrayList<>();
    List<Book> booklist = new ArrayList<>();
    List<User> userlist = new ArrayList<>();
    private JTable usertable;
    private JTable transactiontable;
    private JTable booktable;
    private JTextField baTextField;
    private JTextField bITextField;
    private JTextField bpTextField;


    public AdminInterface() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(100, 100, 929, 507);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        
        transactiontable = new JTable();
        JScrollPane TransactionHistoryPane = new JScrollPane(transactiontable);
        tabbedPane.addTab("Transaction History", null, TransactionHistoryPane, null);
        
        JPanel AddBookPanel = new JPanel();
        tabbedPane.addTab("Add Books", null, AddBookPanel, null);
        
        booktable = new JTable();
        JScrollPane ShowBooksButtonPane = new JScrollPane(booktable);
        tabbedPane.addTab("Show Books",null, ShowBooksButtonPane, null);
        
        usertable = new JTable();
        JScrollPane ShowUserPanel = new JScrollPane(usertable);
        tabbedPane.addTab("Show Users", null, ShowUserPanel, null);

        JLabel AdminVar = new JLabel("ADMIN");
        AdminVar.setForeground(Color.WHITE);
        AdminVar.setFont(new Font("Tahoma", Font.BOLD, 39));
        AdminVar.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton ShowTransactionVar = new JButton("Transaction History");
        ShowTransactionVar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        ShowTransactionVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Transaction History");
        		List<Record> allRecord = getAllRecord();
				transactionlist.clear();
				transactionlist.addAll(allRecord);
        		updateRecord();
        	}
        });
        
        JButton AddbookButtonVar = new JButton("Add Books");
        AddbookButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        AddbookButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Add Books");
        	}
        });
        JButton ShowUserButtonVar = new JButton("Show Users");
        ShowUserButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        ShowUserButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Show Users");
        		List<User> allUser = getAllUser();
        		userlist.clear();
        		userlist.addAll(allUser);
        		updateUser();
        	}
        });
        JButton ShowBooksButtonVar = new JButton("Show Books");
        ShowBooksButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        ShowBooksButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Show Books");
        		List<Book> allBook = getAllBook();
        		booklist.clear();
        		booklist.addAll(allBook);
        		updateBook();
        	}
        });
        JButton LogoutButtonVar = new JButton("Log Out");
        LogoutButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        LogoutButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Login login = new Login();
				login.frame.setVisible(true);
				frame.dispose();

        	}
        });
        
        baTextField = new JTextField();
        baTextField.setColumns(10);
        bITextField = new JTextField();
        bITextField.setColumns(10);
        bpTextField = new JTextField();
        bpTextField.setColumns(10);
        bTtextField = new JTextField();
        bTtextField.setColumns(10);
        
        JButton AddBttn = new JButton("ADD");
        AddBttn.setFont(new Font("Tahoma", Font.PLAIN, 31));
        AddBttn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String bookA = baTextField.getText();
        		String bookIString = bITextField.getText();
        		String bookT = bTtextField.getText();
        		String bookP = bpTextField.getText();
        		
        		if(bookA.isEmpty()||bookT.isEmpty()||bookIString.isEmpty()||bookP.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "One or more field is Empty");
        		}else {
        			try {
        				int bookI = Integer.parseInt(bookIString);
            			Book b = new Book(bookT, bookA, bookI, bookP);
            			b.saveBooktoDatabase();
            			JOptionPane.showMessageDialog(null, "Book Added to Database");
        			}catch(NumberFormatException e1){
        				JOptionPane.showMessageDialog(null, "Invalid input for Book ISBN");
        			}        			
        		}
        	}
        });
        
        
        
        JLabel bTLabel = new JLabel("Book Title");
        bTLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        bTLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel bKLabel = new JLabel("Book Author");
        bKLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        bKLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel bISBNlabel = new JLabel("Book ISBN");
        bISBNlabel.setHorizontalAlignment(SwingConstants.CENTER);
        bISBNlabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        JLabel bPlabel = new JLabel("Book Publisher");
        bPlabel.setHorizontalAlignment(SwingConstants.CENTER);
        bPlabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        GroupLayout gl_AddBookPanel = new GroupLayout(AddBookPanel);
        gl_AddBookPanel.setHorizontalGroup(
        	gl_AddBookPanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_AddBookPanel.createSequentialGroup()
        			.addGap(25)
        			.addGroup(gl_AddBookPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(bITextField, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
        				.addComponent(bTtextField, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
        			.addGap(35)
        			.addGroup(gl_AddBookPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(bpTextField, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
        				.addComponent(baTextField, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
        			.addGap(355))
        		.addGroup(gl_AddBookPanel.createSequentialGroup()
        			.addGap(88)
        			.addComponent(bISBNlabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        			.addGap(144)
        			.addComponent(bPlabel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
        			.addComponent(AddBttn, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
        			.addGap(130))
        		.addGroup(Alignment.LEADING, gl_AddBookPanel.createSequentialGroup()
        			.addGap(83)
        			.addComponent(bTLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        			.addGap(151)
        			.addComponent(bKLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(413, Short.MAX_VALUE))
        );
        gl_AddBookPanel.setVerticalGroup(
        	gl_AddBookPanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_AddBookPanel.createSequentialGroup()
        			.addContainerGap(28, Short.MAX_VALUE)
        			.addGroup(gl_AddBookPanel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(bTLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        				.addComponent(bKLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_AddBookPanel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_AddBookPanel.createSequentialGroup()
        					.addGroup(gl_AddBookPanel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(bTtextField, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        						.addComponent(baTextField, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
        					.addGap(36)
        					.addGroup(gl_AddBookPanel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(bISBNlabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        						.addComponent(bPlabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(gl_AddBookPanel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(bITextField, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        						.addComponent(bpTextField, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
        					.addGap(26))
        				.addGroup(gl_AddBookPanel.createSequentialGroup()
        					.addComponent(AddBttn, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
        					.addGap(93))))
        );
        AddBookPanel.setLayout(gl_AddBookPanel);

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(39)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(ShowTransactionVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(AddbookButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(AdminVar, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(ShowBooksButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(ShowUserButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(LogoutButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))))
        			.addContainerGap(40, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(AdminVar, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(AddbookButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        					.addComponent(ShowTransactionVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        					.addComponent(ShowBooksButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(LogoutButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        					.addComponent(ShowUserButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
        			.addGap(29)
        			.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(28, Short.MAX_VALUE))
        );
                
        
                GroupLayout gl_ShowUserPanel = new GroupLayout(ShowUserPanel);
                
                gl_ShowUserPanel.setHorizontalGroup(
                	gl_ShowUserPanel.createParallelGroup(Alignment.LEADING)
                		.addGap(0, 829, Short.MAX_VALUE)
                );
                gl_ShowUserPanel.setVerticalGroup(
                	gl_ShowUserPanel.createParallelGroup(Alignment.TRAILING)
                		.addGap(0, 260, Short.MAX_VALUE)
                );

        frame.getContentPane().setLayout(groupLayout);
    }

    private void showTab(String tabTitle) {
        if (tabTitle == null) {
            tabbedPane.setSelectedIndex(-1);
        } else {
            int index = tabbedPane.indexOfTab(tabTitle);
            if (index != -1) {
                tabbedPane.setSelectedIndex(index);
            }
        }
    }

	public void setVisible(boolean b) {
		frame.setVisible(true);
	}
	
	private void updateRecord() {
		DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Book Title");
	    model.addColumn("Username");
	    model.addColumn("Due Date");

	    for (Record transaction : transactionlist) {
	        model.addRow(new Object[]{transaction.getBookIssued(),transaction.getUsername(), transaction.getReturnDate()});
	    }
	    
	    transactiontable.setModel(model);
	}
	
	private void updateBook() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Book Title");
		model.addColumn("Book Author");
		model.addColumn("Book ISBN");
		model.addColumn("Book Publisher");
		
		for(Book transaction : booklist) {
			model.addRow(new Object[] {transaction.getBookTitle(),transaction.getPublisher(),transaction.getISBN(),transaction.getPublisher()});
		}
		booktable.setModel(model);
	}
	
	private void updateUser() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("UserName");
		model.addColumn("UserPass");
		
		for(User transaction : userlist) {
			model.addRow(new Object[] {transaction.getUID(),transaction.getPID()});
		}
		usertable.setModel(model);
	}
	
	private List<Record> getAllRecord() {
        List<Record> allRecord = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String displayquery = "SELECT * FROM record";
            try (PreparedStatement preparedStatement = connection.prepareStatement(displayquery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String bookIssued = resultSet.getString("BookIssued");
                        String username = resultSet.getString("Username");
                        Date returnDate = resultSet.getDate("ReturnDate");
						Record record = new Record(bookIssued, username, new java.sql.Date(returnDate.getTime()));
                        allRecord.add(record);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRecord;
	}
	
	private List<Book> getAllBook() {
        List<Book> allBook = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String displayquery = "SELECT * FROM books";
            try (PreparedStatement preparedStatement = connection.prepareStatement(displayquery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String bookTitle = resultSet.getString("BookTitle");
                        String bookAuthor = resultSet.getString("BookAuthor");
                        int bookISBN = resultSet.getInt("BookISBN");
                        String bookPublisher = resultSet.getString("BookPublisher");
						Book book = new Book(bookTitle, bookAuthor,bookISBN,bookPublisher);
                        allBook.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBook;
	}
	
	private List<User> getAllUser() {
        List<User> allUser = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String displayquery = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(displayquery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String UserName = resultSet.getString("UserName");
                        String UserPass = resultSet.getString("UserPass");
						User user = new User(UserName, UserPass);
                        allUser.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUser;
	}
}
