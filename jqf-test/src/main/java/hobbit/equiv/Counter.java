package hobbit.equiv;

public class Counter {
    public static int[] c = new int[2];

    public int execute(int i, int j){
        count(i , j);
        return get();
    }

    public void count(int x, int y){
        c[0] = x;
        c[1] = y + 1;
    }

    public int get(){
        return c[1];
    }
}
