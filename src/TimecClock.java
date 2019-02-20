import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimecClock {

	private JFrame frmTimeClock;
	private JTextField txtEnterEmployeeId;
	private UserDAO userDAO;
	private int xMouse, yMouse;
	protected int xLocation, yLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimecClock window = new TimecClock();
					window.frmTimeClock.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TimecClock() {
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
		
		frmTimeClock = new JFrame();
		frmTimeClock.getContentPane().setBackground(new Color(204, 0, 0));
		frmTimeClock.setTitle("Time Clock");
		frmTimeClock.setUndecorated (true);
		frmTimeClock.setOpacity(0.95f);
		frmTimeClock.setResizable(false);
		frmTimeClock.setBounds(100, 100, 500, 280);
		frmTimeClock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTimeClock.getContentPane().setLayout(null);
		
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
				frmTimeClock.setLocation(xLocation, yLocation);
			}
		});
		lblDrag.setBounds(0, 0, 500, 34);
		frmTimeClock.getContentPane().add(lblDrag);
		
		txtEnterEmployeeId = new JTextField();
		txtEnterEmployeeId.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterEmployeeId.setBounds(171, 57, 150, 20);
		frmTimeClock.getContentPane().add(txtEnterEmployeeId);
		txtEnterEmployeeId.setColumns(10);
		
		JLabel lblMessage = new JLabel();
		lblMessage.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(255, 255, 255));
		lblMessage.setBounds(60, 182, 375, 34);
		lblMessage.setVisible(false);
		frmTimeClock.getContentPane().add(lblMessage);
		
		JButton startShiftButton = new JButton("Start Shift");
		startShiftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String empNumber = txtEnterEmployeeId.getText();
				try
				{
					if (userDAO.findUser(Integer.parseInt(empNumber)))
					{
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
						LocalDateTime now = LocalDateTime.now();  
						lblMessage.setText("Employee " + empNumber + " clocked in at " + dtf.format(now));
						lblMessage.setVisible(true);
						txtEnterEmployeeId.setText("");
						txtEnterEmployeeId.requestFocus();
					}
					else
					{
						lblMessage.setText("Invalid employee number");
						lblMessage.setVisible(true);
						txtEnterEmployeeId.setText("");
						txtEnterEmployeeId.requestFocus();
					}
				}
				catch (Exception ex)
				{
					lblMessage.setText("Invalid employee number");
					lblMessage.setVisible(true);
					txtEnterEmployeeId.setText("");
					txtEnterEmployeeId.requestFocus();
				}
			}
		});
		startShiftButton.setBackground(Color.LIGHT_GRAY);
		startShiftButton.setBounds(60, 100, 160, 61);
		frmTimeClock.getContentPane().add(startShiftButton);
		
		JButton endShiftButton = new JButton("End Shift");
		endShiftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String empNumber = txtEnterEmployeeId.getText();
				try
				{
					if (userDAO.findUser(Integer.parseInt(empNumber)))
					{
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
						LocalDateTime now = LocalDateTime.now();  
						lblMessage.setText("Employee " + empNumber + " clocked out at " + dtf.format(now));
						lblMessage.setVisible(true);
					}
					else
					{
						lblMessage.setText("Invalid employee number");
						lblMessage.setVisible(true);
					}
				}
				catch (Exception ex)
				{
					lblMessage.setText("Invalid employee number");
					lblMessage.setVisible(true);
				}
			}
		});
		endShiftButton.setBackground(Color.LIGHT_GRAY);
		endShiftButton.setBounds(275, 100, 160, 61);
		frmTimeClock.getContentPane().add(endShiftButton);
		
		JLabel lblEmpNumber = new JLabel("Enter Employee Number");
		lblEmpNumber.setFont(new Font("SimSun", Font.BOLD, 15));
		lblEmpNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpNumber.setForeground(new Color(255, 255, 255));
		lblEmpNumber.setBounds(123, 32, 252, 24);
		frmTimeClock.getContentPane().add(lblEmpNumber);
		
		JButton closeButton = new JButton("Close");
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTimeClock.dispose();
			}
		});
		closeButton.setBackground(Color.LIGHT_GRAY);
		closeButton.setBounds(204, 227, 89, 34);
		frmTimeClock.getContentPane().add(closeButton);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmTimeClock.setLocation(dim.width/2-frmTimeClock.getSize().width/2, dim.height/2-frmTimeClock.getSize().height/2);
	}
}


