package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;

public class leave extends SubCommand {
    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return Main.config.get().getString("Messages.usage.leave".replace("&", "§"));
    }

    @Override
    public String getSyntax() {
        return "/event leave";
    }

    @Override
    public void perform(Player p, String[] args) {
        if (p.hasPermission("es.admin") || p.hasPermission("es.player")) {
            if (Main.em.JoinedPlayers.contains(p)) {

                Main.em.removePlayer(p);
                p.sendMessage(Main.config.get().getString("Messages.leave.leaved").replace("&", "§"));

            } else {
                p.sendMessage(Main.config.get().getString("Messages.leave.notjoined").replace("&", "§"));
            }
        } else {
            p.sendMessage(Main.config.get().getString("Messages.noperms").replace("&", "§"));
        }
    }
}
