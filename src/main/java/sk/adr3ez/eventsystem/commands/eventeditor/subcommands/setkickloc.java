package sk.adr3ez.eventsystem.commands.eventeditor.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.eventeditor.SubCommand;

public class setkickloc extends SubCommand {
    @Override
    public String getName() {
        return "setkickloc";
    }

    @Override
    public String getDescription() {
        return "Set location where will players spawn after event.";
    }

    @Override
    public String getSyntax() {
        return "/event setkickloc";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("es.eventeditor")) {

            Main.config.get().set("KickLocation.world", p.getLocation().getWorld().getName());
            Main.config.get().set("KickLocation.x", p.getLocation().getX());
            Main.config.get().set("KickLocation.y", p.getLocation().getY());
            Main.config.get().set("KickLocation.z", p.getLocation().getZ());
            Main.config.get().set("KickLocation.yaw", p.getLocation().getYaw());
            Main.config.get().set("KickLocation.pitch", p.getLocation().getPitch());

            p.sendMessage(Main.config.get().getString("Messages.kicklocationset").replace("&", "§"));
            Main.config.reloadFiles();
            Main.em.reloadEventManager();

        } else {
            p.sendMessage(Main.config.get().getString("Messages.noperms").replace("&", "§"));
        }

    }
}
