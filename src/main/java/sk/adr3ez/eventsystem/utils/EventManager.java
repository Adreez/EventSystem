package sk.adr3ez.eventsystem.utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import sk.adr3ez.eventsystem.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static sk.adr3ez.eventsystem.Main.*;


public class EventManager {

    private final JavaPlugin plugin;

    BukkitScheduler scheduler;
    BukkitScheduler schedulerTimer;

    public EventManager(JavaPlugin plugin) {
        reloadEventManager();
        this.plugin = plugin;
        scheduler = plugin.getServer().getScheduler();
        schedulerTimer = plugin.getServer().getScheduler();
    }

    private int TimePlaying = 0;
    public HashMap<Player, Integer> TimeFinished = new HashMap<>();

    public boolean canJoin = false;
    public HashMap<Player, Integer> playerCheckpoints = new HashMap<>();

    private final ArrayList<Location> ProtectedBlocks = new ArrayList<>();
    public ArrayList<Location> getProtectedBlocks() {
        return ProtectedBlocks;
    }

    int tt = 0;
    public ArrayList<Player> DisplayNameListener = new ArrayList<>();
    public ArrayList<Player> EventCreateListener = new ArrayList<>();
    public HashMap<Player, Integer> MenuEditingPlace = new HashMap<>();

    String currentevent;
    public Player getFirst() {
        return first;
    }
    public Player getSecond() {
        return second;
    }
    public Player getThird() {
        return third;
    }

    public ArrayList<String> eventlist;
    public HashMap<Player, Integer> getFinishedPlayers() {
        return FinishedPlayers;
    }
    Player first = null;
    Player second = null;
    Player third = null;
    Location kickloc;

    public ArrayList<Player> JoinedPlayers = new ArrayList<>();


    public HashMap<Player, Integer> FinishedPlayers = new HashMap<>();
    public HashMap<Player, String> openedMenu = new HashMap<>();

    public void reloadEventManager() {
        World kickw = Bukkit.getServer().getWorld(Main.config.get().getString("KickLocation.world"));
        double kickx = Main.config.get().getDouble("KickLocation.x");
        double kicky = Main.config.get().getDouble("KickLocation.y");
        double kickz = Main.config.get().getDouble("KickLocation.z");
        float kickpitch = (float) Main.config.get().getDouble("KickLocation.pitch");
        float kickyaw = (float) Main.config.get().getDouble("KickLocation.yaw");

        eventlist = new ArrayList<>(eventsyml.get().getConfigurationSection("Events").getKeys(false));
        kickloc = new Location(kickw, kickx, kicky, kickz, kickyaw, kickpitch);
        getProtectedBlocks().clear();
        LoadProtectedBlocks();
    }


    public void addPlayer(Player p) {
        JoinedPlayers.add(p);
        Main.data.createPlayer(p);
    }
    public void removePlayer(Player p) {
        JoinedPlayers.remove(p);
        p.teleport(Main.em.getKickLocation());
    }
    public void addFinishedPlayer(Player p) {
        if (!FinishedPlayers.containsKey(p)) {
            tt = tt + 1;
            if (tt == 1) {
                first = p;
            }
            if (tt == 2) {
                second = p;
            }
            if (tt == 3) {
                third = p;
            }
            FinishedPlayers.put(p, tt);
            p.teleport(getKickLocation());
            Bukkit.broadcastMessage(Main.cm.format(config.get().getString("Messages.broadcast.playerfinished")
                    .replaceAll("%player%", p.getName())
                    .replaceAll("%time%", String.valueOf(TimePlaying))
                    .replaceAll("%position%", String.valueOf(FinishedPlayers.get(p)))));
            TimeFinished.put(p, TimePlaying);
        }
    }

