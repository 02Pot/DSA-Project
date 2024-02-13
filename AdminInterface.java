package LibrarySystemVSCODE;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminInterface {

    private JFrame frame;
    private JTabbedPane tabbedPane;

   

    public AdminInterface() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(100, 100, 791, 507);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel AdminVar = new JLabel("ADMIN");
        AdminVar.setForeground(Color.WHITE);
        AdminVar.setFont(new Font("Tahoma", Font.BOLD, 39));
        AdminVar.setHorizontalAlignment(SwingConstants.CENTER);

        JButton ShowstudentButtonVar = new JButton("Show Student");
        ShowstudentButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        ShowstudentButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Show Students");
        	}
        });
        JButton AddbookButtonVar = new JButton("Add Books");
        AddbookButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        AddbookButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Add Books");
        	}
        });
        JButton RegisterStudentButtonVar = new JButton("Register Student");
        RegisterStudentButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        RegisterStudentButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Register Student");

        	}
        });
        JButton ShowBooksButtonVar = new JButton("Show Books");
        ShowBooksButtonVar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        ShowBooksButtonVar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTab("Show Books");
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

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        JScrollPane showStudentPane = new JScrollPane();
        tabbedPane.addTab("Show Students", null, showStudentPane, null);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Add Books", null, panel, null);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Register Student", null, panel_1, null);

        JScrollPane scrollPane_1 = new JScrollPane();
        tabbedPane.addTab("Show Books", null, scrollPane_1, null);

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(274)
                                                .addComponent(AdminVar, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(39)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(tabbedPane)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(ShowstudentButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(AddbookButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(RegisterStudentButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(ShowBooksButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(LogoutButtonVar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(44, Short.MAX_VALUE))
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
                                                .addComponent(ShowstudentButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(RegisterStudentButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ShowBooksButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(LogoutButtonVar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
                                .addGap(29)
                                .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(28, Short.MAX_VALUE))
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
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
