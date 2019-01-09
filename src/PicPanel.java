import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class is an extension of JPanel that allows you to add a background image to a Panel on a JFrame
 * @authors Tian Lee, Paul Price, and Christian Kennedy
 *
 */
public class PicPanel extends JPanel{

    private BufferedImage image;		//the image to use as the background
    
    /*
     * This constructor takes in a String image pathname for the desired background image for the Panel
     */
    public PicPanel(String image_pathname){
        try {
            image = ImageIO.read(new File(image_pathname));

        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

    /*
     * This method will paint the desired background image onto the Panel
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}