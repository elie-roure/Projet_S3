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
	public static boolean bool = true;


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

		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 100);
		matricerandom = new int[lz2][hz2];

		Main.gc.setFill(couleur);
		Main.gc.fillRect(coordy*20, coordx*20, 20, 20);

		remplirNbaleatoire();        // remplis la mtrice de nb aléatoire de sous-biome

		//MapProcedurale.canvas.setOnMousePressed(mouseEvent -> aff2());
		Main.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (bool){
					if ((int) (e.getX() / 20) == coordx && (int) (e.getY() / 20) == coordy) {
						aleatoire = new Aleatoire(nbAleatoire * ((int) (e.getX() / 20)) + (int) (e.getY() / 20), 100);
						remplirNbaleatoire();
						remplirBiome();
						System.out.println("piou");
						bool = false;
					}
				}
			}
		});
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

				Main.gc.setFill(choixcouleur(i,j));
				Main.gc.fillRect(j*20, i*20, 20, 20);
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

	/*public Rectangle getForme() {
		return forme;
	}*/


	public int getCoordx() {
		return coordx;
	}

	public int getCoordy() {
		return coordy;
	}

	public Aleatoire getAleatoire() {
		return aleatoire;
	}

	public int getNbAleatoire() {
		return nbAleatoire;
	}

	public void setAleatoire(Aleatoire aleatoire) {
		this.aleatoire = aleatoire;
	}

	public static boolean isBool() {
		return bool;
	}

	public static void setBool(boolean bool) {
		Biome.bool = bool;
	}

	public int getVoisinh() {
		return voisinh;
	}

	public void setVoisinh(){
		voisinh = matricerandom[coordx][coordy-1];
	}

	public int getVoisinb() {
		return voisinb;
	}

	public int getVoising() {
		return voising;
	}

	public int getVoisind() {
		return voisind;
	}

	public int getvaleur(int coordx, int coordy) {
		return matricerandom[coordx][coordy];
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

		//biomeGroup.getChildren().add(grille);
		//biomeGroup.getChildren().add(formez);
	}

	public void aff2(){
		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 100);
		remplirNbaleatoire();
		remplirBiome();

	}

	public void affdeplacement(int nbAleatoire, int coordx, int coordy){


		aleatoire = new Aleatoire(nbAleatoire * coordx + coordy, 100);
		Stage biome = new Stage();
		biome.setTitle("Biome " + coordx + " : " + coordy);
		Group biomeGroup = new Group();
		Scene biomeScene = new Scene(biomeGroup,500,500);
		biome.setScene(biomeScene);
		biome.show();
		remplirNbaleatoire();
		remplirBiome();

		//biomeGroup.getChildren().add(grille);
		//biomeGroup.getChildren().add(formez);
	}



}