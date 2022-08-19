package classes;

public class SyteciFactEe1 {
    private int aux(int m, int acc){
        if(m < 0){
            return acc;
        }
        else
            return aux(m-1, m * acc);
    }

    public int factorial(int n){
        return aux(n, 1);
    }
}
