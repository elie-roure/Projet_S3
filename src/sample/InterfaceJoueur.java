package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

<<<<<<< HEAD
import static sample.Main.canvas;
import static sample.Main.gc;
=======
import static sample.Main.*;
>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854

public class InterfaceJoueur extends Parent {

	// map principale
	private MapProcedurale mapProcedurale;

	// autorisation des actions possible :
	public static boolean generationMap;
	public static boolean dezoomable;
	public static boolean zoomable;
	public static boolean estFullScreen = true;

	// sauvegarde des coord du biome courant :
	private int x;
	private int y;


	//bouton generation
	private final Button bGenerer = new Button("Generer la map");
	private final Button bFullScreen = new Button("FullScreen");
	//text field génération
	private final Text tLargeur = new Text("Saisir la hauteur de la carte (entre 0 et " +(int)((0.90*hauteurEcran-40)/20)+ ")");
	private final Text tHauteur = new Text("Saisir la largeur de la carte (entre 0 et " + (int)((0.70*largeurEcran-40)/20) +")");
	private final Text tSeed = new Text("Saisir la seed de la carte ( entre 0 et 999)");
	private final IntField hauteur = new IntField(0, (int)((0.70*largeurEcran-40)/20), 20);
	private final IntField largeur = new IntField(0, (int)((0.90*hauteurEcran-40)/20), 20);
	private final IntField seed = new IntField(0, 999, 0);


	//Bouton de deplacement :
	private final Button bDroite = new Button("droite");
	private final Button bGauche = new Button("gauche");
	private final Button bHaut = new Button("haut");
	private final Button bBas = new Button("bas");
	private final Button bDezoom = new Button("Dezoomer");

	//Decalage de la map sur le caneva
	public static int contour = 20;

	public InterfaceJoueur() {
		dezoomable = false;
		zoomable = true;
<<<<<<< HEAD
		centrable = false;
		mvmt_droite = false;
		mvmt_gauche = false;
		mvmt_haut = false;
		mvmt_bas = false;
		generationMap = true;
=======
>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
	}

