package LibrarySystemVSCODE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Signup extends JFrame{

	final UserDataManager UserDataManagement = null;
	private JPanel contentPane;
	private JTextField userfield;
	private JTextField passfield;
	private JTextField confirmpassfield;
	JPanel panel;
	JLabel SignUplabel;
	JLabel Usernamelabel;
	JLabel Passwordlabel;
	JLabel confirmpasslabel;
	JButton loginb;
	JButton signb;
	
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 443, 403);
		contentPane.add(panel);
		panel.setLayout(null);
		
		SignUplabel = new JLabel("Sign Up");
		SignUplabel.setHorizontalAlignment(SwingConstants.CENTER);
		SignUplabel.setFont(new Font("Tahoma", Font.BOLD, 68));
		SignUplabel.setBounds(10, 11, 423, 134);
		panel.add(SignUplabel);
		
		userfield = new JTextField();
		userfield.setBounds(144, 188, 220, 20);
		panel.add(userfield);
		userfield.setColumns(10);
		
		Usernamelabel = new JLabel("Username");
		Usernamelabel.setBounds(44, 191, 88, 14);
		panel.add(Usernamelabel);
		
		passfield = new JPasswordField();
		passfield.setBounds(144, 233, 220, 20);
		panel.add(passfield);
		passfield.setColumns(10);
		
		Passwordlabel = new JLabel("Password");
		Passwordlabel.setBounds(44, 236, 75, 14);
		panel.add(Passwordlabel);
		
		confirmpassfield = new JPasswordField();
		confirmpassfield.setColumns(10);
		confirmpassfield.setBounds(144, 276, 220, 20);
		panel.add(confirmpassfield);
		
		confirmpasslabel = new JLabel("Confirm Password");
		confirmpasslabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		confirmpasslabel.setBounds(21, 279, 121, 14);
		panel.add(confirmpasslabel);
		
		
	
		
		signb = new JButton("Sign Up");
		signb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enteredUsername = userfield.getText().trim();
				String enteredPassword = passfield.getText().trim();
				
				 if (!enteredUsername.isEmpty()) {
	                    if (enteredPassword.isEmpty()) {
	                        JOptionPane.showMessageDialog(null, "Password is Empty!");
	                    } else {
	                        // Check if the username already exists
	                        if (!doUsernameExists(enteredUsername)) {
	                            // If not, create a new user
	                            UserDataManager userManager = UserDataManager.getInstance();
	                            User user = new User(enteredPassword, enteredUsername);
	                            JOptionPane.showMessageDialog(null, "Account Created!");
	                            user.saveUserToDatabase();
	                            userManager.addUser(user);

	                        } else {
	                            JOptionPane.showMessageDialog(null, "Username already exists. Please choose another one.");
	                        }
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "Please insert a Username!");
	                }
			}
	});
	
		signb.setBounds(274, 322, 89, 23);
		panel.add(signb);
		
		loginb = new JButton("Login");
		loginb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.frame.setVisible(true);
				dispose();
			}
		});
		loginb.setBounds(146, 322, 89, 23);
		panel.add(loginb);
		
	}
	protected boolean doUsernameExists(String enteredUsername) {
		UserDataManager userManager = UserDataManager.getInstance();
        List<User> userList = userManager.getUserList();

        for (User user : userList) {
            if (user.getUID().equalsIgnoreCase(enteredUsername)) {
                return true;
            }
        }

        return false;
    }
}
