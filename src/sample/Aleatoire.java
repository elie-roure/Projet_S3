package sample;
import java.util.Random;

public class Aleatoire {

    private Random rd;
    private int seed;

    public Aleatoire(int seed) {
        this.seed = seed;
        rd = new Random(seed);
    }

    public int donneRandom(){
        return rd.nextInt();
    }
}
