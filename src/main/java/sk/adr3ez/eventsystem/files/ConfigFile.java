package sk.adr3ez.eventsystem.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sk.adr3ez.eventsystem.Main;

import java.io.File;

public class ConfigFile {

    private final Main plugin;
    private FileConfiguration customFile;
    private File file;
    String fileName = "config.yml";


    public ConfigFile(Main plugin) {
        this.plugin = plugin;

        reloadFiles();
    }

    public void reloadFiles() {
        if (file == null)
            file = new File(this.plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            this.plugin.saveResource(fileName, false);
        }
        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public FileConfiguration get() {
        if (customFile == null)
            reloadFiles();

        return customFile;
    }
}
