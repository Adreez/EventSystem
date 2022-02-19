package sk.adr3ez.eventsystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import sk.adr3ez.eventsystem.commands.event.EventCmdManager;
import sk.adr3ez.eventsystem.commands.eventeditor.EventEditorManager;
import sk.adr3ez.eventsystem.listeners.ChatListener;
import sk.adr3ez.eventsystem.listeners.DeathListener;
import sk.adr3ez.eventsystem.listeners.MenuListener;
import sk.adr3ez.eventsystem.menu.PlayerMenuUtility;
import sk.adr3ez.eventsystem.utils.*;
import sk.adr3ez.eventsystem.files.EventsFile;
import sk.adr3ez.eventsystem.listeners.InteractListener;
import sk.adr3ez.eventsystem.files.ConfigFile;

import java.io.File;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static ConfigFile config;
    public static EventsFile eventsyml;
    public static MySQL SQL;
    public static SQLGetter data;
    public static EventManager em;
    public static ChatManager cm;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        new File("plugins/EventSystem").mkdirs();
        config = new ConfigFile(this);
        eventsyml = new EventsFile(this);

        cm = new ChatManager();
        em = new EventManager(this);
        SQL = new MySQL();
        data = new SQLGetter();

        getCommand("event").setExecutor(new EventCmdManager());
        getCommand("eventeditor").setExecutor(new EventEditorManager());

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);


        checkPlaceholderAPI();
        tryMySQLConnect();
        sendStartupMessage();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (SQL.isConnected())
            SQL.disconnect();
        em.stopEvent();
    }

    private void sendStartupMessage(){

        String hasPapi;
        String isConnected;
        if (SQL.isConnected()) {
            isConnected = "§aConnected§r";
        } else {
            isConnected = "§cDisconnected§r";
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            hasPapi = "§cNot found§r";
        } else {
            hasPapi = "§aFound§r";
        }

        getServer().getConsoleSender().sendMessage("" +
                "\n§8[§eEventSystem§8] | §eStatus:§r" +
                "\n§8[§eEventSystem§8] | §f  MySQL §8» " + isConnected +
                "\n§8[§eEventSystem§8] | §f  PlaceholderAPI §8» " + hasPapi +
                "\n§8[§eEventSystem§8] |§r" +
                "\n§8[§eEventSystem§8] | §aPlugin has been enabled.§r");

    }
    private void tryMySQLConnect() {
        try {
            SQL.connect();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if (SQL.isConnected()) {
            data.createTable();
        }
    }
    private void checkPlaceholderAPI(){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PAPIManager().register();
        }
    }

    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;

        if (playerMenuUtilityMap.containsKey(p)) {
            return playerMenuUtilityMap.get(p);
        } else {

            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        }
    }

    public static Main getPlugin() {
        return plugin;
    }


}
