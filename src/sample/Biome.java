package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
Principe :
    - 1 fragment de MapProcédurale.
 */

public class Biome {

	///////////////////////////////////////////////////////////////  attributs : ////////////////////////////////////////////////////////////////

	// attributs en commun a tous les Biome petit:
	private int l;                    // longueur biome
	private int h;                    // hauteur biome
	private int l2;                    // longueur grille dans Biome
	private int h2;                    // hauteur grille dans Biome


	// attributs en commun a tous les Biome zoomer:
	private int lz;                    // longueur biome
	private int hz;                    // hauteur biome
	private int lz2;                    // longueur grille dans Biome
	private int hz2;                    // hauteur grille dans Biome
	private GridPane grille;        // grille du Biome


	// attributs lié au Biome courant :
	private Aleatoire aleatoire;
	private int nbAleatoire;        	// nombre aléatoire du Biome
	private int[][] matricerandom;
	private int coordx;                // coord x du nb du Biome dans MatriceMap
	private int coordy;                // coord x du nb du Biome dans MatriceMap

	// les voisins:
	private int voisinh;
	private int voisinb;
	private int voising;
	private int voisind;
	private int voisinhg;
	private int voisinhd;
	private int voisinbg;
	private int voisinbd;

	// pas définitif :
	private Color couleur;            // couleur du Biome
	private Rectangle forme;        // forme du Biome
	private Color variationColor = Color.BLACK;


	///////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////

	public Biome(int l, int h, int coordx, int coordy, Color couleur, int[] matriceVoisin) {

		this.l = l;
		this.h = h;
		lz = 500;
		hz = 500;
		this.coordx = coordx;
		this.coordy = coordy;
		lz2 = l;
		hz2 = h;
		this.couleur = couleur;

		nbAleatoire = matriceVoisin[0];
		voisinh = matriceVoisin[1];
		voisinb = matriceVoisin[2];
		voising = matriceVoisin[3];
		voisind = matriceVoisin[4];
		voisinhg = matriceVoisin[5];
		voisinhd = matriceVoisin[6];
		voisinbg = matriceVoisin[7];
		voisinbd = matriceVoisin[8];

		//aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 100);
		matricerandom = new int[lz2][hz2];

		forme = new Rectangle(l, h, couleur);
		grille = new GridPane();

		//remplirNbaleatoire();        // remplis la mtrice de nb aléatoire de sous-biome
		//remplirBiome();                // remplis la matrice de sous-biome avec des carré de couleur

		forme.setOnMousePressed(mouseEvent -> aff());
	}


	/////////////////////////////////////////////////////////  méthodes de remplissages  : ///////////////////////////////////////////////////////


	///////////////////////////////////////////////////////// zoomé :

	// remplir la matrice de nb pour les sous-Biome:
	public void remplirNbaleatoire(){
		for(int i = 0; i< lz2; i++){
			for(int j = 0; j< hz2; j++){
				matricerandom[i][j] = aleatoire.donneRandom();
			}
		}
	}

	// remplir  les Biome d'un carré:
	public void remplirBiome(){
		for(int i = 0; i< lz2; i++){
			for(int j = 0; j< hz2; j++){
				Rectangle r = new Rectangle(lz/lz2,hz/hz2, choixcouleur(i,j));
				grille.add(r, j, i);
			}
		}
	}


	////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////

	// Attribution de la couleur des sous-biome :
	public Color choixcouleur(int i,int j){
		if (matricerandom[i][j] < 5){
			return Color.BLACK;
		}
		else {
			return  couleur;
		}
	}


	///////////////////////////////////////////////////////  getter, setter et toString : ////////////////////////////////////////////////////////

	// getter :

	public Rectangle getForme() {
		return forme;
	}

	public GridPane getGrille() {
		return grille;
	}

	public int getCoordx() {
		return coordx;
	}

	public int getCoordy() {
		return coordy;
	}

	// to String des coordonnées

	@Override
	public String toString() {
		return "Biome{" +
				"coordx=" + coordx +
				", coordy=" + coordy +
				'}';
	}

	//
	public void aff(){
		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 100);
		Stage biome = new Stage();
		biome.setTitle("Biome " + coordx + " : " + coordy);
		Group biomeGroup = new Group();
		Scene biomeScene = new Scene(biomeGroup,500,500);
		biome.setScene(biomeScene);
		biome.show();
		remplirNbaleatoire();
		remplirBiome();

		biomeGroup.getChildren().add(grille);
		//biomeGroup.getChildren().add(formez);
	}
}