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

import static sample.Main.*;

public class InterfaceJoueur extends Parent {

	// map principale
	private MapProcedurale mapProcedurale;

	// autorisation des actions possible :
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

		placement(125, 425, bGauche);
		placement(250, 425, bDroite);
		placement(200, 400, bHaut);
		placement(200, 450, bBas);


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


		// dezoome en cliquant sur le boutton dezoome
		bDezoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (dezoomable){
					// action possible apres un dezoom :
					dezoomable = false;
					zoomable = true;

					// crétation map :
					gc.clearRect(contour,contour,mapProcedurale.getLargeur(), mapProcedurale.getHauteur());
					gc.setFill(Color.BLUE);
					gc.fillRect(0,0,0.70*largeurEcran ,0.90*hauteurEcran);
					new MapProcedurale(mapProcedurale.getLargeur()+1,mapProcedurale.getHauteur()+1,mapProcedurale.seed);
					System.out.println("dezoome");

				}


				//autorisation(root);

				//SANS AUTORISATION : root.getChildren().removeAll(bHaut,bBas,bDroite,bGauche,bCentre,bDezoom);
			}
		});
		placement(150,0, bDezoom);

	}

	public void zoom(Group root){
		// zoom en cliquant sur un carré du canvas
		Main.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

			if ( contour < e.getX() && e.getX()   < contour +(mapProcedurale.getHauteur()+1)*20  &&  contour < e.getY() && e.getY()  < contour +(mapProcedurale.getLargeur()+1)*20  && zoomable){
				// action possible apres un zoom :
				zoomable = false;
				dezoomable = true;


				// gestion d'affichage :
				x = (int)(e.getY() -contour )/ (20);
				System.out.println(x);
				y = (int)(e.getX() -contour )/ (20);
				System.out.println(y);

				gc.setFill(Color.WHITESMOKE);
				gc.fillRect(0,0,0.70*largeurEcran ,0.90*hauteurEcran);

				mapProcedurale.creerBiome(x  ,y );
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
				//mapStage.show();
				gc.setFill(Color.BLUE);
				gc.fillRect(0,0,0.70*largeurEcran ,0.90*hauteurEcran);
				mapProcedurale = new MapProcedurale(largeur.getValue(), hauteur.getValue(), seed.getValue());


				//autorisation(root);
				//SANS AUTORISATION : root.getChildren().removeAll(bGenerer,tLongueur,tHauteur,tSeed,longueur,hauteur,seed);

			}
		});

		root.getChildren().addAll(hauteur, largeur, seed, bGenerer, tLargeur, tHauteur, tSeed,bFullScreen);
	}

	public void placement (int x, int y, Node node){
		node.setTranslateX(x);
		node.setTranslateY(y);
	}


}