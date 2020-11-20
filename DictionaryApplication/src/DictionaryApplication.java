import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DictionaryApplication extends Application implements Initializable{
	
	@FXML
	Button lookupButton;
	
	@FXML
	Button clearButton;
	
	@FXML
	TextField wordField;
	
	@FXML
	TextArea definitionArea;
	
	@FXML
	Pane bouncyPane;
	
	@FXML
	Rectangle bouncyRectangle;
	
	WordInformation wi = new WordInformation();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/dictionaryApplication.fxml"));
		final Pane p = loader.load();
		Scene scene = new Scene(p);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bouncyPane.getChildren().add(bouncyRectangle);
		bouncyRectangle.relocate(0, 0);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
			double dx = 20;
			double dy = 20;              

			@Override
			public void handle(ActionEvent t) {
				bouncyRectangle.setLayoutX(bouncyRectangle.getLayoutX() + dx);
				bouncyRectangle.setLayoutY(bouncyRectangle.getLayoutY() + dy);
				Bounds bounds = bouncyPane.getLayoutBounds();
				if (bouncyRectangle.getLayoutX() <= bounds.getMinX()
						|| bouncyRectangle.getLayoutX() >= bounds.getMaxX()) {
					dx = -dx;
				}
				if ((bouncyRectangle.getLayoutY() >= bounds.getMaxY())
						|| (bouncyRectangle.getLayoutY() <= bounds.getMinY())) {
					dy = -dy;
				}
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		bouncyPane.setOnMouseClicked(event -> {
			bouncyRectangle.setLayoutX(event.getX());
			bouncyRectangle.setLayoutY(event.getY());
		});
		
		lookupButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timeline.play();				
				String definition = wi.lookup(wordField.getText());
				definitionArea.setText(definition);
			}
		});
		
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timeline.stop();
				wordField.setText("");
				definitionArea.setText("");
			}
		});

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
