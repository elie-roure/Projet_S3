package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Génération");


        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);


        //On creer un nouveau stage ou sera afficher la carte uniquement pour qu'on puisse la modifier en directe avec l'autre stage
        Stage mapStage = new Stage();
        mapStage.setTitle("Map");
        Group mapGroup = new Group();
        Scene mapScene = new Scene(mapGroup,500,500);
        mapStage.setScene(mapScene);

        demmarage(root, mapGroup, mapStage);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void demmarage(Group root, Group mapGroup, Stage mapStage){
        //On creer les texte d'indication
        Text tLongueur = new Text("Saisir la longueur de la carte (entre 0 et 50)");
        Text tHauteur = new Text("Saisir la hauteur de la carte (entre 0 et 50)");
        Text tSeed = new Text("Saisir la seed de la carte ( entre 0 et 999)");

        //On creer les case ou saisir les valeurs
        IntField longueur = new IntField(0,50,20);
        IntField hauteur = new IntField(0,50,20);
        IntField seed = new IntField(0,999,0);

        //On positionne le tout
        tLongueur.setTranslateX(100);
        tHauteur.setTranslateX(100);
        tSeed.setTranslateX(100);

        tLongueur.setTranslateY(75);
        tHauteur.setTranslateY(175);
        tSeed.setTranslateY(275);

        longueur.minHeight(100);
        hauteur.minWidth(100);
        seed.minWidth(100);

        longueur.setTranslateX(100);
        hauteur.setTranslateX(100);
        seed.setTranslateX(100);

        longueur.setTranslateY(100);
        hauteur.setTranslateY(200);
        seed.setTranslateY(300);

        //On creer un bouton generer qui va creer une map avec les paramettres precedement remplis ou les parrametre par default
        Button generer = new Button("Generer la map");
        generer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mapStage.show();
                MapProcedurale map = new MapProcedurale(longueur.getValue(),hauteur.getValue(), seed.getValue());
                mapGroup.getChildren().add(map);
                //root.getChildren().removeAll(longueur,hauteur,seed,generer, tHauteur, tLongueur, tSeed);
            }
        });
        // MapProcedurale map = new MapProcedurale(20,20, 6);
        root.getChildren().addAll(longueur, hauteur, seed, generer, tHauteur, tLongueur, tSeed);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
