package hobbit.equiv;

public class Arrays1 {

    public int arrays(int[] arr, int i){
        if(0 <= i){
            if(i<10){
                return arr[i];
            }
            else
                return arr[0];
        }
        else
            return arr[0];
    }
}
