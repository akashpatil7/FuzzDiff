package hobbit.equiv;

public class TakeuchiKnuth1 {
    public int takeuchi(int x, int y, int z){
        if(x <= y){
            return y;
        }
        else if(y <= z){
            return z;
        }
        else{
            return x;
        }
    }
}
