package fr.hytashi.frame;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class Frame extends JavaPlugin {

    private static Frame INSTANCE;

    public Frame() {
        super();
    }

    protected Frame(JavaPluginLoader loader, PluginDescriptionFile descriptionFile, File dataFolder, File file) {
        super(loader, descriptionFile, dataFolder, file);
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Frame getInstance() {
        return INSTANCE;
    }

}
