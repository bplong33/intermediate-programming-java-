
/**
 * File: MediaRentalSystem.java Author: Brandon Long Date: 07July2021 Class:
 * CMIS242 / 6382 Purpose: Create MediaRentalSyste class for Week 8 Assignment.
 * Uses all Manager class to handle renting out media.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;

public class MediaRentalSystem {
	Manager manager;
	String directory;

	public MediaRentalSystem() {
		manager = null;
	}

	/**
	 * Display the menu to the user.
	 */
	private void displayMenu() {
		System.out.println("\n   Welcome to Media Rental System");
		System.out.println("1: Load Media Objects...");
		System.out.println("2: Find Media Objects...");
		System.out.println("3: Rent Media Objects...");
		System.out.println("9: Quit\n");
	} // end displayMenu

	/**
	 * Process the user's decision.
	 * 
	 * @param c
	 */
	private void processChoice(int c) {
		switch (c) {
			case (1):
				loadMedia();
				break;
			case (2):
				findMedia();
				break;
			case (3):
				rentMedia();
				break;
			case (9):
				System.out.print("Thank you for using the program. Goodbye!");
				break;
		}
	} // end processChoice

	/**
	 * Prompts the use for a directory to pull media files from. It will then load
	 * media files it discovers in this directory into the Manager.
	 */
	private void loadMedia() {
		Scanner scan = new Scanner(System.in);

		// get user input for directory
		System.out.print("Enter path (directory) where you want to load from: ");
		this.directory = scan.nextLine();

		try {
			manager = new Manager(directory);
		} catch (FileNotFoundException e) {
			System.out.println("File could be opened: " + e.getMessage());
		}
	} // end loadMedia

	/**
	 * This function will find all media matching a given title and then print it
	 * all to the console for the user.
	 */
	private void findMedia() {
		Scanner scan = new Scanner(System.in);

		// get user input for title
		System.out.print("Enter the title: ");
		String title = scan.nextLine();

		ArrayList<Media> matches = manager.findMedia(title);
		if (matches.size() > 0) { // if something matched.
			for (Media match : matches) {
				match.display();
				System.out.println("");
			}
		} else { // there were no matches.
			System.out.println("There is no media with this title: " + title);
		}
	} // end findMedia

	/**
	 * Allows the user to set the rental status of a Media Object to true, based on
	 * ID.
	 */
	private void rentMedia() {
		Scanner scan = new Scanner(System.in);
		double price;
		System.out.print("Enter the id: ");
		if (scan.hasNextInt()) {
			int id = scan.nextInt();
			try {
				price = manager.rentMedia(id, directory);
				if (Double.isNaN(price)) { // == Double.NaN
					System.out.println("The media object id=" + id + " is not found.");
				} else {
					System.out.printf("Media was successfully rented. Rental fee = $%.2f\n", price);
				}
			} catch (IOException e) {
				System.out.println("The media object id=" + id + " is not found.");
			}
		} else {
			System.out.println("Please enter an integer for the ID value.");
		}
	} // end rentMedia

	/**
	 * Main method, loops through menu and other user prompts until the user chooses
	 * to exit.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Print course header
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("Name: Long, Brandon\tCMIS 242/6382\tDate: " + ft.format(dNow) + "\n");

		Scanner scanIn = new Scanner(System.in);
		MediaRentalSystem handler = new MediaRentalSystem();
		int answer;

		do {
			handler.displayMenu();
			System.out.print("Enter your selection : ");
			if (scanIn.hasNextInt()) {
				answer = scanIn.nextInt();
				System.out.println("");
				handler.processChoice(answer);
			} else {
				answer = 0;
				scanIn.nextLine();
				System.out.println("\nThis is not a valid choice. Please enter a valid menu option.");
			}
		} while (answer != 9);

		scanIn.close();
	} // end main
} // end MediaRentalSystem class