package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;

public class join extends SubCommand {

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getDescription() {
        return Main.config.get().getString("Messages.usage.join".replace("&", "§"));
    }

    @Override
    public String getSyntax() {
        return "/event join";
        //            args[0] args[1]
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("es.admin") || p.hasPermission("es.player")) {
            if (Main.SQL.isConnected()) {
                if (Main.em.getCurrentEvent() != null) {
                    if (!Main.em.JoinedPlayers.contains(p) || !Main.em.FinishedPlayers.containsKey(p)) {
                        if (Main.em.canJoin) {
                            Main.em.addPlayer(p);
                            Main.data.addJoinedCount(p);
                            p.teleport(Main.em.getEventLocation(Main.em.getCurrentEvent()));
                            p.sendMessage(Main.config.get().getString("Messages.join.successfull").replace("&", "§"));
                        } else {
                            p.sendMessage("You cannot join.");
                        }
                    } else {
                        p.sendMessage(Main.config.get().getString("Messages.join.alreadyjoined").replace("&", "§"));
                    }
                } else {
                    p.sendMessage(Main.config.get().getString("Messages.join.anyeventstarted").replace("&", "§"));
                }
            } else {
                p.sendMessage(Main.config.get().getString("Messages.join.failedtojoin").replace("&", "§"));
            }
        } else {
            p.sendMessage(Main.config.get().getString("Messages.noperms").replace("&", "§"));
        }

    }
}
