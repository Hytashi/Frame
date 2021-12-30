package fr.hytashi.frame._2D;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.hytashi.frame.base.Cuboid;

/**
 * @author Hytashi
 * @version 1.0.0
 */
public class Rhombus2DCuboid implements Cuboid {

	private Orientation		orientation;

	private World			world;
	private Coordinates2D	minCorner;
	private Coordinates2D	maxCorner;

	private int				height;
	private double			deltaX;
	private double			deltaZ;
	private double			slope;

	// ================================================================================
	// Constructors
	// ================================================================================

	/**
	 * Create a new cuboid
	 * 
	 * @param pair1
	 * @param pair2
	 */
	public Rhombus2DCuboid(Pair<Location, Location> pair1, Pair<Location, Location> pair2) {

		// Check if corners are in the same world
		HashSet<World> worlds = new HashSet<>(Arrays.asList(pair1.getLeft().getWorld(), pair1.getRight().getWorld(),
				pair1.getLeft().getWorld(), pair2.getRight().getWorld()));
		if (worlds.size() > 1)
			throw new IllegalArgumentException("Corners must be in the same world");
		this.world = pair1.getLeft().getWorld();

		// Calculate orientation
		if ((pair1.getLeft().getBlockX() == pair1.getRight().getBlockX())
				&& (pair2.getLeft().getBlockX() == pair2.getRight().getBlockX())) {
			orientation = Orientation.X;
			int h1 = Math.abs(pair1.getLeft().getBlockZ() - pair1.getRight().getBlockZ());
			int h2 = Math.abs(pair2.getLeft().getBlockZ() - pair2.getRight().getBlockZ());
			if (h1 == h2)
				height = h1 - 1;
			else
				throw new IllegalArgumentException("The height must be the same");
		} else if ((pair1.getLeft().getBlockZ() == pair1.getRight().getBlockZ())
				&& (pair2.getLeft().getBlockZ() == pair2.getRight().getBlockZ())) {
			orientation = Orientation.Z;
			int h1 = Math.abs(pair1.getLeft().getBlockX() - pair1.getRight().getBlockX());
			int h2 = Math.abs(pair2.getLeft().getBlockX() - pair2.getRight().getBlockX());
			if (h1 == h2)
				height = h1 - 1;
			else
				throw new IllegalArgumentException("The height must be the same");
		} else {
			throw new IllegalArgumentException("The two locations of a pair must be aligned on at least one axis");
		}

		// Calculate edges
		double minX = Math.min(Math.min(pair1.getLeft().getBlockX(), pair1.getRight().getBlockX()),
				Math.min(pair2.getLeft().getBlockX(), pair2.getRight().getBlockX()));
		double maxX = Math.max(Math.max(pair1.getLeft().getBlockX(), pair1.getRight().getBlockX()),
				Math.max(pair2.getLeft().getBlockX(), pair2.getRight().getBlockX()));

		double minZ = Math.min(Math.min(pair1.getLeft().getBlockZ(), pair1.getRight().getBlockZ()),
				Math.min(pair2.getLeft().getBlockZ(), pair2.getRight().getBlockZ()));
		double maxZ = Math.max(Math.max(pair1.getLeft().getBlockZ(), pair1.getRight().getBlockZ()),
				Math.max(pair2.getLeft().getBlockZ(), pair2.getRight().getBlockZ()));

		this.minCorner = new Coordinates2D(world, minX, minZ);
		this.maxCorner = new Coordinates2D(world, maxX, maxZ);

		// Calculate delta and slope
		deltaX = (double) Math.max(Math.min(pair1.getLeft().getBlockX(), pair1.getRight().getBlockX()),
				Math.min(pair2.getLeft().getBlockX(), pair2.getRight().getBlockX()))
				- Math.min(Math.min(pair1.getLeft().getBlockX(), pair1.getRight().getBlockX()),
						Math.min(pair2.getLeft().getBlockX(), pair2.getRight().getBlockX()))
				- 1;
		deltaZ = (double) Math.max(Math.min(pair1.getLeft().getBlockZ(), pair1.getRight().getBlockZ()),
				Math.min(pair2.getLeft().getBlockZ(), pair2.getRight().getBlockZ()))
				- Math.min(Math.min(pair1.getLeft().getBlockZ(), pair1.getRight().getBlockZ()),
						Math.min(pair2.getLeft().getBlockZ(), pair2.getRight().getBlockZ()))
				- 1;
		slope = (orientation == Orientation.X ? deltaZ / deltaX : deltaX / deltaZ);

		Bukkit.broadcastMessage(toString());

	}

