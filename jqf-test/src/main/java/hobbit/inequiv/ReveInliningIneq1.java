package hobbit.inequiv;

public class ReveInliningIneq1 {
    public int rev(int x){
        int r = x;
        if(x > 1){
            r =  rev(x - 2);
            r = r + 2;
        }
        if(x < 2){
            r = 0;
        }
        return r;
    }
}
