
/**
* File:			MusicCD.java
* Author: 	Brandon Long
* Date:			07July2021
* Class: 		CMIS242 / 6382
* Purpose:	Create MusicCD class for Week 8 Assignment
*/

import java.util.Calendar;

public class MusicCD extends Media {
	private int lengthInMinutes;

	// constructor
	public MusicCD(int id, String title, int yearPublished, int minutes) {
		super(id, title, yearPublished);
		this.lengthInMinutes = minutes;
	}

	public MusicCD(String line) {
		super(line);
		lengthInMinutes = Integer.parseInt(line.substring(line.indexOf("<length>") + 8, line.indexOf(" minutes</length>")));
	}

	// mutators
	public void setLength(int minutes) {
		this.lengthInMinutes = minutes;
	}

	// accessor
	public int getLength() {
		return this.lengthInMinutes;
	}

	public void display() {
		System.out.printf("MusicCD [ ID=%d, Title=%s, Year=%d, Length=%dmin ]", super.getID(), super.getTitle(),
				super.getYearPublished(), this.lengthInMinutes);
	}

	@Override
	public double calculateRentalFee() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (super.getYearPublished() == year) {
			return (this.lengthInMinutes * 0.02) + 1;
		}
		return 3.5;
	}

	public String toString() {
		String tmp = "<MusicCD>" + "<id>" + this.getID() + "</id>" + "<title>" + this.getTitle() + "</title>"
				+ "<yearPublished>" + this.getYearPublished() + "</yearPublished>" + "<length>" + this.getLength()
				+ " minutes</length>" + "<rented>" + this.getRentStatus() + "</rented>" + "</MusicCD>";
		return tmp;
	}
}