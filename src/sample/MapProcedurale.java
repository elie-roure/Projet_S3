package sample;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;

public class MapProcedurale extends Parent {

    // arguments :
    private GridPane grille;
    private int[][] matricerandom;

    private int longueur;
    private int hauteur;

    private Aleatoire aleatoire;

    // constructeur :
    public MapProcedurale(int longueur, int hauteur, int seed) {

        this.longueur = longueur-1;
        this.hauteur = hauteur-1;
        aleatoire = new Aleatoire(seed, 5);

        matricerandom = new int[longueur][hauteur];
        grille = new GridPane();
        remplirBis();
        //remplir(0, 0);
        this.getChildren().add(grille);

    }


    // remplissage de la fenetre (en récursif)
    // + remplissage matriceRandom
    public void remplir(int i, int j){

        if (!(i == longueur && j == hauteur)){

            matricerandom[i][j] = aleatoire.donneRandom();
            creerCarre(i,j);

            if (i == longueur){
                remplir(0,j+1);
            }
            else {
                remplir(i+1,j);
            }
        }
        else {
            creerCarre(i,j);
        }
    }
    public void remplirBis(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                matricerandom[i][j] = aleatoire.donneRandom();
                creerCarre(i,j);
            }
        }
    }

    // créateur de carré
    public void creerCarre(int coordx, int coordy){
        Rectangle r = new Rectangle(20, 20, choisirCouleur(coordx, coordy));
        grille.add(r, coordy, coordx);
    }


    // choix couleur du carré
    public Color choisirCouleur(int i,int j){
        if (matricerandom[i][j] == 0){
            return Color.GOLDENROD;
        }
        else if (matricerandom[i][j] == 1){
            return Color.RED;
        }
        else if (matricerandom[i][j] == 2){
            return Color.GREEN;
        }
        else if (matricerandom[i][j] == 3){
            return Color.BLUE;
        }
        else{
            return Color.YELLOW;
        }

    }
}
