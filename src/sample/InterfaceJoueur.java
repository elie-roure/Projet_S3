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
	private MapProcedurale mapProcedurale;



	public InterfaceJoueur() {
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

		centre.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				//mapProcedurale.getBiome().affdeplacement(mapProcedurale.getBiome().getNbAleatoire(),mapProcedurale.getBiome().getCoordx(),mapProcedurale.getBiome().getCoordy());



			}
		});
		droite.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				//mapProcedurale.getBiome().affdeplacement(mapProcedurale.getBiome().getNbAleatoire(),mapProcedurale.getBiome().getCoordx()+1,mapProcedurale.getBiome().getCoordy());


			}
		});

		gauche.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// mapProcedurale.getBiome().affdeplacement(mapProcedurale.getBiome().getNbAleatoire(),mapProcedurale.getBiome().getCoordx()-1,mapProcedurale.getBiome().getCoordy());



			}
		});

		haut.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// mapProcedurale.getBiome().affdeplacement(mapProcedurale.getBiome().getNbAleatoire(),mapProcedurale.getBiome().getCoordx(),mapProcedurale.getBiome().getCoordy()-1);


			}
		});

		bas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// mapProcedurale.getBiome().affdeplacement(mapProcedurale.getBiome().getNbAleatoire(),mapProcedurale.getBiome().getCoordx(),mapProcedurale.getBiome().getCoordy()+1);


			}
		});

		root.getChildren().addAll(droite,gauche,haut,bas,centre);
	}

	public void deZoom(Group root){/*
		Button dezoom = new Button("Dezoomer");
		dezoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				MapProcedurale map = new MapProcedurale(20,20,0);


			}
		});

		placement(150,0,dezoom);
		Main.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getX() > 400) {
					MapProcedurale map = new MapProcedurale(20,20,0);
					System.out.println("pui");

				}
			}
		});
		root.getChildren().add(dezoom);*/
	}

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
				mapStage.show();
				mapProcedurale = new MapProcedurale(longueur.getValue(), hauteur.getValue(), seed.getValue());

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