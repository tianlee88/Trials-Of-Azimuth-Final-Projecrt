
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
/**
 * This class implements the Control Menu screen, which appears when a user clicks the CONTROLS button in the Main Menu
 * @author christian kennedy, tian lee and  paul price 
 *
 */
public class ControlsMenuScreen extends MenuOutline {
	 
	private JPanel top_panel;
	private JPanel middle_panel;
	private JPanel lower_panel;

	/**
	 * This method is responsible for displaying the different panels in the frame as well as adding buttons and labels to the panels
	 */
	public void openMenu() {
		JFrame current_frame = this;
		Container content_pane = getContentPane();
		top_panel = new PicPanel("trials.png");
		middle_panel = new PicPanel("trials2.png");
		lower_panel = new PicPanel("trials3.png");
		//add panels to the content pane
		content_pane.add(top_panel);
		content_pane.add(middle_panel);
		content_pane.add(lower_panel);
		//*******
		JLabel control_keys_label = createLabel("controlkeys.png");
		middle_panel.add(control_keys_label);
		//****
		JButton return_button = createButton("returntomenu.png");
		return_button.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	current_frame.dispose();
		    	StartMenuScreen start_menu = new StartMenuScreen();
		    }
		});
		lower_panel.add(return_button);
	}

}
