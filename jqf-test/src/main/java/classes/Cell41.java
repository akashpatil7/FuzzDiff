package classes;

public class Cell41 {
    public static int y1 = 0;
    public static int y2 = 0;
    public static int p = 0;

    public int execute(int i){
        set(i);
        return get();
    }

    public void set(int z){
        p = p + 1;
        if(p%2 == 0)
            y1 = z;
        else
            y2 = z;
    }

    public int get(){
        if(p%2 == 0){
            return y1;
        }
        else{
            return y2;
        }
    }
}
