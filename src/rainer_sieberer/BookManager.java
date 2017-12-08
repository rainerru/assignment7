package rainer_sieberer;

import java.util.Optional;
import java.lang.IllegalArgumentException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BookManager extends Application implements CustomObserver{

	BookList model = new BookList();
	ObservableList<String> myList = FXCollections.observableArrayList();
	ListView<String> listView = new ListView<>(myList);

	static final String[] descriptionSmall = { "title", "author", "year", "ISBN" };
	static final String[] descriptionBig = { "Title", "Author", "Year", "ISBN" };

	public static void main(String[] args) { launch(args); }

	@Override
	public void start(final Stage stage) throws Exception
	{
		Exception e = new IllegalArgumentException();
		this.model.setObserver(this);

		BorderPane root = new BorderPane();
		Button cmdAdd = new Button("Add");
		cmdAdd.setOnAction(new AddHandler());
		
		Button cmdRemove = new Button("Remove");
		cmdRemove.setOnAction(new RemoveHandler());

		Button cmdGetInfo = new Button("Get Info");
		cmdGetInfo.setOnAction(new GetInfoHandler());

		ToolBar toolBar = new ToolBar(cmdAdd,cmdRemove,cmdGetInfo);
		root.setTop(toolBar);
		root.setCenter(listView);
		Scene scene = new Scene(root, 600, 800);
		stage.setScene(scene);
		stage.show();
	}

	// Handlers for the buttons:

	private class AddHandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent event)
		{ 
			String title, author;
			Integer year, isbn;
			TextInputDialog dialog;

			Object[] input = new Object[4];

			try
			{
				for ( int i = 0; i < 4; i++ )
				{
					dialog = new TextInputDialog();

					dialog.setTitle("Adding new book");
					dialog.setHeaderText("Enter the " + descriptionSmall[i] +" of the book:");
					dialog.setContentText(descriptionBig[i] + ": ");

					Optional<String> result = dialog.showAndWait();
					if (result.isPresent() && !result.get().equals("") )
					{
							if ( i > 1 )
								input[i] = Integer.parseInt( result.get() );
							else input[i] = result.get();
					} else
					{
						throw new IllegalArgumentException();
					}
				}

				Book newBook = new Book(
					(String) input[0], (String) input[1], (Integer) input[2], (Integer) input[3]);
				model.add(newBook);
			} catch ( IllegalArgumentException e )
			{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Error while adding a book");
					alert.setContentText("Invalid input for a book or canceled!");

					alert.showAndWait();
			}
		}

	}

	private class RemoveHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			if ( !(listView.getSelectionModel().getSelectedItem() == null) )
			{
				int selectedBook = Integer.parseInt( listView.getSelectionModel().getSelectedItem() );
				model.remove(selectedBook);
			}
		}
	}

	private class GetInfoHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			if ( !(listView.getSelectionModel().getSelectedItem() == null) )
			{
				int selectedTitle = Integer.parseInt( listView.getSelectionModel().getSelectedItem() );
				Book selectedBook = model.search(selectedTitle);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Book details");
				alert.setHeaderText("Title: " + selectedBook.getTitle());
				alert.setContentText("Author: " + selectedBook.getAuthor()
					+ "\nYear: " + selectedBook.getYear()
					+	"\nISBN: " + selectedBook.getISBN());

				alert.showAndWait();
			}
		}
	}

	// Handlers for events:

	public void update ( CustomAddEvent<Book> event )
	{
		if ( this.model == event.getSource() )
			myList.add( Integer.toString( event.getTarget().getISBN() ) );
	}
	
	public void update ( CustomRemoveEvent<Book> event )
	{
		if ( this.model == event.getSource() )
			myList.remove( Integer.toString( event.getTarget().getISBN() ) );
	}

}
