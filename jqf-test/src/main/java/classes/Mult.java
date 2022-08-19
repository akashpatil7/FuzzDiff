package classes;

public class Mult {
    public int mult(int x, int y){
        if(y < 0){
            return -1 * mult(x, -y);
        }
        else if (y == 0){
            return 0;
        }
        else
            return x + mult(x, y - 1);
    }
}
