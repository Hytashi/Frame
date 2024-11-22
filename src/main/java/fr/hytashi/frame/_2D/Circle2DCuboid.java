package fr.hytashi.frame._2D;

import fr.hytashi.frame.base.Cuboid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.World;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Circle2DCuboid extends Cuboid {

    private final Location center;
    private final int radius;

    public Circle2DCuboid(Location center, int radius) {
        super(center.getWorld());
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean contains(Location location) {
        if (location.getWorld() != getWorld())
            return false;

        return (Math.pow(location.getX() - center.getX(), 2) + Math.pow(location.getZ() - center.getZ(), 2)) <= Math.pow(radius, 2);
    }

}
