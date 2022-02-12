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

public class EditorEditSelector extends Menu {
    public EditorEditSelector(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§5§l§oEditing: §c" + Main.em.openedMenu.get(playerMenuUtility.getOwner());
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        switch(e.getSlot()) {
            case 53:
                p.closeInventory();
                p.sendMessage("Inventory has been closed.");
                break;
            case 49:
                p.closeInventory();
                new EditorMainSelector(Main.getPlayerMenuUtility(p)).open();
                break;
            case 12:
                p.closeInventory();
                new EditorEditDisplaySettings(Main.getPlayerMenuUtility(p)).open();
                break;
            case 14:
                p.closeInventory();
                new EditorEditRewardsSelector(Main.getPlayerMenuUtility(p)).open();
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

        ItemStack mainmenu = new ItemStack(Material.KNOWLEDGE_BOOK, 1);
        ItemMeta mainmenu_meta = mainmenu.getItemMeta();
        mainmenu_meta.setDisplayName("§a↓ §7Back to §amain menu");
        ArrayList<String> mainmenu_lore = new ArrayList<>();
        mainmenu_lore.add("");
        mainmenu_lore.add("  §c§l• §7§oPress any key to get to main menu.");
        mainmenu_lore.add("");
        mainmenu_meta.setLore(mainmenu_lore);
        mainmenu.setItemMeta(mainmenu_meta);

        ItemStack bookandquill = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta bookandquill_meta = bookandquill.getItemMeta();
        bookandquill_meta.setDisplayName("§6§lDisplay settings");
        ArrayList<String> bookandquill_lore = new ArrayList<>();
        bookandquill_lore.add("");
        bookandquill_lore.add("  §c§l• §7§oPress any key to get to display settings.");
        bookandquill_lore.add("");
        bookandquill_meta.setLore(bookandquill_lore);
        bookandquill.setItemMeta(bookandquill_meta);

        ItemStack netherstar = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta netherstar_meta = netherstar.getItemMeta();
        netherstar_meta.setDisplayName("§6§lRewards settings");
        ArrayList<String> netherstar_lore = new ArrayList<>();
        netherstar_lore.add("");
        netherstar_lore.add("  §c§l• §7§o&mPress any key to get to reward settings.");
        netherstar_lore.add("§c§oSorry but this thing is currently under developement!");
        netherstar_lore.add("§c§oWill be added soon!");
        netherstar_lore.add("");
        netherstar_meta.setLore(netherstar_lore);
        netherstar.setItemMeta(netherstar_meta);

        ItemStack endingloc = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, 1);
        ItemMeta endingloc_meta = netherstar.getItemMeta();
        endingloc_meta.setDisplayName("§6§lDisplay settings");
        ArrayList<String> endingloc_lore = new ArrayList<>();
        endingloc_lore.add("");
        endingloc_lore.add("  §c§l• §7§oPress any key to get to display settings.");
        endingloc_lore.add("");
        endingloc_meta.setLore(endingloc_lore);
        endingloc.setItemMeta(endingloc_meta);

        ItemStack respawnloc = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta respawnloc_meta = respawnloc.getItemMeta();
        respawnloc_meta.setDisplayName("§6§lDisplay settings");
        ArrayList<String> respawnloc_lore = new ArrayList<>();
        respawnloc_lore.add("");
        respawnloc_lore.add("  §c§l• §7§oPress any key to get to display settings.");
        respawnloc_lore.add("");
        respawnloc_meta.setLore(respawnloc_lore);
        respawnloc.setItemMeta(respawnloc_meta);

        ItemStack checkpoints = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, 1);
        ItemMeta checkpoints_meta = checkpoints.getItemMeta();
        respawnloc_meta.setDisplayName("§6§lDisplay settings");
        ArrayList<String> checkpoints_lore = new ArrayList<>();
        checkpoints_lore.add("");
        checkpoints_lore.add("  §c§l• §7§oPress any key to get to display settings.");
        checkpoints_lore.add("");
        checkpoints_meta.setLore(checkpoints_lore);
        checkpoints.setItemMeta(checkpoints_meta);

        ItemStack barrier = new ItemStack(Material.BARRIER, 1);
        ItemMeta barrier_meta = barrier.getItemMeta();
        barrier_meta.setDisplayName("§6§lDisplay settings");
        ArrayList<String> barrier_lore = new ArrayList<>();
        barrier_lore.add("");
        barrier_lore.add("  §c§l• §7§oPress any key to get to display settings.");
        barrier_lore.add("");
        barrier_meta.setLore(barrier_lore);
        barrier.setItemMeta(barrier_meta);


        inventory.setItem(40, barrier);
        inventory.setItem(20, checkpoints);
        inventory.setItem(22, respawnloc);
        inventory.setItem(24, endingloc);
        inventory.setItem(14, netherstar);
        inventory.setItem(12, bookandquill);
        inventory.setItem(53, close);
        inventory.setItem(49, mainmenu);

        ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack blackglass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack orangeglass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);

        inventory.setItem(0, grayglass); inventory.setItem(1, grayglass); inventory.setItem(4, grayglass);
        inventory.setItem(7, grayglass); inventory.setItem(8, grayglass); inventory.setItem(9, grayglass);
        inventory.setItem(17, grayglass); inventory.setItem(36, grayglass); inventory.setItem(44, grayglass);
        inventory.setItem(45, grayglass); inventory.setItem(46, grayglass); inventory.setItem(52, grayglass);

        inventory.setItem(2, blackglass); inventory.setItem(18, blackglass); inventory.setItem(26, blackglass);
        inventory.setItem(27, blackglass); inventory.setItem(35, blackglass); inventory.setItem(47, blackglass);
        inventory.setItem(51, blackglass); inventory.setItem(6, blackglass);

        inventory.setItem(3, orangeglass); inventory.setItem(48, orangeglass); inventory.setItem(50, orangeglass);
        inventory.setItem(5, orangeglass);
    }
}
