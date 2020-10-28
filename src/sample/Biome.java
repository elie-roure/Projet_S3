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

	// attributs en commun a tous les Biome :

	private int l;                    // longueur biome
	private int h;                    // hauteur biome
	private int l2;                    // longueur grille dans Biome
	private int h2;                    // hauteur grille dans Biome
	private int[][] matriceMap;        // matrice des nb aléatoire de la grille de la MapProcedurale
	private GridPane grille;        // grille du Biome

	// attributs lié au Biome courant :
	private Aleatoire aleatoire;
	private int nbAleatoire;        // nombre aléatoire du Biome
	private int[][] matricerandom;    // matrice de nb aléatoire pour les sous-biome
	private int coordx;                // coord x du nb du Biome dans MatriceMap
	private int coordy;                // coord x du nb du Biome dans MatriceMap


	// pas définitif :
	private Color couleur;            // couleur du Biome
	private Rectangle forme;        // forme du Biome
	private Color variationColor = Color.BLACK;



	// constructeur :
	public Biome(int l, int h, int coordx, int coordy, int[][] matriceMap) {

		this.l = l;
		this.h = h;
		this.coordx = coordx;
		this.coordy = coordy;
		this.matriceMap = matriceMap;
		l2 = 10;
		h2 = 10;

		nbAleatoire = matriceMap[coordx][coordy];
		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 5);
		matricerandom = new int[l2][h2];

		forme = new Rectangle(l, h);
		grille = new GridPane();

		remplirNbaleatoire();        // remplis la mtrice de nb aléatoire de sous-biome
		choixcouleur();                // choisi la couleur du Biome
		remplirBiome();                // remplis la matrice de sous-biome avec des carré de couleur


	}

	// Attribution de la couleur du biome :
	public void choixcouleur() {
		if (nbAleatoire == 0) {
			couleur = Color.BLUE;
			forme.setFill(couleur);
		} else if (nbAleatoire == 1) {
			couleur = Color.GREEN;
			forme.setFill(couleur);
		} else if (nbAleatoire == 2) {
			couleur = Color.RED;
			forme.setFill(couleur);
		} else if (nbAleatoire == 3) {
			couleur = Color.PINK;
			forme.setFill(couleur);
		} else {
			couleur = Color.YELLOW;
			forme.setFill(couleur);
		}
	}


	// Attribution de la couleur des sous-biome :
	public Color choixcouleur2(int i,int j){
		return  couleur;
	}

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
				Rectangle r = new Rectangle(2,2, choixcouleur2(i,j));
				grille.add(r, j, i);
			}
		}
	}

	// getter :

	public Rectangle getForme() {
		return forme;
	}

	public GridPane getGrille() {
		return grille;
	}

	// setter :


}
