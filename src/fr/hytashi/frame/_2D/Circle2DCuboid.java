package fr.hytashi.frame._2D;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.hytashi.frame.base.Cuboid;

/**
 * @author Hytashi
 * @version 1.0.0
 */
public class Circle2DCuboid implements Cuboid {

	private World		world;
	private Location	center;
	private int			radius;

	// ================================================================================
	// Constructors
	// ================================================================================

	/**
	 * Create a new cuboid
	 * 
	 * @param pair1
	 * @param pair2
	 */
	public Circle2DCuboid(Location center, int radius) {
		this.center = center;
		this.world = center.getWorld();
		this.radius = radius;
	}

	// ================================================================================
	// Coordinates2D related
	// ================================================================================

	/**
	 * Check if the given Location is contained within the Cube3DCuboid
	 *
	 * @param location the Location
	 * @return true if the Location is within the Cube3DCuboid
	 */
	@Override
	public boolean contains(Location location) {
		if (location.getWorld() != this.world)
			return false;
		if ((Math.pow(location.getX() - center.getX(), 2) + Math.pow(location.getZ() - center.getZ(), 2)) <= Math
				.pow(radius, 2))
			return true;
		return false;
	}

	@Override
	public boolean containsPlayer(Player player) {
		return contains(player.getLocation());
	}

	@Override
	public boolean contains(double x, double y, double z) {
		return contains(new Location(world, x, y, z));
	}

	@Override
	public boolean containsBlock(Block block) {
		return contains(block.getLocation());
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	public int getRadius() {
		return this.radius;
	}

	public Location getCenter() {
		return this.center;
	}

	// ================================================================================
	// Utility
	// ================================================================================

	public String toString() {
		return String.format("Circle2DCuboid[world=%s,center=%s,radius=%s]", world.getName(), center.toString(),
				radius);
	}

}
