package fr.hytashi.frame._2D;

import fr.hytashi.frame.MockWorld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Square2DCuboidTest {

    private final MockWorld world = new MockWorld("world");

    @Test
    public void testContainsPositive() {
        Square2DCuboid c = new Square2DCuboid(world, new Coordinates2D(50, 30), new Coordinates2D(60, 12));
        Assertions.assertTrue(c.contains(50, 0, 12));
        Assertions.assertTrue(c.contains(50, 999, 12));
        Assertions.assertTrue(c.contains(55, 50, 12));
        Assertions.assertTrue(c.contains(55, -20, 29));
        Assertions.assertFalse(c.contains(68, 18, 29));
        Assertions.assertFalse(c.contains(4564165, 12, 0));
        Assertions.assertFalse(c.contains(0, 50, 45154154));
        Assertions.assertFalse(c.contains(-900, 50, -415));
    }

    @Test
    public void testContainsNegative() {
        Square2DCuboid c = new Square2DCuboid(world, new Coordinates2D(-500, 30), new Coordinates2D(-216, 12));
        Assertions.assertTrue(c.contains(-239, 0, 12));
        Assertions.assertTrue(c.contains(-500, 999, 12));
        Assertions.assertFalse(c.contains(68, 18, 29));
        Assertions.assertFalse(c.contains(4564165, 12, 0));
        Assertions.assertFalse(c.contains(0, 50, 45154154));
        Assertions.assertFalse(c.contains(-900, 50, -415));
    }

}
