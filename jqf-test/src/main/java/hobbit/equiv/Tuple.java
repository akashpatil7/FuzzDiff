package hobbit.equiv;

import java.io.Serializable;

public class Tuple implements Serializable {
    int x;
    int y;

    Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
