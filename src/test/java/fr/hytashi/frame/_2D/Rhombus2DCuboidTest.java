package fr.hytashi.frame._2D;

import fr.hytashi.frame.MockWorld;
import fr.hytashi.frame.util.Pair;
import org.bukkit.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Rhombus2DCuboidTest {

    private final MockWorld world = new MockWorld("world");

    @Test
    public void testOrientation() {
        Assertions.assertEquals(new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(0, 5)),
                Pair.of(new Coordinates2D(5, 10), new Coordinates2D(5, 5))).getOrientation(), Rhombus2DCuboid.Orientation.X);

        Assertions.assertEquals(new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(5, 0)),
                Pair.of(new Coordinates2D(5, 5), new Coordinates2D(10, 5))).getOrientation(), Rhombus2DCuboid.Orientation.Z);
    }

    @Test
    public void testContains() {
        // X
        Rhombus2DCuboid cuboid1 = new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(0, 5)),
                Pair.of(new Coordinates2D(5, 10), new Coordinates2D(5, 5)));

        Assertions.assertTrue(cuboid1.contains(new Location(world, 1, 0, 6)));
        Assertions.assertTrue(cuboid1.contains(new Location(world, 3, 0, 5)));
        Assertions.assertTrue(cuboid1.contains(new Location(world, 0, 0, 0)));
        Assertions.assertTrue(cuboid1.contains(new Location(world, 5, 0, 10)));

        // Z
        Rhombus2DCuboid cuboid2 = new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(5, 0)),
                Pair.of(new Coordinates2D(5, 5), new Coordinates2D(10, 5)));

        Assertions.assertTrue(cuboid2.contains(new Location(world, 0, 0, 0)));
        Assertions.assertTrue(cuboid2.contains(new Location(world, 3, 0, 3)));
        Assertions.assertTrue(cuboid2.contains(new Location(world, 5, 0, 0)));
        Assertions.assertTrue(cuboid2.contains(new Location(world, 5, 0, 2)));
        Assertions.assertTrue(cuboid2.contains(new Location(world, 9, 0, 5)));

    }

    @Test
    public void testExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(5, 5)),
                Pair.of(new Coordinates2D(5, 5), new Coordinates2D(10, 5))));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(0, 5)),
                Pair.of(new Coordinates2D(10, 10), new Coordinates2D(5, 5))));
    }

    @Test
    public void testEquals() {
        Assertions.assertEquals(new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(0, 5)),
                        Pair.of(new Coordinates2D(5, 10), new Coordinates2D(5, 5))),
                new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(0, 5)),
                        Pair.of(new Coordinates2D(5, 10), new Coordinates2D(5, 5))));

        Assertions.assertEquals(new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(5, 0)),
                        Pair.of(new Coordinates2D(5, 5), new Coordinates2D(10, 5))),
                new Rhombus2DCuboid(world, Pair.of(new Coordinates2D(0, 0), new Coordinates2D(5, 0)),
                        Pair.of(new Coordinates2D(5, 5), new Coordinates2D(10, 5))));
    }

}
