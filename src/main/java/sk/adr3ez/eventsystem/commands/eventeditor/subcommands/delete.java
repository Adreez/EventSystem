package sk.adr3ez.eventsystem.commands.eventeditor.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.EventAPI;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.eventeditor.SubCommand;

public class delete extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "This command will delete event";
    }

    @Override
    public String getSyntax() {
        return "/ee delete <event>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("es.eventeditor")) {
            if (args.length == 1) {
                p.sendMessage("Usage");
            }
            else if (args.length >= 2) {
                if (EventAPI.isEventCreated(args[1])) {
                    EventAPI.deleteEvent(args[1]);
                    p.sendMessage(Main.config.get().getString("Messages.eventeditor.delete").replace("&", "§").replaceAll("%event%", args[1]));
                } else {
                    p.sendMessage(Main.config.get().getString("Messages.eventnotexist").replace("&", "§").replaceAll("%event%", args[1]));
                }
            }
        }
    }
}
