package classes;

import java.io.Serializable;

public class Tuple implements Serializable {
    int x;
    int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
