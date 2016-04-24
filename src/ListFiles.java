import java.io.File;
import java.util.ArrayList;

public class ListFiles {

	public static void main(String[] args) {
		int currentCount = 0;
		ArrayList<File> fileStack = new ArrayList<>();
		File[] listOfFilesOld;
		File[] listOfFilesNew;
		File files;
		File folder;
		String newFileName = null;

		String path = "C:\\Users\\Admin\\Dropbox\\TESTING";

		folder = new File(path);
		listOfFilesOld = folder.listFiles();

		do {
			String path1 = "C:\\Users\\Admin\\Dropbox\\TESTING";

			folder = new File(path1);
			listOfFilesNew = folder.listFiles();

			for (int i = 0; i < listOfFilesOld.length; i++) {
				if (listOfFilesOld[i].equals(listOfFilesNew[i])) {
				} else {
					newFileName = listOfFilesNew[i].getAbsolutePath();
					System.out.println(listOfFilesNew[i].getAbsoluteFile().exists());
					System.out.println(newFileName);
					break;
				}
			}

		} while (listOfFilesOld.length == listOfFilesNew.length);
	}
}