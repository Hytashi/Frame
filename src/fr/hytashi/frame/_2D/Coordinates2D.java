package fr.hytashi.frame._2D;

import org.bukkit.World;

public class Coordinates2D {

	private World	world;
	private double	x;
	private double	z;

	public Coordinates2D(World world, double x, double z) {
		this.world = world;
		this.x = x;
		this.z = z;
	}

	public World getWorld() {
		return world;
	}

	public double getX() {
		return x;
	}

	public double getZ() {
		return z;
	}

	public String toString() {
		return String.format("Coordinates2D[world=%s,x=%d,z=%d]", world.getName(), (int) x, (int) z);
	}

}
