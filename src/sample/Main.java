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

public class Main extends Application {

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

		demarrage(root, mapGroup, mapStage);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void demarrage(Group root, Group mapGroup, Stage mapStage){
		//On creer les texte d'indication
		Text tLongueur = new Text("Saisir la longueur de la carte (entre 0 et 1500)");
		Text tHauteur = new Text("Saisir la hauteur de la carte (entre 0 et 1500)");
		Text tSeed = new Text("Saisir la seed de la carte ( entre 0 et 999)");

		//On creer les case ou saisir les valeurs
		IntField longueur = new IntField(0,1500,20);
		IntField hauteur = new IntField(0,1500,20);
		//IntField longueur = new IntField(0,1000,20);
		//IntField hauteur = new IntField(0,1000,20);
		IntField seed = new IntField(0,999,0);

		//On positionne le tout
		placement(100,75,tLongueur);
		placement(100,175,tHauteur);
		placement(100,275,tSeed);


		longueur.minHeight(100);
		hauteur.minWidth(100);
		seed.minWidth(100);

		placement(100,100, longueur);
		placement(100,200,hauteur);
		placement(100,300,seed);

		mapGroup.getChildren().add(canvas);

		//On creer un bouton generer qui va creer une map avec les paramettres precedement remplis ou les parrametres par default
		Button generer = new Button("Generer la map");
		generer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mapStage.show();
				MapProcedurale map = new MapProcedurale(longueur.getValue(),hauteur.getValue(), seed.getValue());

				//root.getChildren().removeAll(longueur,hauteur,seed,generer, tHauteur, tLongueur, tSeed);
			}
		});

		// quand on clique a un autre endroit que sur la map ca recree une map (le dézoom)
		Main.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getX() > 400) {
					MapProcedurale map = new MapProcedurale(20,20,0);
					System.out.println("pui");
					Biome.bool = true;
				}
			}
		});



		/**Lorsqu'on clique sur la map, cela recupère les coordonnées de la GridPane, et reccrer une carte à partir
		 * de ces dernier
		 * */
		/*
		mapGroup.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				Node clicked = mouseEvent.getPickResult().getIntersectedNode();
				Integer colIndice = GridPane.getColumnIndex(clicked);
				Integer rowIndice = GridPane.getRowIndex(clicked);
				MapProcedurale maplv2 = new MapProcedurale(longueur.getValue(),hauteur.getValue(),Integer.parseInt("84578" + colIndice + rowIndice));
				//System.out.println(colIndice + ":" + rowIndice);
				mapGroup.getChildren().add(maplv2.getGrille());
			}
		});*/






		// MapProcedurale map = new MapProcedurale(20,20, 6);
		root.getChildren().addAll(longueur, hauteur, seed, generer, tHauteur, tLongueur, tSeed);

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
}