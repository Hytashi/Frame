package fr.hytashi.frame.base;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface Cuboid {

	public abstract boolean containsPlayer(Player player);

	public abstract boolean contains(Location location);

	public abstract boolean contains(double x, double y, double z);

	public abstract boolean containsBlock(Block block);

	public abstract World getWorld();

}
