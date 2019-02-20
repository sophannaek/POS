import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateUserDialog {

	private int xMouse, yMouse;
	protected int xLocation, yLocation;
	private JFrame addUserFrame;
	private JTextField txtEmpNumber, txtEmpUserName, txtEmpPassword;
	private JLabel lblUserExists;
	private JCheckBox chckbxIsadmin;
	private UserDAO userDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUserDialog window = new UpdateUserDialog();
					window.addUserFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateUserDialog() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try
		{
			userDAO = new UserDAO();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		addUserFrame = new JFrame();
		addUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		addUserFrame.setResizable(false);
		addUserFrame.setUndecorated(true);
		addUserFrame.setOpacity(0.95f);
		addUserFrame.setBounds(10, 0, 500, 300);
		//addUserFrame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnInventory, btnSales, btnStaffManagement, btnTimeManagement, btnLog}));
		addUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		addUserFrame.setLocation(dim.width/2-addUserFrame.getSize().width/2, dim.height/2-addUserFrame.getSize().height/2);
		addUserFrame.setTitle("POS SYSTEM LOGIN");
		addUserFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mina\\Dropbox\\CECS 277\\POS\\icon.png"));
		addUserFrame.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		addUserFrame.getContentPane().setForeground(new Color(255, 255, 255, 50));
		addUserFrame.getContentPane().setBackground(new Color(204, 0, 0));
		addUserFrame.getContentPane().setLayout(null);
		
		JLabel lblDrag = new JLabel("");
		lblDrag.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				xMouse = evt.getX();
				yMouse = evt.getY();
			}
		});
		lblDrag.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				int x = evt.getXOnScreen();
				int y = evt.getYOnScreen();
				xLocation = x - xMouse;
				yLocation = y - yMouse;
				addUserFrame.setLocation(xLocation, yLocation);
			}
		});
		lblDrag.setBounds(0, 0, 500, 64);
		addUserFrame.getContentPane().add(lblDrag);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 480, 8);
		addUserFrame.getContentPane().add(separator);
		
		JLabel lblAdminPanel = new JLabel("UPDATE USER");
		lblAdminPanel.setFont(new Font("SimSun", Font.BOLD, 50));
		lblAdminPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPanel.setForeground(Color.WHITE);
		lblAdminPanel.setBounds(0, 13, 500, 45);
		addUserFrame.getContentPane().add(lblAdminPanel);
		
		JLabel lblEmpNumber = new JLabel("Employee Number");
		lblEmpNumber.setFont(new Font("SimSun", Font.BOLD, 17));
		lblEmpNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpNumber.setForeground(Color.WHITE);
		lblEmpNumber.setBounds(10, 85, 180, 35);
		addUserFrame.getContentPane().add(lblEmpNumber);
		
		JLabel lblEmpUserName = new JLabel("Employee User Name");
		lblEmpUserName.setFont(new Font("SimSun", Font.BOLD, 17));
		lblEmpUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpUserName.setForeground(Color.WHITE);
		lblEmpUserName.setBounds(0, 125, 190, 35);
		addUserFrame.getContentPane().add(lblEmpUserName);
		
		JLabel lblEmpPassword = new JLabel("Employee Password");
		lblEmpPassword.setFont(new Font("SimSun", Font.BOLD, 17));
		lblEmpPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpPassword.setForeground(Color.WHITE);
		lblEmpPassword.setBounds(10, 165, 180, 35);
		addUserFrame.getContentPane().add(lblEmpPassword);
		
		JLabel lblEmpIsAdmin = new JLabel("Admin Privileges");
		lblEmpIsAdmin.setFont(new Font("SimSun", Font.BOLD, 17));
		lblEmpIsAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpIsAdmin.setForeground(Color.WHITE);
		lblEmpIsAdmin.setBounds(10, 205, 180, 35);
		addUserFrame.getContentPane().add(lblEmpIsAdmin);
		
		txtEmpNumber = new JTextField();
		txtEmpNumber.setBackground(Color.LIGHT_GRAY);
		txtEmpNumber.setBounds(225, 85, 265, 35);
		txtEmpNumber.setText(String.valueOf(StaffManagement.modUser.getEmpNumber()));
		addUserFrame.getContentPane().add(txtEmpNumber);
		txtEmpNumber.setColumns(10);
		
		txtEmpUserName = new JTextField();
		txtEmpUserName.setBackground(Color.LIGHT_GRAY);
		txtEmpUserName.setBounds(225, 125, 265, 35);
		txtEmpUserName.setText(StaffManagement.modUser.getUserName());
		addUserFrame.getContentPane().add(txtEmpUserName);
		txtEmpUserName.setColumns(10);
		
		txtEmpPassword = new JTextField();
		txtEmpPassword.setBackground(Color.LIGHT_GRAY);
		txtEmpPassword.setBounds(225, 165, 265, 35);
		txtEmpPassword.setText(StaffManagement.modUser.getPassword());
		addUserFrame.getContentPane().add(txtEmpPassword);
		txtEmpPassword.setColumns(10);
		
		chckbxIsadmin = new JCheckBox("");
		chckbxIsadmin.setForeground(Color.LIGHT_GRAY);
		chckbxIsadmin.setBackground(new Color(204, 0, 0));
		chckbxIsadmin.setBounds(225, 213, 21, 23);
		chckbxIsadmin.setSelected(StaffManagement.modUser.isAdmin());
		addUserFrame.getContentPane().add(chckbxIsadmin);
		
		lblUserExists = new JLabel("User Already Exists");
		lblUserExists.setFont(new Font("SimSun", Font.BOLD, 17));
		lblUserExists.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserExists.setForeground(Color.WHITE);
		lblUserExists.setBounds(10, 254, 190, 35);
		addUserFrame.getContentPane().add(lblUserExists);
		
		JButton btnAddUser = new JButton("Update User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		btnAddUser.setBackground(Color.LIGHT_GRAY);
		btnAddUser.setFont(new Font("SimSun", Font.PLAIN, 15));
		btnAddUser.setBounds(225, 255, 129, 35);
		addUserFrame.getContentPane().add(btnAddUser);
		lblUserExists.setVisible(false);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				addUserFrame.setVisible(false);
			}
		});
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setFont(new Font("SimSun", Font.PLAIN, 15));
		btnCancel.setBounds(361, 255, 129, 35);
		addUserFrame.getContentPane().add(btnCancel);
	}
	
	protected void addUser() 
	{
		try
		{
			int empNumber = Integer.valueOf(txtEmpNumber.getText());
			String userName = txtEmpUserName.getText();
			String password = txtEmpPassword.getText();
			boolean isAdmin = chckbxIsadmin.isSelected();
			
			User tempUser = new User(empNumber, userName, password, isAdmin);
			if(txtEmpNumber.getText().isEmpty() || txtEmpUserName.getText().isEmpty() || txtEmpPassword.getText().isEmpty())
			{
				txtEmpNumber.setText(String.valueOf(StaffManagement.modUser.getEmpNumber()));
				txtEmpUserName.setText(StaffManagement.modUser.getUserName());
				txtEmpPassword.setText(StaffManagement.modUser.getPassword());
				txtEmpNumber.requestFocus();
				chckbxIsadmin.setSelected(StaffManagement.modUser.isAdmin());
				lblUserExists.setText("All Fields Required");
				lblUserExists.setVisible(true);
			}
			else
			{
				userDAO.updateEmployee(tempUser);
				StaffManagement.refreshTable();
				addUserFrame.setVisible(false);
				
				/*txtEmpNumber.setText(String.valueOf(StaffManagement.modUser.getEmpNumber()));
				txtEmpUserName.setText(StaffManagement.modUser.getUserName());
				txtEmpPassword.setText(StaffManagement.modUser.getPassword());
				txtEmpNumber.requestFocus();
				chckbxIsadmin.setSelected(StaffManagement.modUser.isAdmin());
				lblUserExists.setText("User Updated");
				lblUserExists.setVisible(true);*/
			}
		}
		catch (Exception e)
		{
			txtEmpNumber.setText(String.valueOf(StaffManagement.modUser.getEmpNumber()));
			txtEmpUserName.setText(StaffManagement.modUser.getUserName());
			txtEmpPassword.setText(StaffManagement.modUser.getPassword());
			txtEmpNumber.requestFocus();
			chckbxIsadmin.setSelected(StaffManagement.modUser.isAdmin());
			lblUserExists.setText("All Fields Required");
			lblUserExists.setVisible(true);
		}
	}
}
