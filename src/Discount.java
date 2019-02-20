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

public class Discount {

	private JFrame frame;
	private JTextField txtAmount;
	private JTextField txtPercent;
	private int xMouse, yMouse;
	protected int xLocation, yLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Discount window = new Discount();
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
	public Discount() {
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
		
		JLabel lblError = new JLabel("Invalid Entry");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("SimSun", Font.BOLD, 16));
		lblError.setBounds(20, 116, 253, 35);
		lblError.setVisible(false);
		frame.getContentPane().add(lblError);
		
		JLabel lblDollar = new JLabel("$ Amount");
		lblDollar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDollar.setForeground(Color.WHITE);
		lblDollar.setFont(new Font("SimSun", Font.BOLD, 16));
		lblDollar.setBounds(20, 54, 96, 35);
		frame.getContentPane().add(lblDollar);
		
		JLabel lblItemPrice = new JLabel("Percentage");
		lblItemPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemPrice.setForeground(Color.WHITE);
		lblItemPrice.setFont(new Font("SimSun", Font.BOLD, 16));
		lblItemPrice.setBounds(20, 82, 96, 35);
		frame.getContentPane().add(lblItemPrice);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 280, 16);
		frame.getContentPane().add(separator);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("SimSun", Font.BOLD, 30));
		lblDiscount.setBounds(10, 0, 280, 35);
		frame.getContentPane().add(lblDiscount);
		
		txtAmount = new JTextField();
		txtAmount.setBackground(Color.LIGHT_GRAY);
		txtAmount.setBounds(133, 63, 157, 20);
		frame.getContentPane().add(txtAmount);
		txtAmount.setColumns(10);
		
		txtPercent = new JTextField();
		txtPercent.setBackground(Color.LIGHT_GRAY);
		txtPercent.setBounds(133, 91, 157, 20);
		frame.getContentPane().add(txtPercent);
		txtPercent.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 154, 280, 8);
		frame.getContentPane().add(separator_1);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					if (txtAmount.getText().isEmpty() && !txtPercent.getText().isEmpty())
					{
						Double percent = Double.parseDouble(txtPercent.getText());
						if (percent > 0 && percent <= 100)
						{
							SalesPanel.total -= SalesPanel.total * (percent / 100);
							double tax = SalesPanel.total * (8.75 / 100);
							SalesPanel.txt.append("\n -----------------------------------------\n");
							SalesPanel.txt.append(String.format(" After %.0f%% discount\n\n", percent));
							SalesPanel.txt.append(String.format(" Subtotal\t\t\t$%.2f\n\n", SalesPanel.total));
							SalesPanel.txt.append(String.format(" Sales Tax\t\t$%.2f\n\n", tax));
							SalesPanel.txt.append(String.format(" TOTAL\t\t\t$%.2f\n", SalesPanel.total + tax));
							frame.setVisible(false);
						}
						else
							throw new Exception();
					}
					else if (!txtAmount.getText().isEmpty() && txtPercent.getText().isEmpty())
					{
						Double amount = Double.parseDouble(txtAmount.getText());
						if (amount > 0 && amount <= SalesPanel.total)
						{
							SalesPanel.total -= amount;
							double tax = SalesPanel.total * (8.75 / 100);
							SalesPanel.txt.append("\n -----------------------------------------\n");
							SalesPanel.txt.append(String.format(" After $%.2f discount\n\n", amount));
							SalesPanel.txt.append(String.format(" Subtotal\t\t\t$%.2f\n\n", SalesPanel.total));
							SalesPanel.txt.append(String.format(" Sales Tax\t\t$%.2f\n\n", tax));
							SalesPanel.txt.append(String.format(" TOTAL\t\t\t$%.2f\n", SalesPanel.total + tax));
							frame.setVisible(false);
						}
						else
							throw new Exception();
					}
					else
					{
						txtAmount.setText("");
						txtPercent.setText("");
						txtAmount.requestFocus();
						lblError.setVisible(true);
					}
				}
				catch (Exception ex)
				{
					txtAmount.setText("");
					txtPercent.setText("");
					txtAmount.requestFocus();
					lblError.setVisible(true);
				}
			}
		});
		btnApply.setBackground(Color.LIGHT_GRAY);
		btnApply.setBounds(10, 166, 135, 23);
		frame.getContentPane().add(btnApply);
		
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
