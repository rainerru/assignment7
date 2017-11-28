package rainer_sieberer;

public class Book
{

	private String title;
	private String author;
	private int year;
	private int ISBN;

	public Book ( String newTitle, String newAuthor, int newYear, int newISBN )
	{
		this.title = newTitle;
		this.author = newAuthor;
		this.year = newYear;
		this.ISBN = newISBN;
	}

	public void setTitle( String newTitle ) { this.title = newTitle; }

	public void setAuthor( String newAuthor ) { this.author = newAuthor; }

	public void setYear( int newYear ) { this.year = newYear; }

	public void setISBN( int newISBN ) { this.ISBN = newISBN; }

	public String getTitle () { return this.title; }

	public String getAuthor () { return this.author; }

	public int getYear () { return this.year; }

	public int getISBN () { return this.ISBN; }

}
