package sk.adr3ez.eventsystem.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventTabCompleter implements TabCompleter {

    List<String> arguments = new ArrayList<String>();


    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, String[] label) {
        if (arguments.isEmpty()) {
            if (sender.hasPermission("es.admin")) {
                arguments.add("start");
                arguments.add("stop");
                arguments.add("reload");
                arguments.add("setkickloc");
            }
            else if (sender.hasPermission("es.player") || sender.hasPermission("es.admin")) {
                arguments.add("join");
                arguments.add("leave");
            }
        }


        List<String> result = new ArrayList<String>();
        if (label.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(label[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }

}
