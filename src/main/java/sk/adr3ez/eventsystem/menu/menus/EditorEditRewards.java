package sk.adr3ez.eventsystem.menu.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sk.adr3ez.eventsystem.Main;
import sk.adr3ez.eventsystem.menu.PaginatedMenu;
import sk.adr3ez.eventsystem.menu.PlayerMenuUtility;

import java.util.ArrayList;


public class EditorEditRewards extends PaginatedMenu {
    public EditorEditRewards(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§5§lEditing: §cRewards";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

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


    @Override
    public void setMenuItems() {

        addMenuBorder();
        ItemStack bookandquill = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta bookandquill_meta = bookandquill.getItemMeta();
        bookandquill_meta.setDisplayName("§a§lAdd new reward");
        ArrayList<String> bookandquill_lore = new ArrayList<>();
        bookandquill_lore.add("");
        bookandquill_lore.add("  §a§l• §7§oLeft click add new reward.");
        bookandquill_lore.add("");
        bookandquill_meta.setLore(bookandquill_lore);
        bookandquill.setItemMeta(bookandquill_meta);

        inventory.setItem(4, bookandquill);

       /* if (Main.eventsyml.get().getString("Events." + Main.em.openedMenu.get(playerMenuUtility.getOwner()) + ".FirstReward") != null) {
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
        }*/
    }
}
