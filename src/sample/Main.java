package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("MAP");

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);

        MapProcedurale map = new MapProcedurale(20,20, 0);
        root.getChildren().add(map);

        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
