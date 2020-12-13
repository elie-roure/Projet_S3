package sample;

import javafx.scene.paint.Color;

import static sample.InterfaceJoueur.contour;

/*
Principe :
    - 1 fragment de MapProcédurale.
 */

public class Biome {

	///////////////////////////////////////////////////////////////  attributs : ////////////////////////////////////////////////////////////////


	// attributs en commun a tous les Biome zoomer:
	private int l;                    // longueur biome
	private int h;                    // hauteur biome
	private int l2;                    // longueur grille dans Biome
	private int h2;                    // hauteur grille dans Biome


	// attributs lié au Biome courant :
	private Aleatoire aleatoire;
	private int nbAleatoire;        	// nombre aléatoire du Biome
	private int[][] matricerandom;		// matrice du biome (20*20)
	private int coordx;                // coord x du nb du Biome dans MatriceMap
	private int coordy;                // coord x du nb du Biome dans MatriceMap

	// les voisins:
	private int[][] matriceVoisin;

	// pas définitif :
	private Color couleur;            // couleur du Biome
	private Color variationColor = Color.BLACK;
	public boolean dezoom;
	private boolean destructible;

	private MapProcedurale mapProcedurale;


	private Color[] couleurs;


	///////////////////////////////////////////////////////////////  constructeur : ////////////////////////////////////////////////////////////////

	public Biome(int l2, int h2, int coordx, int coordy, Color couleur, int nbAleatoire, int[][] matriceVoisin, int[] place, MapProcedurale mapProcedurale) {

		Color[] c1 = {Color.GOLDENROD,Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
		couleurs=c1;

		l = 500;
		h = 500;
		this.coordx = coordx;
		this.coordy = coordy;
		this.l2 = l2;
		this.h2 = h2;
		this.couleur = couleur;
		this.nbAleatoire = nbAleatoire;
		this.mapProcedurale=mapProcedurale;

		matricerandom = new int[l2][h2];
		this.matriceVoisin = matriceVoisin;

		dezoom = true;
		destructible = false;

		aleatoire = new Aleatoire(Integer.parseInt("" + nbAleatoire + coordx + coordy), 100);

		remplirNbaleatoire(nbAleatoire);
		remplirBiome(place[0],place[1]);
	}

	/////////////////////////////////////////////////////////  méthodes de remplissages  : ///////////////////////////////////////////////////////


	///////////////////////////////////////////////////////// zoomé :

	// remplir la matrice de nb pour les sous-Biome:
	public void remplirNbaleatoire(int nb){
		for (int i = 0; i < l2; i++) {
			for (int j = 0; j < h2; j++) {
				if (coordx==0&&coordy==0){//en haut a gauche
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j] = nbAleatoire;
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),mapProcedurale.getIntCouleurBiome(coordy+1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if (coordx==mapProcedurale.getLargeur()&&coordy==0){//en haut a droite
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j] = nbAleatoire;
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]=choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if(coordx==0&&coordy==mapProcedurale.getHauteur()){// en bas a gauche
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),mapProcedurale.getIntCouleurBiome(coordy-1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j] = nbAleatoire;
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if(coordx==mapProcedurale.getLargeur()&&coordy==mapProcedurale.getHauteur()){// en bas a droite
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j] = nbAleatoire;
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if (coordy<mapProcedurale.getHauteur()&&coordy>0&&coordx<mapProcedurale.getLargeur()&&coordx>0){ //si je suis au milieu
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),mapProcedurale.getIntCouleurBiome(coordy-1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]=choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),mapProcedurale.getIntCouleurBiome(coordy+1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if(coordx==0&&coordy>0&&coordy<mapProcedurale.getHauteur()){//gauche
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),mapProcedurale.getIntCouleurBiome(coordy-1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),mapProcedurale.getIntCouleurBiome(coordy+1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if(coordx==mapProcedurale.getLargeur()&&coordy>0&&coordy<mapProcedurale.getHauteur()){//droite
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]=choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if(coordy==0&&coordx>0&&coordx<mapProcedurale.getLargeur()){ //haut
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]=choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),mapProcedurale.getIntCouleurBiome(coordy+1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy+1,coordx),nbAleatoire);
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j] = nbAleatoire;
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}else if(coordy==mapProcedurale.getLargeur()&&coordx>0&&coordx<mapProcedurale.getHauteur()){// bas
					if (i<(h2/4)&&j<(l2/4)){//en haut a gauche
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx-1),mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (j>((l2/4)*3)-1&&i<(h2/4)) {//en haut a droite
						matricerandom[i][j]= choixIntCouleurAngles(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),mapProcedurale.getIntCouleurBiome(coordy-1,coordx+1),mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if(i>(h2/4*3)-1&&j<(l2/4)){//en bas a gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4*3)-1){//en bas a droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j<(l2/4)){ //gauche
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx-1),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4*3)-1){ //droite
						matricerandom[i][j]=  choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy,coordx+1),nbAleatoire);
					}else if (i>(h2/4*3)-1&&j>(l2/4)-1&&j<(l2/4)*3){//bas
						matricerandom[i][j] = nbAleatoire;
					}else if (i<(h2/4)&&j>(l2/4)-1&&j<(l2/4)*3){//haut
						matricerandom[i][j]= choixIntCouleurCotes(mapProcedurale.getIntCouleurBiome(coordy-1,coordx),nbAleatoire);
					}else if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3){// centre
						matricerandom[i][j] = nbAleatoire;
					}
				}
			}
		}
	}

	public int choixIntCouleurAngles(int couleurVoisinA, int couleurVoisinB, int couleurVoiinC, int couleurBiome){
		Aleatoire proba=new Aleatoire(aleatoire.donneRandom(),100);
		int nbAlea = proba.donneRandom();
		if (nbAlea<15){
			return couleurVoisinA;
		}else if (nbAlea<30){
			return couleurVoisinB;
		}else if(nbAlea<45){
			return couleurVoiinC;
		}else{
			return couleurBiome;
		}
	}

	public int choixIntCouleurCotes(int couleurVoisin, int couleurBiome){
		Aleatoire proba=new Aleatoire(aleatoire.donneRandom(),100);
		int nbAlea = proba.donneRandom();
		if (nbAlea<25){
			return couleurVoisin;
		}else return couleurBiome;
	}


	// remplir  les Biome d'un carré:
	public void remplirBiome(int placex, int placey){
		for(int i = 0; i< l2; i++){
			for(int j = 0; j< h2; j++){
				/*if (i>(h2/4)-1&&i<(h2/4)*3&&j>(l2/4)-1&&j<(l2/4)*3) {
					Main.gc.setFill(choixcouleurCentre(i, j));
					Main.gc.fillRect(j * 20 + contour + 400 * placex - 200, i * 20 + contour + 400 * placey - 200, 20, 20);
				}else {*/
				Main.gc.setFill(choisirCouleur(i, j));
				Main.gc.fillRect(j * 20 + contour + 400 * placex - 200, i * 20 + contour + 400 * placey - 200, 20, 20);

			}
		}
	}


	////////////////////////////////////////////////////////  méthodes de choix du visuel : //////////////////////////////////////////////////////

	// Attribution de la couleur des sous-biome :
	public Color choixcouleurCentre(int i,int j){
		if (matricerandom[i][j] < 0){
			return Color.WHITESMOKE;
		}
		else if (matricerandom[i][j] < 5){
			return Color.BLACK;
		}
		else {
			return couleur;
		}
	}


	public Color choisirCouleur(int i,int j) {
		Aleatoire proba=new Aleatoire(aleatoire.donneRandom(),100);
		int nbAlea = proba.donneRandom();
		if (nbAlea<3){
			return Color.BLACK;
		}else {
			return couleurs[matricerandom[i][j]];
		}
	}

	/*public Color choisirCouleur(int i,int j) {
		if (matricerandom[i][j]<0){
			return Color.WHITESMOKE;
		} else if (matricerandom[i][j] ==0) {
			return Color.BLUE;		// couleur du bord
		} else if (matricerandom[i][j] ==1) {
			return Color.GOLDENROD;
		}else if (matricerandom[i][j] ==2 ){
			return Color.RED;
		} else if (matricerandom[i][j] ==3) {
			return Color.GREEN;
		} else if (matricerandom[i][j] ==4) {
			return Color.BLUE;
		} else {
			return Color.YELLOW;
		}
	}*/

