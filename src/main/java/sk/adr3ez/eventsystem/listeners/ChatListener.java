package sk.adr3ez.eventsystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import sk.adr3ez.eventsystem.EventAPI;
import sk.adr3ez.eventsystem.Main;

public class ChatListener implements Listener {

    @EventHandler
    public void onEventCreate(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (Main.em.EventCreateListener.contains(p)) {

            if (!e.getMessage().contains("cancel")) {

                if (!EventAPI.isEventCreated(e.getMessage())) {
                    EventAPI.createEvent(e.getMessage(), p.getName());
                    e.getPlayer().sendMessage("Event created!");
                } else {
                    e.getPlayer().sendMessage("Event is already created!");
                }

            } else {
                e.getPlayer().sendMessage("You cancelled event creation.");
            }

            e.setCancelled(true);
            Main.em.EventCreateListener.remove(p);
        }


        else if (Main.em.DisplayNameListener.contains(p)) {
            if (!e.getMessage().contains("cancel")) {

                p.sendMessage("Changed!");
                EventAPI.changeDisplayName(Main.em.openedMenu.get(p), e.getMessage());
                //new DisplaySettings(Main.getPlayerMenuUtility(p)).open();

            } else {
                e.getPlayer().sendMessage("DisplayName changer has been disabled.");
            }

            e.setCancelled(true);
            Main.em.DisplayNameListener.remove(p);
        }
    }

}
