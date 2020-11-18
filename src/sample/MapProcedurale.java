package sample;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class MapProcedurale {

    //////////////////////////////////////////////////////////////// attributs : ////////////////////////////////////////////////////////////////


    private int[][] matricerandom;
    private int longueur;
    private int hauteur;
    private Aleatoire aleatoire;

    // pas définitif :
    public boolean zoom;
    private boolean destructible;

    // pas encore utile :
    private int l2;         // nb de case dans une map
    private int h2;         // nb de case dans une map
    private int nbniveau;   // nb de niveau de la map (nb de niveau de précision possible)


    /////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////


    public MapProcedurale(int longueur, int hauteur, int seed) {

        this.longueur = longueur-1;
        this.hauteur = hauteur-1;
        aleatoire = new Aleatoire(seed, 5);
        matricerandom = new int[longueur][hauteur];
        zoom = true;
        destructible = false;

        remplirNbAleatoire();	// remplie matriceRandom
        remplirDeCarre();       // remplie la fenetre de carré

    }


    /////////////////////////////////////////////////////////  méthodes de remplissages : ///////////////////////////////////////////////////////


    //remplissage de la matrice matricerandom
    public void remplirNbAleatoire(){
        Aleatoire proba=new Aleatoire(aleatoire.donneRandom(),100);
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                int a= proba.donneRandom();
                //si je ne suis pas au bord
                if (i>0&&j>0) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i - 1][j] == matricerandom[i][j - 1]) {
                        if (a<80){
                            matricerandom[i][j] = matricerandom[i - 1][j];
                        }else {
                            matricerandom[i][j] = aleatoire.donneRandom();
                        }
                    }else {
                        if (a<40){
                            matricerandom[i][j] = matricerandom[i - 1][j];
                        }else if(a<80){
                            matricerandom[i][j] = matricerandom[i][j-1];
                        }else {
                            matricerandom[i][j] = aleatoire.donneRandom();
                        }
                    }
                    // si je suis tout en haut
                }else if(i==0&&j>0) {
                    if (a < 6) {
                        matricerandom[i][j] = matricerandom[i][j - 1];
                    } else{
                        matricerandom[i][j] = aleatoire.donneRandom();
                    }
                    // si je suis a gauche
                }else if (i>0&&j==0){
                    if (a < 6) {
                        matricerandom[i][j] = matricerandom[i-1][j];
                    } else{
                        matricerandom[i][j] = aleatoire.donneRandom();
                    }
                    // si je suis au départ
                }else {
                    matricerandom[i][j] = aleatoire.donneRandom();
                }
            }
        }
        lisserCouleur();
    }

    //adapte les chiffre pour obtenir des zones de meme nb plus cohérente (appeler dans remplirNbAleatoire)
    public void lisserCouleur(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++) {
                //si je suis au milieu
                if (i>0&&j>0&&i<longueur-1&&j<hauteur-1) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i - 1][j] == matricerandom[i][j - 1]  &&  matricerandom[i][j - 1]==matricerandom[i+1][j]  &&  matricerandom[i+1][j]==matricerandom[i][j+1]) {
                        matricerandom[i][j]=matricerandom[i - 1][j];
                    }
                }
                /*
                // si je suis a gauche
                if (i>0&&j==0&&i<longueur-1) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i - 1][j] == matricerandom[i][j+1]  && matricerandom[i+1][j]==matricerandom[i][j+1]) {
                        matricerandom[i][j]=matricerandom[i - 1][j];
                    }
                }
                //si je suis a droite
                if (i>0&&j==hauteur-1&&i<longueur-1) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i - 1][j] == matricerandom[i][j-1]  && matricerandom[i+1][j]==matricerandom[i][j-1]) {
                        matricerandom[i][j]=matricerandom[i - 1][j];
                    }
                }
                //si je suis en haut
                if (i==0&&j>0&&j<hauteur-1) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i][j-1] == matricerandom[i][j + 1]  &&  matricerandom[i][j - 1]==matricerandom[i+1][j]) {
                        matricerandom[i][j]=matricerandom[i + 1][j];
                    }
                }
                //si je suis en bas
                if (i==longueur-1&&j>0&&j<hauteur-1) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i-1][j] == matricerandom[i][j - 1]  &&  matricerandom[i][j - 1]==matricerandom[i][j+1]) {
                        matricerandom[i][j]=matricerandom[i - 1][j];
                    }
                }
                //si je suis en haut a gauche
                if (i==0&&j==0) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i + 1][j] == matricerandom[i][j + 1]) {
                        matricerandom[i][j]=matricerandom[i + 1][j];
                    }
                }
                //si je suis en haut a droite
                if (i==0&&j==hauteur-1) {
                    //si je suis entouré par une couleur
                    if (matricerandom[i][j-1] == matricerandom[i+1][j]) {
                        matricerandom[i][j]=matricerandom[i + 1][j];
                    }
                }
                a finir*/
            }
        }
    }

    //remplissage de la matrice .matricerandom et de la grille (en carré)
    public void remplirDeCarre(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                creerCarre(i,j);
            }
        }
    }


    //////////////////////////////////////////////////  méthodes de création d'élément javaFX : ////////////////////////////////////////////////


    // créateur de Biome :
    public void creerBiome(int coordx, int coordy){

        // gestion des voisins (pas opti)
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

        new Biome(20, 20, coordx, coordy,choisirCouleur(coordx, coordy), matriceVoisin);
    }

    // créateur de carré
    public void creerCarre(double coordx, double coordy){
        Main.gc.setFill(choisirCouleur((int)coordx, (int)coordy));
        Main.gc.fillRect(coordy*20, coordx*20, 20, 20);
    }


    ////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////


    // choix couleur du carré et du biome : (on peut aussi mettre cette fonction dans Biome)
    public Color choisirCouleur(int i,int j) {
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


    ////////////////////////////////////////////////////////  getter, setter et toString : ///////////////////////////////////////////////////////

    // getter :


    public int getLongueur() {
        return longueur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getL2() {
        return l2;
    }

    public int getH2() {
        return h2;
    }

    // to String :
    @Override
    public String toString() {
        return "MapProcedurale{" +
                "matricerandom=" + Arrays.toString(matricerandom) +
                '}';

    }
}