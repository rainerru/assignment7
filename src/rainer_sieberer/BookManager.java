package rainer_sieberer;

import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BookManager extends Application {

	ObservableList<Object> myList = FXCollections.observableArrayList(); //TODO adapt type to your needs
	ListView<Object> listView = new ListView<>(myList); //TODO adapt type to your needs

	public static void main(String[] args) { launch(args); }

	@Override
	public void start(final Stage stage) throws Exception
	{
		

		BorderPane root = new BorderPane();
		Button cmdAdd = new Button("Add");
		cmdAdd.setOnAction(new AddHandler());
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

		Button cmdRemove = new Button("Remove");
		cmdRemove.setOnAction(new EventHandler<ActionEvent>()
		{  //Option1: as anonymous class (see below)
			@Override
			public void handle(ActionEvent event)
			{
				//TODO
				//TODO 1) use a nice title and message for the dialog. 
				//     2) showAndWait returns an Optional<String> (handle 'Cancel' properly)
				///String input = new TextInputDialog().showAndWait().orElse(null); 
				Object selectedBook = listView.getSelectionModel().getSelectedItem();
				myList.remove(selectedBook);
				//here we directly add it to the list; 
				//don't do this for the assignment. TODO: modify your 'real' model (add a new entry).
				///myList.add(input);
			}
		});

		ToolBar toolBar = new ToolBar(cmdAdd,cmdRemove);
		root.setTop(toolBar);
		root.setCenter(listView);
		Scene scene = new Scene(root, 200, 200);
		stage.setScene(scene);
		stage.show();
	}

	//TODO
	//TODO
	//TODO ;-)

	//TODO: this class should be an Observer of your model. 
	//      in case you get a notification that the model has changed, update the list of your listView (i.e., myList)



	//Handlers for the Button: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// Option 2: as extra class

	//cmdAdd.setOnAction(new AddHandler());

	private class AddHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event)
		{ 
			TextInputDialog dialog = new TextInputDialog();
 
			dialog.setTitle("Adding new book");
			dialog.setHeaderText("Enter your name:");
			dialog.setContentText("Name:");

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


			dialog = new TextInputDialog();
 
			dialog.setTitle("Adding new book");
			dialog.setHeaderText("Enter your name2222:");
			dialog.setContentText("Name:");

			//TODO
			//TODO 1) use a nice title and message for the dialog. 
			//     2) showAndWait returns an Optional<String> (handle 'Cancel' properly)
			String input2 = dialog.showAndWait().orElse(null); 
			//here we directly add it to the list; 
			//don't do this for the assignment. TODO: modify your 'real' model (add a new entry).
			myList.add(input2);
		}

	}
	

	/* Option 3: lambda

	cmdAdd.setOnAction(e -> {
	//System.out.println("bla");
	});
	*/
}
