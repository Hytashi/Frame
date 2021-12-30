package fr.hytashi.frame.group;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.hytashi.frame.base.Cuboid;

/**
 * @author Hytashi
 * @version 1.0.0
 */
public class CuboidGroup {

	private List<Cuboid>	members	= new ArrayList<>();
	private World			world	= null;

	public CuboidGroup(Cuboid... cuboids) {

		for (Cuboid cuboid : cuboids) {

			if (this.world == null)
				this.world = cuboid.getWorld();
			else if (!(this.world.equals(cuboid.getWorld())))
				throw new IllegalArgumentException("Members of the group must be in the same world");

			members.add(cuboid);
		}

	}

	public List<Cuboid> getMembers() {
		return this.members;
	}

	public boolean containsPlayer(Player player) {
		return members.stream().anyMatch(c -> c.containsPlayer(player));
	}

	public boolean containsBlock(Block block) {
		return members.stream().anyMatch(c -> c.containsBlock(block));
	}

	public boolean contains(double x, double y, double z) {
		return members.stream().anyMatch(c -> c.contains(x, y, z));
	}

	public World getWorld() {
		return this.world;
	}

}
