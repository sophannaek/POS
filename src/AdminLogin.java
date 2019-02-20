import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class AdminLogin {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private int xMouse, yMouse;
	protected int xLocation, yLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SALES PANEL");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mina\\Dropbox\\CECS 277\\POS\\icon.png"));
		frame.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frame.getContentPane().setForeground(new Color(255, 255, 255, 50));
		frame.getContentPane().setBackground(new Color(204, 0, 0));
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setOpacity(0.95f);
		
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
				frame.setLocation(xLocation, yLocation);
			}
		});
		lblDrag.setBounds(0, 0, 300, 43);
		frame.getContentPane().add(lblDrag);
		
		JLabel lblError = new JLabel("Invalid Credentials");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("SimSun", Font.BOLD, 16));
		lblError.setBounds(20, 118, 251, 35);
		lblError.setVisible(false);
		frame.getContentPane().add(lblError);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("SimSun", Font.BOLD, 16));
		lblUsername.setBounds(20, 54, 96, 35);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("SimSun", Font.BOLD, 16));
		lblPassword.setBounds(20, 84, 96, 35);
		frame.getContentPane().add(lblPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 280, 16);
		frame.getContentPane().add(separator);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setForeground(Color.WHITE);
		lblAdminLogin.setFont(new Font("SimSun", Font.BOLD, 30));
		lblAdminLogin.setBounds(10, 0, 280, 35);
		frame.getContentPane().add(lblAdminLogin);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(Color.LIGHT_GRAY);
		txtUsername.setBounds(133, 63, 157, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(Color.LIGHT_GRAY);
		txtPassword.setBounds(133, 93, 157, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 154, 280, 8);
		frame.getContentPane().add(separator_1);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					UserDAO userDAO = new UserDAO();
					String username = txtUsername.getText();
					String password = txtPassword.getText();
					User user = userDAO.getUser(username);
					if (user.getPassword().equals(password) && user.isAdmin())
					{
						if (SalesPanel.discount)
							Discount.main(null);
						else
							CustomEntry.main(null);
						frame.setVisible(false);
					}
					else
					{
						txtUsername.setText("");
						txtPassword.setText("");
						txtUsername.requestFocus();
						lblError.setVisible(true);
					}
				}
				catch (Exception ex)
				{
					txtUsername.setText("");
					txtPassword.setText("");
					txtUsername.requestFocus();
					lblError.setVisible(true);
				}
			}
		});
		btnDone.setBackground(Color.LIGHT_GRAY);
		btnDone.setBounds(10, 166, 135, 23);
		frame.getContentPane().add(btnDone);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
			}
		});
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setBounds(155, 166, 135, 23);
		frame.getContentPane().add(btnCancel);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
}
