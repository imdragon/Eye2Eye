import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class canvas extends JPanel{
	
	static int origHeight;
    static int origWidth;
	static BufferedImage image = null;
	
	public static void main(String[] args) {

  //import the picture store it 
      BufferedImage img = null;
	  File file = new File("flir1.jpg");
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
      JFrame frame = new JFrame();
      Image origImage = createImage();
      Image cropImage = createCrop();
      Image partImage = createPartition();
    	frame.getContentPane().add(new JLabel(new ImageIcon(partImage)));
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // set picture height width
    	frame.setSize(origWidth, origHeight);

    	frame.setVisible(true);
	}

	private static Image createImage(){
    	BufferedImage bufferedImage = new BufferedImage(origWidth,origHeight,BufferedImage.TYPE_INT_RGB);
    	Graphics g = bufferedImage.getGraphics();
    	int[][] imgPixels = getRGB(image);
		for (int r = 0; r < imgPixels.length; r++) {
			for (int c = 0; c < imgPixels[r].length; c++) {
				int rgb = imgPixels[r][c];
				if (rgb > 20) {
					rgb += 255;
				} else {
					rgb = 0;
				}
				bufferedImage.setRGB(c,r,rgb);
			}
		}
		return bufferedImage;
  }
			
	private static Image createCrop(){
    	BufferedImage bufferedImage = new BufferedImage(origWidth,origHeight,BufferedImage.TYPE_INT_RGB);
    	Graphics g = bufferedImage.createGraphics();
        for(int i=0;i<21;i++){
            // unless divided by some factor, these lines were being
            // drawn outside the bound of the image..
        }
    	int[][] imgPixels = getRGB(image);
		for (int r = 160; r <= 320 ; r++) {
			for (int c = 0; c < imgPixels[r].length; c++) {
				bufferedImage.setRGB(c,r,imgPixels[r][c]);
			}
		}
		return bufferedImage;
  }
	
	private static Image createPartition(){
    	BufferedImage bufferedImage = new BufferedImage(origWidth,origHeight,BufferedImage.TYPE_INT_RGB);
    	Graphics g = bufferedImage.getGraphics();
    	int[][] imgPixels = getRGB(image);
    	int leftSide = 0;
    	int otherSide = 0;
    	int rightSide = 0;
    	int hotLeft = 0;
    	int hotRight = 0;
    	int[][] redPixels = getReds(image);
		for (int r = 160; r <= 320 ; r++) {
			for (int c = 0; c < 480; c++) {
				Color mycolor = new Color(image.getRGB(c, r));
				int red = mycolor.getRed();
				if (c <= 188) {
					leftSide += red;
					hotLeft += 1;
				} else if (c >= 293) {
					otherSide += red;						
					hotRight += 1;
				}
			}
		}
		int meanLeft = leftSide/hotLeft;
		int meanRight = otherSide/hotRight;
    	int xAvgLeft = 0;
    	int yAvgLeft = 0;
    	int xAvgRight = 0;
    	int yAvgRight = 0;
    	int xCount = 0;
    	int yCount = 0;
		for (int r = 160; r <= 320 ; r++) {
			for (int c = 0; c < 480; c++) {
				Color mycolor = new Color(image.getRGB(c, r));
				int red = mycolor.getRed();
				if (c <= 188) {
					if (meanLeft == red) {
						System.out.println("x: " + c + "y: " + r);
						xAvgLeft += c;
						yAvgLeft += r;
						xCount += 1;
					}
				} else if (c >= 293) {
					if (meanRight == red) {
						xAvgRight += c;
						yAvgRight += r;
						yCount += 1;
					}
				}
			}
		}
		int avgX = xAvgLeft/xCount;
		int avgY = yAvgLeft/xCount;
		int avgXr = xAvgRight/yCount;
		int avgYr = yAvgRight/yCount;
		System.out.println(avgX);
		System.out.println(avgY);	
		
		for (int r = 160; r <= 320 ; r++) {
			for (int c = 0; c < imgPixels[r].length; c++) {
				bufferedImage.setRGB(c,r,imgPixels[r][c]);
			}
		}
		g.drawLine(avgX, avgY, avgXr, avgY);
		g.drawLine(240 - 20, avgY - 30, 240, avgY - 30);
		g.drawArc(avgX + 20, avgY - 60, 100, 75, 225, 90);
		g.drawArc(avgXr - 90, avgY - 60, 100, 75, 225, 90);	
		return bufferedImage;
  }
	private static int[][] getRGB(BufferedImage image) {
		int[][] result = new int[origHeight][origWidth];

		for (int row = 0; row < origHeight; row++) {
			for (int col = 0; col < origWidth; col++) {
				result[row][col] = image.getRGB(col, row);
			}
		}
		return result;
	}

	private static int[][] getReds(BufferedImage image) {
		int[][] result = new int[160][480];

		for (int r = 160; r <= 320 ; r++) {
			for (int c = 0; c < 480; c++) {
				Color mycolor = new Color(image.getRGB(c, r));
				int red = mycolor.getRed();
			}
		}
		return result;
	}
}

