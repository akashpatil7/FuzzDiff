package classes;

public class Arrays11 {

    public int arrays(int[] arr, int i){
        return getIndex(arr, 9 - i);
    }

    private int getIndex(int[] arr, int i){
        if(0 <= i){
            if(i<10){
                return arr[9-i];
            }
            else
                return arr[0];
        }
        else
            return arr[0];
    }

}
