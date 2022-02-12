package sk.adr3ez.eventsystem.listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import sk.adr3ez.eventsystem.Main;

public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
            if (Main.em.getCurrentEvent() != null) {
                if (!Main.em.FinishedPlayers.containsKey(e.getPlayer()) && Main.em.JoinedPlayers.contains(e.getPlayer())) {
                    String event = Main.em.getCurrentEvent();

                    if (e.getClickedBlock().getLocation().getBlockX() == Main.em.getEndingLoc(event).getBlockX()) {
                        if (e.getClickedBlock().getLocation().getBlockY() == Main.em.getEndingLoc(event).getBlockY()) {
                            if (e.getClickedBlock().getLocation().getBlockZ() == Main.em.getEndingLoc(event).getBlockZ()) {
                                Main.em.addFinishedPlayer(e.getPlayer());
                                e.getPlayer().sendMessage(Main.config.get().getString("Messages.player-finished").replace("&", "§")
                                        .replaceAll("%position%", String.valueOf(Main.em.FinishedPlayers.get(e.getPlayer()))));
                            }
                        }
                    }
                }
            }
        }else if (e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            if (Main.em.getCurrentEvent() != null) {
                if (!Main.em.FinishedPlayers.containsKey(e.getPlayer()) && Main.em.JoinedPlayers.contains(e.getPlayer())) {

                    if (e.getClickedBlock() instanceof TileState) {
                        TileState tileState = (TileState) e.getClickedBlock().getState();

                        PersistentDataContainer container = tileState.getPersistentDataContainer();
                        if (container.has(new NamespacedKey(Main.getPlugin(), "event"), PersistentDataType.STRING)) {
                            if (container.has(new NamespacedKey(Main.getPlugin(), "checkpoint"), PersistentDataType.INTEGER)) {
                                Main.em.playerCheckpoints.put(e.getPlayer(), container.get(new NamespacedKey(Main.getPlugin(), "checkpoint"), PersistentDataType.INTEGER));
                                e.getPlayer().sendMessage("test");
                            }
                        }
                    }
                }
            }
        }
    }
}
