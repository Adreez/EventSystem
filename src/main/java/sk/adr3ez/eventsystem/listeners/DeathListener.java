package sk.adr3ez.eventsystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import sk.adr3ez.eventsystem.Main;

public class DeathListener implements Listener {


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        if (e.getEntity() instanceof Player) {

            Player p = e.getEntity().getPlayer();
            if (Main.em.JoinedPlayers.contains(p) || Main.em.getFinishedPlayers().containsKey(p)) {
                e.setKeepInventory(true);
                e.setKeepLevel(true);
                e.getDrops().clear();
            }
        }
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (Main.em.JoinedPlayers.contains(p) || Main.em.getFinishedPlayers().containsKey(p)) {
            p.sendMessage("§aYou've been respawned!");
            e.setRespawnLocation(Main.em.getEventLocation(Main.em.getCurrentEvent()));
        }
    }

}
