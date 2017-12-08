package rainer_sieberer;

import java.util.Optional;

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

	public static void main(String[] args) { launch(args); }

	@Override
	public void start(final Stage stage) throws Exception
	{
		this.model.setObserver(this);

		model.add(new Book("LOTR1","Tolkien",1990,1));
		model.add(new Book("LOTR2","Tolkien",1991,2));
		model.add(new Book("LOTR3","Tolkien",1992,3));
		model.add(new Book("LOTR1","Tolkien",1990,4));

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

	//TODO
	//TODO
	//TODO ;-)

	//TODO: this class should be an Observer of your model. 
	//      in case you get a notification that the model has changed, update the list of your listView (i.e., myList)



	// Handlers for the Button:

	private class AddHandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent event)
		{ 
			String title, author;
			int year, isbn;
			TextInputDialog dialog;
 
			dialog = new TextInputDialog();
 
			dialog.setTitle("Adding new book");
			dialog.setHeaderText("Enter the title of the book:");
			dialog.setContentText("Title:");

			title = dialog.showAndWait().orElse(null); 

			dialog = new TextInputDialog();
 
			dialog.setTitle("Adding new book");
			dialog.setHeaderText("Enter the author of the book:");
			dialog.setContentText("Author:");

			author = dialog.showAndWait().orElse(null); 

			dialog = new TextInputDialog();
 
			dialog.setTitle("Adding new book");
			dialog.setHeaderText("Enter the year of the book:");
			dialog.setContentText("Year:");

			year = Integer.parseInt( dialog.showAndWait().orElse(null) ); 

			dialog = new TextInputDialog();
 
			dialog.setTitle("Adding new book");
			dialog.setHeaderText("Enter the ISBN of the book (only numbers):");
			dialog.setContentText("ISBN:");

			isbn = Integer.parseInt( dialog.showAndWait().orElse(null) ); 

			Book newBook = new Book(title,author,year,isbn);
			model.add(newBook);
		}

	}

	private class RemoveHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			int selectedBook = Integer.parseInt( listView.getSelectionModel().getSelectedItem() );
			model.remove(selectedBook);
		}
	}

	private class GetInfoHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
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

//TODO
			//TODO 1) use a nice title and message for the dialog. 
			//     2) showAndWait returns an Optional<String> (handle 'Cancel' properly)
			/*Optional<String> input = dialog.showAndWait(); 
			input.ifNotPresent(() -> {myList.add("no input");} );
			if ( input.isPresent() )
			{
				String input1 = input.get();
				myList.add(input1);
			} else 
			{
				myList.add("no input");
			}*/
			/*input.ifPresent(value -> {
				myList.add(value);
			}).ifNotPresent(() -> {
				System.out.println("! isPresent");
			});*/
			//here we directly add it to the list; 
			//don't do this for the assignment. TODO: modify your 'real' model (add a new entry).




//TODO
			//TODO 1) use a nice title and message for the dialog. 
			//     2) showAndWait returns an Optional<String> (handle 'Cancel' properly)
//			title = dialog.showAndWait().orElse(null); 
			//here we directly add it to the list; 
			//don't do this for the assignment. TODO: modify your 'real' model (add a new entry).





/*cmdAdd.setOnAction(new EventHandler<ActionEvent>()
		{  //Option1: as anonymous class (see below)
			@Override
			public void handle(ActionEvent event)
			{
				//TODO
				//TODO 1) use a nice title and message for the dialog. 
				//     2) showAndWait returns an Optional<String> (handle 'Cancel' properly)
				String input = new TextInputDialog().showAndWait().orElse(null); 
				//here we directly add it to the list; 
				//don't do this for the assignment. TODO: modify your 'real' model (add a new entry).
				myList.add(input);
			}
		});*/
