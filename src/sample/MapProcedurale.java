package sample;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;

public class MapProcedurale extends Parent {

    // arguments :
    private GridPane grille;
    private ArrayList listerandom;

    // constructeur :
    public MapProcedurale() {

        Rectangle r1 = new Rectangle(20, 20, Color.BLUE);
        Rectangle r2 = new Rectangle(20, 20, Color.GREEN);
        Rectangle r3 = new Rectangle(20, 20, Color.RED);

        grille = new GridPane();

        grille.add(r1, 0, 0);
        grille.add(r2, 1, 1);
        grille.add(r3, 0, 1);
        getChildren().add(grille);
        System.out.println("oui");
    }
}
