package sk.adr3ez.eventsystem.commands.eventeditor.subcommands;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.commands.eventeditor.SubCommand;
import sk.adr3ez.eventsystem.menu.menus.EditorMainSelector;

public class test extends SubCommand {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "test";
    }

    @Override
    public String getSyntax() {
        return "/ee test";
    }

    @Override
    public void perform(Player p, String[] args) {

        Main.eventsyml.get().set("Messages.usage.info", "idk");
        Main.eventsyml.reload();

        new EditorMainSelector(Main.getPlayerMenuUtility(p)).open();

    }
}