    public Location getEventLocation(String event){

        double eventx = Main.eventsyml.get().getDouble("Events." + event + ".Spawnlocation.x");
        double eventy = Main.eventsyml.get().getDouble("Events." + event + ".Spawnlocation.y");
        double eventz = Main.eventsyml.get().getDouble("Events." + event + ".Spawnlocation.z");
        float eventpitch = (float) Main.eventsyml.get().getDouble("Events." + event + ".Spawnlocation.pitch");
        float eventyaw = (float) Main.eventsyml.get().getDouble("Events." + event + ".Spawnlocation.yaw");
        World eventw = Bukkit.getWorld(Main.eventsyml.get().getString("Events." + event + ".Spawnlocation.world"));

        return new Location(eventw, eventx, eventy, eventz, eventyaw, eventpitch);
    }

    public Location getKickLocation(){
        return kickloc;
    }

    public boolean checkIfEventExists(String event) {
        return Main.eventsyml.get().getString("Events." + event) != null;
    }
    public String getCurrentEvent(){
        return currentevent;
    }

    public Location getEndingLoc(String event) {
        World w = Bukkit.getServer().getWorld(Main.eventsyml.get().getString("Events." + event + ".EndPosition.world"));
       // World w = Bukkit.getWorld("world");
        double x = Main.eventsyml.get().getDouble("Events." + event + ".EndPosition.x");
        double y = Main.eventsyml.get().getDouble("Events." + event + ".EndPosition.y");
        double z = Main.eventsyml.get().getDouble("Events." + event + ".EndPosition.z");

        return new Location(w, x, y, z);
    }
    public void setEndingLocation(String event, Player p) {
        Main.eventsyml.get().set("Events." + event + ".EndPosition.world",p.getLocation().getWorld().getName());
        Main.eventsyml.get().set("Events." + event + ".EndPosition.x",p.getLocation().getBlockX());
        Main.eventsyml.get().set("Events." + event + ".EndPosition.y",p.getLocation().getBlockY());
        Main.eventsyml.get().set("Events." + event + ".EndPosition.z",p.getLocation().getBlockZ());
        Main.eventsyml.reload();

        p.getLocation().getBlock().setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
    }

