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

<<<<<<< HEAD
    public static Group root;
    public static Scene scene;
    public static Canvas canvas  = new Canvas(5000,5000);;
    public static GraphicsContext gc = canvas.getGraphicsContext2D();


    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Génération");


        root = new Group();
        scene = new Scene(root, 500, 500, Color.WHITE);


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


    public void placement (int x, int y, Node node){
        node.setTranslateX(x);
        node.setTranslateY(y);
    }
    public static void main(String[] args) {
        launch(args);
    }

    // affiche les coord qu'ont lui passe
	/*public static void aff(Biome b){
		System.out.println(b.getCoordx() + " , " + b.getCoordy());
	}*/
=======
	public static Group root;
	public static Scene scene;
	public static Canvas canvas  = new Canvas(5000,5000);;
	public static GraphicsContext gc = canvas.getGraphicsContext2D();

	public static Stage primaryStage2;

	public static int hauteurEcran = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int largeurEcran = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();



	@Override
	public void start(Stage primaryStage) throws Exception{

		//pour editer le primarystage faut utiliser primaryStage2
		primaryStage2 = primaryStage;

		//Poour colorier la zone en bleu, avec les dimentions
		gc.setFill(Color.BLUE);
		gc.fillRect(0,0,0.70*largeurEcran ,0.90*hauteurEcran);
		primaryStage.setFullScreen(true);

		primaryStage.setTitle("Map Procedurale");


		root = new Group();

		scene = new Scene(root, largeurEcran, hauteurEcran,Color.WHITESMOKE);




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


>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
}