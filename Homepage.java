package LibrarySystemVSCODE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class Homepage{

	JFrame frame;
	private JTextField searchbook;
    private DefaultTableModel transactionTableModel;
    private JTable transactiontable;
    final JTabbedPane tabbedPane;
    JPanel panel;
    JPanel issuebook;
    JLabel labelissuebook;
    JPanel bookpic;
    JLabel booktitlelabel;
    JButton issuebutton;
    JToggleButton borrowbutton;
    JPanel record;
    JScrollPane scrollPane_1;
    JPanel returnbook;
    JButton returnbutton_1;
    JButton recordbutton;
     JLabel recordlabel;
     JButton logoutbutton;
     JScrollPane scrollPane;
     private JButton btnNewButton;
     private JLabel returnbooklabel;
     private JButton returnbutton;
     private JButton returnallbutton;
     private JSpinner spinner;
     
	 private User currentUser;
     List<Record> transactionlist = new ArrayList<>();
     
	public Homepage(User currentUser) {
		this.tabbedPane = new JTabbedPane();
		this.currentUser = currentUser;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Library System");
		transactionTableModel = new DefaultTableModel();
        transactionTableModel.addColumn("Book Title");
        transactionTableModel.addColumn("Due Date");
        
		panel = new JPanel();
		panel.setBackground(SystemColor.windowText);
		panel.setBounds(0, 0, 984, 620);
		frame.getContentPane().add(panel);
		
		logoutbutton = new JButton("Log Out");
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		issuebook = new JPanel();
		issuebook.setBackground(new Color(250, 250, 210));
		tabbedPane.addTab("issuebook", null, issuebook, null);
		issuebook.setLayout(null);

		labelissuebook = new JLabel("Issue a Book");
		labelissuebook.setBounds(263, 5, 200, 38);
		labelissuebook.setFont(new Font("Tahoma", Font.BOLD, 31));
		issuebook.add(labelissuebook);
		
		searchbook = new JTextField();
		searchbook.setHorizontalAlignment(SwingConstants.CENTER);
		searchbook.setBounds(244, 331, 247, 38);
		issuebook.add(searchbook);
		searchbook.setColumns(10);
		
		bookpic = new JPanel();
		bookpic.setBounds(238, 51, 253, 233);
		issuebook.add(bookpic);
		
		spinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spinner.setBounds(244, 405, 247, 45);
		issuebook.add(spinner);

		borrowbutton = new JToggleButton("ISSUE");
		borrowbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrowbutton.setEnabled(false);

				String searchBookTitle = searchbook.getText().trim();
		        String username = currentUser.getUID();

		        if (username != null && !username.trim().isEmpty()) {
		            boolean bookFound = Book.isBookAvailable(searchBookTitle);

		            if (bookFound) {
		                boolean alreadyBorrowed = Book.isBookBorrowed(searchBookTitle);

		                if (alreadyBorrowed) {
		                    JOptionPane.showMessageDialog(null, "You have already borrowed this book.");
		                } else {
		                    java.	util.Date selectedDateUtil = (java.util.Date) spinner.getValue();
		                    java.sql.Date selectedDate = new java.sql.Date(selectedDateUtil.getTime());

		                    Calendar returnDateCalendar = Calendar.getInstance();
		                    returnDateCalendar.setTime(selectedDate);
		                    returnDateCalendar.add(Calendar.DAY_OF_MONTH, 14);
		                    java.sql.Date returnDate = new java.sql.Date(returnDateCalendar.getTimeInMillis());
							
		
		                    Record recordTrans = new Record(searchBookTitle,username,returnDate);
		                    recordTrans.setBookIssued(searchBookTitle);
		                    recordTrans.setUsername(username);
		                    recordTrans.setReturnDate(returnDate);
							
							recordTrans.saveToRecord(username);
		                    Book.addRecord(recordTrans);

		                    JOptionPane.showMessageDialog(null, "Book Issued");
		                    updateRecord();
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Cannot Find Book");
						System.out.println(searchBookTitle);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
		        }
				borrowbutton.setEnabled(true);
		    }
		});
		
		borrowbutton.setBounds(311, 461, 121, 23);
		issuebook.add(borrowbutton);
		
		booktitlelabel = new JLabel("Book Title");
		booktitlelabel.setBounds(343, 306, 89, 14);
		issuebook.add(booktitlelabel);
		bookpic.setVisible(false);
		
		record = new JPanel();
		tabbedPane.addTab("record", null, record, null);
		
		scrollPane_1 = new JScrollPane((Component) null);
		record.add(scrollPane_1);
		
		transactiontable = new JTable();
		record.add(transactiontable);
		
		returnbook = new JPanel();
		returnbook.setLayout(null);
		tabbedPane.addTab("returnbook", null, returnbook, null);
		
		returnbooklabel = new JLabel("Return Book");
		returnbooklabel.setHorizontalAlignment(SwingConstants.CENTER);
		returnbooklabel.setFont(new Font("Tahoma", Font.BOLD, 73));
		returnbooklabel.setBounds(10, 11, 706, 86);
		returnbook.add(returnbooklabel);
		
		returnbutton = new JButton("Return");
		returnbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(transactionlist.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No records found");
				}else {
		            List<String> bookTitles = new ArrayList<>();
		            for (Record transaction : transactionlist) {
		                bookTitles.add(transaction.getBookIssued());
		            }

		            String selectedBook = (String) JOptionPane.showInputDialog(
		                    null,
		                    "Select a book to return:",
		                    "Return Book",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    bookTitles.toArray(),
		                    null
		            );

		            if (selectedBook != null) {
		            	List<Record> recordsToRemove = new ArrayList<>();
		                for (Record transaction : transactionlist) {
		                    if (transaction.getBookIssued().equals(selectedBook)) {
		                        recordsToRemove.add(transaction);
		                    }
		                }
		                transactionlist.removeAll(recordsToRemove);

		                JOptionPane.showMessageDialog(null, "Book '" + selectedBook + "' returned successfully.");
		                updateRecord();
		            } else {
		                JOptionPane.showMessageDialog(null, "Return canceled.");
		            }
				}
			}
		});
		
		returnbutton.setFont(new Font("Tahoma", Font.BOLD, 27));
		returnbutton.setBounds(124, 375, 205, 86);
		returnbook.add(returnbutton);
		
		returnallbutton = new JButton("Return All");
		returnallbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (transactionlist.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "No records found. Cannot return any books.");
		        } else {
		            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to return all books?", "Confirmation", JOptionPane.YES_NO_OPTION);
		            
		            if (dialogResult == JOptionPane.YES_OPTION) {
		                transactionlist.clear();

		                JOptionPane.showMessageDialog(null, "All books returned successfully.");
		                updateRecord();
		            }
		        }
			}
		});
		returnallbutton.setFont(new Font("Tahoma", Font.BOLD, 27));
		returnallbutton.setBounds(408, 375, 208, 86);
		returnbook.add(returnallbutton);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		returnbutton_1 = new JButton("Return Book");
		returnbutton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		
		recordbutton = new JButton("Record");
		recordbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		issuebutton = new JButton("Issue a Book");
		issuebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(issuebutton, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(recordbutton, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(returnbutton_1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutbutton, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(292)
					.addComponent(issuebutton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(recordbutton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(returnbutton_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(logoutbutton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(41)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
	}
	
	
	
	private void updateRecord() {
		DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Book Title");
	    model.addColumn("Due Date");

	    for (Record transaction : transactionlist) {
	        model.addRow(new Object[]{transaction.getBookIssued(), transaction.getReturnDate()});
	    }
	    
	    transactiontable.setModel(model);
	}
}
