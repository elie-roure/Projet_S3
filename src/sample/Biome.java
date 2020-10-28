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

public class Biome extends Parent {

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

	private Color[] couleurs;


	// constructeur :
	public Biome(int l, int h, int coordx, int coordy, int[][] matriceMap) {

		couleurs= new Color[5];
		couleurs[0]=Color.BLUE;
		couleurs[1]=Color.GREEN;
		couleurs[2]=Color.RED;
		couleurs[3]=Color.PINK;
		couleurs[4]=Color.YELLOW;

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
		forme.setFill(couleurs[nbAleatoire]);
	}

	public Color variationColor() {

		if (matriceMap[coordx][coordy] == 0) return Color.DARKBLUE;
		else if (nbAleatoire == 1) return Color.DARKGREEN;
		else if (nbAleatoire == 2) return Color.DARKRED;
		else if (nbAleatoire == 3) return Color.DEEPPINK;
		else return Color.LIGHTGOLDENRODYELLOW;
	}


	private Color getColorBas() {
		if (coordx < matriceMap.length - 1) {
			if (matriceMap[coordx + 1][coordy] == 0) return Color.BLUE;
			else if (matriceMap[coordx + 1][coordy] == 1) return Color.GREEN;
			else if (matriceMap[coordx + 1][coordy] == 2) return Color.RED;
			else if (matriceMap[coordx + 1][coordy] == 3) return Color.PINK;
			else if (matriceMap[coordx + 1][coordy] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	private Color getColorHaut() {
		if (0 < coordx && coordx < matriceMap.length) {
			if (matriceMap[coordx - 1][coordy] == 0) return Color.BLUE;
			else if (matriceMap[coordx - 1][coordy] == 1) return Color.GREEN;
			else if (matriceMap[coordx - 1][coordy] == 2) return Color.RED;
			else if (matriceMap[coordx - 1][coordy] == 3) return Color.PINK;
			else if (matriceMap[coordx - 1][coordy] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	private Color getColorDroite() {
		if (coordy < matriceMap.length - 1) {
			if (matriceMap[coordx][coordy + 1] == 0) return Color.BLUE;
			else if (matriceMap[coordx][coordy + 1] == 1) return Color.GREEN;
			else if (matriceMap[coordx][coordy + 1] == 2) return Color.RED;
			else if (matriceMap[coordx][coordy + 1] == 3) return Color.PINK;
			else if (matriceMap[coordx][coordy + 1] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	private Color getColorGauche() {
		if (0 < coordy && coordy < matriceMap.length) {
			if (matriceMap[coordx][coordy - 1] == 0) return Color.BLUE;
			else if (matriceMap[coordx][coordy - 1] == 1) return Color.GREEN;
			else if (matriceMap[coordx][coordy - 1] == 2) return Color.RED;
			else if (matriceMap[coordx][coordy - 1] == 3) return Color.PINK;
			else if (matriceMap[coordx][coordy - 1] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	private Color getColorHautGauche() {
		if (0 < coordy && coordy < matriceMap.length && 0 < coordx && coordx < matriceMap.length) {
			if (matriceMap[coordx - 1][coordy - 1] == 0) return Color.BLUE;
			else if (matriceMap[coordx - 1][coordy - 1] == 1) return Color.GREEN;
			else if (matriceMap[coordx - 1][coordy - 1] == 2) return Color.RED;
			else if (matriceMap[coordx - 1][coordy - 1] == 3) return Color.PINK;
			else if (matriceMap[coordx - 1][coordy - 1] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	private Color getColorBasDroit() {
		if (coordy < matriceMap.length - 1 && coordx < matriceMap.length - 1) {
			if (matriceMap[coordx + 1][coordy + 1] == 0) return Color.BLUE;
			else if (matriceMap[coordx + 1][coordy + 1] == 1) return Color.GREEN;
			else if (matriceMap[coordx + 1][coordy + 1] == 2) return Color.RED;
			else if (matriceMap[coordx + 1][coordy + 1] == 3) return Color.PINK;
			else if (matriceMap[coordx + 1][coordy + 1] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	private Color getColorHautDroit() {
		if (coordy < matriceMap.length - 1 && 0 < coordx && coordx < matriceMap.length) {
			if (matriceMap[coordx - 1][coordy + 1] == 0) return Color.BLUE;
			else if (matriceMap[coordx - 1][coordy + 1] == 1) return Color.GREEN;
			else if (matriceMap[coordx - 1][coordy + 1] == 2) return Color.RED;
			else if (matriceMap[coordx - 1][coordy + 1] == 3) return Color.PINK;
			else if (matriceMap[coordx - 1][coordy + 1] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	private Color getColorBasGauche() {
		if (0 < coordy && coordy < matriceMap.length && coordx < matriceMap.length - 1) {
			if (matriceMap[coordx + 1][coordy - 1] == 0) return Color.BLUE;
			else if (matriceMap[coordx + 1][coordy - 1] == 1) return Color.GREEN;
			else if (matriceMap[coordx + 1][coordy - 1] == 2) return Color.RED;
			else if (matriceMap[coordx + 1][coordy - 1] == 3) return Color.PINK;
			else if (matriceMap[coordx + 1][coordy - 1] == 4) return Color.YELLOW;
			else return couleur;
		} else return couleur;
	}

	public Color frontiereBas() {
		return getColorBas();
	}

	public Color frontiereHaut() {
		return getColorHaut();
	}

	public Color frontiereDroite() {
		return getColorDroite();
	}

	public Color frontiereGauche() {
		return getColorGauche();
	}

	public Color frontiereHautGauche() {
		return getColorHautGauche();
	}

	public Color frontiereHautDroit() {
		return getColorHautDroit();
	}

	public Color frontiereBasGauche() {
		return getColorBasGauche();
	}

	public Color frontiereBasDroit() {
		return getColorBasDroit();
	}

	public Color frontiereAffineGauche(int i, int j) {
		if (-1<j && j<2 && 1<i &&i<8) return getColorGauche();
		return couleur;
	}

	public Color frontiereAffineHaut(int i, int j) {
		if (-1<i && i<2 && 1<j &&j<8) return getColorHaut();
		return couleur;
	}

	public Color frontiereAffineBas(int i, int j) {
		if (7<i && i<matricerandom.length && 1<j && j<8) return getColorBas();
		return couleur;
	}

	public Color frontiereAffineDroite(int i, int j) {
		if (7<j && j<matricerandom.length && 1<i &&i<8) return getColorDroite();
		return couleur;
	}

	public Color frontiereAffineHautGauche(int i, int j) {
		if (-1 < j && j < 2 && -1 < i && i < 2) return getColorHautGauche();
		return couleur;
	}

	public Color frontiereAffineHautDroit(int i, int j) {
		if (7 < j && j < matricerandom.length && -1 < i && i < 2) return getColorHautDroit();
		return couleur;
	}

	public Color frontiereAffineBasGauche(int i, int j){
		if (-1<j && j<2 && 7<i && i<matricerandom.length)return getColorBasGauche();
		return couleur;
	}

	public Color frontiereAffineBasDroit(int i, int j){
		if (7<j && j<matricerandom.length && 7<i && i<matricerandom.length)return getColorBasDroit();
		return couleur;
	}

	public Color frontiereGlobal(int i, int j){
		if (-1<j && j<2 && 1<i &&i<9) return getColorGauche();
		else if (-1<i && i<2 && 1<j &&j<9) return getColorHaut();
		else if (7<i && i<matricerandom.length && 1<j && j<9) return getColorBas();
		else if (7<j && j<matricerandom.length && 1<i &&i<9) return getColorDroite();
		else if (-1 < j && j < 2 && -1 < i && i < 2) return getColorHautGauche();
		else if (7 < j && j < matricerandom.length && -1 < i && i < 2) return getColorHautDroit();
		else if (-1<j && j<2 && 7<i && i<matricerandom.length)return getColorBasGauche();
		else if (7<j && j<matricerandom.length && 7<i && i<matricerandom.length)return getColorBasDroit();
		return variationColor();
	}


	// Attribution de la couleur des sous-biome :
	public Color choixcouleur2(int i,int j){
		if (matricerandom[i][j] == 0){
			return  frontiereGlobal(i,j);

		}
		else{
			return couleur;
		}
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
