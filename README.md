# Projet_S3
Projet S3 : Génération procédurale de cartes par une approche top-bottom




## Ce qu'il faut savoir pour javafx :

Du coup j'ai fait en sorte d'initialiser le projet en javaFX.
En fait si vous essayez (en l'ouvrant avec intellij idea) de run le main ca devrait marcher.

Mais pour que ca marche il faut d'abord :

 - dl le javafx sdk plus recent que la 14.0.1 (https://gluonhq.com/products/javafx/ le 15)
 (https://openjfx.io/openjfx-docs/#introduction ce tuto est en anglais mais il est pas mal si vous arrivez pas a mettre javafx)
 
 
 - Avoir une variable d'environnement JAVA_FX vers le dossier lib de javafx
   - (RUN -> Edit configuration -> Environnement variable)
   - En gros vous devrez y mettre un truc comme ca (en mettant votre propre chemin): JAVA_FX=/home/gab/Téléchargements/javafx-sdk-14.0.1/lib
 
 - Ajouter le dossier lib de javafx à project structure
    - File -> Project Structure -> Librairies ->  + (en haut) -> java -> et la vous selectionnez le dossier lib de votre javafx sdk 14.0.1
    - oubliez pas d'appuyer sur apply en bas a droite de la fenetre pour valider les modif
   
 
 - Ajouter:  --module-path ${JAVA_FX} --add-modules javafx.controls,javafx.fxml aux VM options dans RUN -> Edit configuration
