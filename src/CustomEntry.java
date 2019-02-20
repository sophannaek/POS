import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class CustomEntry {

	private JFrame frame;
	private JTextField txtItem;
	private JTextField txtPrice;
	private int xMouse, yMouse;
	protected int xLocation, yLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomEntry window = new CustomEntry();
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
	public CustomEntry() {
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
		
		JLabel lblError = new JLabel("Invalid Details");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("SimSun", Font.BOLD, 16));
		lblError.setBounds(20, 116, 253, 35);
		lblError.setVisible(false);
		frame.getContentPane().add(lblError);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemName.setForeground(Color.WHITE);
		lblItemName.setFont(new Font("SimSun", Font.BOLD, 16));
		lblItemName.setBounds(20, 54, 96, 35);
		frame.getContentPane().add(lblItemName);
		
		JLabel lblItemPrice = new JLabel("Item Price");
		lblItemPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemPrice.setForeground(Color.WHITE);
		lblItemPrice.setFont(new Font("SimSun", Font.BOLD, 16));
		lblItemPrice.setBounds(20, 82, 96, 35);
		frame.getContentPane().add(lblItemPrice);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 280, 16);
		frame.getContentPane().add(separator);
		
		JLabel lblCustomEntry = new JLabel("Custom Entry");
		lblCustomEntry.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomEntry.setForeground(Color.WHITE);
		lblCustomEntry.setFont(new Font("SimSun", Font.BOLD, 30));
		lblCustomEntry.setBounds(10, 0, 280, 35);
		frame.getContentPane().add(lblCustomEntry);
		
		txtItem = new JTextField();
		txtItem.setBackground(Color.LIGHT_GRAY);
		txtItem.setBounds(133, 63, 157, 20);
		frame.getContentPane().add(txtItem);
		txtItem.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBackground(Color.LIGHT_GRAY);
		txtPrice.setBounds(133, 91, 157, 20);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 154, 280, 8);
		frame.getContentPane().add(separator_1);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String item = txtItem.getText();
					Double price = Double.parseDouble(txtPrice.getText());
					if (item.length() < 10)
						SalesPanel.txt.append(String.format(" %s \t\t\t $%.2f\n\n", item, price));
					else if (item.length() >= 10 && item.length() < 25)
						SalesPanel.txt.append(String.format(" %s \t\t $%.2f\n\n", item, price));
					else
						SalesPanel.txt.append(String.format(" %s \t $%.2f\n\n", item, price));
					SalesPanel.total += price;
					frame.setVisible(false);
				}
				catch (Exception ex)
				{
					txtItem.setText("");
					txtPrice.setText("");
					txtItem.requestFocus();
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
