package sk.adr3ez.eventsystem.utils;

import net.md_5.bungee.api.ChatColor;
import sk.adr3ez.eventsystem.Main;

public class ChatManager {

    public String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String noPerms = format(Main.config.get().getString("Messages.noperms"));
    public String cmdNotExist = format("&cSorry but this command doesn't exist!");

}
