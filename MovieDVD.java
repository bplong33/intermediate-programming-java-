
/**
* File:			MovieDVD.java
* Author: 	Brandon Long
* Date:			07July2021
* Class: 		CMIS242 / 6382
* Purpose:	Create MovieDVD class for Week 8 Assignment
*/

import java.util.Calendar;

public class MovieDVD extends Media {
	private double sizeInMegabytes;

	// constructor
	public MovieDVD(int id, String title, int yearPublished, double mb) {
		super(id, title, yearPublished);
		this.sizeInMegabytes = mb;
	}

	public MovieDVD(String line) {
		super(line);
		sizeInMegabytes = Double.parseDouble(line.substring(line.indexOf("<size>") + 6, line.indexOf("MB</size>")));
	}

	// mutators
	public void setSize(int mb) {
		this.sizeInMegabytes = mb;
	}

	// accessor
	public double getSize() {
		return this.sizeInMegabytes;
	}

	public void display() {
		System.out.printf("MovieDVD [ id=%d, title=%s, year=%d, size=%.1fMB, available=%b ]", super.getID(),
				super.getTitle(), super.getYearPublished(), this.sizeInMegabytes, super.getRentStatus());
	}

	public String toString() {
		String tmp = "<MovieDVD>" + "<id>" + this.getID() + "</id>" + "<title>" + this.getTitle() + "</title>"
				+ "<yearPublished>" + this.getYearPublished() + "</yearPublished>" + "<size>" + this.getSize() + "MB</size>"
				+ "<rented>" + this.getRentStatus() + "</rented>" + "</MovieDVD>";
		return tmp;
	}
}