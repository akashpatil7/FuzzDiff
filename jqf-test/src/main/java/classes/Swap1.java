package classes;

public class Swap1 {

    public Tuple swap(Tuple num){
        num.x = num.x + num.y;
        num.y = num.x - num.y;
        num.x = num.x - num.y;
        return new Tuple(num.x, num.y);
    }
}
