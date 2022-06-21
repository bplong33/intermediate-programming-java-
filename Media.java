/* 
* File:			Media.java
* Author: 	Brandon Long
* Date:			07July2021
* Class: 		CMIS242 / 6382
* Purpose:	Create media class for Week 8 Assignment
*/

public abstract class Media {
	private int id;
	private String title;
	private int yearPublished;
	private boolean isRented;

	// constructor
	public Media(int id, String title, int yearPublished) {
		this.id = id;
		this.title = title;
		this.yearPublished = yearPublished;
		this.isRented = false;
	}

	public Media(String line) {
		id = Integer.parseInt(line.substring(line.indexOf("<id>") + 4, line.indexOf("</id>")));
		title = line.substring(line.indexOf("<title>") + 7, line.indexOf("</title>"));
		yearPublished = Integer
				.parseInt(line.substring(line.indexOf("<yearPublished>") + 15, line.indexOf("</yearPublished>")));
		isRented = Boolean.parseBoolean(line.substring(line.indexOf("<rented>") + 8, line.indexOf("</rented>")));
	}

	// mutator
	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeYearPublished(int year) {
		this.yearPublished = year;
	}

	public void setRentStatus(boolean rented) {
		this.isRented = rented;
	}

	// accessors
	public int getID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean getRentStatus() {
		return this.isRented;
	}

	public int getYearPublished() {
		return this.yearPublished;
	}

	// other methods
	public double calculateRentalFee() {
		return 3.5;
	}

	protected abstract void display();
}