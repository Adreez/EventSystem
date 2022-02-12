package sk.adr3ez.eventsystem.menu.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.menu.Menu;
import sk.adr3ez.eventsystem.menu.PlayerMenuUtility;

import java.util.ArrayList;

import static sk.adr3ez.eventsystem.Main.*;

public class EditorMainSelector extends Menu {
    public EditorMainSelector(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "           &e☼ &5&lMain Menu &e☼".replace("&", "§");
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.hasPermission("es.eventeditor.*") || p.hasPermission("es.eventeditor.create") ||
                p.hasPermission("es.eventeditor.edit") || p.hasPermission("es.eventeditor.start")) {
            switch (e.getSlot()) {
                case 26:
                    // Close menu
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage("&7&oMenu closed.".replace("&", "§"));
                    break;
                case 15:
                    // Create new event
                    if (e.isLeftClick()) {
                        if (p.hasPermission("es.eventeditor.*") || p.hasPermission("es.eventeditor.create")) {
                            em.EventCreateListener.add(p);
                            p.closeInventory();
                            p.sendMessage("§6§lInfo: §7Please type new event name. \n" +
                                    "&7If you want to cancel new event creation just type into chat §6cancel§7.".replace("&", "§"));
                        } else {
                            p.sendMessage(config.get().getString("Messages.noperms".replace("&", "§")));
                        }
                    }
                    break;
                case 13:
                    // Start created event
                    if (e.isLeftClick()) {
                        if (p.hasPermission("es.eventeditor.*") || p.hasPermission("es.eventeditor.start")) {
                            p.sendMessage("This thing is not currently avaiable.");
                        } else {
                            p.sendMessage(config.get().getString("Messages.noperms".replace("&", "§")));
                        }
                    }
                    break;
                case 11:
                    // Edit created event
                    if (p.hasPermission("es.eventeditor.*") || p.hasPermission("es.eventeditor.edit")) {
                        p.closeInventory();
                        new EditorEventsList(Main.getPlayerMenuUtility((Player) e.getWhoClicked())).open();
                    } else {
                        p.sendMessage(config.get().getString("Messages.noperms".replace("&", "§")));
                    }
                    break;
            }
        }
    }

    @Override
    public void setMenuItems() {

        ItemStack knowledgebook = new ItemStack(Material.KNOWLEDGE_BOOK, 1);
        ItemMeta knowledgebook_meta = knowledgebook.getItemMeta();
        knowledgebook_meta.setDisplayName("§5§lEvent changer");
        ArrayList<String> knowledgebook_lore = new ArrayList<>();
        knowledgebook_lore.add("");
        knowledgebook_lore.add("  §5§l• §7§oLeft click to change any created event.");
        knowledgebook_lore.add("");
        knowledgebook_meta.setLore(knowledgebook_lore);
        knowledgebook.setItemMeta(knowledgebook_meta);

        ItemStack lever = new ItemStack(Material.LEVER, 1);
        ItemMeta lever_meta = lever.getItemMeta();
        lever_meta.setDisplayName("§6§lStart event");
        ArrayList<String> lever_lore = new ArrayList<>();
        lever_lore.add("");
        lever_lore.add("  §6§l• §7§oLeft click to start any created event.");
        lever_lore.add("");
        lever_meta.setLore(lever_lore);
        lever.setItemMeta(lever_meta);

        ItemStack bookandquill = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta bookandquill_meta = bookandquill.getItemMeta();
        bookandquill_meta.setDisplayName("§a§lCreate event");
        ArrayList<String> bookandquill_lore = new ArrayList<>();
        bookandquill_lore.add("");
        bookandquill_lore.add("  §a§l• §7§oLeft click to start any created event.");
        bookandquill_lore.add("");
        bookandquill_meta.setLore(bookandquill_lore);
        bookandquill.setItemMeta(bookandquill_meta);

        ItemStack close = new ItemStack(Material.RED_DYE, 1);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName("§c§lClose menu");
        ArrayList<String> close_lore = new ArrayList<>();
        close_lore.add("");
        close_lore.add("  §c§l• §7§oPress any key to close menu.");
        close_lore.add("");
        close_meta.setLore(close_lore);
        close.setItemMeta(close_meta);

        //Fillers
        ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack blackglass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack orangeglass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);

        inventory.setItem(0, grayglass); inventory.setItem(1, grayglass); inventory.setItem(2, grayglass);
        inventory.setItem(6, grayglass); inventory.setItem(7, grayglass); inventory.setItem(8, grayglass);
        inventory.setItem(9, grayglass); inventory.setItem(17, grayglass); inventory.setItem(18, grayglass);
        inventory.setItem(19, grayglass); inventory.setItem(20, grayglass); inventory.setItem(24, grayglass);
        inventory.setItem(25, grayglass);

        inventory.setItem(3, blackglass); inventory.setItem(5, blackglass); inventory.setItem(23, blackglass); inventory.setItem(21, blackglass);

        inventory.setItem(4, orangeglass); inventory.setItem(22, orangeglass);

        inventory.setItem(26, close);
        inventory.setItem(15, bookandquill);
        inventory.setItem(13, lever);
        inventory.setItem(11, knowledgebook);
    }
}
