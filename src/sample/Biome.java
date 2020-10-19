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
	private int longueur;
	private int hauteur;
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
	public Biome(int longueur, int hauteur, int coordx, int coordy, int[][] matriceMap) {

		this.longueur = longueur;
		this.hauteur = hauteur;
		this.coordx = coordx;
		this.coordy = coordy;
		this.matriceMap = matriceMap;

		nbAleatoire = matriceMap[coordx][coordy];
		aleatoire = new Aleatoire(nbAleatoire*coordx+coordy, 5);
		matricerandom = new int[longueur][hauteur];

		forme = new Rectangle(longueur, hauteur);
		grille = new GridPane();
		remplir();

		choixcouleur();
	}

	// Attribution de la couleur du biome :
	public void choixcouleur(){
		if (nbAleatoire == 0){
			forme.setFill(Color.BLUE);
		}
		else if (nbAleatoire == 1){
			forme.setFill(Color.GREEN);
		}
		else if (nbAleatoire == 2){
			forme.setFill(Color.RED);
		}
		else if (nbAleatoire == 3){
			forme.setFill(Color.PINK);
		}
		else{
			forme.setFill(Color.YELLOW);
		}
	}

	// Attribution de la couleur du sous-biome :
	public void choixcouleur2(){
		if (nbAleatoire == 0){
			forme.setFill(Color.BLUE);
		}
		else if (nbAleatoire == 1){
			forme.setFill(Color.GREEN);
		}
		else if (nbAleatoire == 2){
			forme.setFill(Color.RED);
		}
		else if (nbAleatoire == 3){
			forme.setFill(Color.PINK);
		}
		else{
			forme.setFill(Color.YELLOW);
		}
	}

	// remplir :
	public void remplir(){
		for(int i = 0; i< longueur; i++){
			for(int j=0 ; j<hauteur ; j++){
				matricerandom[i][j] = aleatoire.donneRandom();
				Rectangle r = new Rectangle(1,1, choixcouleur2());
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