	public void autorisation(Group root){
		root.getChildren().removeAll(bGenerer, tHauteur, tLargeur,tSeed, hauteur, largeur,seed);

		if (!dezoomable) root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche,bDezoom);
		else root.getChildren().add(bDezoom);

	}

	public void autorisationHBGD(Group root){
		root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
	}

	public void autorisationCentre(Group root){
		root.getChildren().addAll(bHaut,bBas,bDroite,bGauche);
	}


	public void deplacementJoueur(Group root){
<<<<<<< HEAD
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
=======

		placement(125, 425, bGauche);
		placement(250, 425, bDroite);
		placement(200, 400, bHaut);
		placement(200, 450, bBas);
>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854


		zoom(root);


		// clic sur le bouton droite pour afficher le biome de droite
		bDroite.setOnMouseClicked(mouseEvent -> {
			if (y < mapProcedurale.getHauteur()) {
				// action possible apres un centre :


				// gestion d'affichage :
				y +=1 ;
				mapProcedurale.creerBiome(x, y);
				System.out.println("droite");
			}

			//autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});

		// clic sur le bouton gauche pour afficher le biome de gauche
		bGauche.setOnMouseClicked(mouseEvent -> {
			if ( y>0) {
				// action possible apres un centre :

				// gestion d'affichage :
				y-=1;
				mapProcedurale.creerBiome(x, y);
				System.out.println("gauche");
			}
			//autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});

		// clic sur le bouton haut pour afficher le biome de haut
		bHaut.setOnMouseClicked(mouseEvent -> {
			if ( x>0) {

				// action possible apres un centre :

				// gestion d'affichage :
				x-=1;
				mapProcedurale.creerBiome(x, y);
				System.out.println("haut");

			}
			//autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});

		// clic sur le bouton bas pour afficher le biome de bas
		bBas.setOnMouseClicked(mouseEvent -> {
			if ( x<mapProcedurale.getLargeur()) {
				// action possible apres un centre :

				// gestion d'affichage :
				x+=1;
				mapProcedurale.creerBiome(x, y);
				System.out.println("bas");
			}
			//autorisationHBGD(root);
			/*SANS AUTORISATION :
			root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche);
			root.getChildren().add(bCentre);
			*/

		});


	}

	public void deZoom(Group root){

<<<<<<< HEAD
		// dezoome en appuyant sur le bouton dezoom
		dezoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
=======

		// dezoome en cliquant sur le boutton dezoome
		bDezoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
			@Override
			public void handle(MouseEvent e) {
				if (dezoomable){
					// action possible apres un dezoom :
					dezoomable = false;
					zoomable = true;

					// crétation map :
<<<<<<< HEAD
					gc.clearRect(0,0,1500,1500);
					new MapProcedurale(mapProcedurale.getLongueur()+1,mapProcedurale.getHauteur()+1,0);
=======
					gc.clearRect(contour,contour,mapProcedurale.getLargeur(), mapProcedurale.getHauteur());
					gc.setFill(Color.BLUE);
					gc.fillRect(0,0,0.70*largeurEcran ,0.90*hauteurEcran);
					new MapProcedurale(mapProcedurale.getLargeur()+1,mapProcedurale.getHauteur()+1,mapProcedurale.seed);
>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
					System.out.println("dezoome");

				}

<<<<<<< HEAD
		root.getChildren().add(dezoom);
=======

				//autorisation(root);

				//SANS AUTORISATION : root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche,bCentre,bDezoom);
			}
		});
		placement(150,0, bDezoom);

>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
	}

	public void zoom(Group root){
		// zoom en cliquant sur un carré du canvas
		Main.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
<<<<<<< HEAD
			if (e.getY() < (mapProcedurale.getLongueur()+1)*(mapProcedurale.getLongueur()+1)  && e.getX() < (mapProcedurale.getHauteur()+1)*(mapProcedurale.getHauteur()+1) && zoomable){
=======

			if ( contour < e.getX() && e.getX()   < contour +(mapProcedurale.getHauteur()+1)*20  &&  contour < e.getY() && e.getY()  < contour +(mapProcedurale.getLargeur()+1)*20  && zoomable){
>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
				// action possible apres un zoom :
				zoomable = false;
				dezoomable = true;


				// gestion d'affichage :
<<<<<<< HEAD
				x = (int)e.getY() / mapProcedurale.getLongueurCarre();
				y = (int)e.getX() / mapProcedurale.getLongueurCarre();
				gc.clearRect(0,0,1500,1500);
				mapProcedurale.creerBiome(x,y);
				System.out.println("zoom : " + x + ";" + y);
=======
				x = (int)(e.getY() -contour )/ (20);
				System.out.println(x);
				y = (int)(e.getX() -contour )/ (20);
				System.out.println(y);

				gc.setFill(Color.WHITESMOKE);
				gc.fillRect(0,0,0.70*largeurEcran ,0.90*hauteurEcran);

				mapProcedurale.creerBiome(x  ,y );
				System.out.println("zoom");

				root.getChildren().addAll(bHaut,bBas,bDroite,bGauche,bDezoom);
>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
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
		placement(100, 75, tHauteur);
		placement(100, 175, tLargeur);
		placement(100, 275, tSeed);


		hauteur.minHeight(100);
		largeur.minWidth(100);
		seed.minWidth(100);

		placement(100, 100, hauteur);
		placement(100, 200, largeur);
		placement(100, 300, seed);
		placement(100,30,bGenerer);
		placement(500,(int)(hauteurEcran*0.05),canvas);
		gc.setFill(Color.BLUE);
		root.getChildren().add(canvas);


		bFullScreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				primaryStage2.setFullScreen(!estFullScreen);
				estFullScreen = !estFullScreen;

			}
		});


		//On creer un bouton generer qui va creer une map avec les paramettres precedement remplis ou les parrametres par default
		bGenerer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
<<<<<<< HEAD
				if (generationMap){
					mapStage.show();
					mapProcedurale = new MapProcedurale(longueur.getValue(), hauteur.getValue(), seed.getValue());
					generationMap = false;
				}
				//root.getChildren().removeAll(longueur,hauteur,seed,generer, tHauteur, tLongueur, tSeed);
=======
				//mapStage.show();
				gc.setFill(Color.BLUE);
				gc.fillRect(0,0,0.70*largeurEcran ,0.90*hauteurEcran);
				mapProcedurale = new MapProcedurale(largeur.getValue(), hauteur.getValue(), seed.getValue());


				//autorisation(root);
				//SANS AUTORISATION : root.getChildren().removeAll(bGenerer,tLongueur,tHauteur,tSeed,longueur,hauteur,seed);

>>>>>>> 81c30383c2855dae371abfd3e24f97b56c638854
			}
		});

		root.getChildren().addAll(hauteur, largeur, seed, bGenerer, tLargeur, tHauteur, tSeed,bFullScreen);
	}

	public void placement (int x, int y, Node node){
		node.setTranslateX(x);
		node.setTranslateY(y);
	}


}