package hobbit.inequiv;

public class ReveAddHornIneq {
    public int rev(int i, int j){
        int r = 0;
        if(i == 0){
            r = j;
        }
        else{
            r = rev(i - 1, j + 1);
        }
        return r;
    }
}
