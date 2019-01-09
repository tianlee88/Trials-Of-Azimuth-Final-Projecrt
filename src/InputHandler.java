import java.awt.Component;
import java.awt.event.*;

/**
 * This class detects all input from the keyboard to be used within the AzimuthGame class
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class InputHandler implements KeyListener{

  private boolean[] keys;	//the list of keys to look for input from

	/**
	 * Assigns the InputHandler to a Component (AzimuthGame extends JFrame)
	 * @param c - the Component to get input from
	 */
	public InputHandler(Component c){
	  keys = new boolean[256];
	  c.addKeyListener(this);
	}
	
	/**
	 * Checks whether a specific key is down
	 * @param keyCode - the key to check
	 * @return - true if the key is pressed, otherwise false
	 */
	public boolean isKeyDown(int keyCode){
	    if (keyCode > 0 && keyCode < 256){
	    	return keys[keyCode];
	    }
	    return false;
	}
	
	/**
	 * Called when a key is pressed while the component is focused
	 * @param e - KeyEvent sent by the component
	 */
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256){
			keys[e.getKeyCode()] = true;
		}
	}
	
	/**
	 * Called when a key is released while the component is focused
	 * @param e - the KeyEvent sent by the component
	 */
	public void keyReleased(KeyEvent e){
	    if (e.getKeyCode() > 0 && e.getKeyCode() < 256){
	    	keys[e.getKeyCode()] = false;
	    }
	}
	
	/**
	 * keyTyped is not currently a feature of our game
	 */
	public void keyTyped(KeyEvent e){}
	
}
