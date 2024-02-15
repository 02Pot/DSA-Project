package LibrarySystemVSCODE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class Login {

	JFrame frame;
	private JTextField userfield;
	private JPasswordField pass;
	JPanel panel_1;
	JPanel panel_2;
	JLabel userlabel;
	JLabel passwordlabel;
	JButton loginbutton;
	JButton signup;
	JLabel bigloginlabel;
	
	public Login() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 726, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(10, 11, 690, 468);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(137, 27, 434, 408);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		userfield = new JTextField();
		userfield.setBackground(SystemColor.inactiveCaptionBorder);
		userfield.setBounds(84, 228, 270, 30);
		panel_2.add(userfield);
		userfield.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBackground(SystemColor.inactiveCaptionBorder);
		pass.setBounds(84, 302, 270, 30);
		panel_2.add(pass);
		
		userlabel = new JLabel("Username");
		userlabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		userlabel.setBounds(184, 203, 65, 14);
		panel_2.add(userlabel);
		  
		passwordlabel = new JLabel("Password");
		passwordlabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		passwordlabel.setBounds(184, 270, 78, 14);
		panel_2.add(passwordlabel);
		
		loginbutton = new JButton("Login");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDataManager userManager = UserDataManager.getInstance();
				List<User> userList = userManager.getUserList();
				String enteredUsername = userfield.getText().trim();
				@SuppressWarnings("deprecation")
				String enteredPassword = pass.getText().trim();
				
				if(enteredUsername.equalsIgnoreCase("admin")&&enteredPassword.equalsIgnoreCase("admin")) {
					AdminInterface Administrator = new AdminInterface();
					Administrator.setVisible(true);
					frame.setVisible(false);
				}else {
					if(!enteredUsername.isEmpty() && !enteredPassword.isEmpty()) {
						boolean loginSuccess = false;
						
						for(User user : userList) {
							if (user.getUID().equalsIgnoreCase(enteredUsername) && user.getPID().equals(enteredPassword)) {
					            loginSuccess = true;
					            break;
					        }
						}
						if (loginSuccess) {
					        JOptionPane.showMessageDialog(null, "Login Success");
							User loggedInUser = new User(enteredUsername, enteredPassword);
					        Homepage home = new Homepage(loggedInUser);
					        home.frame.setVisible(true);
					        frame.dispose();
					        
					    	} else {
					        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					    	}
						}else {
							JOptionPane.showMessageDialog(null, "Please enter Username and Password");
						}
				}
			}
		});
		
		loginbutton.setBounds(226, 354, 89, 23);
		panel_2.add(loginbutton);
		
		signup = new JButton("Sign Up");
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp sign = new SignUp();
				sign.setVisible(true);
				frame.dispose();
			}
		});
		
		signup.setBounds(115, 354, 89, 23);
		panel_2.add(signup);
		
		bigloginlabel = new JLabel("Log In");
		bigloginlabel.setFont(new Font("Tahoma", Font.BOLD, 87));
		bigloginlabel.setBounds(73, 11, 289, 143);
		panel_2.add(bigloginlabel);
	}
}