	/**
	 * Check if the given Location is contained within the Cube3DCuboid
	 *
	 * @param location the Location
	 * @return true if the Location is within the Cube3DCuboid
	 */
	@Override
	public boolean contains(Location location) {
		if (!(world.equals(location.getWorld())))
			return false;
		if (orientation == Orientation.X) {
			if (location.getBlockX() >= minCorner.getX() && location.getBlockX() <= maxCorner.getX()
					&& location.getBlockZ() >= minCorner.getZ() && location.getBlockZ() <= maxCorner.getZ()) {
				// Calcul du Z en fonction du X
				int minZ = (int) (minCorner.getZ() + (location.getX() - minCorner.getX()) * slope);
				int maxZ = (int) (minCorner.getZ() + (location.getX() - minCorner.getX()) * slope) + height;
				return location.getZ() >= minZ && location.getZ() <= maxZ;
			}
		} else {
			if (location.getBlockX() >= minCorner.getX() && location.getBlockX() <= maxCorner.getX()
					&& location.getBlockZ() >= minCorner.getZ() && location.getBlockZ() <= maxCorner.getZ()) {
				// Calcul du X en fonction du Z
				// Je ne sais pas pourquoi mais les coordonnées ont un offset de 1 (donc on fait
				// +1)
				int minX = (int) (minCorner.getX() + (location.getZ() - minCorner.getZ()) * slope);
				int maxX = minX + height + 1;
				return location.getX() >= minX && location.getX() <= maxX;
			}
		}
		return false;
	}

	// ================================================================================
	// Player related
	// ================================================================================

	/**
	 * Returns wether a player is in the cuboid or not
	 * 
	 * @param player
	 * @return true if the player is in the cuboid
	 */
	@Override
	public boolean containsPlayer(Player player) {
		return contains(player.getLocation());
	}

	// ================================================================================
	// Coordinates2D related
	// ================================================================================

	@Override
	public World getWorld() {
		return world;
	}

	/**
	 * Return true if the point is contained within the Rhombus2DCuboid
	 *
	 * @param x	the X coordinate
	 * @param y	the Y coordinate
	 * @param z	the Z coordinate
	 * @return true if the given point is within the Rhombus2DCuboid
	 */
	@Override
	public boolean contains(double x, double y, double z) {
		return contains(new Location(world, x, y, z));
	}

	// ================================================================================
	// Blocks related
	// ================================================================================

	/**
	 * Check if the given block is contained within the Rhombus2DCuboid
	 * 
	 * @param block the Block
	 * @return true if the Block is within the Rhombus2DCuboid
	 */
	@Override
	public boolean containsBlock(Block block) {
		return contains(block.getLocation());
	}

	// ================================================================================
	// Utility
	// ================================================================================

	public String toString() {
		return String.format(
				"Rhombus2DCuboid[world=%s,orientation=%s,minCorner=%s,maxCorner=%s,deltaX=%s,deltaZ=%S,slope=%s,height=%s]",
				world.getName(), orientation, minCorner, maxCorner, deltaX, deltaZ, slope, height);
	}

	// ================================================================================
	// Private
	// ================================================================================

	private enum Orientation {
		X, Z;
	}

}