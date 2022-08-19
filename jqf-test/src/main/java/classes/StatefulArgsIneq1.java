package classes;

public class StatefulArgsIneq1 {
    public int x = 0;

    //state has changed so not equivalent
    public boolean stateful(){
        x = x - 1;
        return x == 2;
    }
}
