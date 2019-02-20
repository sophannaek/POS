import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenu {
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 280);
		frame.setTitle("POS SYSTEM LOGIN");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mina\\Dropbox\\CECS 277\\POS\\icon.png"));
		frame.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 15));
		frame.getContentPane().setForeground(new Color(255, 255, 255, 50));
		frame.getContentPane().setBackground(new Color(204, 0, 0));
		frame.getContentPane().setLayout(null);
		SimpleAttributeSet attribs = new SimpleAttributeSet();  
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(attribs, 85);
		StyleConstants.setFontFamily(attribs, "SimSun");
		Color w = new Color(255,255,255);
		StyleConstants.setForeground(attribs, w);
		
		JLabel lblPressAnyKey = new JLabel("Press any key to exit . . .");
		lblPressAnyKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblPressAnyKey.setFont(new Font("SimSun", Font.BOLD, 25));
		lblPressAnyKey.setForeground(new Color(255, 255, 255));
		lblPressAnyKey.setBounds(10, 213, 460, 56);
		frame.getContentPane().add(lblPressAnyKey);
		
		JTextPane txtpnLoginSuccessful = new JTextPane();
		txtpnLoginSuccessful.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.exit(0);
			}
		});
		txtpnLoginSuccessful.setForeground(new Color(255, 255, 255));
		txtpnLoginSuccessful.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		txtpnLoginSuccessful.setEditable(false);
		txtpnLoginSuccessful.setBackground(new Color(204, 0, 0));
		txtpnLoginSuccessful.setText("LOGIN SUCCESSFUL");
		txtpnLoginSuccessful.setFont(new Font("SimSun", Font.BOLD, 80));
		txtpnLoginSuccessful.setBounds(10, 11, 480, 258);
		txtpnLoginSuccessful.setParagraphAttributes(attribs,true);
		
		
		frame.getContentPane().add(txtpnLoginSuccessful);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setOpacity(0.83f);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
}
