package fr.hytashi.frame.group;

import fr.hytashi.frame.base.Cuboid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class CuboidGroup {

    private final List<Cuboid> members = new ArrayList<>();
    private World world = null;

    /**
     * Constructor
     *
     * @param cuboids the members of the group
     */
    public CuboidGroup(Cuboid... cuboids) {

        for (Cuboid cuboid : cuboids) {

            if (this.world == null)
                this.world = cuboid.getWorld();
            else if (!(this.world.equals(cuboid.getWorld())))
                throw new IllegalArgumentException("Members of the group must be in the same world");

            members.add(cuboid);
        }

    }

    /**
     * Adds a cuboid to the group
     *
     * @param cuboid the cuboid to add
     */
    public void addCuboid(Cuboid cuboid) {
        members.add(cuboid);
    }

    /**
     * Checks if a player is inside the cuboid group
     *
     * @param player the player
     * @return true if the player is inside the cuboid group
     */
    public boolean containsPlayer(Player player) {
        return members.stream().anyMatch(c -> c.containsPlayer(player));
    }

    /**
     * Checks if a block is inside the cuboid group
     *
     * @param block the block
     * @return true if the block is inside the cuboid group
     */
    public boolean containsBlock(Block block) {
        return members.stream().anyMatch(c -> c.containsBlock(block));
    }

    /**
     * Checks if a point is inside the cuboid group
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param z the Z coordinate
     * @return true if the point is inside the cuboid group
     */
    public boolean contains(double x, double y, double z) {
        return members.stream().anyMatch(c -> c.contains(x, y, z));
    }

    /**
     * Checks if a cuboid is a member of the cuboid group
     *
     * @param cuboid the cuboid
     * @return true if the cuboid is a member of the cuboid group
     */
    public boolean containsCuboid(Cuboid cuboid) {
        return members.stream().anyMatch(member -> member.equals(cuboid));
    }

}
