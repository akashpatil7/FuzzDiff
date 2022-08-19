package classes;

public class CounterV21 {
    public static int c = 0;

    public int execute(){
        count();
        return get();
    }

    public void count(){
        c = c - 2;
    }

    public int get(){
        return -c/2;
    }
}
