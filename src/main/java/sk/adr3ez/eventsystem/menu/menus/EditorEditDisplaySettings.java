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

public class EditorEditDisplaySettings extends Menu {

    public EditorEditDisplaySettings(PlayerMenuUtility playerMenuUtility) {
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
            case 21:
                p.closeInventory();
                p.sendMessage("§6§lInfo: §7Please type new displayname for event. &8(&e" + Main.em.openedMenu.get(p) + "&8) \n" +
                        ("&7If you want to cancel displayname editor just type into chat §6cancel§7." +
                                "\n&7&oYou also can use &6%ID%&7&o that shows event ID").replace("&", "§"));
                Main.em.DisplayNameListener.add(p);
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

        ItemStack displayname = new ItemStack(Material.KNOWLEDGE_BOOK, 1);
        ItemMeta displayname_meta = displayname.getItemMeta();
        displayname_meta.setDisplayName("§7Change: §6§lDisplayName");
        ArrayList<String> displayname_lore = new ArrayList<>();
        displayname_lore.add("");
        displayname_lore.add("  §c§l• §7§oPress right click to change displayname");
        displayname_lore.add("§7§oCurrent displayname: §r%displayname%".replace("%displayname%", Main.eventsyml.get().getString("Events." +
                Main.em.openedMenu.get(playerMenuUtility.getOwner()) + ".Menu.DisplayName")));
        displayname_lore.add("");
        displayname_meta.setLore(displayname_lore);
        displayname.setItemMeta(displayname_meta);

        ItemStack lore = new ItemStack(Material.KNOWLEDGE_BOOK, 1);
        ItemMeta lore_meta = lore.getItemMeta();
        lore_meta.setDisplayName("§7Change: §6§lLore");
        ArrayList<String> lore_lore = new ArrayList<>();
        lore_lore.add("");
        lore_lore.add("  §c§l• §7§oPress right click to change lore.");
        lore_lore.add("");
        lore_meta.setLore(lore_lore);
        lore.setItemMeta(lore_meta);

        inventory.setItem(53, close);
        inventory.setItem(49, mainmenu);
        inventory.setItem(21, displayname);
        inventory.setItem(23, lore);



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
