package hobbit.inequiv;

public class ReveAckermannIneq {
    public int rev(int m, int n){
        int r = 0;
        int x = 0;
        if(m == 0){
            r = n + 1;
        }
        else if(m > 0 && n == 0){
            r = rev(m - 1, 1);
        }
        else{
            x = rev(m, n-1);
            r = rev(m-1, x);
        }
        return r;
    }
}
