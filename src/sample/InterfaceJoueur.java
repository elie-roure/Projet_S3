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

public class InterfaceJoueur extends Parent {

	// map principale
	private MapProcedurale mapProcedurale;

	// autorisation des actions possible :
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


	//bouton generation
	private final Button bGenerer = new Button("Generer la map");
	//text field génération
	private final Text tLongueur = new Text("Saisir la longueur de la carte (entre 0 et 1500)");
	private final Text tHauteur = new Text("Saisir la hauteur de la carte (entre 0 et 1500)");
	private final Text tSeed = new Text("Saisir la seed de la carte ( entre 0 et 999)");
	private final IntField longueur = new IntField(0, 1500, 20);
	private final IntField hauteur = new IntField(0, 1500, 20);
	private final IntField seed = new IntField(0, 999, 0);


	//Bouton de deplacement :
	private final Button bDroite = new Button("droite");
	private final Button bGauche = new Button("gauche");
	private final Button bHaut = new Button("haut");
	private final Button bBas = new Button("bas");
	private final Button bCentre = new Button("centre");
	private final Button bDezoom = new Button("Dezoomer");




	public InterfaceJoueur() {
		dezoomable = false;
		zoomable = true;
		centrable = false;
		mvmt_droite = false;
		mvmt_gauche = false;
		mvmt_haut = false;
		mvmt_bas = false;



	}

	public void autorisation(Group root){
		root.getChildren().removeAll(bGenerer,tLongueur,tHauteur,tSeed,longueur,hauteur,seed);

		if (!dezoomable) root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche,bCentre,bDezoom);
		else root.getChildren().add(bDezoom);

	}

	public void autorisationHBGD(Group root){
		root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
		root.getChildren().add(bCentre);
	}

	public void autorisationCentre(Group root){
		root.getChildren().remove(bCentre);
		root.getChildren().addAll(bHaut,bBas,bDroite,bGauche);
	}


	public void deplacementJoueur(Group root){

		placement(125, 425, bGauche);
		placement(250, 425, bDroite);
		placement(200, 400, bHaut);
		placement(200, 450, bBas);
		placement(187, 425, bCentre);

		zoom(root);

		// clic sur le bouton centre pour revenir au biome du centre
		bCentre.setOnMouseClicked(mouseEvent -> {

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


			autorisationCentre(root);

			/*SANS AUTORISATION : 	root.getChildren().remove(bCentre);
									root.getChildren().addAll(bHaut,bBas,bDroite,bGauche);*/

		});

		// clic sur le bouton droite pour afficher le biome de droite
		bDroite.setOnMouseClicked(mouseEvent -> {
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

			autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});

		// clic sur le bouton gauche pour afficher le biome de gauche
		bGauche.setOnMouseClicked(mouseEvent -> {
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
			autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});

		// clic sur le bouton haut pour afficher le biome de haut
		bHaut.setOnMouseClicked(mouseEvent -> {
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
			autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});

		// clic sur le bouton bas pour afficher le biome de bas
		bBas.setOnMouseClicked(mouseEvent -> {
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
			autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});


	}

	public void deZoom(Group root){


		// dezoome en cliquant a coté de la map
		bDezoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (dezoomable){
					// action possible apres un dezoom :
					dezoomable = false;
					zoomable = true;
					mvmt_droite = false;

					// crétation map :
					new MapProcedurale(20,20,0);
					System.out.println("dezoome");

				}


				autorisation(root);

				//SANS AUTORISATION : root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche,bCentre,bDezoom);
			}
		});
		placement(150,0, bDezoom);

		// dezoome en appuyant sur le bouton dezoome
		Main.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if ((e.getX() > 400 || e.getY() > 400) && dezoomable){
					// action possible apres un dezoom :
					dezoomable = false;
					zoomable = true;
					mvmt_droite = false;

					// crétation map :
					new MapProcedurale(20,20,0);
					System.out.println("dezoome");


				}

				autorisation(root);

				//SANS AUTORISATION : root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche,bCentre,bDezoom);
			}
		});



	}

	public void zoom(Group root){
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
				mapProcedurale.creerBiome(x,y);
				System.out.println("zoom");

				root.getChildren().addAll(bHaut,bBas,bDroite,bGauche,bDezoom);
			}
		});
	}

	// initialisation de la 1ere fenetre (fenetre de controle)
	public void demarrage(Group root, Group mapGroup, Stage mapStage) {
		//On creer les texte d'indication


		//On creer les case ou saisir les valeurs

		//IntField longueur = new IntField(0,1000,20);
		//IntField hauteur = new IntField(0,1000,20);

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
		bGenerer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mapStage.show();
				mapProcedurale = new MapProcedurale(longueur.getValue(), hauteur.getValue(), seed.getValue());



				autorisation(root);
				//SANS AUTORISATION : root.getChildren().removeAll(bGenerer,tLongueur,tHauteur,tSeed,longueur,hauteur,seed);

			}
		});

		root.getChildren().addAll(longueur, hauteur, seed, bGenerer, tHauteur, tLongueur, tSeed);
	}

	public void placement (int x, int y, Node node){
		node.setTranslateX(x);
		node.setTranslateY(y);
	}


}