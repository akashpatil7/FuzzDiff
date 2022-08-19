package hobbit.equiv;

public class Swap1 {

    public Tuple swap(Tuple num){
        int a = num.x;
        int b = num.y;
        num.x = a - b;
        num.y = a + b;
        num.x = b - a;
        return new Tuple(num.x, num.y);
    }
}
