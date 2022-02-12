package sk.adr3ez.eventsystem.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sk.adr3ez.eventsystem.Main;

public class PAPIManager extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "EventSystem";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Adr3ez_";
    }

    @Override
    public @NotNull String getVersion() {
        return "DEV-0.0.1";
    }

    @Override
    public String onRequest(OfflinePlayer p, String params) {
        if(params.equalsIgnoreCase("wincount")) {
            return String.valueOf(Main.data.getWinCount((Player) p));
        }
        if(params.equalsIgnoreCase("secondcount")) {
            return String.valueOf(Main.data.getSecondCount((Player) p));
        }
        if(params.equalsIgnoreCase("thirdcount")) {
            return String.valueOf(Main.data.getThirdCount((Player) p));
        }
        if(params.equalsIgnoreCase("joincount")) {
            return String.valueOf(Main.data.getJoinedCount((Player) p));
        }
        return "";
    }
}
