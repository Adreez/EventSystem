package sk.adr3ez.eventsystem.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public abstract class PaginatedMenu extends Menu {

    //Keep track of what page the menu is on
    protected int page = 0;
    //28 is max items because with the border set below,
    //28 empty slots are remaining.
    protected static int maxItemsPerPage = 28;
    //the index represents the index of the slot
    //that the loop is on
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    //Set the border and menu buttons for the menu
    public void addMenuBorder(){
        //45 start of 6 line
        inventory.setItem(48, makeItem(Material.DARK_OAK_BUTTON, ChatColor.GREEN + "§6↶ Back"));

        ItemStack close = new ItemStack(Material.RED_DYE, 1);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName("§c§lClose menu");
        ArrayList<String> close_lore = new ArrayList<>();
        close_lore.add("");
        close_lore.add("  §c§l• §7§oPress any key to close menu.");
        close_lore.add("");
        close_meta.setLore(close_lore);
        close.setItemMeta(close_meta);

        ItemStack mainmenu = new ItemStack(Material.KNOWLEDGE_BOOK, 1);
        ItemMeta mainmenu_meta = mainmenu.getItemMeta();
        mainmenu_meta.setDisplayName("§a↓ §7Back to §amain menu");
        ArrayList<String> mainmenu_lore = new ArrayList<>();
        mainmenu_lore.add("");
        mainmenu_lore.add("  §c§l• §7§oPress any key to get to main menu.");
        mainmenu_lore.add("");
        mainmenu_meta.setLore(mainmenu_lore);
        mainmenu.setItemMeta(mainmenu_meta);

        inventory.setItem(53, close);
        inventory.setItem(49, mainmenu);
        inventory.setItem(50, makeItem(Material.DARK_OAK_BUTTON, ChatColor.GREEN + "§6Further ↷"));

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }

        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);
        inventory.setItem(36, super.FILLER_GLASS);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }

    public static int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }
}

