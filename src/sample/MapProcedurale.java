package sample;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;

public class MapProcedurale extends Parent {

    // arguments :
    private GridPane grille;
    private ArrayList listerandom;

    private int longueur;
    private int hauteur;

    // constructeur :
    public MapProcedurale(int longueur, int hauteur) {

        this.longueur = longueur-1;
        this.hauteur = hauteur-1;

        grille = new GridPane();
        remplir(0, 0);
        getChildren().add(grille);
    }


    // remplissage de la fenetre (en récursif)
    public void remplir(int i, int j){

        if (!(i == longueur && j == hauteur)){

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


    // créateur de carré
    public void creerCarre(int coordx, int coordy){
        Rectangle r = new Rectangle(20, 20, Color.BLUE);
        grille.add(r, coordy, coordx);
    }


    // choix couleur du carré
    public Color choisirCouleur(){
        return Color.BLUE;
    }
}
