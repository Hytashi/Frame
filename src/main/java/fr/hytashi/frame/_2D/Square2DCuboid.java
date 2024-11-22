package fr.hytashi.frame._2D;

import fr.hytashi.frame.base.Cuboid;
import org.bukkit.Location;
import org.bukkit.World;

public class Square2DCuboid extends Cuboid {

    private final int minX, maxX, minZ, maxZ;

    public Square2DCuboid(World world, Coordinates2D corner1, Coordinates2D corner2) {
        super(world);
        this.minX = Math.min(corner1.getX(), corner2.getX());
        this.maxX = Math.max(corner1.getX(), corner2.getX());
        this.minZ = Math.min(corner1.getZ(), corner2.getZ());
        this.maxZ = Math.max(corner1.getZ(), corner2.getZ());
    }

    @Override
    public boolean contains(Location location) {
        return location.getX() >= minX && location.getX() <= maxX && location.getZ() >= minZ && location.getZ() <= maxZ;
    }

}
