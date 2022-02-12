package sk.adr3ez.eventsystem.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sk.adr3ez.eventsystem.Main;

import java.io.File;
import java.io.IOException;

public class EventsFile {

    private final Main plugin;
    private FileConfiguration customFile;
    private File file;
    String fileName = "events.yml";


    public EventsFile(Main plugin) {
        this.plugin = plugin;

        reload();
    }

    public void reload() {
        if (file == null)
            file = new File(this.plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            this.plugin.saveResource(fileName, false);
        }


        saveFiles();
        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public void saveFiles() {
        if (customFile == null) {
            return;
        }
        try {
            customFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration get() {
        if (customFile == null)
            reload();

        return customFile;
    }
}
