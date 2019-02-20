import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.JLabel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class EmployeePanel {

	private int xMouse, yMouse;
	protected int xLocation, yLocation;
	private JFrame frmAdminPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePanel window = new EmployeePanel();
					window.frmAdminPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeePanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminPanel = new JFrame();
		frmAdminPanel.setTitle("POS SYSTEM LOGIN");
		frmAdminPanel.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mina\\Dropbox\\CECS 277\\POS\\icon.png"));
		frmAdminPanel.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frmAdminPanel.getContentPane().setForeground(new Color(255, 255, 255, 50));
		frmAdminPanel.getContentPane().setBackground(new Color(204, 0, 0));
		frmAdminPanel.getContentPane().setLayout(null);
		
		JButton btnSales = new JButton("Sales\r\n");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SalesPanel.main(null);
			}
		});
		btnSales.setBackground(Color.LIGHT_GRAY);
		btnSales.setBounds(30, 106, 140, 70);
		frmAdminPanel.getContentPane().add(btnSales);
		
		JButton btnTimeManagement = new JButton("Time Management");
		btnTimeManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				TimecClock.main(null);
			}
		});
		btnTimeManagement.setToolTipText("");
		btnTimeManagement.setBackground(Color.LIGHT_GRAY);
		btnTimeManagement.setBounds(328, 106, 140, 70);
		frmAdminPanel.getContentPane().add(btnTimeManagement);
		
		JLabel lblAdminPanel = new JLabel("EMPLOYEE PANEL");
		lblAdminPanel.setFont(new Font("SimSun", Font.BOLD, 50));
		lblAdminPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPanel.setForeground(Color.WHITE);
		lblAdminPanel.setBounds(0, 11, 500, 45);
		frmAdminPanel.getContentPane().add(lblAdminPanel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(55, 54, 400, 16);
		frmAdminPanel.getContentPane().add(separator);
		
		JButton btnLog = new JButton("Logout");
		btnLog.setBackground(Color.LIGHT_GRAY);
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.main(null);
				frmAdminPanel.setVisible(false);
				Login.activeUsers.remove(0);
			}
		});
		btnLog.setBounds(180, 106, 140, 70);
		frmAdminPanel.getContentPane().add(btnLog);
		
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
				frmAdminPanel.setLocation(xLocation, yLocation);
			}
		});
		lblDrag.setBounds(0, 0, 500, 64);
		frmAdminPanel.getContentPane().add(lblDrag);
		
		JLabel lblEmployeeID = new JLabel("Employee ID: " + String.valueOf(Login.activeUsers.get(0).getEmpNumber()));
		lblEmployeeID.setForeground(Color.WHITE);
		lblEmployeeID.setFont(new Font("SimSun", Font.BOLD, 20));
		lblEmployeeID.setBounds(36, 67, 254, 28);
		frmAdminPanel.getContentPane().add(lblEmployeeID);
				
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		frmAdminPanel.setResizable(false);
		frmAdminPanel.setUndecorated(true);
		frmAdminPanel.setOpacity(0.95f);
		frmAdminPanel.setBounds(10, 0, 500, 190);
		frmAdminPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmAdminPanel.setLocation(dim.width/2-frmAdminPanel.getSize().width/2, dim.height/2-frmAdminPanel.getSize().height/2);
		frmAdminPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnSales, btnLog, btnTimeManagement}));
	}
}
