import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class canvasTest extends JPanel{
	
	static int origHeight;
    static int origWidth;
	static BufferedImage image = null;
	static JFrame frame;
	
	public static void main(String[] args) {
		

  //import the picture store it 
      BufferedImage img = null;
	  File file = new File("c:\\flir.jpg");
	  // storing color values here
	  int[][] imgPixels;
      
      // just checking permissions
	  boolean bool = file.canExecute();
	  boolean bool2 = file.getAbsoluteFile().exists();
	  System.out.println("Can I read this file? " + bool);
	  System.out.println("Does file exist? " + bool2);
      
	  try {
	      image = ImageIO.read(file);
	  } catch (IOException e) {
	      // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  
	  origWidth = image.getWidth();
	  origHeight = image.getHeight();
	  System.out.println(origWidth);
	  System.out.println(origHeight);
      frame = new JFrame();
    	frame.getContentPane().add(new canvasTest());
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // set picture height width
    	frame.setSize(origWidth, origHeight);
    	frame.setVisible(true);
    	createImage();
    	
	}

	private static Image createImage(){
    	BufferedImage bufferedImage = new BufferedImage(origWidth,origHeight,BufferedImage.TYPE_INT_RGB);
    	Graphics g = bufferedImage.getGraphics();
    	System.out.println("Starting drawing");
    	int[][] imgPixels = getRGB(image);
		for (int r = 0; r < imgPixels.length; r++) {
			for (int c = 0; c < imgPixels[r].length; c++) {
				bufferedImage.setRGB(c,r,imgPixels[r][c]);
			}
		}
		System.out.println("Done drawing");
		g.setColor(Color.BLUE);;
		return bufferedImage;
  }
			
	private static int[][] getRGB(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] result = new int[height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				result[row][col] = image.getRGB(col, row);
			}
		}
		return result;
	}
}
