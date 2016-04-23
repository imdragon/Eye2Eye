import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class tinker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedImage img = null;
		File file = new File("C:\\flir.jpg");
		// storing color values here
		int[][] imgPixels;

		// just checking permissions
		boolean bool = file.canExecute();
		boolean bool2 = file.getAbsoluteFile().exists();
		System.out.println("Can I read this file? " + bool);
		System.out.println("Does file exist? " + bool2);

		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("The height is: " + img.getHeight() + ", and the width is: " + img.getWidth());
		// System.out.println(getRGB(img).toString());

		imgPixels = getRGB(img);
		for (int r = 0; r < imgPixels.length; r++) {
			for (int c = 0; c < imgPixels[r].length; c++) {
//				System.out.println("Pixel at:" + r + "" + imgPixels[r] + ", Pixel at: " + c + imgPixels[c]);
				System.out.println(Arrays.toString(imgPixels[r]));
//				System.out.println("Pixel at: " + r + ", " + c + imgPixels[r][c]);
			}
		}
	}

	// extract pixels from picture
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
	// test

	public void getDimensions(BufferedImage myPic) {
		System.out.println("The height is: " + myPic.getHeight() + ", and the width is: " + myPic.getWidth());

	}
}
