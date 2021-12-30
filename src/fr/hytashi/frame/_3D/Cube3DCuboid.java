package fr.hytashi.frame._3D;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.hytashi.frame.base.Cuboid;

/**
 * @author Hytashi
 * @version 1.0.0
 */
public class Cube3DCuboid implements Cuboid {

	private int		minX, minY, minZ;
	private int		maxX, maxY, maxZ;
	private World	world;

	// ================================================================================
	// Constructors
	// ================================================================================

	/**
	 * Create a new cuboid
	 * 
	 * @param corner1
	 * @param corner2
	 */
	public Cube3DCuboid(Location corner1, Location corner2) {
		if (!corner1.getWorld().equals(corner2.getWorld())) {
			throw new IllegalArgumentException("Corners must be in the same world");
		}
		minX = Math.min(corner1.getBlockX(), corner2.getBlockX());
		minY = Math.min(corner1.getBlockY(), corner2.getBlockY());
		minZ = Math.min(corner1.getBlockZ(), corner2.getBlockZ());
		maxX = Math.max(corner1.getBlockX(), corner2.getBlockX());
		maxY = Math.max(corner1.getBlockY(), corner2.getBlockY());
		maxZ = Math.max(corner1.getBlockZ(), corner2.getBlockZ());
		this.world = corner1.getWorld();
	}

	/**
	 * Copy an existing cuboid
	 * 
	 * @param cuboid
	 */
	public Cube3DCuboid(Cube3DCuboid cuboid) {
		this(cuboid.getLowerCorner(), cuboid.getUpperCorner());
	}

	// ================================================================================
	// Player related
	// ================================================================================

	/**
	 * Returns wether the given player is in the cuboid or not
	 * 
	 * @param player
	 * @return true if the player is in the cuboid
	 */
	@Override
	public boolean containsPlayer(Player player) {
		if (player.getWorld() == world) {
			if (player.getLocation().getX() >= getMinX() && player.getLocation().getX() <= getMaxX()
					&& player.getLocation().getY() >= getMinY() && player.getLocation().getY() <= getMaxY()
					&& player.getLocation().getZ() >= getMinZ() && player.getLocation().getZ() <= getMaxZ()) {
				return true;
			}
		}
		return false;
	}

	// ================================================================================
	// Coordinates2D related
	// ================================================================================

	public Location getLowerCorner() {
		return new Location(getWorld(), minX, minY, minZ);
	}

	public Location getUpperCorner() {
		return new Location(getWorld(), maxX, maxY, maxZ);
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMinZ() {
		return minZ;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMaxZ() {
		return maxZ;
	}

	@Override
	public World getWorld() {
		return world;
	}

	/**
	 * Return true if the point is contained within the Cube3DCuboid
	 *
	 * @param x	the X coordinate
	 * @param y	the Y coordinate
	 * @param z	the Z coordinate
	 * @return true if the given point is within the Cube3DCuboid
	 */
	@Override
	public boolean contains(double x, double y, double z) {
		return x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ;
	}

	/**
	 * Check if the given Location is contained within the Cube3DCuboid
	 *
	 * @param location the Location
	 * @return true if the Location is within the Cube3DCuboid
	 */
	@Override
	public boolean contains(Location location) {
		return world.equals(location.getWorld())
				&& contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	// ================================================================================
	// Blocks related
	// ================================================================================

	/**
	 * Returns the number of blocks of a certain type in the cuboid
	 * 
	 * @param blockType Block type ({@link org.bukkit.Material})
	 * @return The number of blocks of the given type in the cuboid
	 */
	public int getNumberOfBlocks(Material blockType) {
		int numberOfBlocks = 0;
		for (int x = (int) getMinX(); x <= (int) getMaxX(); x++) {
			for (int y = (int) getMinY(); y <= (int) getMaxY(); y++) {
				for (int z = (int) getMinZ(); z <= (int) getMaxZ(); z++) {
					if (world.getBlockAt(x, y, z).getType() == blockType)
						numberOfBlocks++;
				}
			}
		}
		return numberOfBlocks;
	}

	/**
	 * Returns the number of blocks of a certain type and subtype in the cuboid
	 * 
	 * @param blockType Block type ({@link org.bukkit.Material})
	 * @param data      Block subtype ({@link org.bukkit.Block#getData()})
	 * @return The number of blocks of the given type and subtype in the cuboid
	 */
	public int getNumberOfBlocks(Material blockType, byte... data) {
		List<Byte> dataAsList = new ArrayList<Byte>();
		for (byte b : data)
			dataAsList.add(b);
		int numberOfBlocks = 0;
		for (int x = (int) getMinX(); x <= (int) getMaxX(); x++) {
			for (int y = (int) getMinY(); y <= (int) getMaxY(); y++) {
				for (int z = (int) getMinZ(); z <= (int) getMaxZ(); z++) {
					Block block = world.getBlockAt(x, y, z);
					if (block.getType() == blockType && dataAsList.contains(block.getData()))
						numberOfBlocks++;
				}
			}
		}
		return numberOfBlocks;
	}

	/**
	 * Check if the given block is contained within the Cube3DCuboid
	 * 
	 * @param block the Block
	 * @return true if the Block is within the Cube3DCuboid
	 */
	@Override
	public boolean containsBlock(Block block) {
		return contains(block.getX(), block.getY(), block.getZ());
	}

	// ================================================================================
	// Utility
	// ================================================================================

	/**
	 * Clone the current Cube3DCuboid
	 */
	public Cube3DCuboid clone() {
		return new Cube3DCuboid(this);
	}

	public String toString() {
		return String.format("Cube3DCuboid: { world: %s, minX: %s, maxX: %s, minY: %s, maxY: %s, minZ: %s, maxZ: %s }",
				world, getMinX(), getMaxX(), getMinY(), getMaxY(), getMinZ(), getMaxZ());
	}

}