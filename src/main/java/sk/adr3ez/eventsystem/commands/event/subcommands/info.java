package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;

import java.util.List;

public class info extends SubCommand {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return Main.config.get().getString("Messages.usage.info".replace("&", "§"));
    }

    @Override
    public String getSyntax() {
        return "/event info";
    }

    @Override
    public void perform(Player p, String[] args) {

        /*if (p.hasPermission("es.player") || p.hasPermission("es.admin")) {
            if (Main.em.getCurrentEvent() != null) {

                String playerposition = Main.config.get().getString("Messages.playernotfinished");
                String st = Main.config.get().getString("Messages.broadcast.playernull");
                String nd = Main.config.get().getString("Messages.broadcast.playernull");
                String rd = Main.config.get().getString("Messages.broadcast.playernull");

                if (Main.em.getFinishedPlayers().containsKey(p)) {
                    playerposition = Main.em.getFinishedPlayers().get(p).toString();
                }
                if (Main.em.getFirst() != null) {
                    st = Main.em.getFirst().getName();
                }
                if (Main.em.getSecond() != null) {
                    nd = Main.em.getSecond().getName();
                }
                if (Main.em.getThird() != null) {
                    rd = Main.em.getThird().getName();
                }

                List<String> list = Main.config.get().getStringList("Messages.info.message");
                for (String line : list) {
                    String currentevent = Main.em.getCurrentEvent();
                    p.sendMessage(line.replaceAll("%event_displayname%", Main.eventsyml.get().getString("Events." + currentevent + ".DisplayName"))
                            .replaceAll("%event_lore%", Main.eventsyml.get().getString("Events." + currentevent + ".Lore"))
                            .replaceAll("%first%", st)
                            .replaceAll("%second%", nd)
                            .replaceAll("%third%", rd)
                            .replaceAll("%PlayerPosition%", playerposition)
                            .replace("&", "§"));
                }
            } else {
                p.sendMessage(Main.config.get().getString("Messages.info.anyeventstarted"));
            }
        } else {
            p.sendMessage(Main.config.get().getString("Messages.noperms").replace("&", "§"));
        }*/

    }
}
