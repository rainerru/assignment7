package rainer_sieberer;

import java.util.LinkedList;

import javafx.application.Application;

public class BookList
{

	protected LinkedList<Book> listOfBooks;
	protected CustomObserver observer;

	public BookList () { this.listOfBooks = new LinkedList<Book>(); }

	public void setObserver ( CustomObserver newObserver ) { this.observer = newObserver; }

	// public void notifyObserver ( CustomEvent<Book> event ) { this.observer.update(event); }

	public void notifyObserver ( CustomAddEvent<Book> event )
	{
		if ( this.observer != null )
			this.observer.update(event);
	}

	public void notifyObserver ( CustomRemoveEvent<Book> event )
	{
		if ( this.observer != null )
		this.observer.update(event);
	}


	public boolean add ( Book newBook )
	{
		if ( this.search( newBook.getISBN() ) == null )
		{
			if ( this.listOfBooks.add( newBook ) == true )
			{
				notifyObserver( new CustomAddEvent<Book>( this, newBook ) );
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	public boolean remove ( Book oldBook )
	{
		if ( this.listOfBooks.remove( oldBook ) == true )
		{
			notifyObserver( new CustomRemoveEvent<Book>( this, oldBook ) );
			return true;
		}
		else
			return false;
	}

	public boolean remove ( String title )
	{
		return this.remove( this.search(title) );
	}

	public boolean remove ( int isbn )
	{
		return this.remove( this.search(isbn) );
	}

	public Book search ( String title ) // this allows editing the books
	{
		for ( Book currentBook: this.listOfBooks )
		{
			if ( currentBook.getTitle().equals( title ) )
			{
				return currentBook;
			}
		}
		return null;
	}

	public Book search ( int isbn ) // this allows editing the books
	{
		for ( Book currentBook: this.listOfBooks )
		{
			if ( currentBook.getISBN() == isbn )
			{
				return currentBook;
			}
		}
		return null;
	}

}
