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

<<<<<<< HEAD
	// attributs en commun a tous les Biome :
=======

	// attributs en commun a tous les Biome zoomer:
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
	private int l;                    // longueur biome
	private int h;                    // hauteur biome
	private int l2;                    // longueur grille dans Biome
	private int h2;                    // hauteur grille dans Biome
<<<<<<< HEAD
	private GridPane grille;        // grille du Biome
=======

>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79

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
	private Color variationColor = Color.BLACK;
	public boolean dezoom;
	private boolean destructible;


	///////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////
<<<<<<< HEAD



	public Biome(int l, int h, int coordx, int coordy, Color couleur, int[] matriceVoisin) {
=======

	public Biome(int l2, int h2, int coordx, int coordy, Color couleur, int[] matriceVoisin) {
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79

		l = 500;
		h = 500;
		this.coordx = coordx;
		this.coordy = coordy;
<<<<<<< HEAD
		l2 = 10;
		h2 = 10;
=======
		this.l2 = l2;
		this.h2 = h2;
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
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

<<<<<<< HEAD
		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 5);
=======
		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 100);
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
		matricerandom = new int[l2][h2];

		dezoom = true;
		destructible = false;

		remplirNbaleatoire();        // remplis la mtrice de nb aléatoire de sous-biome
<<<<<<< HEAD
		remplirBiome();                // remplis la matrice de sous-biome avec des carré de couleur
	}


	/////////////////////////////////////////////////////////  méthodes de remplissages : ///////////////////////////////////////////////////////
=======
		remplirBiome();
	}


	/////////////////////////////////////////////////////////  méthodes de remplissages  : ///////////////////////////////////////////////////////


	///////////////////////////////////////////////////////// zoomé :
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79

	// remplir la matrice de nb pour les sous-Biome:
	public void remplirNbaleatoire(){
		for(int i = 0; i< l2; i++){
			for(int j = 0; j< h2; j++){
				matricerandom[i][j] = aleatoire.donneRandom();
			}
		}
	}

	// remplir  les Biome d'un carré:
	public void remplirBiome(){
		for(int i = 0; i< l2; i++){
			for(int j = 0; j< h2; j++){
<<<<<<< HEAD
				Rectangle r = new Rectangle(2,2, choixcouleur(i,j));
				grille.add(r, j, i);
=======

				Main.gc.setFill(choixcouleur(i,j));
				Main.gc.fillRect(j*20, i*20, 20, 20);
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
			}
		}
	}


	////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////

	// Attribution de la couleur des sous-biome :
	public Color choixcouleur(int i,int j){
<<<<<<< HEAD
		return  couleur;
=======
		if (matricerandom[i][j] < 5){
			return Color.BLACK;
		}
		else {
			return  couleur;
		}
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
	}


	///////////////////////////////////////////////////////  getter, setter et toString : ////////////////////////////////////////////////////////

	// getter :

	public int getCoordx() {
		return coordx;
	}

	public int getCoordy() {
		return coordy;
	}

<<<<<<< HEAD
	public Color getCouleur() {
		return couleur;
	}

	// setter :
=======
	// to String des coordonnées
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79

	@Override
	public String toString() {
		return "Biome{" +
				"coordx=" + coordx +
				", coordy=" + coordy +
				'}';
	}

}