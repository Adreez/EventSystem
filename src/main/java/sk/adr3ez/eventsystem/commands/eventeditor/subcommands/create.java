package sk.adr3ez.eventsystem.commands.eventeditor.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.EventAPI;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.eventeditor.SubCommand;

public class create extends SubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "This command will create new event";
    }

    @Override
    public String getSyntax() {
        return "/ee create <event>";
    }

    @Override
    public void perform(Player p, String[] args) {
        if (p.hasPermission("es.eventeditor")) {
            if (args.length == 1) {
                p.sendMessage("usage");
            }
            else if (args.length >= 2) {
                if (!Main.eventsyml.get().contains("Events." + args[1])) {
                    EventAPI.createEvent(args[1], p.getName());
                    EventAPI.setEventSpawnLocation(p.getLocation(), args[1]);

                    p.sendMessage(Main.config.get().getString("Messages.eventeditor.eventcreated").replace("&", "§")
                            .replaceAll("%event%", args[1])
                            .replaceAll("%x%", String.valueOf(p.getLocation().getX()))
                            .replaceAll("%y%", String.valueOf(p.getLocation().getY()))
                            .replaceAll("%z%", String.valueOf(p.getLocation().getZ())));
                } else {
                    p.sendMessage("This event already exists!");
                }
            }
        }
    }
}