    public void sendRewards(){
        if (currentevent != null) {
            if (first != null) {
                Main.data.addWinCount(first);
                List<String> list = Main.eventsyml.get().getStringList("Events." + currentevent + ".Rewards.First");

                for (String line : list) {
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), line.replaceAll("%player%", first.getName()));
                }
            }
            if (second != null) {
                Main.data.addSecondCount(second);
                List<String> list = Main.eventsyml.get().getStringList("Events." + currentevent + ".Rewards.Second"
                        .replaceAll("%player%", second.getName()));

                for (String line : list) {
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), line.replaceAll("%player%", first.getName()));
                }
            }
            if (third != null) {
                Main.data.addThirdCount(third);
                List<String> list = Main.eventsyml.get().getStringList("Events." + currentevent + ".Rewards.Third"
                        .replaceAll("%player%", third.getName()));

                for (String line : list) {
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), line.replaceAll("%player%", first.getName()));
                }
            }
        }
    }
    public void stopEvent(){
        if (getCurrentEvent() != null) {

            String m1 = Main.config.get().getString("Messages.broadcast.playernull");
            String m2 = Main.config.get().getString("Messages.broadcast.playernull");
            String m3 = Main.config.get().getString("Messages.broadcast.playernull");

            if (first != null) {
                m1 = first.getName();
            }
            if (second != null) {
                m2 = second.getName();
            }
            if (third != null) {
                m3 = third.getName();
            }



            Bukkit.broadcastMessage(Main.config.get().getString("Messages.broadcast.eventfinished").replace("&", "??")
                    .replaceAll("%first%", m1)
                    .replaceAll("%second%", m2)
                    .replaceAll("%third%", m3));

            sendRewards();
            tt = 0;
            first = null;
            second = null;
            third = null;
            currentevent = null;
            JoinedPlayers.clear();
            FinishedPlayers.clear();
            scheduler.cancelTasks(plugin);
            playerCheckpoints.clear();
            ProtectedBlocks.clear();
            TimePlaying = 0;
            TimeFinished.clear();
            cpm.unloadGameCP();
        }
    }

    int cooldown;
    String f = Main.config.get().getString("Messages.broadcast.playernull");
    String s = Main.config.get().getString("Messages.broadcast.playernull");
    String t = Main.config.get().getString("Messages.broadcast.playernull");
    public void startEvent(String event, int start_cooldown) {
        canJoin = true;
        currentevent = event;
        cooldown = 10;


        scheduler.runTaskTimer(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (JoinedPlayers.contains(p) && !FinishedPlayers.containsKey(p)) {
                    if (first != null)
                        f = first.getName();
                    if (second != null)
                        s = first.getName();
                    if (third != null)
                        t = first.getName();
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Main.cm.format(config.get().getString("ActionBar.Joined")
                            .replaceAll("%players_playing%", String.valueOf(JoinedPlayers.size()))
                            .replaceAll("%first%", f)
                            .replaceAll("%second%", s)
                            .replaceAll("%third%", t)
                            .replaceAll("%checkpoint%", String.valueOf(playerCheckpoints.get(p)))
                            .replaceAll("%time%", String.valueOf(TimePlaying)))));
                }
                else if (FinishedPlayers.containsKey(p)) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Main.cm.format(config.get().getString("ActionBar.Finished")
                            .replaceAll("%place%", String.valueOf(FinishedPlayers.get(p)))
                            .replaceAll("%time%", String.valueOf(TimePlaying)))));
                } else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Main.cm.format(config.get().getString("ActionBar.NotJoined")
                            .replaceAll("%time%", String.valueOf(TimePlaying)))));
                }
            }
            TimePlaying++;
        }, 0L, 20L);

        Bukkit.getServer().broadcastMessage(config.get().getString(Main.cm.format("Messages.broadcast.eventstarting-countdown"))
                .replaceAll("%cooldown%", String.valueOf(start_cooldown)));

        BukkitTask id = Bukkit.getServer().getScheduler().runTaskTimer(plugin, () -> {
            if (cooldown > 0) {
                Bukkit.getServer().broadcastMessage("Events starts in " + cooldown);
                for (Player p : JoinedPlayers) {
                    p.sendTitle("??7Event starts in", String.valueOf(cooldown), 20, 40, 0);
                }
                cooldown--;
            }else if (cooldown == 0) {
                Bukkit.getServer().broadcastMessage(config.get().getString("Messages.broadcast.eventstartsnow")
                        .replace("&", "??"));
                for (Player p : JoinedPlayers) {
                    p.sendTitle("??7Event starts", "??6??lNOW", 0, 20, 0);
                    canJoin = false;
                }
                schedulerTimer.cancelTasks(plugin);
            }
        }, (start_cooldown * 20L), 20L);
        Bukkit.getServer().getScheduler().cancelTask(id.getTaskId());

        cpm.loadGameCP(event);
    }

    public void LoadProtectedBlocks() {
        if (eventlist != null) {
            for (String s : eventlist) {

                getProtectedBlocks().add(new Location(Bukkit.getWorld(eventsyml.get().getString("Events." + s + ".EndPosition.world")),
                        eventsyml.get().getDouble("Events." + s + ".EndPosition.x"),
                        eventsyml.get().getDouble("Events." + s + ".EndPosition.y"),
                        eventsyml.get().getDouble("Events." + s + ".EndPosition.z")));

                if (eventsyml.get().getConfigurationSection("Events." + s + ".Checkpoints").getKeys(false) != null) {
                    for (String c : eventsyml.get().getConfigurationSection("Events." + s + ".Checkpoints").getKeys(false)) {

                        getProtectedBlocks().add(new Location(Bukkit.getWorld(eventsyml.get().getString("Events." + s + ".Checkpoints." + c + ".world")),
                                eventsyml.get().getDouble("Events." + s + ".Checkpoints." + c + ".x"),
                                eventsyml.get().getDouble("Events." + s + ".Checkpoints." + c + ".y"),
                                eventsyml.get().getDouble("Events." + s + ".Checkpoints." + c + ".z")));
                    }
                }
            }
        }
    }
}
