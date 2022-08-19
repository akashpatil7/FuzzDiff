package classes;

public class RecurFactorial1 {
    private int fun_acc(int n, int acc){
        if(n <= 1){
            return acc;
        }
        else
            return n * fun_acc(n-1, n * acc);
    }

    public int factorial(int n){
        return fun_acc(n, 1);
    }
}
