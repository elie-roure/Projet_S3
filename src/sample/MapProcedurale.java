package sample;

<<<<<<< HEAD
<<<<<<< HEAD
import javafx.scene.layout.GridPane;
=======
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
<<<<<<< HEAD
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class MapProcedurale {
<<<<<<< HEAD
<<<<<<< HEAD

    //////////////////////////////////////////////////////////////// attributs : ////////////////////////////////////////////////////////////////

    private GridPane grille;
    private int[][] matricerandom;
=======

    //////////////////////////////////////////////////////////////// attributs : ////////////////////////////////////////////////////////////////
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79


=======

    //////////////////////////////////////////////////////////////// attributs : ////////////////////////////////////////////////////////////////


>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    private int[][] matricerandom;
    private int longueur;
    private int hauteur;
    private Aleatoire aleatoire;

<<<<<<< HEAD
<<<<<<< HEAD
    private Color[] couleurs;
=======
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    // pas définitif :
    public boolean zoom;
    private boolean destructible;

    // pas encore utile :
    private int l2;         // nb de case dans une map
    private int h2;         // nb de case dans une map
    private int nbniveau;   // nb de niveau de la map (nb de niveau de précision possible)
<<<<<<< HEAD
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79


    /////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////

<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
=======

>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    public MapProcedurale(int longueur, int hauteur, int seed) {

        this.longueur = longueur-1;
        this.hauteur = hauteur-1;
        aleatoire = new Aleatoire(seed, 5);
        matricerandom = new int[longueur][hauteur];
<<<<<<< HEAD
<<<<<<< HEAD
        grille = new GridPane();

        couleurs= new Color[5];
        couleurs[0]=Color.GOLDENROD;
        couleurs[1]=Color.RED;
        couleurs[2]=Color.GREEN;
        couleurs[3]=Color.BLUE;
        couleurs[4]=Color.YELLOW;
=======
        zoom = true;
        destructible = false;
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79

        remplirNbAleatoire();	// remplie matriceRandom
        remplirDeCarre();       // remplie la fenetre de carré

<<<<<<< HEAD
        remplirNbAleatoire();	// remplie matriceRandom
        //remplirBis();			// remplie matriceRandom et la grille de carré

        remplirDeBiome();		// remplie la grille de Biome
    }


    /////////////////////////////////////////////////////////  méthodes de remplissages : ///////////////////////////////////////////////////////

=======
=======
        zoom = true;
        destructible = false;

        remplirNbAleatoire();	// remplie matriceRandom
        remplirDeCarre();       // remplie la fenetre de carré

>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    }


    /////////////////////////////////////////////////////////  méthodes de remplissages : ///////////////////////////////////////////////////////


<<<<<<< HEAD
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
            }
        }
        lisserCouleur();
    }

<<<<<<< HEAD
=======
    //adapte les chiffre pour obtenir des zones de meme nb plus cohérente (appeler dans remplirNbAleatoire)
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
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
<<<<<<< HEAD
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
            }
        }
        lisserCouleur();
    }

