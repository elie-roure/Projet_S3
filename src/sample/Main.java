package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Aleatoire al = new Aleatoire(4, 10);
        System.out.println(al.donneRandom());
        System.out.println(al.donneRandom());
        System.out.println(al.donneRandom());
        System.out.println(al.donneRandom());

    }


    public static void main(String[] args) {
        launch(args);
    }
}
