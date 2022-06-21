
/**
* File:			EBook.java
* Author: 	Brandon Long
* Date:			07July2021
* Class: 		CMIS242 / 6382
* Purpose:	Create EBook class for Week 8 Assignment
*/

import java.util.Calendar;

public class EBook extends Media {
	private int numberOfChapters;

	// Constructor
	public EBook(int id, String title, int yearPublished, int numberOfChapters) {
		super(id, title, yearPublished);
		this.numberOfChapters = numberOfChapters;
	}

	public EBook(String line) {
		super(line);
		numberOfChapters = Integer.parseInt(line.substring(line.indexOf("<chapters>") + 10, line.indexOf("</chapters>")));
	}

	// mutators
	public void setNumOfChapters(int num) {
		this.numberOfChapters = num;
	}

	// accessors
	public int getNumOfChapters() {
		return this.numberOfChapters;
	}

	public void display() {
		System.out.printf("EBook [ id=%d, title=%s, year=%d, chapters=%d, available=%b ]", super.getID(), super.getTitle(),
				super.getYearPublished(), this.numberOfChapters, super.getRentStatus());
	}

	@Override
	public double calculateRentalFee() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (super.getYearPublished() == year) {
			return (this.numberOfChapters * 0.1) + 1;
		}
		return 3.5;
	}

	public String toString() {
		String tmp = "<EBook>" + "<id>" + this.getID() + "</id>" + "<title>" + this.getTitle() + "</title>"
				+ "<yearPublished>" + this.getYearPublished() + "</yearPublished>" + "<chapters>" + this.getNumOfChapters()
				+ "</chapters>" + "<rented>" + this.getRentStatus() + "</rented>" + "</Ebook>";
		return tmp;
	}
}