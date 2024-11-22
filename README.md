# Frame

:question: | This API allows you to easily manipulate cuboids, it also contains useful methods to get the players, blocks... in the cuboid.

:warning: | You need the Bukkit or Spigot API in your build path.

<br/>

# Setup

Simply download the [latest release](https://github.com/Hytashi/Frame/releases/download/2.1.0/Frame.jar) and add the jar to your *plugins* folder, then restart your server to load the plugin.<br/>
Add the downloaded jar file to your project's build path and you're good to go! 

<br/>

# Creating cuboids

**Cube3DCuboid**

> *Create a new instance*
>
> ```Java
> World world = Bukkit.getWorld("world"); // Get an instance of your world
> Location corner1 = new Location(world, x1, y1, z1); // Location of the first corner
> Location corner2 = new Location(world, x2, y2, z2); // Location of the second corner
> Cube3DCuboid cuboid = new Cube3DCuboid(corner1, corner2); // Create a new instance
> ```

<br/>

**Square2DCuboid**

> *Create a new instance*
>
> ```Java
> World world = Bukkit.getWorld("world"); // Get an instance of your world
> Coordinates2D corner1 = new Coordinates2D(30, 10);
> Coordinates2D corner2 = new Coordinates2D(65, 200);
> Square2DCuboid cuboid = new Square2DCuboid(world, corner1, radius); // Create a new instance
> ```

<br/>

**Circle2DCuboid**

> *Create a new instance*
>
> ```Java
> World world = Bukkit.getWorld("world"); // Get an instance of your world
> Location center = new Location(world, x1, y1, z1); // Location of the center
> int radius = 2; // Circle radius
> Circle2DCuboid cuboid = new Circle2DCuboid(corner1, radius); // Create a new instance
> ```

<br/>

**Rhombus2DCuboid**

> *Create a new instance*
>
> ```Java
> World world = Bukkit.getWorld("world"); // Get an instance of your world
> Pair<Coordinates2D, Coordinates2D> side1 = Pair.of(new Coordinates2D(0, 0), new Coordinates2D(0, 5)); // Pair that represents the first side
> Pair<Coordinates2D, Coordinates2D> side2 = Pair.of(new Coordinates2D(5, 10), new Coordinates2D(5, 5)); // Pair that represents the second side
> Rhombus2DCuboid cuboid = new Rhombus2DCuboid(side1, side2); // Create a new instance
> ```

<br/>

**CuboidGroup** <br/>
*Cuboid groups allow you to group cuboids of different types together. A cuboid group behaves like any other cuboid and contains the same methods as other cuboids.*
> *Create a new instance*
>
> ```Java
> CuboidGroup cuboidGroup = new CuboidGroup(cube3DCuboid, circle2DCuboid, rhombus2DCuboid); // Create a new instance (you can pass as many cuboids as you want in the constructor)
> ```

<br/>

# Examples

These methods work with all cuboid types

> *Check if a player is in a cuboid*
>
> ```Java
> Player player = Bukkit.getPlayer("Hytashi"); // Get an intance of your player
> boolean playerIsInCuboid = cuboid.containsPlayer(player); // Returns true if the player is in the cuboid
> ```

> *Check if a block is in a cuboid*
>
> ```Java
> Block block = Bukkit.getWorld("world").getBlockAt(1, 2, 3); // Get an intance of your block
> boolean playerIsInCuboid = cuboid.containsPlayer(player); // Returns true if the block is in the cuboid
> ```

<br/>

# JavaDoc

These methods work with all cuboid types

| Method prototype | Description | Return type |
|--|--|--|
| containsPlayer(Player player) | Returns whether the player is contained within the cuboid | boolean |
| containsBlock(Block block) | Returns whether the block is contained within the cuboid | boolean |
| contains(Location location) | Returns whether the location is contained within the cuboid | boolean |
| contains(double x, double y, double z) | Returns whether the coordinates are contained within the cuboid | boolean |
| getWorld() | Returns the World the cuboid is in | World |
