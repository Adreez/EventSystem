package sk.adr3ez.eventsystem.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;

import java.util.HashMap;

public class CheckpointsManager {

    private final HashMap<Player, Integer> PlayerCP = new HashMap<>();
    public HashMap<Player, Integer> getPlayerCP() {
        return PlayerCP;
    }

    public void setCP(Location loc, String gameID, Integer checkpoint) {
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
