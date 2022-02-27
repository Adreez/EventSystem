package sk.adr3ez.eventsystem.commands.eventeditor.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.eventeditor.SubCommand;

public class checkpoint extends SubCommand {
    @Override
    public String getName() {
        return "checkpoint";
    }

    @Override
    public String getDescription() {
        return "Null";
    }

    @Override
    public String getSyntax() {
        return "/ee checkpoint set/remove <gameID> <checkpointID>";
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void perform(Player p, String[] args) {
        if (p.hasPermission("es.checkpoint") || p.hasPermission("es.admin")) {
            if (args.length == 4) {
                if (args[1].equalsIgnoreCase("set")) {
                    if (Main.em.checkIfEventExists(args[2])) {
                        if (isInt(args[3])) {

                            Main.cpm.setCP(p.getLocation(), args[2], Integer.valueOf(args[3]));
                            Main.em.reloadEventManager();
                            p.sendMessage("Checkpoint has been set on your position!");

                        } else {
                            p.sendMessage("checkpointID must be number!");
                        }
                    } else {
                        p.sendMessage("Event not exist!");
                    }
                } else if(args[1].equalsIgnoreCase("remove")) {

                    p.sendMessage("Checkpoint has been removed.");
                } else {
                    p.sendMessage("Usage: /ee checkpoint set/remove <gameID> <checkpointID>");
                }
            } else {
                p.sendMessage("Usage: /ee checkpoint set/remove <gameID> <checkpointID>");
            }
        } else {
            p.sendMessage(Main.cm.noPerms);
        }
    }
}
