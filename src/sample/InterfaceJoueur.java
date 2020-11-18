package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static sample.Main.canvas;
import static sample.Main.gc;

public class InterfaceJoueur extends Parent {

	// map principale
	private MapProcedurale mapProcedurale;

	// autorisation des actions possible :
	public static boolean generationMap;
	public static boolean dezoomable;
	public static boolean zoomable;
	public static boolean centrable;
	public static boolean mvmt_droite;
	public static boolean mvmt_gauche;
	public static boolean mvmt_haut;
	public static boolean mvmt_bas;

	// sauvegarde des coord du biome courant :
	private int x;
	private int y;


	public InterfaceJoueur() {
		dezoomable = false;
		zoomable = true;
		centrable = false;
		mvmt_droite = false;
		mvmt_gauche = false;
		mvmt_haut = false;
		mvmt_bas = false;
		generationMap = true;
	}

	public void deplacementJoueur(Group root){
		Button droite = new Button("droite");
		Button gauche = new Button("gauche");
		Button haut = new Button("haut");
		Button bas = new Button("bas");
		Button centre = new Button("centre");
		placement(125, 425, gauche );
		placement(250, 425, droite );
		placement(200, 400, haut );
		placement(200, 450, bas );
		placement(187, 425, centre );

		zoom();

		// clic sur le bouton centre pour revenir au biome du centre
		centre.setOnMouseClicked(mouseEvent -> {

			if (centrable) {
				// action possible apres un centre :
				centrable = false;
				mvmt_droite = true;
				mvmt_gauche = true;
				mvmt_bas = true;
				mvmt_haut = true;

				// gestion d'affichage :
				mapProcedurale.creerBiome(x, y);
				System.out.println("centre");
			}
		});

		// clic sur le bouton droite pour afficher le biome de droite
		droite.setOnMouseClicked(mouseEvent -> {
			if (mvmt_droite && y!= mapProcedurale.getLongueur()) {
				// action possible apres un centre :

				centrable = true;
				mvmt_droite = false;
				mvmt_gauche = false;
				mvmt_haut = false;
				mvmt_bas = false;

				// gestion d'affichage :
				mapProcedurale.creerBiome(x, y+1);
				System.out.println("droite");
			}

		});

		// clic sur le bouton gauche pour afficher le biome de gauche
		gauche.setOnMouseClicked(mouseEvent -> {
			if (mvmt_gauche && y!=0) {
				// action possible apres un centre :

				centrable = true;
				mvmt_droite = false;
				mvmt_gauche = false;
				mvmt_haut = false;
				mvmt_bas = false;

				// gestion d'affichage :
				mapProcedurale.creerBiome(x, y-1);
				System.out.println("gauche");
			}

		});



		// clic sur le bouton haut pour afficher le biome de haut
		haut.setOnMouseClicked(mouseEvent -> {
			if (mvmt_haut && x!=0) {

				// action possible apres un centre :

				centrable = true;
				mvmt_droite = false;
				mvmt_gauche = false;
				mvmt_haut = false;
				mvmt_bas = false;

				// gestion d'affichage :
				mapProcedurale.creerBiome(x-1, y);
				System.out.println("haut");
			}

		});

		// clic sur le bouton bas pour afficher le biome de bas
		bas.setOnMouseClicked(mouseEvent -> {
			if (mvmt_bas && x!= mapProcedurale.getHauteur()) {
				// action possible apres un centre :

				centrable = true;
				mvmt_droite = false;
				mvmt_gauche = false;
				mvmt_haut = false;
				mvmt_bas = false;

				// gestion d'affichage :
				mapProcedurale.creerBiome(x+1, y);
				System.out.println("bas");
			}

		});

		root.getChildren().addAll(droite,gauche,haut,bas,centre);
	}

	public void deZoom(Group root){
		Button dezoom = new Button("Dezoomer");

		// dezoome en appuyant sur le bouton dezoom
		dezoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (dezoomable){
					// action possible apres un dezoom :
					dezoomable = false;
					zoomable = true;
					mvmt_droite = false;

					// crétation map :
					gc.clearRect(0,0,1500,1500);
					new MapProcedurale(mapProcedurale.getLongueur()+1,mapProcedurale.getHauteur()+1,0);
					System.out.println("dezoome");
				}
			}
		});
		placement(150,0,dezoom);

		root.getChildren().add(dezoom);
	}

	public void zoom(){
		// zoom en cliquant sur un carré du canvas
		Main.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (e.getX() < (mapProcedurale.getLongueur()+1)*(mapProcedurale.getLongueur()+1)  && e.getY() < (mapProcedurale.getHauteur()+1)*(mapProcedurale.getHauteur()+1) && zoomable){
				// action possible apres un zoom :
				centrable = false;
				zoomable = false;
				dezoomable = true;
				mvmt_droite = true;
				mvmt_gauche = true;
				mvmt_bas = true;
				mvmt_haut = true;

				// gestion d'affichage :
				x = (int)e.getY() / (mapProcedurale.getLongueur()+1);
				y = (int)e.getX() / (mapProcedurale.getHauteur()+1);
				gc.clearRect(0,0,1500,1500);
				mapProcedurale.creerBiome(x,y);
				System.out.println("zoom : " + x + ";" + y);
			}
		});
	}
	// initialisation de la 1ere fenetre (fenetre de controle)
	public void demarrage(Group root, Group mapGroup, Stage mapStage) {
		//On creer les texte d'indication
		Text tLongueur = new Text("Saisir la longueur de la carte (entre 0 et 1500)");
		Text tHauteur = new Text("Saisir la hauteur de la carte (entre 0 et 1500)");
		Text tSeed = new Text("Saisir la seed de la carte ( entre 0 et 999)");

		//On creer les case ou saisir les valeurs
		IntField longueur = new IntField(0, 1500, 20);
		IntField hauteur = new IntField(0, 1500, 20);
		//IntField longueur = new IntField(0,1000,20);
		//IntField hauteur = new IntField(0,1000,20);
		IntField seed = new IntField(0, 999, 0);

		//On positionne le tout
		placement(100, 75, tLongueur);
		placement(100, 175, tHauteur);
		placement(100, 275, tSeed);


		longueur.minHeight(100);
		hauteur.minWidth(100);
		seed.minWidth(100);

		placement(100, 100, longueur);
		placement(100, 200, hauteur);
		placement(100, 300, seed);

		mapGroup.getChildren().add(canvas);

		//On creer un bouton generer qui va creer une map avec les paramettres precedement remplis ou les parrametres par default
		Button generer = new Button("Generer la map");
		generer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (generationMap){
					mapStage.show();
					mapProcedurale = new MapProcedurale(longueur.getValue(), hauteur.getValue(), seed.getValue());
					generationMap = false;
				}
				//root.getChildren().removeAll(longueur,hauteur,seed,generer, tHauteur, tLongueur, tSeed);
			}
		});

		root.getChildren().addAll(longueur, hauteur, seed, generer, tHauteur, tLongueur, tSeed);
	}

	public void placement (int x, int y, Node node){
		node.setTranslateX(x);
		node.setTranslateY(y);
	}


}