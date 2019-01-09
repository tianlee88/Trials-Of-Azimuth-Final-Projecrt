
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/** 
 * This class is in charge of defining the panels and buttons
 * for the start menu
 * @author christian kennedy, tian lee and  paul price 
 *
 */
public class PostGameScreen extends JFrame{
	
	private JPanel top_panel;
	private JPanel middle_panel;
	private JPanel lower_panel;
	private int window_width = 1200;	//width of the window
	private int window_height = 800;	//height of the window
	public static String username;		//current user's username
	private int user_score;				//score the current user just achieved
	
	/**
	 * This constructor takes in the user score from the previously played game and saves it in a field, then initializes the window
	 * @param user_score - the score the user got while playing the game just now
	 */
	public PostGameScreen(int user_score) {
		this.username = username;
		this.user_score = user_score;
		initializeGUI();
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
	 * This method takes in the score the user got and returns an ArrayList of JLabels that represent the score
	 * @param score - an integer that is the score the user achieved in the game
	 * @return - an ArrayList of JLabels; one JLabel for each digit in the score
	 */
	public ArrayList<JLabel> breakDownScore(int score){
		ArrayList<JLabel> digits = new ArrayList<JLabel>();
		if (score < 10) {
			digits.add(displayDigit(score));
		} else if (score < 100) {
			int first_digit = score/10;	//this will be the value of the first digit of the two
			int second_digit = score - (first_digit*10);	//value of the second digit
			digits.add(displayDigit(first_digit));
			digits.add(displayDigit(second_digit));
			
		} else if (score < 1000) {
			int first_digit = score / 100;
			int second_digit = (score - (first_digit*100))/10;
			int third_digit = score - (first_digit*100) - (second_digit*10);
			digits.add(displayDigit(first_digit));
			digits.add(displayDigit(second_digit));
			digits.add(displayDigit(third_digit));
		} else {
			JLabel how = createLabel("how.png");
			digits.add(how);
		}
		return digits;
	}
	
	/**
	 * This method takes in a single digit and returns a JLabel with that digit as an image
	 * @param single_digit - the integer to be returned as a JLabel image
	 * @return - the JLabel with the integer representation
	 */
	public JLabel displayDigit(int single_digit){
		if (single_digit < 10) {
			JLabel digit = null;
			switch(single_digit) {
			case 0:
				digit = createLabel("0.png");
				break;
			case 1:
				digit = createLabel("1.png");
				break;
			case 2:
				digit = createLabel("2.png");
				break;
			case 3:
				digit = createLabel("3.png");
				break;
			case 4:
				digit = createLabel("4.png");
				break;
			case 5:
				digit = createLabel("5.png");
				break;
			case 6:
				digit = createLabel("6.png");
				break;
			case 7:
				digit = createLabel("7.png");
				break;
			case 8:
				digit = createLabel("8.png");
				break;
			case 9:
				digit = createLabel("9.png");
				break;
			}
			return digit;
		} else {
			return null;
		}
	}

	
	/**
	 * This method is responsible for displaying the different panels in the frame as well as adding buttons and labels to the panels
	 */
	public void openMenu() {
		Container content_pane = getContentPane();	
		JFrame current_frame = this;
		top_panel = new PicPanel("trials.png");
		middle_panel = new PicPanel("trials2.png");
		lower_panel = new PicPanel("trials3.png");
		//add panels to content frame
		content_pane.add(top_panel);
		content_pane.add(middle_panel);
		content_pane.add(lower_panel);
		//*********
		JLabel skinnyblank_label1 = createLabel("skinnyblank.png");		//blank label for formatting of panel
		JLabel skinnyblank_label2 = createLabel("skinnyblank.png");		//blank label for formatting of panel
		//******		
		JLabel your_score = createLabel("yourscore.png");
		middle_panel.add(your_score);
		ArrayList<JLabel> digits = breakDownScore(user_score);
		for (JLabel one_digit : digits) {
			if (one_digit != null) {
				middle_panel.add(one_digit);
			}
		}
		GameDatabase gd = new GameDatabase();
		if (user_score > gd.getUserHighscore(username)) {
			//NEW HIGHSCORE
			gd.updateUser(username, user_score);
			JLabel new_highscore = createLabel("newhighscore.png");
			middle_panel.add(skinnyblank_label2);
			middle_panel.add(new_highscore);
		}
		//******
		JButton play_again = createButton("playagain.png");
		play_again.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	AzimuthGame new_game = new AzimuthGame();
		    	new_game.start();
		    }
		}); 
		//********
		JButton return_button = createButton("return_to_main.png");
		return_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	StartMenuScreen start_menu = new StartMenuScreen();
		    }
		});		
		//*******
		lower_panel.add(play_again);
		lower_panel.add(skinnyblank_label1);
		lower_panel.add(return_button);
		middle_panel.setSize(window_width, window_height);
	}
	
}