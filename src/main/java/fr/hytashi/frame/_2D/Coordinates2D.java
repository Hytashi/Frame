package fr.hytashi.frame._2D;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Coordinates2D {

    private final int x, z;

    public Coordinates2D(int x, int z) {
        this.x = x;
        this.z = z;
    }

}
