package sample;

import javafx.scene.paint.Color;

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
	private int[][] matricerandom;
	private int coordx;                // coord x du nb du Biome dans MatriceMap
	private int coordy;                // coord x du nb du Biome dans MatriceMap

	// les voisins:
	private int voisingauche;
	private int voisindroite;
	private int voisinhaut;
	private int voisinbas;
	private int voisinhautgauche;
	private int voisinbasgauche;
	private int voisinhautdroite;
	private int voisinbasdroite;

	// pas définitif :
	private Color couleur;            // couleur du Biome
	private Color variationColor = Color.BLACK;
	public boolean dezoom;
	private boolean destructible;


	private Color[] couleurs;

	///////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////

	public Biome(int l2, int h2, int coordx, int coordy, Color couleur, int[] matriceVoisin) {

		Color[] c1 = {Color.GOLDENROD,Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
		couleurs=c1;

		l = 500;
		h = 500;
		this.coordx = coordx;
		this.coordy = coordy;
		this.l2 = l2;
		this.h2 = h2;
		this.couleur = couleur;

		nbAleatoire = matriceVoisin[0];
		voisingauche = matriceVoisin[1];
		voisindroite = matriceVoisin[2];
		voisinhaut = matriceVoisin[3];
		voisinbas = matriceVoisin[4];
		voisinhautgauche = matriceVoisin[5];
		voisinbasgauche = matriceVoisin[6];
		voisinhautdroite = matriceVoisin[7];
		voisinbasdroite = matriceVoisin[8];

		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 100);
		matricerandom = new int[l2][h2];

		dezoom = true;
		destructible = false;

		remplirNbaleatoire();        // remplis la mtrice de nb aléatoire de sous-biome
		remplirBiome();
	}


	/////////////////////////////////////////////////////////  méthodes de remplissages  : ///////////////////////////////////////////////////////


	///////////////////////////////////////////////////////// zoomé :

	// remplir la matrice de nb pour les sous-Biome:
	public void remplirNbaleatoire(){
		for(int i = 0; i< l2; i++){
			for(int j = 0; j< h2; j++){
				if (i<5&&j<5){
					matricerandom[i][j]= voisinhautgauche;
				}else if (j>24&&i<5) {
					matricerandom[i][j]= voisinhautdroite;
				}else if(i>24&&j<5){
					matricerandom[i][j]= voisinbasgauche;
				}else if (i>24&&j>24){
					matricerandom[i][j]= voisinbasdroite;
				}else if (i<5&&j>4&&j<25){ //haut
					matricerandom[i][j]= voisinhaut;
				}else if (j>24&&i>4&&i<25){ //droite
					matricerandom[i][j]= voisindroite;
				}else if (i>24&&j>4&&j<25){//bas
					matricerandom[i][j]= voisinbas;
				}else if (j<5&&i>4&&i<25){//gauche
					matricerandom[i][j]= voisingauche;
				}else{
					matricerandom[i][j] = aleatoire.donneRandom();
				}
			}
		}

	}

	// remplir  les Biome d'un carré:
	public void remplirBiome(){
		for(int i = 0; i< l2; i++){
			for(int j = 0; j< h2; j++){
				if (i<25&&i>4&&j<25&&j>4) {
					Main.gc.setFill(choixcouleur(i, j));
					Main.gc.fillRect(j * 20, i * 20, 20, 20);
				}else {
					Main.gc.setFill(choisirCouleurVoisin(i,j));
					Main.gc.fillRect(j * 20, i * 20, 20, 20);
				}
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

	public Color choisirCouleurVoisin(int i,int j) {
		if (matricerandom[i][j] == 0) {
			return Color.GOLDENROD;
		} else if (matricerandom[i][j] == 1) {
			return Color.RED;
		} else if (matricerandom[i][j] == 2) {
			return Color.GREEN;
		} else if (matricerandom[i][j] == 3) {
			return Color.BLUE;
		} else {
			return Color.YELLOW;
		}
	}


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