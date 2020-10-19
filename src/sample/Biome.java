package sample;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
	private Color couleur;
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

	// Attribution de la couleur du sous-biome :
	public Color choixcouleur2(int i,int j){
		if (matricerandom[i][j] == 0){
			return Color.BLACK;
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
