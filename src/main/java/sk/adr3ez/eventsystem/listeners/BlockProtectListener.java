package sk.adr3ez.eventsystem.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sk.adr3ez.eventsystem.Main;

public class BlockProtectListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        if (e.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE || e.getBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            if (Main.em.getProtectedBlocks().contains(e.getBlock().getLocation())) {
                e.getPlayer().sendMessage("This block is protected!");
                e.setCancelled(true);
            }
        }
    }
}
