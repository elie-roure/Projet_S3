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
    public static int seed;
    private Aleatoire aleatoire;

    // pas définitif :
    public boolean zoom;
    private boolean destructible;

    // pas encore utile :
    private int l2;         // nb de case dans une map
    private int h2;         // nb de case dans une map
    private int nbniveau;   // nb de niveau de la map (nb de niveau de précision possible)

    private Color[] couleurs;


    /////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////




    public MapProcedurale(int longueur, int hauteur, int seed) {

        Color[] c1 = {Color.GOLDENROD,Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
        couleurs=c1;

        //couleurs={Color.GOLDENROD,Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW}; //test
        /*couleurs= new Color[5];
        couleurs[0]=Color.GOLDENROD;
        couleurs[1]=Color.RED;
        couleurs[2]=Color.GREEN;
        couleurs[3]=Color.BLUE;
        couleurs[4]=Color.YELLOW;*/

        this.longueur = longueur-1;
        this.hauteur = hauteur-1;
        MapProcedurale.seed = seed;
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
        for(int j=0 ;j<=longueur;j++){
            for(int i=0 ; i<=hauteur ; i++){
                int a= proba.donneRandom();
                //si je ne suis pas au bord
                if (j>0&&i>0) {
                    //si je suis entouré par une couleur
                    if (matricerandom[j - 1][i] == matricerandom[j][i - 1]) {
                        if (a<80){
                            matricerandom[j][i] = matricerandom[j - 1][i];
                        }else {
                            matricerandom[j][i] = aleatoire.donneRandom();
                        }
                    }else {
                        if (a<40){
                            matricerandom[j][i] = matricerandom[j - 1][i];
                        }else if(a<80){
                            matricerandom[j][i] = matricerandom[j][i-1];
                        }else {
                            matricerandom[j][i] = aleatoire.donneRandom();
                        }
                    }
                    // si je suis tout en haut
                }else if(j==0&&i>0) {
                    if (a < 6) {
                        matricerandom[j][i] = matricerandom[j][i - 1];
                    } else{
                        matricerandom[j][i] = aleatoire.donneRandom();
                    }
                    // si je suis a gauche
                }else if (j>0&&i==0){
                    if (a < 6) {
                        matricerandom[j][i] = matricerandom[j-1][i];
                    } else{
                        matricerandom[j][i] = aleatoire.donneRandom();
                    }
                    // si je suis au départ
                }else {
                    matricerandom[j][i] = aleatoire.donneRandom();
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
        int [] matriceVoisin = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
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

        new Biome(30, 30, coordx, coordy,choisirCouleur(coordx, coordy), matriceVoisin);
    }

    // créateur de carré
    public void creerCarre(double coordx, double coordy){
        Main.gc.setFill(choisirCouleur((int)coordx, (int)coordy));
        Main.gc.fillRect(coordy*20+InterfaceJoueur.contour, coordx*20+InterfaceJoueur.contour, 20, 20);
    }


    ////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////


    // choix couleur du carré et du biome : (on peut aussi mettre cette fonction dans Biome)
    public Color choisirCouleur(int i,int j) {
        return couleurs[matricerandom[i][j]];
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

    public int getSeed() {
        return seed;
    }

    // to String :
    @Override
    public String toString() {
        return "MapProcedurale{" +
                "matricerandom=" + Arrays.toString(matricerandom) +
                '}';

    }
}