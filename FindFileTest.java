import java.io.*;
import java.util.*;

public class FindFileTest {
	public static void main(String[] args) {
		String workingDirectory = "C:/Users/bplon/Documents/programs/CMIS242/Week 8/Assignment/MediaFiles";

		Manager manager = new Manager();

		manager.addMedia(new EBook(1, "Testing", 2021, 500));
		manager.addMedia(new MusicCD(2, "tryhard", 2018, 38));
		manager.addMedia(new MovieDVD(3, "Black Widow", 2021, 154.5));
		manager.addMedia(new EBook(123, "Forever Young", 2018, 20));
		manager.addMedia(new MovieDVD(126, "Forever Young", 2021, 140));

		try {
			manager.createMediaFiles(workingDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Manager manager2 = null;
		try {
			manager2 = new Manager(workingDirectory);
		} catch (FileNotFoundException e) {
			System.out.print("Error: " + e);
			e.printStackTrace();
		}
		ArrayList<Media> matchList = manager2.findMedia("Testing");
		for (int i = 0; i < matchList.size(); i++) {
			System.out.println(matchList.get(i).toString());
		}
	}
}
