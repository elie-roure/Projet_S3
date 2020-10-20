package sample;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.Arrays;

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
        remplirNbAleatoire();
        remplirDeBiome();

        //remplir(0, 0);

        this.getChildren().add(grille);

    }


    //remplissage de la matrice .matricerandom (itératif)
    public void remplirNbAleatoire(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                matricerandom[i][j] = aleatoire.donneRandom();
            }
        }
    }

    //remplissage de Biome dans la grille (itératif)
    public void remplirDeBiome(){
        for(int i=0 ;i<=longueur;i++){
            for(int j=0 ; j<=hauteur ; j++){
                creerBiome(i,j);
            }
        }
    }

    // créateur de Biome :
    public void creerBiome(int coordx, int coordy){
        Biome b = new Biome(20, 20, coordx, coordy, matricerandom);
        grille.add(b.getForme(), coordy, coordx);
        grille.add(b.getGrille(), coordy, coordx);
    }


    // fonction brouillon / pas-optimale :

    /*
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
*/

    @Override
    public String toString() {
        return "MapProcedurale{" +
                "matricerandom=" + Arrays.toString(matricerandom) +
                '}';
    }
}
