package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;

public class start extends SubCommand {
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getDescription() {
        return Main.config.get().getString("Messages.usage.start".replace("&", "§"));
    }

    @Override
    public String getSyntax() {
        return "/event start <event>";
    }

    @Override
    public void perform(Player p, String[] args) {
        if (p.hasPermission("es.admin") || p.hasPermission("es.start")) {
            if (Main.SQL.isConnected()) {
                if (args.length == 2) {
                    if (Main.em.checkIfEventExists(args[1])) {

                        Main.em.startEvent(args[1]);
                        p.sendMessage(Main.config.get().getString("Messages.eventstarted").replace("&", "§").replaceAll("%event%", args[1]));

                    } else {
                        p.sendMessage(Main.config.get().getString("Messages.eventnotexist").replace("&", "§"));
                    }
                }
                else if (args.length == 1 || args.length >= 3) {
                    p.sendMessage("Usage:" + getSyntax());
                }
            } else {
                p.sendMessage(Main.config.get().getString("Messages.sqlnotconnected").replace("&", "§"));
            }
        } else {
            p.sendMessage(Main.config.get().getString("Messages.noperms").replace("&", "§"));
        }
    }
}
