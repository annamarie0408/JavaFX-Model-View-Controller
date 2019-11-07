//Anna Collins
//8/22/19
//Java Advanced Assignment 1

//I have abide by GTCC Academic Integrity Policy and/or
//that the program is my original work

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import java.awt.image.ImageFilter;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;



public class CollinsMain extends Application {

    public void start(Stage primaryStage) throws FileNotFoundException {

        ArrayList<CollinsBook> arrBookList = new ArrayList<>();

      //  String[] ImagePath = {};

        try { //starting a try, this way the program still executes if the file is missing

            File fileinput = new File("src/CollinsBookData"); //defining the filepath

            Scanner sc = new Scanner(fileinput); //starting scanner


            while (sc.hasNextLine()) { //This will continue till there is no next line

                //defining variables/attributes and reading through the text file and assigning to the right thing
                //this is defined by the space between the items.
                String publisher = sc.next();
                String title = sc.next();
                String ISBN = sc.next();
                String imageName = sc.next();
                Double price = sc.nextDouble();


                //Instantiating the object
                try {
                    CollinsBook test = new CollinsBook(publisher, title, ISBN, imageName, price);
                    arrBookList.add(test); //assigning the newly created object to the ArrayList


                } catch (CollinsInvalidISBN message) {
                    System.out.println("The following Books & ISBN do not work! Book Title: " + title + ", ISBN: " + ISBN);

                }

            }

            //Reads through the Arraylist arrBookList and prints out each line
            //using the toString override method in the CollinsBook Class
            System.out.println("\nHere are the books in the BookArrayList that have a valid ISBN:\n");
            for (CollinsBook book : arrBookList) {
                System.out.println(book);

            }

            ListView<String> bookListview = new ListView<>();
            for (CollinsBook b: arrBookList) {
                bookListview.getItems().add(b.getTitle());
            }


// Create a pane to hold image views
            FlowPane imagePane = new FlowPane(10, 10);
            HBox hTop = new HBox();
            HBox hBottom = new HBox();
            hTop.setAlignment(Pos.CENTER);
            hBottom.setAlignment(Pos.CENTER);

            BorderPane pane = new BorderPane();
            pane.setLeft(new ScrollPane(bookListview));
            pane.setBottom(hBottom);
            pane.setTop(hTop);
            pane.setCenter(imagePane);

            pane.setStyle("-fx-background-color: #d3ecff;");



            bookListview.getSelectionModel().selectedItemProperty().addListener(
                    ov -> {
                        int index = bookListview.getSelectionModel().getSelectedIndex();
                        CollinsBook x = arrBookList.get(index);

                        imagePane.getChildren().clear();
                        hTop.getChildren().clear();
                        hBottom.getChildren().clear();

                        ImageView imageView = new ImageView(x.getImageName());
                        imageView.setFitWidth(350);
                        imageView.setFitHeight(550);
                        imagePane.getChildren().add(imageView);

                        Label topLabel = new Label(x.getTitle());
                        Label botLabel = new Label(x.getISBN());
                        hTop.getChildren().add(topLabel);
                        hBottom.getChildren().add(botLabel);

                    });

            // Create a scene and place it in the stage
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Java Books"); // Set the stage title
            primaryStage.setScene(scene); // Place the scene in the stage
            primaryStage.setHeight(650);
            primaryStage.setWidth(650);


            primaryStage.show(); // Display the stage



        } catch (FileNotFoundException e) { //if the file is not found it will through this exception adn print it to the console
            System.out.println(e.toString());
        }
    }
}

// Define a custom pane to hold a label in the center of the pane
class CustomPane extends StackPane {
    public CustomPane(String title) {
        getChildren().add(new Label(title));
        setStyle("-fx-border-color: pink");
        setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    }

}

