package sk.adr3ez.eventsystem.commands.event;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.event.subcommands.*;

import java.util.ArrayList;

public class EventCmdManager implements CommandExecutor {

    private final ArrayList<SubCommand> subcommands = new ArrayList<>();

    public EventCmdManager(){
        subcommands.add(new join());
        subcommands.add(new start());
        subcommands.add(new stop());
        subcommands.add(new leave());
        subcommands.add(new reload());
        subcommands.add(new broadcast());
        subcommands.add(new info());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length > 0){
                for (int i = 0; i < getSubcommands().size(); i++){
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(p, args);
                    }
                }
            }else if(args.length == 0){
                if (p.hasPermission("es.admin") || p.hasPermission("es.help")) {
                    p.sendMessage("§8+---------+ [§eEventSystem §8| §eBy: Adr3ez_§8]  +---------+");
                    for (int i = 0; i < getSubcommands().size(); i++){
                        p.sendMessage("§e" + getSubcommands().get(i).getSyntax() + " §8» §7" + getSubcommands().get(i).getDescription().replace("&", "§"));
                    }
                    p.sendMessage("§8+----------------------------------------------+");
                } else {
                    p.sendMessage(Main.config.get().getString("Messages.noperms"));
                }
            }
        } else {
            sender.sendMessage("This command cannot be performed by console! :)");
        }


        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }


}
