package sk.adr3ez.eventsystem.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;

import java.util.HashMap;

public class CheckpointsManager {

    private final HashMap<Player, Integer> PlayerCP = new HashMap<>();

    public HashMap<Player, Integer> getPlayerCP() {
        return PlayerCP;
    }

    public void createNewCP(Location loc) {

    }

    public void setCP(Location loc, Integer id) {
        //Main.eventsyml.get().set();
    }
}
