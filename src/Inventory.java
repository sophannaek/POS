import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Inventory {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane pane;
	private JTextField textId;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textQuantity;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JLabel lblNewLabel_1;
	private int xMouse, yMouse;
	protected int xLocation, yLocation;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
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
	public Inventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frame.getContentPane().setForeground(new Color(255, 255, 255, 50));
		frame.getContentPane().setBackground(new Color(204, 0, 0));
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setOpacity(0.95f);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Inventory Panel");
		frame.setSize(880,400);
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

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
		lblDrag.setBounds(0, 0, 880, 47);
		frame.getContentPane().add(lblDrag);
		
		table = new JTable();
		Object[] columns = {"ProductID","Name","Price (USD)","Quantity"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		//set the model to the table 
		table.setModel(model);
		
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.BLACK);
		Font font = new Font("",1,22);
		table.setFont(font);
		table.setRowHeight(30);
		//create textfields
		textId = new JTextField();
		textId.setBackground(Color.LIGHT_GRAY);
		textName = new JTextField();
		textName.setBackground(Color.LIGHT_GRAY);
		textPrice = new JTextField();
		textPrice.setBackground(Color.LIGHT_GRAY);
		textQuantity = new JTextField();
		textQuantity.setBackground(Color.LIGHT_GRAY);
		
		//create JButtons
		btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.LIGHT_GRAY);
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.LIGHT_GRAY);
	
		
		textId.setBounds(129,231, 138,25);
		textName.setBounds(129,262, 138,25);
		textPrice.setBounds(494,231, 138,25);
		textQuantity.setBounds(494,268,138,25);
		btnAdd.setBounds(43,333,100,25);
		btnDelete.setBounds(384,333,100,25);
		btnUpdate.setBounds(203,333,100,25);
		
		//create JScrollPane
		pane =  new JScrollPane(table); 
		pane.setBounds(0,0,880, 200);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(pane);
		frame.getContentPane().add(textId);
		frame.getContentPane().add(textName);
		frame.getContentPane().add(textPrice);
		frame.getContentPane().add(textQuantity);
		frame.getContentPane().add(btnAdd);
		frame.getContentPane().add(btnDelete);
		frame.getContentPane().add(btnUpdate);
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblProductId.setForeground(Color.WHITE);
		lblProductId.setBounds(17, 231, 100, 16);
		frame.getContentPane().add(lblProductId);
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(17, 267, 61, 16);
		frame.getContentPane().add(lblName);
		JLabel lblNewLabel = new JLabel("Price");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(393, 236, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCancel = new JButton("Exit");
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
			}
		});

		btnCancel.setBounds(583, 331, 100, 25);
		frame.getContentPane().add(btnCancel);
		
		lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(393, 267, 91, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		//create an array of objects to set the row data
		Object[] row = new Object[4];
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row[0] = textId.getText();	
				row[1] = textName.getText();
				row[2] = textPrice.getText();
				row[3] = textQuantity.getText();
				//add row to the model
				model.addRow(row);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i >= 0) {
					model.removeRow(i);
				}
				else {System.out.println("No Item to delete!");}
			}
		});
		
		//get selected row data from table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				textId.setText(model.getValueAt(i, 0).toString());
				textName.setText(model.getValueAt(i, 1).toString());
				textPrice.setText(model.getValueAt(i, 2).toString());
				textQuantity.setText(model.getValueAt(i, 3).toString());
			}		
		});
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i= table.getSelectedColumn();
				if(i >= 0)
				{
					model.setValueAt(textId.getText(), i, 0);
					model.setValueAt(textName.getText(), i, 1);
					model.setValueAt(textPrice.getText(), i, 2);
					model.setValueAt(textQuantity.getText(), i,3);
				}
				else { System.out.print("update error"); }
			}	});
	}
}