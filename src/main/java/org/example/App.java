package org.example;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * JavaFX App
 */
public class App extends Application {

    private final static int SCENE_HEIGHT = 500;
    private final static int SCENE_WIDTH = 500;

    @Override
    public void start(Stage stage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, SCENE_HEIGHT, SCENE_WIDTH);

        // Change the background
        BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, null, null);
        Background background = new Background(backgroundFill);

        pane.setBackground(background);
        Circle circle = new Circle(20, Color.RED);
        circle.relocate(10,10);
        pane.getChildren().add(circle);

        // Create a Label for the title
        Label titleLabel = new Label("Welcome to JavaFX");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Customize the font and size
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);
        pane.getChildren().add(titleLabel);

        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), new EventHandler<ActionEvent>() {
            double dx = 2;
            double dy = 5;
            @Override
            public void handle(ActionEvent actionEvent) {
                circle.setLayoutX(circle.getLayoutX() + dx);
                circle.setLayoutY(circle.getLayoutY() + dy);

                Bounds bounds = pane.getBoundsInLocal();

                if (circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius()) ||
                        circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius())) {
                    dx = -dx;

                    // change the circle color when it hits a certain corner
                    if (circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius())) {
                        circle.setFill(Color.GREEN);
                    } else {
                        circle.setFill(Color.BLUE);
                    }
                }

                if(circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius()) ||
                        circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius())) {
                    dy = -dy;
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}