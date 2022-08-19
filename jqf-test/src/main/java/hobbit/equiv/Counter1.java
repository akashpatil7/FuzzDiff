package hobbit.equiv;

public class Counter1 {
    public static int c = 0;

    public int execute(){
        count();
        return get();
    }

    public void count(){
        c = c - 1;
    }

    public int get(){
        return -c;
    }
}
