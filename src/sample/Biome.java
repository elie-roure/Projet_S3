package sample;

import javafx.scene.paint.Color;

import static sample.InterfaceJoueur.contour;

/*
Principe :
    - 1 fragment de MapProcédurale.
 */

public class Biome {

	///////////////////////////////////////////////////////////////  attributs : ////////////////////////////////////////////////////////////////


	// attributs en commun a tous les Biome zoomer:
	private int l;                    // longueur biome
	private int h;                    // hauteur biome
	private int l2;                    // longueur grille dans Biome
	private int h2;                    // hauteur grille dans Biome


	// attributs lié au Biome courant :
	private Aleatoire aleatoire;
	private int nbAleatoire;        	// nombre aléatoire du Biome
	private int[][] matricerandom;		// matrice du biome (20*20)
	private int coordx;                // coord x du nb du Biome dans MatriceMap
	private int coordy;                // coord x du nb du Biome dans MatriceMap

	// les voisins:
	private int[][] matriceVoisin;

	// pas définitif :
	private Color couleur;            // couleur du Biome
	private Color variationColor = Color.BLACK;
	public boolean dezoom;
	private boolean destructible;


	private Color[] couleurs;

	///////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////

	public Biome(int l2, int h2, int coordx, int coordy, Color couleur, int[][] matriceVoisin, int[] place) {

		Color[] c1 = {Color.GOLDENROD,Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
		couleurs=c1;

		l = 500;
		h = 500;
		this.coordx = coordx;
		this.coordy = coordy;
		this.l2 = l2;
		this.h2 = h2;
		this.couleur = couleur;

		matricerandom = new int[l2][h2];
		this.matriceVoisin = matriceVoisin;

		dezoom = true;
		destructible = false;

		aleatoire = new Aleatoire(matriceVoisin[1][1] * coordx + coordy, 100);
		remplirNbaleatoire(matriceVoisin[1][1]);
		remplirBiome(place[0],place[1]);
	}


	/////////////////////////////////////////////////////////  méthodes de remplissages  : ///////////////////////////////////////////////////////


	///////////////////////////////////////////////////////// zoomé :

	// remplir la matrice de nb pour les sous-Biome:
	public void remplirNbaleatoire(int nb){
		for (int i = 0; i < l2; i++) {
			for (int j = 0; j < h2; j++) {
				matricerandom[i][j] = aleatoire.donneRandom();
			}
		}
	}



	// remplir  les Biome d'un carré:
	public void remplirBiome(int placex, int placey){
		for(int i = 0; i< l2; i++){
			for(int j = 0; j< h2; j++){
				Main.gc.setFill(choixcouleur(i, j));
				Main.gc.fillRect(j * 20 + contour + 400*placex-200, i * 20 + contour + 400*placey-200, 20, 20);
			}
		}
	}


	////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////

	// Attribution de la couleur des sous-biome :
	public Color choixcouleur(int i,int j){
		if (matricerandom[i][j] < 0){
			return Color.WHITESMOKE;
		}
		else if (matricerandom[i][j] < 5){
			return Color.BLACK;
		}
		else {
			return couleur;
		}
	}
/*
	public Color choisirCouleurVoisin(int i) {
		if (matriceVoisin[i] == -1) {
			return Color.BLUE;		// couleur du bord
		} else if (matriceVoisin[i] == 0) {
			return Color.GOLDENROD;
		}else if (matriceVoisin[i] == 1) {
			return Color.RED;
		} else if (matriceVoisin[i] == 2) {
			return Color.GREEN;
		} else if (matriceVoisin[i] == 3) {
			return Color.BLUE;
		} else {
			return Color.YELLOW;
		}
	}*/



	///////////////////////////////////////////////////////  getter, setter et toString : ////////////////////////////////////////////////////////

	// getter :

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

}