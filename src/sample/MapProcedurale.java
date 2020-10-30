package sample;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class MapProcedurale {

    //////////////////////////////////////////////////////////////// attributs : ////////////////////////////////////////////////////////////////

    private GridPane grille;
    private int[][] matricerandom;

    private int longueur;
    private int hauteur;

    private Aleatoire aleatoire;

    //private Color[] couleurs;
    private Color colorParent;

    /////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////

    public MapProcedurale(int longueur, int hauteur, int seed) {

        this.longueur = longueur-1;
        this.hauteur = hauteur-1;
        aleatoire = new Aleatoire(seed, 100);
        colorParent=null;
        matricerandom = new int[longueur][hauteur];
        grille = new GridPane();

        /*couleurs= new Color[5];
        couleurs[0]=Color.GOLDENROD;
        couleurs[1]=Color.RED;
        couleurs[2]=Color.GREEN;
        couleurs[3]=Color.BLUE;
        couleurs[4]=Color.YELLOW;*/

        //remplirBis();			// remplie matriceRandom et la grille de carré

        remplirNbAleatoire();	// remplie matriceRandom
        remplirDeBiome();		// remplie la grille de Biome
    }
    public MapProcedurale(int longueur, int hauteur, int seed, Color c) {

        this.longueur = longueur-1;
        this.hauteur = hauteur-1;
        aleatoire = new Aleatoire(seed, 100);

        matricerandom = new int[longueur][hauteur];
        grille = new GridPane();

        /*couleurs= new Color[5];
        couleurs[0]=Color.GOLDENROD;
        couleurs[1]=Color.RED;
        couleurs[2]=Color.GREEN;
        couleurs[3]=Color.BLUE;
        couleurs[4]=Color.YELLOW;*/
        colorParent=c;
        //remplirBis();			// remplie matriceRandom et la grille de carré

        remplirNbAleatoire();	// remplie matriceRandom
        remplirDeBiome();		// remplie la grille de Biome
    }

    /////////////////////////////////////////////////////////  méthodes de remplissages : ///////////////////////////////////////////////////////

    //remplissage de la matrice matricerandom
    public void remplirNbAleatoire(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                matricerandom[i][j] = aleatoire.donneRandom();
            }
        }
    }

    //remplissage de Biome dans la grille
    public void remplirDeBiome(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                creerBiome(i,j);
            }
        }
    }

    //remplissage de la matrice .matricerandom et de la grille (en carré)
    public void remplirBis(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                matricerandom[i][j] = aleatoire.donneRandom();
                creerCarre(i,j);
            }
        }
    }


    //////////////////////////////////////////////////  méthodes de création d'élément javaFX : ////////////////////////////////////////////////

    // créateur de Biome :
    public void creerBiome(int coordx, int coordy){

        // gestion des voisins
        int [] matriceVoisin = new int[9];
        matriceVoisin[0] = matricerandom[coordx][coordy];
        if (coordx > 0){
            matriceVoisin[3] = matricerandom[coordx-1][coordy];
            if (coordy > 0) {
                matriceVoisin[1] = matricerandom[coordx][coordy-1];
                matriceVoisin[5] = matricerandom[coordx-1][coordy-1];
            }
            if (coordy < hauteur) {
                matriceVoisin[2] = matricerandom[coordx][coordy+1];
                matriceVoisin[7] = matricerandom[coordx - 1][coordy + 1];
            }
        }
        if (coordx < longueur){
            matriceVoisin[4] = matricerandom[coordx+1][coordy];
            if (coordy > 0) {
                matriceVoisin[1] = matricerandom[coordx][coordy-1];
                matriceVoisin[6] = matricerandom[coordx+1][coordy-1];
            }
            if (coordy < hauteur) {
                matriceVoisin[2] = matricerandom[coordx][coordy+1];
                matriceVoisin[8] = matricerandom[coordx+1][coordy+1];
            }
        }
        if(this.colorParent==null) {
            Biome b = new Biome(20, 20, coordx, coordy, choisirCouleur(coordx, coordy), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
            grille.add(b.getForme(), coordy, coordx);
            grille.add(b.getGrille(), coordy, coordx);
        }
        else {
            Biome b = new Biome(20, 20, coordx, coordy, choisirCouleurFILS(coordx, coordy,colorParent), matriceVoisin);
            grille.add(b.getForme(), coordy, coordx);
            grille.add(b.getGrille(), coordy, coordx);
        }

    }

    // créateur de carré
    public void creerCarre(int coordx, int coordy){

            Rectangle r = new Rectangle(20, 20, choisirCouleur(coordx, coordy));
            grille.add(r, coordy, coordx);

    }


    ////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////

    // choix couleur du carré et du biome :
    public Color choisirCouleurFILS(int i,int j, Color c) {
        if (matricerandom[i][j] < 80) {
            return c;
        } else if (80<=matricerandom[i][j]&&matricerandom[i][j]<90) {
            return Color.DARKGREY;
        } else {
            return Color.DARKVIOLET;
        }
    }

    public Color choisirCouleur(int i,int j) {
        if (matricerandom[i][j] < 20) {
            return Color.GOLDENROD;
        } else if (20<=matricerandom[i][j]&&matricerandom[i][j]<40) {
            return Color.RED;
        } else if (40<=matricerandom[i][j]&&matricerandom[i][j]<60) {
            return Color.GREEN;
        } else if (60<=matricerandom[i][j]&&matricerandom[i][j]<80) {
            return Color.BLUE;
        } else {
            return Color.YELLOW;
        }
    }




    ////////////////////////////////////////////////////////  getter, setter et toString : ///////////////////////////////////////////////////////

    // getter :
    public GridPane getGrille() {
        return grille;
    }
    public Biome getBiome(int i, int j){
        return grille.get
    }

    // to String :
    @Override
    public String toString() {
        return "MapProcedurale{" +
                "matricerandom=" + Arrays.toString(matricerandom) +
                '}';

    }
}
