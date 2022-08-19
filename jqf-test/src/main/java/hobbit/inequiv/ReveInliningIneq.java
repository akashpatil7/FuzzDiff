package hobbit.inequiv;

public class ReveInliningIneq {
    public int rev(int x){
        int r = x;
        if(x > 0){
            r =  rev(x - 1);
            r = r + 1;
        }
        if(x < 0){
            r = 0;
        }
        return r;
    }
}
