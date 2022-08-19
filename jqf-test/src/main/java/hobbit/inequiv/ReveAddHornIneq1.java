package hobbit.inequiv;

public class ReveAddHornIneq1 {
    public int rev(int i, int j){
        int r = 0;
        if(i == 0){
            r = j;
        }
        else if(i == 1){
            r = j + 1;
        }
        else if(i == 2){
            r = j;
        }
        else{
            r = rev(i - 1, j + 1);
        }
        return r;
    }
}
