package sk.adr3ez.eventsystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import sk.adr3ez.eventsystem.Main;

import java.util.HashMap;

public class CheckpointsManager {

    public HashMap<Location, Integer> CurrentGameCheckpoints = new HashMap<>();

    public void loadGameCP(String gameID) {
        if (Main.eventsyml.get().getConfigurationSection("Events." + gameID + ".Checkpoints").getKeys(false) != null) {
            for (String s : Main.eventsyml.get().getConfigurationSection("Events." + gameID + ".Checkpoints").getKeys(false)) {
                CurrentGameCheckpoints.put(new Location(
                        Bukkit.getWorld(Main.eventsyml.get().getString("Events." + gameID + ".Checkpoints." + s + ".world")),
                        Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + s + ".x"),
                        Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + s + ".y"),
                        Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + s + ".z")
                ),Integer.valueOf(s));
            }
        }
    }
    public void unloadGameCP() {
        CurrentGameCheckpoints.clear();
    }

    public void setCP(Location loc, String gameID, Integer checkpoint) {
        if (Main.eventsyml.get().get("Events." + gameID + ".Checkpoints." + checkpoint) != null) {
            Main.em.getProtectedBlocks().remove(new Location(
                    Bukkit.getWorld(Main.eventsyml.get().getString("Events." + gameID + ".Checkpoints." + checkpoint + ".world")),
                    Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + checkpoint + ".x"),
                    Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + checkpoint + ".y"),
                    Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + checkpoint + ".z")
            ));

            new Location(Bukkit.getWorld(Main.eventsyml.get().getString("Events." + gameID + ".Checkpoints." + checkpoint + ".world")),
                    Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + checkpoint + ".x"),
                    Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + checkpoint + ".y"),
                    Main.eventsyml.get().getDouble("Events." + gameID + ".Checkpoints." + checkpoint + ".z")
            ).getBlock().setType(Material.AIR);
        }
        Main.eventsyml.get().set("Events." + gameID + ".Checkpoints." + checkpoint + ".world", loc.getWorld().getName());
        Main.eventsyml.get().set("Events." + gameID + ".Checkpoints." + checkpoint + ".x", loc.getBlockX());
        Main.eventsyml.get().set("Events." + gameID + ".Checkpoints." + checkpoint + ".y", loc.getBlockY());
        Main.eventsyml.get().set("Events." + gameID + ".Checkpoints." + checkpoint + ".z", loc.getBlockZ());
        Main.eventsyml.get().set("Events." + gameID + ".Checkpoints." + checkpoint + ".pitch", loc.getPitch());
        Main.eventsyml.get().set("Events." + gameID + ".Checkpoints." + checkpoint + ".yaw", loc.getYaw());

        loc.getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
        Main.eventsyml.reload();
    }
}
