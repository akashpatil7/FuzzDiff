package hobbit.equiv;

public class RecurFactorial1 {
    private int fun_acc(int n, int acc){
        if(n <= 1){
            return acc;
        }
        else
            return fun_acc(n-1, n * acc);
    }

    public int factorial(int n){
        return fun_acc(n, 1);
    }
}
