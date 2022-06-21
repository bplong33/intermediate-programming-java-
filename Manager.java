
/**
* File:			MovieDVD.java
* Author: 	Brandon Long
* Date:			07July2021
* Class: 		CMIS242 / 6382
* Purpose:	Create Manager class for Week 8 Assignment
*/

import java.util.*;
import java.io.*;

public class Manager {
	private ArrayList<Media> mediaList;

	// Default Constructor
	public Manager() {
		this.mediaList = new ArrayList<Media>();
	}

	/**
	 * Constructor takes a string of directory as an argument. The function will
	 * search through the provided directory for files that hold Media information
	 * and add them to the Managers mediaList variable.
	 * 
	 * @param directory
	 * @throws FileNotFoundException
	 */
	public Manager(String directory) throws FileNotFoundException {
		this.mediaList = new ArrayList<Media>();

		// Create a File object for directory
		File directoryPath = new File(directory);

		// Get list of all files and directories
		File fileslist[] = directoryPath.listFiles();

		if (fileslist == null)
			throw new FileNotFoundException("Could not load, no such directory");

		// declare local variables
		Media media = null;
		String line = null;
		Scanner scan = null;

		// Process each Media file
		for (File file : fileslist) {

			// parse files whose filename starts with "EBook", "MovieDVD" or "MusicCD"
			if (file.getName().contains("EBook") || file.getName().contains("MovieDVD")
					|| file.getName().contains("MusicCD")) {

				// open and read line (assumes whole object is stored on single line)
				scan = new Scanner(file);
				line = scan.nextLine(); // assumes the file is not empty
				// System.out.println(line);

				// if EBook object than call EBook constructor
				if (file.getName().contains("EBook-"))
					media = new EBook(line);

				// if MovieDVD object than call MovieDVD constructor
				if (file.getName().contains("MovieDVD-"))
					media = new MovieDVD(line);

				// if MusicCD object than call MusicCD constructor
				if (file.getName().contains("MusicCD-"))
					media = new MusicCD(line);

				// add Pet object to pets attribute
				mediaList.add(media);
			}
		}
	}

	/**
	 * Creates media files in the provided directory for all Media objects held
	 * within the mediaList ArrayList variable.
	 * 
	 * @param directory
	 * @throws IOException
	 */
	public void createMediaFiles(String directory) throws IOException {
		PrintWriter out = null;

		// for all Pet objects create files using Pet type and id value as filename
		for (int i = 0; i < mediaList.size(); i++) {
			String filename = directory + "/" + mediaList.get(i).getClass().getSimpleName() + "-" + mediaList.get(i).getID()
					+ ".txt";
			out = new PrintWriter(new FileWriter(filename)); // create/overwrite file
			out.println(mediaList.get(i).toString()); // write the pet's data
			out.flush(); // flush all the data to the file
			out.close(); // close the stream
		}
	}

	/**
	 * Allows the user to add a Media object.
	 * 
	 * @param media
	 */
	public void addMedia(Media media) {
		mediaList.add(media);
	}

	/**
	 * Searches existing Media objects and returns a list of all objects whose title
	 * matches a given title.
	 * 
	 * @param title
	 * @return
	 */
	public ArrayList<Media> findMedia(String title) {
		ArrayList<Media> match = new ArrayList<Media>();
		// System.out.println(mediaList.size());
		for (int i = 0; i < mediaList.size(); i++) {
			Media tmp = mediaList.get(i);
			if (tmp.getTitle().toLowerCase().equals(title.toLowerCase())) {
				match.add(tmp);
			}
		}
		return match;
	}

	/**
	 * Sets the rented variable of a Media object to true, edits the media file to
	 * reflect the change, and returns the calculated price of the rental
	 * 
	 * @param id
	 * @param directory
	 * @return
	 * @throws IOException
	 */
	public double rentMedia(int id, String directory) throws IOException {
		PrintWriter out = null;

		for (int i = 0; i < mediaList.size(); i++) {
			if (mediaList.get(i).getID() == id) {
				mediaList.get(i).setRentStatus(true); // set object rented status to true
				String filename = directory + "/" + mediaList.get(i).getClass().getSimpleName() + "-" + mediaList.get(i).getID()
						+ ".txt";
				out = new PrintWriter(new FileWriter(filename)); // create/overwrite file
				out.println(mediaList.get(i).toString()); // write the pet's data
				out.flush(); // flush all the data to the file
				out.close(); // close the stream
				return mediaList.get(i).calculateRentalFee();
			}
		}

		return Double.NaN; // returned if the id was not found
	}
}
