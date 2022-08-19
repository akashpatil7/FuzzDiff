package classes;

public class TakeuchiKnuth {
    public int takeuchi(int x, int y, int z){
        if(x <= y){
            return y;
        }
        else{
            return takeuchi(takeuchi(x-1,y,z), takeuchi(y-1,x,z), takeuchi(z-1,x,y));
        }
    }
}
