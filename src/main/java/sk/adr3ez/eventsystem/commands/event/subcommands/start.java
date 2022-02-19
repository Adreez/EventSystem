package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;
import sk.adr3ez.eventsystem.utils.ChatManager;

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
        return "/event start <event> <countdown_in_seconds>";
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    public void perform(Player p, String[] args) {
        if (p.hasPermission("es.admin") || p.hasPermission("es.start")) {
            if (Main.SQL.isConnected()) {
                if (args.length == 3) {
                    if (Main.em.checkIfEventExists(args[1])) {

                        if (isInt(args[2])) {
                            Main.em.startEvent(args[1], Integer.parseInt(args[2]));
                            p.sendMessage(Main.config.get().getString("Messages.eventstarted").replace("&", "§").replaceAll("%event%", args[1]));
                        } else {
                            p.sendMessage(Main.cm.format("&c&lError! &7Third argument must be number!"));
                        }

                    } else {
                        p.sendMessage(Main.config.get().getString("Messages.eventnotexist").replace("&", "§"));
                    }
                }
                else if (args.length == 1 || args.length >= 4) {
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
