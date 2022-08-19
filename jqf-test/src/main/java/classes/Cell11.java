package classes;

public class Cell11 {
    public static int y1 = 0;
    public static int y2 = 0;
    public static boolean p = true;

    public int execute(int i){
        set(i);
        return get();
    }

    public void set(int z){
        p = !p;
        y1 = z;
        y2 = z;
    }

    public int get(){
        if(!p){
            return y1;
        }
        else{
            return y2;
        }
    }
}
