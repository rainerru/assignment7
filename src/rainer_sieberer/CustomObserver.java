package rainer_sieberer;

public interface CustomObserver
{

	public void update ( CustomAddEvent<Book> event );

	public void update ( CustomRemoveEvent<Book> event );

}
