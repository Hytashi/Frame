package fr.hytashi.frame._2D;

import fr.hytashi.frame.base.Cuboid;
import fr.hytashi.frame.util.FrameMath;
import fr.hytashi.frame.util.Pair;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Hytashi
 * @version 2.0.0
 */
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Rhombus2DCuboid extends Cuboid {

    private final World world;
    private final int[][] polygon;
    private final Orientation orientation;

    /**
     * Constructor
     *
     * @param pair1 the first side
     * @param pair2 the second side
     * @deprecated Use {@link Rhombus2DCuboid#Rhombus2DCuboid(World, Pair, Pair)}
     */
    @Deprecated
    public Rhombus2DCuboid(org.apache.commons.lang3.tuple.Pair<Location, Location> pair1, org.apache.commons.lang3.tuple.Pair<Location, Location> pair2) {

        this(pair1.getLeft().getWorld(), Pair.of(new Coordinates2D(pair1.getLeft().getBlockX(), pair1.getLeft().getBlockZ()),
                        new Coordinates2D(pair1.getRight().getBlockX(), pair1.getRight().getBlockZ())),
                Pair.of(new Coordinates2D(pair2.getLeft().getBlockX(), pair2.getLeft().getBlockZ()),
                        new Coordinates2D(pair2.getRight().getBlockX(), pair2.getRight().getBlockZ())));

        // Check if corners are in the same world
        HashSet<World> worlds = new HashSet<>(Arrays.asList(pair1.getLeft().getWorld(), pair1.getRight().getWorld(), pair1.getLeft().getWorld(), pair2.getRight().getWorld()));
        if (worlds.size() > 1)
            throw new IllegalArgumentException("Corners must be in the same world");

    }

    /**
     * Constructor
     *
     * @param world the world in which the cuboid is
     * @param side1 the first side
     * @param side2 the second side
     */
    public Rhombus2DCuboid(World world, Pair<Coordinates2D, Coordinates2D> side1, Pair<Coordinates2D, Coordinates2D> side2) {

        this.world = world;

        // Compute orientation
        if ((side1.getLeft().getX() == side1.getRight().getX()) && (side2.getLeft().getX() == side2.getRight().getX())) {

            int h1 = Math.abs(side1.getLeft().getZ() - side1.getRight().getZ());
            int h2 = Math.abs(side2.getLeft().getZ() - side2.getRight().getZ());
            if (h1 != h2)
                throw new IllegalArgumentException("Both sides should have the same length (found " + h1 + " and " + h2 + ")");

            polygon = new int[][]{
                    {side1.getLeft().getX(), Math.min(side1.getLeft().getZ(), side1.getRight().getZ())},
                    {side1.getLeft().getX(), Math.max(side1.getLeft().getZ(), side1.getRight().getZ())},
                    {side2.getLeft().getX(), Math.max(side2.getLeft().getZ(), side2.getRight().getZ())},
                    {side2.getLeft().getX(), Math.min(side2.getLeft().getZ(), side2.getRight().getZ())}
            };

            orientation = Orientation.X;

        } else if ((side1.getLeft().getZ() == side1.getRight().getZ()) && (side2.getLeft().getZ() == side2.getRight().getZ())) {

            int h1 = Math.abs(side1.getLeft().getX() - side1.getRight().getX());
            int h2 = Math.abs(side2.getLeft().getX() - side2.getRight().getX());
            if (h1 != h2)
                throw new IllegalArgumentException("Both sides should have the same length (found " + h1 + " and " + h2 + ")");

            polygon = new int[][]{
                    {Math.min(side1.getLeft().getX(), side1.getRight().getX()), side1.getLeft().getZ()},
                    {Math.max(side1.getLeft().getX(), side1.getRight().getX()), side1.getLeft().getZ()},
                    {Math.max(side2.getLeft().getX(), side2.getRight().getX()), side2.getLeft().getZ()},
                    {Math.min(side2.getLeft().getX(), side2.getRight().getX()), side2.getLeft().getZ()}
            };

            orientation = Orientation.Z;

        } else throw new IllegalArgumentException("The two locations of a pair must be aligned on at least one axis");

    }

    @Override
    public boolean contains(Location location) {

        if (!world.equals(location.getWorld()))
            return false;

        int x = location.getBlockX(), z = location.getBlockZ();

        for (int[] corner : polygon)
            if (corner[0] == x && corner[1] == z)
                return true;

        int windingNumber = 0;

        for (int i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
            int xi = polygon[i][0], yi = polygon[i][1];
            int xj = polygon[j][0], yj = polygon[j][1];

            if (orientation == Orientation.X) {
                if (yj <= z) {
                    if (yi > z)
                        if (FrameMath.isLeft(new int[]{xj, yj}, new int[]{xi, yi}, new int[]{x, z}) > 0)
                            windingNumber++;
                } else {
                    if (yi <= z)
                        if (FrameMath.isLeft(new int[]{xj, yj}, new int[]{xi, yi}, new int[]{x, z}) <= 0)
                            windingNumber--;
                }
            } else {
                if (xj <= x) {
                    if (xi > x)
                        if (FrameMath.isLeft(new int[]{xj, yj}, new int[]{xi, yi}, new int[]{x, z}) >= 0)
                            windingNumber++;
                } else {
                    if (xi <= x)
                        if (FrameMath.isLeft(new int[]{xj, yj}, new int[]{xi, yi}, new int[]{x, z}) < 0)
                            windingNumber--;
                }
            }
        }

        return windingNumber != 0;

    }

    public enum Orientation {
        X, Z;
    }

}