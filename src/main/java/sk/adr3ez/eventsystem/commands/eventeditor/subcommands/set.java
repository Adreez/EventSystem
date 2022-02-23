package sk.adr3ez.eventsystem.commands.eventeditor.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.eventeditor.SubCommand;

import java.text.DecimalFormat;

public class set extends SubCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "set everything you need";
    }

    @Override
    public String getSyntax() {
        return "/ee set <event> spawnloc/displayname/lore/endloc";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("es.eventeditor")) {
            if (args.length == 1) {
                p.sendMessage("Usage: " + getSyntax());
            }
            else if(args.length == 2) {
                if (Main.eventsyml.get().contains("Events." + args[1])) {
                    p.sendMessage("Usage: " + getSyntax());
                } else {
                    p.sendMessage(Main.config.get().getString("Messages.eventnotexist").replace("&", "§").replaceAll("%event%", args[1]));
                }
            }
            else if (args.length == 3) {


                if (args[2].contains("spawnloc")) {
                    Main.eventsyml.get().set("Events." + args[1] + ".Spawnlocation.world", p.getLocation().getWorld().getName());
                    Main.eventsyml.get().set("Events." + args[1] + ".Spawnlocation.x", p.getLocation().getX());
                    Main.eventsyml.get().set("Events." + args[1] + ".Spawnlocation.y", p.getLocation().getY());
                    Main.eventsyml.get().set("Events." + args[1] + ".Spawnlocation.z", p.getLocation().getZ());
                    Main.eventsyml.get().set("Events." + args[1] + ".Spawnlocation.pitch", p.getLocation().getPitch());
                    Main.eventsyml.get().set("Events." + args[1] + ".Spawnlocation.yaw", p.getLocation().getYaw());
                    Main.eventsyml.reload();

                    DecimalFormat df = new DecimalFormat("0.000");
                    p.sendMessage(Main.config.get().getString("Messages.eventeditor.set.spawnloc").replace("&", "§")
                            .replaceAll("%event%", args[1])
                            .replaceAll("%x%", df.format(p.getLocation().getX()))
                            .replaceAll("%y%", df.format(p.getLocation().getY()))
                            .replaceAll("%z%", df.format(p.getLocation().getZ())));
                }
                else if(args[2].contains("endloc")) {
                    Main.em.setEndingLocation(args[1], p);
                    Main.em.reloadEventManager();
                    p.sendMessage(Main.config.get().getString("Messages.eventeditor.set.endloc").replace("&", "§")
                            .replaceAll("%event%", args[1])
                            .replaceAll("%x%", String.valueOf(p.getLocation().getBlockX()))
                            .replaceAll("%y%", String.valueOf(p.getLocation().getBlockY()))
                            .replaceAll("%z%", String.valueOf(p.getLocation().getBlockZ())));
                }

            }

            else if (args.length >= 4) {
                if (Main.eventsyml.get().contains("Events." + args[1])) {


                    if (args[2].contains("displayname")) {
                        StringBuilder smBuilder = new StringBuilder();
                        for (int i = 3; i < args.length; i++){
                            String arg = (args[i] + " ");
                            smBuilder.append(arg);
                        }
                        String sm = smBuilder.toString();

                        Main.eventsyml.get().set("Events." + args[1] + ".DisplayName", sm);
                        Main.eventsyml.reload();
                        p.sendMessage(Main.config.get().getString("Messages.eventeditor.set.displayname").replace("&", "§")
                                .replaceAll("%event%", args[1])
                                .replaceAll("%newvalue%", sm));
                    }


                    else if (args[2].contains("lore")) {
                        StringBuilder smBuilder = new StringBuilder();
                        for (int i = 3; i < args.length; i++){
                            String arg = (args[i] + " ");
                            smBuilder.append(arg);
                        }
                        String sm = smBuilder.toString();

                        Main.eventsyml.get().set("Events." + args[1] + ".Lore", sm);
                        Main.eventsyml.reload();
                        p.sendMessage(Main.config.get().getString("Messages.eventeditor.set.lore").replace("&", "§")
                                .replaceAll("%event%", args[1])
                                .replaceAll("%newvalue%", sm));
                    }

                } else {
                    p.sendMessage(Main.config.get().getString("Messages.eventnotexist").replace("&", "§").replaceAll("%event%", args[1]));
                }
            }
        }
    }
}
