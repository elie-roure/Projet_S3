# Projet_S3
Projet S3 : Génération procédurale de cartes par une approche top-bottom




# Ce qu'il faut savoir :

Du coup j'ai fait en sorte d'initialiser le projet en javaFX.
Du coup en fait si vous essayez (en l'ouvrant avec intellij idea) de run le main ca devrait marcher.

Mais pour que ca marche il faut :

 - dl le sdk javafx 14.0.1 (vous trompez pas de version)
 - Avoir une variable d'environnement JAVA_FX vers le dossier lib de javafx
 
 - Ajouter le dossier lib de javafx à project structure
 - Ajouter --module-path ${JAVA_FX} --add-modules javafx.controls,javafx.fxml aux options VM dans run/configuration