<<<<<<< HEAD
<<<<<<< HEAD


    //remplissage de Biome dans la grille
    public void remplirDeBiome(){
=======
    //adapte les chiffre pour obtenir des zones de meme nb plus cohérente (appeler dans remplirNbAleatoire)
    public void lisserCouleur(){
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
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
<<<<<<< HEAD
    public void remplirBis(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                matricerandom[i][j] = aleatoire.donneRandom();
=======
    public void remplirDeCarre(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
                creerCarre(i,j);
            }
        }
    }


    //////////////////////////////////////////////////  méthodes de création d'élément javaFX : ////////////////////////////////////////////////
<<<<<<< HEAD

    // créateur de Biome :
    public void creerBiome(int i, int j) {

        // gestion des voisins
        int[] matriceVoisin = new int[9];
        matriceVoisin[0] = matricerandom[i][j];
        if (i > 0) {
            matriceVoisin[3] = matricerandom[i - 1][j];
            if (j > 0) {
                matriceVoisin[1] = matricerandom[i][j - 1];
                matriceVoisin[5] = matricerandom[i - 1][j - 1];
            }
            if (j < hauteur) {
                matriceVoisin[2] = matricerandom[i][j + 1];
                matriceVoisin[7] = matricerandom[i - 1][j + 1];
            }
        }
        if (i < longueur) {
            matriceVoisin[4] = matricerandom[i + 1][j];
            if (j > 0) {
                matriceVoisin[1] = matricerandom[i][j - 1];
                matriceVoisin[6] = matricerandom[i + 1][j - 1];
            }
            if (j < hauteur) {
                matriceVoisin[2] = matricerandom[i][j + 1];
                matriceVoisin[8] = matricerandom[i + 1][j + 1];
            }
        }
        Biome b = new Biome(20, 20, i, j,choisirCouleur(i,j), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
        grille.add(b.getForme(), j, i);
        grille.add(b.getGrille(), j, i);
        /*if (i==0||j==0){
            Biome b = new Biome(20, 20, i, j,choisirCouleur(i,j), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
            grille.add(b.getForme(), j, i);
            grille.add(b.getGrille(), j, i);
            return b;
        }
        else if (matricerandom[i][j-1]<20){
            Biome b = new Biome(20, 20, i, j,choisirCouleurGOLDENROD(i,j), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
            grille.add(b.getForme(), j, i);
            grille.add(b.getGrille(), j, i);
            return b;
        }
        else if(20<=matricerandom[i][j-1]&&matricerandom[i][j]<40){
            Biome b = new Biome(20, 20, i, j,choisirCouleurRED(i,j), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
            grille.add(b.getForme(), j, i);
            grille.add(b.getGrille(), j, i);
            return b;
        }
        else if(40<=matricerandom[i][j-1]&&matricerandom[i][j]<60){
            Biome b = new Biome(20, 20, i, j,choisirCouleurGREEN(i,j), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
            grille.add(b.getForme(), j, i);
            grille.add(b.getGrille(), j, i);
            return b;
        }
        else if(60<=matricerandom[i][j-1]&&matricerandom[i][j]<80){
            Biome b = new Biome(20, 20, i, j,choisirCouleurBLUE(i,j), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
            grille.add(b.getForme(), j, i);
            grille.add(b.getGrille(), j, i);
            return b;
        }
        else{
            Biome b = new Biome(20, 20, i, j,choisirCouleurYELLOW(i,j), matriceVoisin); // couleurs[matricerandom[coordx][coordy]]
            grille.add(b.getForme(), j, i);
            grille.add(b.getGrille(), j, i);
            return b;
        }*/
    }

    // créateur de carré
    public void creerCarre(int i, int j){
        Rectangle r = new Rectangle(20, 20, choisirCouleur(i,j));
        grille.add(r,j,i);
        /*if (i==0&&j==0){
            Rectangle r = new Rectangle(20, 20, choisirCouleur(i,j));
            grille.add(r,j,i);
        }
        else if (i==0||j==0){
            Rectangle r = new Rectangle(20, 20, choisirCouleur(i,j));
            grille.add(r,j,i);
        }
        else if (matricerandom[i-1][j]<20){
            Rectangle r = new Rectangle(20, 20, choisirCouleurGOLDENROD(i,j));
            grille.add(r,j,i);
        }
        else if(20<=matricerandom[i-1][j]&&matricerandom[i][j]<40){
            Rectangle r = new Rectangle(20, 20, choisirCouleurRED(i,j));
            grille.add(r,j,i);
        }
        else if(40<=matricerandom[i-1][j]&&matricerandom[i][j]<60){
            Rectangle r = new Rectangle(20, 20, choisirCouleurGREEN(i,j));
            grille.add(r,j,i);
        }
        else if(60<=matricerandom[i-1][j]&&matricerandom[i][j]<80){
            Rectangle r = new Rectangle(20, 20, choisirCouleurBLUE(i,j));
            grille.add(r,j,i);
        }
        else{
            Rectangle r = new Rectangle(20, 20, choisirCouleurYELLOW(i,j));
            grille.add(r,j,i);
        }*/
    }
=======


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
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79

        new Biome(20, 20, coordx, coordy,choisirCouleur(coordx, coordy), matriceVoisin);
=======
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
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    }

<<<<<<< HEAD
    ////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////

<<<<<<< HEAD
    // choix couleur du carré et du biome :
    public Color choisirCouleur(int i,int j) {
        return couleurs[matricerandom[i][j]];
    }

    /*public Color choisirCouleurGOLDENROD(int i,int j) {
        if (matricerandom[i][j] < 91) {
            return Color.GOLDENROD;
        } else if (91<=matricerandom[i][j]&&matricerandom[i][j]<93) {
            return Color.RED;
        } else if (93<=matricerandom[i][j]&&matricerandom[i][j]<95) {
            return Color.GREEN;
        } else if (95<=matricerandom[i][j]&&matricerandom[i][j]<97){
            return Color.BLUE;
        } else {
            return Color.YELLOW;
        }
=======
    // créateur de carré
    public void creerCarre(double coordx, double coordy){
        Main.gc.setFill(choisirCouleur((int)coordx, (int)coordy));
        Main.gc.fillRect(coordy*20, coordx*20, 20, 20);
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    }

    public Color choisirCouleurRED(int i,int j) {
        if (matricerandom[i][j] < 2) {
            return Color.GOLDENROD;
        } else if (2<=matricerandom[i][j]&&matricerandom[i][j]<93) {
            return Color.RED;
        } else if (93<=matricerandom[i][j]&&matricerandom[i][j]<95) {
            return Color.GREEN;
        } else if (95<=matricerandom[i][j]&&matricerandom[i][j]<97){
            return Color.BLUE;
        } else {
            return Color.YELLOW;
        }
    }

<<<<<<< HEAD
    public Color choisirCouleurGREEN(int i,int j) {
        if (matricerandom[i][j] < 2) {
            return Color.GOLDENROD;
        } else if (2<=matricerandom[i][j]&&matricerandom[i][j]<4) {
            return Color.RED;
        } else if (4<=matricerandom[i][j]&&matricerandom[i][j]<95) {
            return Color.GREEN;
        } else if (95<=matricerandom[i][j]&&matricerandom[i][j]<97){
            return Color.BLUE;
        } else {
            return Color.YELLOW;
        }
    }

    public Color choisirCouleurBLUE(int i,int j) {
        if (matricerandom[i][j] < 2) {
=======
    ////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////


=======
    ////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////


>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    // choix couleur du carré et du biome : (on peut aussi mettre cette fonction dans Biome)
    public Color choisirCouleur(int i,int j) {
        if (matricerandom[i][j] == 0) {
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
            return Color.GOLDENROD;
        } else if (2<=matricerandom[i][j]&&matricerandom[i][j]<4) {
            return Color.RED;
        } else if (4<=matricerandom[i][j]&&matricerandom[i][j]<6) {
            return Color.GREEN;
        } else if (6<=matricerandom[i][j]&&matricerandom[i][j]<97) {
            return Color.BLUE;
        } else {
            return Color.YELLOW;
        }
    }

    public Color choisirCouleurYELLOW(int i,int j) {
        if (matricerandom[i][j] < 2) {
            return Color.GOLDENROD;
        } else if (2<=matricerandom[i][j]&&matricerandom[i][j]<4) {
            return Color.RED;
        } else if (4<=matricerandom[i][j]&&matricerandom[i][j]<6) {
            return Color.GREEN;
        } else if (6<=matricerandom[i][j]&&matricerandom[i][j]<8) {
            return Color.BLUE;
        } else {
            return Color.YELLOW;
        }
    }*/

<<<<<<< HEAD
<<<<<<< HEAD

    ////////////////////////////////////////////////////////  getter, setter et toString : ///////////////////////////////////////////////////////

    // getter :
    public GridPane getGrille() {
        return grille;
    }

=======
    ////////////////////////////////////////////////////////  getter, setter et toString : ///////////////////////////////////////////////////////

=======
    ////////////////////////////////////////////////////////  getter, setter et toString : ///////////////////////////////////////////////////////

>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    // getter :


    public int getLongueur() {
        return longueur;
    }

    public int getHauteur() {
        return hauteur;
<<<<<<< HEAD
    }

    public int getL2() {
        return l2;
    }

=======
    }

    public int getL2() {
        return l2;
    }

>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    public int getH2() {
        return h2;
    }

<<<<<<< HEAD
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
=======
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
    // to String :
    @Override
    public String toString() {
        return "MapProcedurale{" +
                "matricerandom=" + Arrays.toString(matricerandom) +
                '}';

    }
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
=======
}
>>>>>>> 90c6f289c940711cd9b6b8abc79dbc4f78d0fd79
