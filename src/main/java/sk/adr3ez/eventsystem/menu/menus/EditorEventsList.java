package sk.adr3ez.eventsystem.menu.menus;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.menu.PaginatedMenu;
import sk.adr3ez.eventsystem.menu.PlayerMenuUtility;

import java.util.ArrayList;
import java.util.List;

public class EditorEventsList extends PaginatedMenu {
    public EditorEventsList(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Events";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getPlugin(), "event-id"), PersistentDataType.STRING)) {

            String openedevent = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getPlugin(), "event-id"), PersistentDataType.STRING);
            Main.em.openedMenu.put(p, openedevent);
            new EditorEditSelector(Main.getPlayerMenuUtility(p)).open();

        }else {
            switch (e.getSlot()) {
                case 53:
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage("&7&oMenu closed.".replace("&", "§"));
                    break;
                case 50:
                    if (!((index + 1) >= Main.em.eventlist.size())){
                        page = page + 1;
                        super.open();
                    }else{
                        p.sendMessage("You are on the last page.");
                    }
                    break;
                case 48:
                    if (page == 0){
                        p.sendMessage("You are already on the first page.");
                    }else{
                        page = page - 1;
                        super.open();
                    }

                    break;
                case 49:
                    new EditorMainSelector(Main.getPlayerMenuUtility(p)).open();
                    break;
            }
        }
    }

    @Override
    public void setMenuItems() {

        addMenuBorder();

        if (Main.em.eventlist != null && !Main.em.eventlist.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {

                index = getMaxItemsPerPage() * page + i;
                if (index >= Main.em.eventlist.size()) break;
                if (Main.em.eventlist.get(index) != null) {

                    ItemStack item = new ItemStack(Material.valueOf(Main.eventsyml.get().getString("Events." + Main.em.eventlist.get(index) + ".Menu.Item").toUpperCase()), 1);
                    ItemMeta itemMeta = item.getItemMeta();

                    //DisplayName
                    itemMeta.setDisplayName(Main.eventsyml.get().getString("Events." + Main.em.eventlist.get(index) + ".Menu.DisplayName").replace("&", "§")
                            .replaceAll("%ID%", Main.em.eventlist.get(index)));


                    //ItemLore replacer
                    List<String> list = new ArrayList<>();
                    for (String string : Main.eventsyml.get().getStringList("Events." + Main.em.eventlist.get(index) + ".Menu.Lore")) {
                        list.add(string.replace("&", "§")
                        .replace("%creator%", Main.eventsyml.get().getString("Events." + Main.em.eventlist.get(index) + ".EventStats.creator"))
                        .replace("%createdate%", Main.eventsyml.get().getString("Events." + Main.em.eventlist.get(index) + ".EventStats.createdate"))
                        .replace("%timesplayed%", Main.eventsyml.get().getString("Events." + Main.em.eventlist.get(index) + ".EventStats.timesplayed")));
                    }
                    itemMeta.setLore(list);

                    //Persistentdata
                    PersistentDataContainer data = itemMeta.getPersistentDataContainer();
                    data.set(new NamespacedKey(Main.getPlugin(), "event-id"), PersistentDataType.STRING, Main.em.eventlist.get(index));

                    //Close and settings
                    item.setItemMeta(itemMeta);
                    inventory.addItem(item);


                }
            }


        }
    }

}
