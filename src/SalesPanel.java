import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalesPanel {

	private JFrame frmSalesPanel;
	private int xMouse, yMouse;
	protected int xLocation, yLocation;
	protected static double total = 0;
	protected static boolean discount;
	protected static JTextArea txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesPanel window = new SalesPanel();
					window.frmSalesPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SalesPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSalesPanel = new JFrame();
		frmSalesPanel.setSize(1200, 770);
		frmSalesPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalesPanel.setTitle("SALES PANEL");
		frmSalesPanel.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mina\\Dropbox\\CECS 277\\POS\\icon.png"));
		frmSalesPanel.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frmSalesPanel.getContentPane().setForeground(new Color(255, 255, 255, 50));
		frmSalesPanel.getContentPane().setBackground(new Color(204, 0, 0));
		frmSalesPanel.getContentPane().setLayout(null);
		frmSalesPanel.setResizable(false);
		frmSalesPanel.setUndecorated(true);
		frmSalesPanel.setOpacity(0.95f);
		
		JLabel lblSalesPanel = new JLabel("SALES PANEL");
		lblSalesPanel.setFont(new Font("SimSun", Font.BOLD, 50));
		lblSalesPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesPanel.setForeground(new Color(255, 255, 255));
		lblSalesPanel.setBounds(23, 11, 1155, 49);
		frmSalesPanel.getContentPane().add(lblSalesPanel);
		
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
				frmSalesPanel.setLocation(xLocation, yLocation);
			}
		});
		lblDrag.setBounds(0, 0, 1000, 47);
		frmSalesPanel.getContentPane().add(lblDrag);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 58, 1155, 12);
		frmSalesPanel.getContentPane().add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 0, 0));
		panel.setForeground(Color.BLACK);
		panel.setBorder(null);
		panel.setBounds(23, 69, 706, 695);
		frmSalesPanel.getContentPane().add(panel);
		panel.setLayout(new GridLayout(10, 3, 1, 1));
		
		txt = new JTextArea();
		txt.setEditable(false);
		txt.setTabSize(10);
		txt.setColumns(1);
		txt.setFont(new Font("Monospaced", Font.PLAIN, 16));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		int checkNumber = ThreadLocalRandom.current().nextInt(1000, 100000);
		txt.setText("SERVER :" + Login.activeUsers.get(0).getEmpNumber() + "\t" + dtf.format(now) +
					"\nCHECK: #" + checkNumber +
					"\n -----------------------------------------\n");
		txt.setLineWrap(true);
		txt.setRows(1);
		txt.setBounds(615, 71, 364, 507);
		frmSalesPanel.getContentPane().add(txt);
		
		JScrollPane scrollPane = new JScrollPane(txt);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(739, 71, 439, 480);
		frmSalesPanel.getContentPane().add(scrollPane);
		
		JButton btnNewButton_1 = new JButton("Triple Dipper");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Triple Dipper \t\t $12.19\n\n");
				total += 12.19;
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setForeground(Color.BLACK);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Boneless Wings");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Boneless Wings \t\t $10.19\n\n");
				total += 10.19;
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setForeground(Color.BLACK);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Bacon Burger");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Bacon Burger \t\t $11.79\n\n");
				total += 11.79;
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setForeground(Color.BLACK);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Sunrise Burger");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Sunrise Burger \t\t $11.79\n\n");
				total += 11.79;
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.setForeground(Color.BLACK);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Full Order Ribs");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Full Order Ribs \t\t $18.99\n\n");
				total += 18.99;
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.setBackground(Color.LIGHT_GRAY);
		btnNewButton_5.setForeground(Color.BLACK);
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Half Order Ribs");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Half Order Ribs \t\t $11.69\n\n");
				total += 11.69;
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_6.setBackground(Color.LIGHT_GRAY);
		btnNewButton_6.setForeground(Color.BLACK);
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Chicken Fajitas");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Chicken Fajitas \t\t $14.69\n\n");
				total += 14.69;
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_7.setBackground(Color.LIGHT_GRAY);
		btnNewButton_7.setForeground(Color.BLACK);
		panel.add(btnNewButton_7);
		
		JButton btnNewButton_20 = new JButton("Steak Fajitas");
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Steak Fajitas \t\t $16.69\n\n");
				total += 16.69;
			}
		});
		btnNewButton_20.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_20.setBackground(Color.LIGHT_GRAY);
		btnNewButton_20.setForeground(Color.BLACK);
		panel.add(btnNewButton_20);
		
		JButton btnNewButton_10 = new JButton("Dinner For 2");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Dinner For 2 \t\t $22.00\n\n");
				total += 22;
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_10.setBackground(Color.LIGHT_GRAY);
		btnNewButton_10.setForeground(Color.BLACK);
		panel.add(btnNewButton_10);
		
		JButton btnNewButton_8 = new JButton("3 For $10");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" 3 For $10 \t\t $10.00\n\n");
				total += 10;
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_8.setBackground(Color.LIGHT_GRAY);
		btnNewButton_8.setForeground(Color.BLACK);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Fresco Salad");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Fresco Salad \t\t $3.99\n\n");
				total += 3.99;
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_9.setBackground(Color.LIGHT_GRAY);
		btnNewButton_9.setForeground(Color.BLACK);
		panel.add(btnNewButton_9);
		
		JButton btnNewButton_12 = new JButton("Chili & House Salad");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" C & H Salad \t\t $8.19\n\n");
				total += 8.19;
			}
		});
		btnNewButton_12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_12.setBackground(Color.LIGHT_GRAY);
		btnNewButton_12.setForeground(Color.BLACK);
		panel.add(btnNewButton_12);
		
		JButton btnNewButton = new JButton("California Turkey Club");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Cal Turkey Club \t\t $9.79\n\n");
				total += 9.79;
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.BLACK);
		panel.add(btnNewButton);
		
		JButton btnNewButton_14 = new JButton("Chicken Sandwich");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Chicken Sandwich \t\t $9.89\n\n");
				total += 9.89;
			}
		});
		btnNewButton_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_14.setBackground(Color.LIGHT_GRAY);
		btnNewButton_14.setForeground(Color.BLACK);
		panel.add(btnNewButton_14);
		
		JButton btnNewButton_11 = new JButton("Chicken Crispers");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Chicken Crispers \t\t $10.89\n\n");
				total += 10.89;
			}
		});
		btnNewButton_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_11.setBackground(Color.LIGHT_GRAY);
		btnNewButton_11.setForeground(Color.BLACK);
		panel.add(btnNewButton_11);
		
		JButton btnNewButton_16 = new JButton("Shrimp Fajitas");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.append(" Shrimp Fajitas \t\t $16.69\n\n");
				total += 16.69;
			}
		});
		btnNewButton_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_16.setBackground(Color.LIGHT_GRAY);
		btnNewButton_16.setForeground(Color.BLACK);
		panel.add(btnNewButton_16);
		
		JButton btnNewButton_13 = new JButton("Ancho Salmon");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt.append(" Ancho Salmon \t\t $15.29\n\n");
				total += 15.29;
			}
		});
		btnNewButton_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_13.setBackground(Color.LIGHT_GRAY);
		btnNewButton_13.setForeground(Color.BLACK);
		panel.add(btnNewButton_13);
		
		JButton btnNewButton_18 = new JButton("Mango-Chile Chicken");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Mango Chile Chicken \t $10.79\n\n");
				total += 10.79;
			}
		});
		btnNewButton_18.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_18.setBackground(Color.LIGHT_GRAY);
		btnNewButton_18.setForeground(Color.BLACK);
		panel.add(btnNewButton_18);
		
		JButton btnNewButton_21 = new JButton("Spicy Shrimp Tacos");
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Spicy Shrimp Tacos \t $11.59\n\n");
				total += 11.59;
			}
		});
		btnNewButton_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_21.setBackground(Color.LIGHT_GRAY);
		btnNewButton_21.setForeground(Color.BLACK);
		panel.add(btnNewButton_21);
		
		JButton btnNewButton_15 = new JButton("Beef Quesadillas");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Beef Quesadillas \t\t $10.79\n\n");
				total += 10.79;
			}
		});
		btnNewButton_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_15.setBackground(Color.LIGHT_GRAY);
		btnNewButton_15.setForeground(Color.BLACK);
		panel.add(btnNewButton_15);
		
		JButton btnNewButton_17 = new JButton("Smokehouse Combo");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Smokehouse Combo \t\t $15.99\n\n");
				total += 15.99;
			}
		});
		btnNewButton_17.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_17.setBackground(Color.LIGHT_GRAY);
		btnNewButton_17.setForeground(Color.BLACK);
		panel.add(btnNewButton_17);
		
		JButton btnNewButton_24 = new JButton("Ultimate House Combo");
		btnNewButton_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Ultimate House Combo \t $17.99\n\n");
				total += 17.99;
			}
		});
		btnNewButton_24.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_24.setBackground(Color.LIGHT_GRAY);
		btnNewButton_24.setForeground(Color.BLACK);
		panel.add(btnNewButton_24);
		
		JButton btnNewButton_30 = new JButton("Cajun Shrimp Pasta");
		btnNewButton_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Cajun Shrimp Pasta \t $13.49\n\n");
				total += 13.49;
			}
		});
		btnNewButton_30.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_30.setBackground(Color.LIGHT_GRAY);
		btnNewButton_30.setForeground(Color.BLACK);
		panel.add(btnNewButton_30);
		
		JButton btnNewButton_19 = new JButton("Chicken Enchiladas");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Chicken Enchiladas \t $9.99\n\n");
				total += 9.99;
			}
		});
		btnNewButton_19.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_19.setBackground(Color.LIGHT_GRAY);
		btnNewButton_19.setForeground(Color.BLACK);
		panel.add(btnNewButton_19);
		
		JButton btnNewButton_22 = new JButton("Southwestern BLT");
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Southwestern BLT \t\t $6.00\n\n");
				total += 6.00;
			}
		});
		btnNewButton_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_22.setBackground(Color.LIGHT_GRAY);
		btnNewButton_22.setForeground(Color.BLACK);
		panel.add(btnNewButton_22);
		
		JButton btnNewButton_27 = new JButton("Margherita Flatbread");
		btnNewButton_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Margherita Flatbread \t $8.99\n\n");
				total += 8.99;
			}
		});
		btnNewButton_27.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_27.setBackground(Color.LIGHT_GRAY);
		btnNewButton_27.setForeground(Color.BLACK);
		panel.add(btnNewButton_27);
		
		JButton btnNewButton_23 = new JButton("Molten Chocolate Cake");
		btnNewButton_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Molten Chocolate Cake \t $7.49\n\n");
				total += 7.49;
			}
		});
		btnNewButton_23.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_23.setBackground(Color.LIGHT_GRAY);
		btnNewButton_23.setForeground(Color.BLACK);
		panel.add(btnNewButton_23);
		
		JButton btnNewButton_25 = new JButton("Cheesecake");
		btnNewButton_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Cheesecake \t\t $6.79\n\n");
				total += 6.79;
			}
		});
		btnNewButton_25.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_25.setBackground(Color.LIGHT_GRAY);
		btnNewButton_25.setForeground(Color.BLACK);
		panel.add(btnNewButton_25);
		
		JButton btnNewButton_32 = new JButton("Peppers Pals Pasta");
		btnNewButton_32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Peppers Pals Pasta \t $5.50\n\n");
				total += 5.50;
			}
		});
		btnNewButton_32.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_32.setBackground(Color.LIGHT_GRAY);
		btnNewButton_32.setForeground(Color.BLACK);
		panel.add(btnNewButton_32);
		
		JButton btnNewButton_26 = new JButton("Pepper Pals Pizza");
		btnNewButton_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Pepper Pals Pizza \t\t $5.50\n\n");
				total += 5.50;
			}
		});
		btnNewButton_26.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_26.setBackground(Color.LIGHT_GRAY);
		btnNewButton_26.setForeground(Color.BLACK);
		panel.add(btnNewButton_26);
		
		JButton btnNewButton_28 = new JButton("Pepper Pals Quesadilla");
		btnNewButton_28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Pepper Pals Quesadilla \t $5.20\n\n");
				total += 5.20;
			}
		});
		btnNewButton_28.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_28.setBackground(Color.LIGHT_GRAY);
		btnNewButton_28.setForeground(Color.BLACK);
		panel.add(btnNewButton_28);
		
		JButton btnNewButton_29 = new JButton("Pepper Pals Dippers");
		btnNewButton_29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Pepper Pals Dippers \t $5.50\n\n");
				total += 5.50;
			}
		});
		btnNewButton_29.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_29.setBackground(Color.LIGHT_GRAY);
		btnNewButton_29.setForeground(Color.BLACK);
		panel.add(btnNewButton_29);
		
		JButton btnNewButton_31 = new JButton("Burger Bites");
		btnNewButton_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Burger Bites \t\t $5.50\n\n");
				total += 5.50;
			}
		});
		btnNewButton_31.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_31.setBackground(Color.LIGHT_GRAY);
		btnNewButton_31.setForeground(Color.BLACK);
		panel.add(btnNewButton_31);
		
		JButton btnNewButton_36 = new JButton("Party Platter - Small");
		btnNewButton_36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Party Platter - Small \t $24.00\n\n");
				total += 24.00;
			}
		});
		btnNewButton_36.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_36.setBackground(Color.LIGHT_GRAY);
		btnNewButton_36.setForeground(Color.BLACK);
		panel.add(btnNewButton_36);
		
		JButton btnNewButton_33 = new JButton("Party Platter - Medium");
		btnNewButton_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Party Platter - Medium \t $32.00\n\n");
				total += 32.00;
			}
		});
		btnNewButton_33.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_33.setBackground(Color.LIGHT_GRAY);
		btnNewButton_33.setForeground(Color.BLACK);
		panel.add(btnNewButton_33);
		
		JButton btnNewButton_34 = new JButton("Party Platter - Large");
		btnNewButton_34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Party Platter - Large \t $40.00\n\n");
				total += 40.00;
			}
		});
		btnNewButton_34.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_34.setBackground(Color.LIGHT_GRAY);
		btnNewButton_34.setForeground(Color.BLACK);
		panel.add(btnNewButton_34);
		
		JButton btnNewButton_35 = new JButton("Fountain Drinks");
		btnNewButton_35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Fountain Drinks \t\t $2.89\n\n");
				total += 2.89;
			}
		});
		btnNewButton_35.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_35.setBackground(Color.LIGHT_GRAY);
		btnNewButton_35.setForeground(Color.BLACK);
		panel.add(btnNewButton_35);
		
		JButton btnNewButton_37 = new JButton("Iced Tea");
		btnNewButton_37.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Iced Tea \t\t $2.89\n\n");
				total += 2.89;
			}
		});
		btnNewButton_37.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_37.setBackground(Color.LIGHT_GRAY);
		btnNewButton_37.setForeground(Color.BLACK);
		panel.add(btnNewButton_37);
		
		JButton btnNewButton_38 = new JButton("Beer");
		btnNewButton_38.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Beer \t\t\t $4.25\n\n");
				total += 4.25;
			}
		});
		btnNewButton_38.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_38.setBackground(Color.LIGHT_GRAY);
		btnNewButton_38.setForeground(Color.BLACK);
		panel.add(btnNewButton_38);
		
		JButton btnNewButton_39 = new JButton("Wine");
		btnNewButton_39.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.append(" Wine \t\t\t $10.00\n\n");
				total += 10.00;
			}
		});
		btnNewButton_39.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_39.setBackground(Color.LIGHT_GRAY);
		btnNewButton_39.setForeground(Color.BLACK);
		panel.add(btnNewButton_39);
		
		JButton[] menuItems = new JButton[] {btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6, btnNewButton_7,
				btnNewButton_8, btnNewButton_9, btnNewButton_10, btnNewButton_11, btnNewButton_12, btnNewButton_13, btnNewButton_14, btnNewButton_15, btnNewButton_16,
				btnNewButton_17, btnNewButton_18, btnNewButton_19, btnNewButton_20, btnNewButton_21, btnNewButton_22, btnNewButton_23, btnNewButton_24, btnNewButton_25,
				btnNewButton_26, btnNewButton_27, btnNewButton_28, btnNewButton_29, btnNewButton_30, btnNewButton_31, btnNewButton_32, btnNewButton_33, btnNewButton_34,
				btnNewButton_35, btnNewButton_36, btnNewButton_37, btnNewButton_38, btnNewButton_39};
		
		JButton btnDiscount = new JButton("Discount");
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				discount = true;
				if (Login.activeUsers.get(0).isAdmin())
					Discount.main(null);
				else
					AdminLogin.main(null);
			}
		});
		btnDiscount.setBackground(Color.LIGHT_GRAY);
		btnDiscount.setBounds(739, 609, 214, 50);
		btnDiscount.setEnabled(false);
		frmSalesPanel.getContentPane().add(btnDiscount);
		
		JButton btnCustomEntry = new JButton("Custom Entry");
		btnCustomEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				discount = false;
				if (Login.activeUsers.get(0).isAdmin())
					CustomEntry.main(null);
				else
					AdminLogin.main(null);
			}
		});
		btnCustomEntry.setBackground(Color.LIGHT_GRAY);
		btnCustomEntry.setBounds(963, 609, 215, 50);
		frmSalesPanel.getContentPane().add(btnCustomEntry);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setBackground(Color.LIGHT_GRAY);
		btnPay.setBounds(739, 664, 214, 100);
		btnPay.setEnabled(false);
		frmSalesPanel.getContentPane().add(btnPay);
		
		JButton btnTotal = new JButton("Total");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (total != 0)
				{
					double tax = total * (8.75 / 100);
					txt.append("\n -----------------------------------------\n");
					txt.append(String.format(" Subtotal\t\t\t$%.2f\n\n", total));
					txt.append(String.format(" Sales Tax\t\t$%.2f\n\n", tax));
					txt.append(String.format(" TOTAL\t\t\t$%.2f\n", total + tax));
					
					for (JButton item: menuItems)
						item.setEnabled(false);
					btnTotal.setEnabled(false);
					btnCustomEntry.setEnabled(false);
					btnDiscount.setEnabled(true);
					btnPay.setEnabled(true);
				}
			}
		});
		btnTotal.setBackground(Color.LIGHT_GRAY);
		btnTotal.setBounds(739, 554, 215, 50);
		frmSalesPanel.getContentPane().add(btnTotal);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txt.setText("SERVER :" + Login.activeUsers.get(0).getEmpNumber() + dtf.format(now) +
							"\nCHECK: #" + checkNumber +
							"\n -----------------------------------------\n");
				total = 0;
				for (JButton item: menuItems)
					item.setEnabled(true);
				btnTotal.setEnabled(true);
				btnCustomEntry.setEnabled(true);
				btnDiscount.setEnabled(false);
				btnPay.setEnabled(false);
			}
		});
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.setBounds(963, 554, 215, 50);
		frmSalesPanel.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frmSalesPanel.setVisible(false);
			}
		});
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setBounds(963, 664, 215, 100);
		frmSalesPanel.getContentPane().add(btnExit);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSalesPanel.setLocation(dim.width/2-frmSalesPanel.getSize().width/2, dim.height/2-frmSalesPanel.getSize().height/2);
	}
}
