package sample;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

/*
Principe :
    - 1 fragment de MapProcédurale
 */

public class Biome extends Parent {

	// attributs en commun a tous les Biome :
	private int l;
	private int h;
	private int l2;
	private int h2;
	private int[][] matriceMap;
	private GridPane grille;

	// attributs lié au Biome courant :
	private Aleatoire aleatoire;
	private int nbAleatoire;
	private int coordx;
	private int coordy;
	private int[][] matricerandom;


	// pas définitif :
	private Color frontiere;
	private Color couleur;
	private Color variationColor = Color.BLACK;
	private Rectangle forme;

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
		aleatoire = new Aleatoire(nbAleatoire*coordx+coordy, 5);
		matricerandom = new int[l2][h2];

		forme = new Rectangle(l, h);
		grille = new GridPane();
		remplir();

		choixcouleur();

		// Boucle pour comprendre les frontiere
		for (int i = 0 ; i<l2 ; i++){
			for (int j = 0 ; j<h2 ; j++){
				if (i<l2-1) {
					System.out.println(matricerandom[i + 1][j]);
				}
			}
		}
	}

	// Attribution de la couleur du biome :
	public void choixcouleur(){
		if (nbAleatoire == 0){
			couleur = Color.BLUE;
			forme.setFill(couleur);
		}
		else if (nbAleatoire == 1){
			couleur = Color.GREEN;

			forme.setFill(couleur);
		}
		else if (nbAleatoire == 2){
			couleur = Color.RED;

			forme.setFill(couleur);
		}
		else if (nbAleatoire == 3){
			couleur = Color.PINK;

			forme.setFill(couleur);
		}
		else{
			couleur = Color.YELLOW;

			forme.setFill(couleur);
		}
	}




	public void variationColor(){

			if (matriceMap[coordx][coordy] == 0) variationColor = Color.DARKBLUE;
			else if (nbAleatoire == 1) variationColor = Color.DARKGREEN;
			else if (nbAleatoire == 2) variationColor = Color.DARKRED;
			else if (nbAleatoire == 3) variationColor = Color.DEEPPINK;
			else variationColor = Color.LIGHTGOLDENRODYELLOW;


	}
	public void frontierBas() {
		for (int i = 0 ; i<l2 ; i++){
			for (int j = 0 ; j<h2 ; j++){
				if (i<l2-1) {
					System.out.println(matricerandom[i + 1][j]);
					if (matricerandom[i+1][j]==0) frontiere = Color.BLUE;
					else if (matricerandom[i+1][j]==1) frontiere = Color.GREEN;
					else if (matricerandom[i+1][j]==2) frontiere = Color.RED;
					else if (matricerandom[i+1][j]==3) frontiere = Color.PINK;
					else frontiere = Color.YELLOW;

				}
			}
		}
		/*//if (coordx < l2 - 1) {
			for (int i = 0 ; i<h2; i++){
				for (int j = 0 ; j<l2 -1 ; j++){
					if (matricerandom[i+1][j ] == 0) {
						//System.out.println(coordx);
						//System.out.println(matriceMap[coordx][coordy]);
						frontiere = Color.BLUE;
					} else if (matricerandom[i+1][j] == 1) {
						System.out.println("vert");
						frontiere = Color.GREEN;
					} else if (matricerandom[i + 1][j] == 2) {
						frontiere = Color.RED;
					} else if (matricerandom[i + 1][j] == 3) {
						frontiere = Color.PINK;
					} else frontiere = Color.YELLOW;
				}
		//	}

		}*/
	}
	// Attribution de la couleur du sous-biome :
	public Color choixcouleur2(int i,int j){
		if (matricerandom[i][j] == 0){
			 variationColor();
			 //frontierBas();
			 //return frontiere;
			return variationColor;
		}
		else{
			return couleur;
		}
	}


	// remplir :
	public void remplir(){
		for(int i = 0; i< l2; i++){
			for(int j = 0; j< h2; j++){
				matricerandom[i][j] = aleatoire.donneRandom();
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
