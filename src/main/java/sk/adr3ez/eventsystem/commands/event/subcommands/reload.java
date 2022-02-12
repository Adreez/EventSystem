package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;

public class reload extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return Main.config.get().getString("Messages.usage.reload".replace("&", "§"));
    }

    @Override
    public String getSyntax() {
        return "/event reload";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("es.reload") || p.hasPermission("es.admin")) {
            Main.config.reloadFiles();
            Main.eventsyml.reload();
            Main.em.reloadEventManager();
            try {
                Main.SQL.connect();
            } catch (Exception e) {
                //e.printStackTrace();
            }
            if (Main.SQL.isConnected()) {
                Main.data.createTable();
            }
            p.sendMessage(Main.config.get().getString("Messages.reload").replace("&", "§"));
        } else {
            p.sendMessage(Main.config.get().getString("Messages.noperms").replace("&", "§"));
        }

    }
}
