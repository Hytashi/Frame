package fr.hytashi.frame._3D;

import fr.hytashi.frame.base.Cuboid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Cube3DCuboid extends Cuboid {

    private final int minX, minY, minZ;
    private final int maxX, maxY, maxZ;

    public Cube3DCuboid(Location corner1, Location corner2) {
        super(corner1.getWorld());
        if (!corner1.getWorld().equals(corner2.getWorld()))
            throw new IllegalArgumentException("Corners must be in the same world");
        minX = Math.min(corner1.getBlockX(), corner2.getBlockX());
        minY = Math.min(corner1.getBlockY(), corner2.getBlockY());
        minZ = Math.min(corner1.getBlockZ(), corner2.getBlockZ());
        maxX = Math.max(corner1.getBlockX(), corner2.getBlockX());
        maxY = Math.max(corner1.getBlockY(), corner2.getBlockY());
        maxZ = Math.max(corner1.getBlockZ(), corner2.getBlockZ());
    }

    /**
     * Copies an existing cuboid
     */
    public Cube3DCuboid(Cube3DCuboid cuboid) {
        this(cuboid.getLowerCorner(), cuboid.getUpperCorner());
    }

    @Override
    public boolean contains(Location location) {
        return getWorld().equals(location.getWorld()) && location.getX() >= minX && location.getX() <= maxX
                && location.getY() >= minY && location.getY() <= maxY && location.getZ() >= minZ && location.getZ() <= maxZ;
    }

    /**
     * Gets all the blocks contained inside the cuboid
     *
     * @return a list of all the blocks contained within the cuboid
     */
    public List<Block> getBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (int x = getMinX(); x <= getMaxX(); x++)
            for (int y = getMinY(); y <= getMaxY(); y++)
                for (int z = getMinZ(); z <= getMaxZ(); z++)
                    blocks.add(getWorld().getBlockAt(x, y, z));
        return blocks;
    }

    /**
     * Gets the number of blocks in the cuboid
     *
     * @return the number of blocks contained within the cuboid
     */
    public int getNumberOfBlocks() {
        return getBlocks().size();
    }

    /**
     * Gets the number of blocks of a certain material in the cuboid
     *
     * @param material the {@link org.bukkit.Material}
     * @return the number of blocks of the given material contained within the cuboid
     */
    public int getNumberOfBlocks(Material material) {
        return (int) getBlocks().stream().filter(block -> block.getType() == material).count();
    }

    /**
     * Gets the number of blocks of a certain material and data in the cuboid
     *
     * @param material the {@link org.bukkit.Material}
     * @param data     the data ({@link org.bukkit.block.Block#getData()})
     * @return the number of blocks of the given material and data contained within the cuboid
     */
    public int getNumberOfBlocks(Material material, Byte... data) {
        List<Byte> dataList = new ArrayList<>(Arrays.asList(data));
        return (int) getBlocks().stream().filter(block -> block.getType() == material).filter(block -> dataList.contains(block.getData())).count();
    }

    public Location getLowerCorner() {
        return new Location(getWorld(), minX, minY, minZ);
    }

    public Location getUpperCorner() {
        return new Location(getWorld(), maxX, maxY, maxZ);
    }

}