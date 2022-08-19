package classes;

public class Counter {
    public static int[] c = new int[2];

    public int execute(){
        c[0] = 0;
        c[1] = 0;
        count();
        return get();
    }

    public void count(){
        c[1]++;
    }

    public int get(){
        return c[1];
    }
}
