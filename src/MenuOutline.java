
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 * This abstract class is for implementing the Flyweight design pattern for our different GUI windows
 * @authors Tian Lee, Paul Price, Christian Kennedy
 */
public abstract class MenuOutline extends JFrame{
	
	private int window_width = 1200;
	private int window_height = 800;
	
	public MenuOutline() {
		this.initializeGUI();
	}
	
	/**
	 * This method creates a new JButton, loads a BufferedImage from the provided String filename, uses that image to build
	 * an ImageIcon, and then sets the new button's icon to that icon
	 * @param image_filename - the String that indicates the desired file
	 * @return - the newly created JButton
	 */
	public JButton createButton(String image_filename) {
		JButton new_button = new JButton();
		try {
		BufferedImage new_image = ImageIO.read(new File(image_filename));
		ImageIcon new_icon = new ImageIcon(new_image);
		new_button.setIcon(new_icon);
		
		} catch (IOException e) {
		  System.out.println("Error: " + e);
		}
		new_button.setBackground(Color.BLACK);
		return new_button;
	}
	
	/**
	 * This method loads a BufferedImage from the provided String filename, uses that image to build
	 * an ImageIcon, and then creates a new JLabel with the specified ImageIcon
	 * @param image_filename - the String that indicates the desired file
	 * @return - the newly created JLabel
	 */
	public JLabel createLabel(String image_filename) {
		JLabel new_label = null;
		try {
			BufferedImage title_image = ImageIO.read(new File(image_filename));
			ImageIcon title_icon = new ImageIcon(title_image);
			new_label = new JLabel(title_icon);
			} catch (IOException e) {
			  System.out.println("Error: " + e);
			}
		return new_label;
	}
	
	/**
	 * This method initializes the window itself
	 */
	public void initializeGUI() {
		getContentPane().setLayout(new GridLayout(3,1));
		setTitle("The Trials of Azimuth");
		setSize(window_width, window_height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);		
		openMenu();
		setVisible(true);		      		      
	 }
	
	
	/**
	 * This method is responsible for displaying the different panels in the frame as well as adding buttons and labels to the panels
	 */
	public abstract void openMenu();
	
	
}
