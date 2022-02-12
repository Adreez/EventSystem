package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;

public class stop extends SubCommand {
    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return Main.config.get().getString("Messages.usage.stop".replace("&", "§"));
    }

    @Override
    public String getSyntax() {
        return "/event stop";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("es.admin") || p.hasPermission("es.stop")) {
            if (Main.em.getCurrentEvent() != null) {
                for (Player player : Main.em.JoinedPlayers) {
                    player.teleport(Main.em.getKickLocation());
                }

                Main.em.stopEvent();
            } else {
                p.sendMessage(Main.config.get().getString("Messages.Join.anyeventstarted").replace("&", "§"));
            }
        } else {
            p.sendMessage(Main.config.get().getString("Messages.noperms").replace("&", "§"));
        }

    }
}
