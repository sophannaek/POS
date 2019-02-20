import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Login {

	private int xMouse, yMouse;
	protected int xLocation, yLocation;
	private JFrame frmPosSystemLogin;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private UserDAO userDAO;
	protected static List<User> activeUsers = new ArrayList<User>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmPosSystemLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		try
		{
			userDAO = new UserDAO();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		frmPosSystemLogin = new JFrame();
		frmPosSystemLogin.setTitle("POS SYSTEM LOGIN");
		frmPosSystemLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mina\\Dropbox\\CECS 277\\POS\\icon.png"));
		frmPosSystemLogin.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frmPosSystemLogin.getContentPane().setForeground(new Color(255, 255, 255, 50));
		frmPosSystemLogin.getContentPane().setBackground(new Color(204, 0, 0));
		frmPosSystemLogin.getContentPane().setLayout(null);
		frmPosSystemLogin.setResizable(false);
		frmPosSystemLogin.setUndecorated(true);
		frmPosSystemLogin.setOpacity(0.95f);
		
		JLabel lblPosLogin = new JLabel("POS LOGIN");
		lblPosLogin.setFont(new Font("SimSun", Font.BOLD, 50));
		lblPosLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosLogin.setForeground(new Color(255, 255, 255));
		lblPosLogin.setBounds(0, 11, 500, 52);
		frmPosSystemLogin.getContentPane().add(lblPosLogin);
		
		JLabel lblInvalidUsernameOr = new JLabel("Invalid username or password");
		lblInvalidUsernameOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidUsernameOr.setForeground(Color.WHITE);
		lblInvalidUsernameOr.setFont(new Font("SimSun", Font.BOLD, 20));
		lblInvalidUsernameOr.setBounds(38, 190, 387, 28);
		lblInvalidUsernameOr.setVisible(false);
		frmPosSystemLogin.getContentPane().add(lblInvalidUsernameOr);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 20));
		lblNewLabel.setBounds(38, 86, 135, 30);
		frmPosSystemLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 20));
		lblNewLabel_1.setBounds(38, 137, 135, 20);
		frmPosSystemLogin.getContentPane().add(lblNewLabel_1);
		
		@SuppressWarnings("serial")
		AbstractAction loginAction = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	try
		    	{
		    		String userName = txtUserName.getText();
					String password = txtPassword.getText();
					boolean userFound = userDAO.findUser(userName);
					if (userFound)
					{
						User myUser = userDAO.getUser(userName);
						if (userName.equals(myUser.getUserName()) && password.equals(myUser.getPassword())) 
						{
							if (myUser.isAdmin())
								AdminPanel.main(null);
							else
								EmployeePanel.main(null);
							activeUsers.add(myUser);
							lblInvalidUsernameOr.setVisible(false);
							txtUserName.setText(null);
							txtPassword.setText(null);
							frmPosSystemLogin.setVisible(false);
						}
						else
						{
							lblInvalidUsernameOr.setText("Incorrect Password");
							lblInvalidUsernameOr.setVisible(true);
							txtPassword.setText(null);
						}
					}
					else 
					{
						lblInvalidUsernameOr.setText("User Not Found");
						lblInvalidUsernameOr.setVisible(true);
						txtUserName.setText(null);
						txtPassword.setText(null);
						txtUserName.requestFocus();
					}
		    	}
		    	catch (Exception ex)
		    	{
		    		ex.printStackTrace();
		    	}
		    }
		};
		
		txtUserName = new JTextField();
		txtUserName.setBackground(Color.LIGHT_GRAY);
		txtUserName.setBounds(229, 92, 196, 20);
		txtUserName.setFont(new Font("SimSun", Font.PLAIN, 18));
		frmPosSystemLogin.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField(20);
		txtPassword.setBackground(Color.LIGHT_GRAY);
		txtPassword.addActionListener(loginAction);
		txtPassword.setBounds(229, 137, 196, 20);
		frmPosSystemLogin.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(loginAction);
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(58, 230, 102, 32);
		frmPosSystemLogin.getContentPane().add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setText(null);
				txtPassword.setText(null);
				txtUserName.requestFocus();
				lblInvalidUsernameOr.setVisible(false);
			}
		});
		btnClear.setBounds(205, 230, 102, 32);
		frmPosSystemLogin.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(352, 230, 102, 32);
		frmPosSystemLogin.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(58, 214, 396, 16);
		frmPosSystemLogin.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(58, 58, 396, 16);
		frmPosSystemLogin.getContentPane().add(separator_1);
		
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
				frmPosSystemLogin.setLocation(xLocation, yLocation);
			}
		});
		lblDrag.setBounds(0, 0, 500, 47);
		frmPosSystemLogin.getContentPane().add(lblDrag);
		
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		frmPosSystemLogin.setBounds(200, 200, 500, 280);
		frmPosSystemLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPosSystemLogin.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUserName, txtPassword, btnLogin, btnClear, btnExit}));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmPosSystemLogin.setLocation(dim.width/2-frmPosSystemLogin.getSize().width/2, dim.height/2-frmPosSystemLogin.getSize().height/2);
	}
}
