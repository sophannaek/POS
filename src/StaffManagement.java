

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffManagement {

	private int xMouse, yMouse;
	protected int xLocation, yLocation;
	private static UserDAO userDAO;
	private JFrame frmStaffMng;
	private static JTable table;
	boolean passwordVisible = false;
	static UserTableModel model = null;
	public static User modUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffManagement window = new StaffManagement();
					window.frmStaffMng.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffManagement() {
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
		
		frmStaffMng = new JFrame();
		frmStaffMng.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStaffMng.setTitle("STAFF MANAGEMENT PANEL");
		frmStaffMng.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mina\\Dropbox\\CECS 277\\POS\\icon.png"));
		frmStaffMng.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frmStaffMng.getContentPane().setForeground(new Color(255, 255, 255, 50));
		frmStaffMng.getContentPane().setBackground(new Color(204, 0, 0));
		frmStaffMng.getContentPane().setLayout(null);
		
		JLabel lblAdminPanel = new JLabel("STAFF MANAGEMENT PANEL");
		lblAdminPanel.setFont(new Font("SimSun", Font.BOLD, 45));
		lblAdminPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPanel.setForeground(Color.WHITE);
		lblAdminPanel.setBounds(0, 11, 750, 45);
		frmStaffMng.getContentPane().add(lblAdminPanel);
		
		JLabel lblError = new JLabel("You must select a user");
		lblError.setFont(new Font("SimSun", Font.BOLD, 25));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.WHITE);
		lblError.setBounds(30, 442, 539, 38);
		lblError.setVisible(false);
		frmStaffMng.getContentPane().add(lblError);
		
		 ActionListener taskPerformer = new ActionListener() 
		 {
		       public void actionPerformed(ActionEvent evt) 
		       {
		    	   lblError.setVisible(false);
		       }
		   };
		
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
				frmStaffMng.setLocation(xLocation, yLocation);
			}
		});
		lblDrag.setBounds(0, 0, 700, 64);
		frmStaffMng.getContentPane().add(lblDrag);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 67, 720, 9);
		frmStaffMng.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 87, 539, 352);
		frmStaffMng.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowHeight(35);
		table.setFont(new Font("SimSun", Font.PLAIN, 20));	
		
		DefaultTableCellRenderer masked = new DefaultTableCellRenderer()
		{
			private static final long serialVersionUID = 1L;
		    public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) 
		    {
		        int length =0;
		        if (arg1 instanceof String) 
		            length =  ((String) arg1).length();
		        else if (arg1 instanceof char[]) 
		             length = ((char[])arg1).length;
		        setText(mask(length));
		        return this;
		   }
		};

		DefaultTableCellRenderer unmasked = new DefaultTableCellRenderer()
		{
			private static final long serialVersionUID = 1L;
		    public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) 
		    {
		        setText(arg1.toString());
		        return this;
		   }
		};	
		
		try 
		{
			List<User> myUsers = new ArrayList<User>();
			myUsers = userDAO.getAllUsers();
			model = new UserTableModel(myUsers);
			table.setModel(model);
			
			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(JLabel.LEFT);
			table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
			table.getColumnModel().getColumn(2).setCellRenderer(masked);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
		JButton btnTogglePassword = new JButton("Show Password");
		btnTogglePassword.setBackground(Color.LIGHT_GRAY);
		btnTogglePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(!passwordVisible)
				{
					passwordVisible = true;
	                table.getColumnModel().getColumn(2).setCellRenderer(unmasked);
	                model.fireTableDataChanged();
	                btnTogglePassword.setText("Hide Password");
	             }
				else if(passwordVisible)
				{
					passwordVisible = false;
					table.getColumnModel().getColumn(2).setCellRenderer(masked);
	                model.fireTableDataChanged();
	                btnTogglePassword.setText("Show Password");
				}
			}
		});
		btnTogglePassword.setBounds(579, 90, 160, 23);
		frmStaffMng.getContentPane().add(btnTogglePassword);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setBackground(Color.LIGHT_GRAY);
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				AddUserDialog.main(null);
			}
		});
		btnAddUser.setBounds(579, 130, 160, 23);
		frmStaffMng.getContentPane().add(btnAddUser);
		
		JButton btnUpdate = new JButton("Update User");
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int row = table.getSelectedRow();
				
				if (row < 0)
				{
	                lblError.setText("You Must Select A User");
					lblError.setVisible(true);
					new Timer(10000, taskPerformer).start();
				}
				else
				{
					User tempUser = (User) table.getValueAt(row, UserTableModel.OBJECT_COL);
					modUser = tempUser;
					UpdateUserDialog.main(null);
	                btnTogglePassword.setText("Show Password");
	                lblError.setText("User Updated");
					lblError.setVisible(true);
					new Timer(10000, taskPerformer).start();
				}
			}
		});
		btnUpdate.setBounds(579, 170, 160, 23);
		frmStaffMng.getContentPane().add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove User");
		btnRemove.setBackground(Color.LIGHT_GRAY);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int row = table.getSelectedRow();
				
				if (row < 0)
				{
	                lblError.setText("You Must Select A User");
					lblError.setVisible(true);
					new Timer(10000, taskPerformer).start();
				}
				else
				{
					try 
					{
						User tempUser = (User) table.getValueAt(row, UserTableModel.OBJECT_COL);
						userDAO.removeUser(tempUser);
						System.out.println(tempUser);
						refreshTable();
		                btnTogglePassword.setText("Show Password");
		                lblError.setText("User Removed");
		                lblError.setVisible(true);
						new Timer(10000, taskPerformer).start();
					}
					catch (Exception e2) 
					{
						e2.printStackTrace();
					}
				}
			}
		});
		btnRemove.setBounds(579, 210, 160, 23);
		frmStaffMng.getContentPane().add(btnRemove);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frmStaffMng.setVisible(false);
			}
		});
		btnExit.setBounds(579, 250, 160, 23);
		frmStaffMng.getContentPane().add(btnExit);
		
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		frmStaffMng.setResizable(false);
		frmStaffMng.setUndecorated(true);
		frmStaffMng.setOpacity(0.95f);
		frmStaffMng.setBounds(10, 0, 750, 480);
		frmStaffMng.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmStaffMng.setLocation(dim.width/2-frmStaffMng.getSize().width/2, dim.height/2-frmStaffMng.getSize().height/2);
	}
	
	private static String mask(int length) 
	{
	    StringBuilder sb = new StringBuilder(length);
	    for (int i = 0; i < length; i++) 
	        sb.append('\u25CF');
	    return new String(sb);
	}

	public static void refreshTable() 
	{
		DefaultTableCellRenderer masked = new DefaultTableCellRenderer()
		{
			private static final long serialVersionUID = 1L;
		    public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) 
		    {
		        int length =0;
		        if (arg1 instanceof String) 
		            length =  ((String) arg1).length();
		        else if (arg1 instanceof char[]) 
		             length = ((char[])arg1).length;
		        setText(mask(length));
		        return this;
		   }
		};
		
		try 
		{
			List<User> myUsers = new ArrayList<User>();
			myUsers = userDAO.getAllUsers();
			model = new UserTableModel(myUsers);
			table.setModel(model);
			
			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(JLabel.LEFT);
			table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
			table.getColumnModel().getColumn(2).setCellRenderer(masked);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}