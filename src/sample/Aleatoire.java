package sample;
import java.util.Random;

public class Aleatoire {

    // arguments :
    private Random rd;
    private int seed;       // la graine
    private int taille;     // borne du nb aléatoire (0 à taille-1)

    // constructeur :
    public Aleatoire(int seed, int taille) {
        this.seed = seed;
        rd = new Random(seed);
        this.taille = taille;
    }

    // méthode :
    public int donneRandom(){
        return rd.nextInt(taille);      // donne un nb aléatoire
    }
}