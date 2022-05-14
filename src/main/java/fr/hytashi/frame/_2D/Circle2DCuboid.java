package fr.hytashi.frame._2D;

import fr.hytashi.frame.base.Cuboid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Hytashi
 * @version 1.1.0
 */
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Circle2DCuboid extends Cuboid {

    private final World world;
    private final Location center;
    private final int radius;

    /**
     * Constructor
     *
     * @param center the center of the circle
     * @param radius the radius of the circle
     */
    public Circle2DCuboid(Location center, int radius) {
        this.center = center;
        this.world = center.getWorld();
        this.radius = radius;
    }

    @Override
    public boolean contains(Location location) {
        if (location.getWorld() != this.world)
            return false;

        return (Math.pow(location.getX() - center.getX(), 2) + Math.pow(location.getZ() - center.getZ(), 2)) <= Math.pow(radius, 2);
    }

}
