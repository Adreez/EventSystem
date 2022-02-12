package sk.adr3ez.eventsystem.commands.event.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.SubCommand;

public class broadcast extends SubCommand {
    @Override
    public String getName() {
        return "broadcast";
    }

    @Override
    public String getDescription() {
        return Main.config.get().getString("Messages.usage.broadcast".replace("&", "§"));
    }

    @Override
    public String getSyntax() {
        return "/event broadcast <message>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("es.admin") || p.hasPermission("es.broadcast")) {
            StringBuilder smBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++){
                String arg = (args[i] + " ");
                smBuilder.append(arg);
            }
            String sm = smBuilder.toString().replace("&", "§");

            Bukkit.getServer().broadcastMessage(Main.config.get().getString("Messages.broadcast.prefix").replace("&", "§") + sm);
        } else {
            p.sendMessage("Messages.noperms".replace("&", "§"));
        }
    }
}
