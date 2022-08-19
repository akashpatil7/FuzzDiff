package classes;

public class Cell1 {
    public static int y = 0;

    public int execute(int i){
        set(i);
        return get();
    }

    private void set(int z){
        y = z;
    }

    private int get(){
        return y;
    }

}
