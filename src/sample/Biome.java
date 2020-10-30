package sample;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
Principe :
    - 1 fragment de MapProcédurale
 */

public class Biome {

	///////////////////////////////////////////////////////////////  attributs : ////////////////////////////////////////////////////////////////

	// attributs en commun a tous les Biome :
	private int l;                    // longueur biome
	private int h;                    // hauteur biome
	private int l2;                    // longueur grille dans Biome
	private int h2;                    // hauteur grille dans Biome
	private GridPane grille;        // grille du Biome

	// attributs lié au Biome courant :
	private Aleatoire aleatoire;
	private int nbAleatoire;        	// nombre aléatoire du Biome
	private int[][] matricerandom;
	private int coordx;                // coord x du nb du Biome dans MatriceMap
	private int coordy;                // coord y du nb du Biome dans MatriceMap

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
		this.coordx = coordx;
		this.coordy = coordy;
		l2 = 10;
		h2 = 10;
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

		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 5);
		matricerandom = new int[l2][h2];

		forme = new Rectangle(l, h);
		grille = new GridPane();

		remplirNbaleatoire();        // remplis la mtrice de nb aléatoire de sous-biome
		remplirBiome();                // remplis la matrice de sous-biome avec des carré de couleur
	}


	/////////////////////////////////////////////////////////  méthodes de remplissages : ///////////////////////////////////////////////////////

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
				Rectangle r = new Rectangle(2,2, choixcouleur(i,j));
				grille.add(r, j, i);
			}
		}
	}


	////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////

	// Attribution de la couleur des sous-biome :
	public Color choixcouleur(int i,int j){
		return  couleur;
	}


	///////////////////////////////////////////////////////  getter, setter et toString : ////////////////////////////////////////////////////////

	// getter :

	public Rectangle getForme() {
		return forme;
	}

	public GridPane getGrille() {
		return grille;
	}

	// setter :


}