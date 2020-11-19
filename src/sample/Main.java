package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

	public static Group root;
	public static Scene scene;
	public static Canvas canvas  = new Canvas(5000,5000);;
	public static GraphicsContext gc = canvas.getGraphicsContext2D();

	public static Stage primaryStage2;

	public static int hauteurEcran = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int largeurEcran = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();



	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage2 = primaryStage;
		gc.setFill(Color.BLUE);
		gc.fillRect(0,0,0.66*largeurEcran ,0.90*hauteurEcran);

		primaryStage.setTitle("Génération");


		root = new Group();

		scene = new Scene(root, largeurEcran, hauteurEcran);

		primaryStage.setFullScreen(true);


		//On creer un nouveau stage ou sera afficher la carte uniquement pour qu'on puisse la modifier en directe avec l'autre stage
		Stage mapStage = new Stage();
		mapStage.setTitle("Map");
		Group mapGroup = new Group();
		Scene mapScene = new Scene(mapGroup,500,500);
		mapStage.setScene(mapScene);

		InterfaceJoueur interfaceJoueur = new InterfaceJoueur();
		interfaceJoueur.demarrage(root, mapGroup, mapStage);
		interfaceJoueur.deZoom(root);
		interfaceJoueur.deplacementJoueur(root);

		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);
	}


}