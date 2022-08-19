package classes;

public class Mccarthy {

    public int mccarthy(int n){
        if(n > 100){
            return n-10;
        }
        else{
            return mccarthy(mccarthy(n+11));
        }
    }
}
