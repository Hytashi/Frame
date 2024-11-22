package fr.hytashi.frame.base;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class Cuboid {

    private final World world;

    protected Cuboid(World world) {
        this.world = world;
    }

    /**
     * Checks if a location is inside the cuboid
     *
     * @return true if the location is inside the cuboid
     */
    public abstract boolean contains(Location location);

    /**
     * Checks if a player is inside the cuboid
     *
     * @return true if the player is inside the cuboid
     */
    public boolean containsPlayer(Player player) {
        return contains(player.getLocation());
    }

    /**
     * Checks if a point is inside the cuboid
     *
     * @return true if the point is inside the cuboid
     */
    public boolean contains(double x, double y, double z) {
        return contains(new Location(world, x, y, z));
    }

    /**
     * Checks if a block is inside the cuboid
     *
     * @return true if the block is inside the cuboid
     */
    public boolean containsBlock(Block block) {
        return contains(block.getLocation());
    }

    public final World getWorld() {
        return world;
    }

}
