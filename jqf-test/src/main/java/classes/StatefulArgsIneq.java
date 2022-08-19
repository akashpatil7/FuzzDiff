package classes;

public class StatefulArgsIneq {

    public int x = 0;

    public boolean stateful(){
        x = x + 1;
        return x == 2;
    }
}