/*
	public Color choisirCouleurVoisin(int i) {
		if (matriceVoisin[i] == -1) {
			return Color.BLUE;		// couleur du bord
		} else if (matriceVoisin[i] == 0) {
			return Color.GOLDENROD;
		}else if (matriceVoisin[i] == 1) {
			return Color.RED;
		} else if (matriceVoisin[i] == 2) {
			return Color.GREEN;
		} else if (matriceVoisin[i] == 3) {
			return Color.BLUE;
		} else {
			return Color.YELLOW;
		}
	}*/




	///////////////////////////////////////////////////////  getter, setter et toString : ////////////////////////////////////////////////////////

	// getter :

	public int getCoordx() {
		return coordx;
	}

	public int getCoordy() {
		return coordy;
	}

	// to String du biome

	@Override
	public String toString() {
		String phrase = "Biome : " + coordx + " ; " + coordy + " de seed " + matriceVoisin[1][1] + "\nmatrice voisin : \n";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				phrase += matriceVoisin[i][j] + " ";
			}
			phrase += "\n";
		}

		return phrase;
	}

	public String toStringContenue() {
		String phrase = "Biome : " + coordx + " ; " + coordy + "\nmatrice interieur : \n";
		for (int i = 0; i < h2; i++) {
			for (int j = 0; j < l2; j++) {
				phrase += matricerandom[i][j] + " ";
			}
			phrase += "\n";
		}

		return phrase;
	}

}