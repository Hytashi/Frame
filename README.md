

# CuboidUtils

:question: | This class allows you to manipulate cuboids, it also contains useful methods to get the players, blocks... in the cuboid.

:warning: | You need the Bukkit or Spigot API in your build path.


# Usage

**Create a new Cuboid**

```Java
World world = Bukkit.getWorld("world"); // Get an instance of your world
Location corner1 = new Location(world, x1, y1, z1); // Location of the first corner
Location corner2 = new Location(world, x2, y2, z2); // Location of the second corner
Cuboid cuboid = new Cuboid(corner1, corner2); // Create a new instance of Cuboid with your two corners
 ```

<br/>

**Check if a player is in Cuboid**

```Java
Player player = Bukkit.getPlayer("Hytashi"); // Get an intance of your player
boolean playerIsInCuboid = cuboid.containsPlayer(player); // Returns true if the player is in the Cuboid
 ```

<br/>

**Get the number of blocks of a certain type in the Cuboid** 
```Java
int numberOfBlocks = cuboid.getNumberOfBlocks(Material.DIRT);
 ```
 
<br/>

**Get the number of blocks of a certain type and subtype in the Cuboid** 
```Java
// One subtype
int numberOfBlocks = cuboid.getNumberOfBlocks(Material.WOOL, (byte) 5);

// Multiple subtypes
int numberOfBlocks = cuboid.getNumberOfBlocks(Material.WOOL, (byte) 5), (byte) 7);
 ```

# JavaDoc

| Method | Arguments | Description | Return type |
|--|--|--|--|
| containsPlayer | Player player | Returns whether a player is in the cuboid | boolean |
| getMinX | :x: | Gets the minimum X | double |
| getMinY | :x: | Gets the minimum Y | double |
| getMinZ | :x: | Gets the minimum Z | double |
| getMaxX | :x: | Gets the maximum X | double |
| getMaxY | :x: | Gets the maximum Y | double |
| getMaxZ | :x: | Gets the maximum Z | double |
| toString | :x: | Returns the cuboid as a String | String |
| getNumberOfBlocks | Material blockType | Gets the number of blocks of a certain type in the cuboid | int |
| getNumberOfBlocks | Material blockType, byte... data | Gets the number of blocks of a certain type and subtype in the cuboid | int |