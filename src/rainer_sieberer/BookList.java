package rainer_sieberer;

import java.util.LinkedList;

public class BookList
{

	protected LinkedList<Book> listOfBooks;

	public BookList ()
	{
		this.listOfBooks = new LinkedList<Book>();
	}

	public boolean addBook ( Book newBook )
	{
		if ( this.listOfBooks.indexOf(newBook) == -1 ) // -1 means, it's not present in the list
			return this.listOfBooks.add( newBook );
		else
			return false;
	}

	public boolean removeBook ( Book oldBook ) { return this.listOfBooks.remove( oldBook ); }

	public Book search ( String title )
	{
		for ( Book currentBook: this.listOfBooks )
		{
			if ( currentBook.getTitle() == title )
				return currentBook;
		}

		return null;
	}

}
