package sk.adr3ez.eventsystem.menu.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.menu.Menu;
import sk.adr3ez.eventsystem.menu.PlayerMenuUtility;

import java.util.ArrayList;

import static sk.adr3ez.eventsystem.Main.em;

public class EditorEditRewardsSelector extends Menu {
    public EditorEditRewardsSelector(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§5§l§oEditing: §c" + Main.em.openedMenu.get(playerMenuUtility.getOwner());
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        switch (e.getSlot()) {
            case 11:
                em.MenuEditingPlace.put(p, 3);
                break;

            case 13:
                em.MenuEditingPlace.put(p, 2);
                break;

            case 15:
                em.MenuEditingPlace.put(p, 1);
                break;

            case 21:
                // Close menu
                e.getWhoClicked().closeInventory();
                new EditorEditSelector(new PlayerMenuUtility(p)).open();
                break;
            case 26:
                // Close menu
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage("&7&oMenu closed.".replace("&", "§"));
                break;
        }
    }

    @Override
    public void setMenuItems() {


        ItemStack close = new ItemStack(Material.RED_DYE, 1);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName("§c§lClose menu");
        ArrayList<String> close_lore = new ArrayList<>();
        close_lore.add("");
        close_lore.add("  §c§l• §7§oPress any key to close menu.");
        close_lore.add("");
        close_meta.setLore(close_lore);
        close.setItemMeta(close_meta);

        ItemStack gold = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta gold_meta = gold.getItemMeta();
        gold_meta.setDisplayName("§e§lThird place");
        ArrayList<String> gold_lore = new ArrayList<>();
        gold_lore.add("");
        gold_lore.add("  §c§l• §7§oPress any key to open 3rd place rewards.");
        gold_lore.add("");
        gold_meta.setLore(gold_lore);
        gold.setItemMeta(gold_meta);

        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemMeta emerald_meta = emerald.getItemMeta();
        emerald_meta.setDisplayName("§a§lSecond place");
        ArrayList<String> emerald_lore = new ArrayList<>();
        emerald_lore.add("");
        emerald_lore.add("  §c§l• §7§oPress any key to open 2nd place rewards.");
        emerald_lore.add("");
        emerald_meta.setLore(emerald_lore);
        emerald.setItemMeta(emerald_meta);

        ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
        ItemMeta diamond_meta = diamond.getItemMeta();
        diamond_meta.setDisplayName("§b§lFirst place");
        ArrayList<String> diamond_lore = new ArrayList<>();
        diamond_lore.add("");
        diamond_lore.add("  §c§l• §7§oPress any key to open 1st place rewards.");
        diamond_lore.add("");
        diamond_meta.setLore(diamond_lore);
        diamond.setItemMeta(diamond_meta);

        //Fillers
        ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack blackglass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack orangeglass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);

        inventory.setItem(0, grayglass); inventory.setItem(1, grayglass); inventory.setItem(2, grayglass);
        inventory.setItem(6, grayglass); inventory.setItem(7, grayglass); inventory.setItem(8, grayglass);
        inventory.setItem(9, grayglass); inventory.setItem(17, grayglass); inventory.setItem(18, grayglass);
        inventory.setItem(19, grayglass); inventory.setItem(20, grayglass); inventory.setItem(24, grayglass);
        inventory.setItem(25, grayglass);

        inventory.setItem(3, blackglass); inventory.setItem(5, blackglass); inventory.setItem(23, blackglass); //inventory.setItem(21, blackglass);

        inventory.setItem(4, orangeglass); inventory.setItem(22, orangeglass);

        inventory.setItem(21, makeItem(Material.DARK_OAK_BUTTON, ChatColor.DARK_GREEN + "§6↶ Back"));

        inventory.setItem(11, gold);
        inventory.setItem(13, emerald);
        inventory.setItem(15, diamond);

        inventory.setItem(26, close);
    }

}
