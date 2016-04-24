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

public class canvas extends JPanel {

	static int origHeight;
	static int origWidth;
	static BufferedImage image = null;

	public static void main(String[] args) {

		// import the picture store it
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
		JFrame frame = new JFrame();
		Image ourImage = createImage();
		frame.getContentPane().add(new JLabel(new ImageIcon(ourImage)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set picture height width
		frame.setSize(origWidth, origHeight);

		frame.setVisible(true);
	}

	private static Image createImage() {
		BufferedImage bufferedImage = new BufferedImage(origWidth, origHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.getGraphics();
		int[][] imgPixels = getRGB(image);
		int count = 0;
		int[] sections = {187, 0, 0};
		for (int r = 0; r < imgPixels.length; r++) {
			for (int c = 292; c < imgPixels[r].length; c++) {
				/* code to figure out where to split the image
				 * first section until rows 0-187
				 * second section 188-292
				 * third section 293-length
				 * 
				 */
//					int lastPixel = imgPixels[187][220];
//				if (lastPixel == imgPixels[r][c]) {
//					bufferedImage.setRGB(c, r, imgPixels[r][c]);
//				} else {
//					System.out.println("Column: "+c+"is where we split!");
//					count = c;
//					break;
//				} 
				bufferedImage.setRGB(c, r, imgPixels[r][c]);

				
				 //				bufferedImage.setRGB(c, r, makeGrey(imgPixels[r][c]));
			}
		}
		System.out.println("this is called");System.out.println(count);
		return bufferedImage;
	}
	
	

	public static int makeGrey(int color) {
		int newColor = 0;
		String colorString = String.valueOf(color);
		String temp = colorString.substring(1, 2);
		if (Integer.parseInt(temp) < 66) {
			newColor = Integer.parseInt("0" + temp + "2222");

		} else {
		}

		return newColor;
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
}