package fr.hytashi.frame.base;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class Cuboid {

    private World world;

    /**
     * Checks if a location is inside the cuboid
     *
     * @param location the location
     * @return true if the location is inside the cuboid
     */
    public abstract boolean contains(Location location);

    /**
     * Checks if a player is inside the cuboid
     *
     * @param player the player
     * @return true if the player is inside the cuboid
     */
    public boolean containsPlayer(Player player) {
        return contains(player.getLocation());
    }

    /**
     * Checks if a point is inside the cuboid
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param z the Z coordinate
     * @return true if the point is inside the cuboid
     */
    public boolean contains(double x, double y, double z) {
        return contains(new Location(world, x, y, z));
    }

    /**
     * Checks if a block is inside the cuboid
     *
     * @param block the block
     * @return true if the block is inside the cuboid
     */
    public boolean containsBlock(Block block) {
        return contains(block.getLocation());
    }

    public abstract World getWorld();

}
