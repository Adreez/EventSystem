package sk.adr3ez.eventsystem;

import org.bukkit.Location;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventAPI {

    /*
    Create event, this void not returning anything.
    If event that you want to create is already created
    it will ignore the create request
    Location spawnlocation defines where event will have spawnlocation
     */
    public static void createEvent(String event, String creator) {
        if (!isEventCreated(event)) {

            SimpleDateFormat format = new SimpleDateFormat(Main.config.get().getString("DateFormat"));
            Date date = new Date();

            ArrayList<String> lorelist = new ArrayList<>();

            lorelist.add("");
            lorelist.add(" &e&lINFO:");
            lorelist.add(" &6• &7&oEvent created by &6%creator%");
            lorelist.add(" &6• &7&oEvent has been created at &6%createdate%");
            lorelist.add("");
            lorelist.add(" &e&lSTATS:");
            lorelist.add(" &6• &7&oStarted &6%timesplayed% §7times.");
            lorelist.add("");


            Main.eventsyml.get().set("Events." + event + ".EventStats.creator", creator);
            Main.eventsyml.get().set("Events." + event + ".EventStats.createdate", format.format(date));
            Main.eventsyml.get().set("Events." + event + ".EventStats.timesplayed", 0);
            Main.eventsyml.get().set("Events." + event + ".CountdownStartSeconds", 300);

            Main.eventsyml.get().set("Events." + event + ".Menu.DisplayName", " &6• &7Default display &8(&e%ID%&8)");
            Main.eventsyml.get().set("Events." + event + ".Menu.Lore", lorelist);
            Main.eventsyml.get().set("Events." + event + ".Menu.Item", "DARK_OAK_BUTTON");

            ArrayList<String> first = new ArrayList<>();
            ArrayList<String> second = new ArrayList<>();
            ArrayList<String> third = new ArrayList<>();
            ArrayList<String> join = new ArrayList<>();

            first.add("give %player% minecraft:diamond 10");
            second.add("give %player% minecraft:diamond 5");
            third.add("give %player% minecraft:diamond 3");
            join.add("give %player% minecraft:diamond 1");

            Main.eventsyml.get().set("Events." + event + ".Rewards.First", first);
            Main.eventsyml.get().set("Events." + event + ".Rewards.Second", second);
            Main.eventsyml.get().set("Events." + event + ".Rewards.Third", third);
            Main.eventsyml.get().set("Events." + event + ".Rewards.Join", join);

            Main.eventsyml.reload();
            Main.em.reloadEventManager();
        }
    }



    /*
    This will just check if event is created or if just exists in events.yml
    It returns boolean.
     */
    public static boolean isEventCreated(String event) {
        return Main.eventsyml.get().contains("Events." + event);
    }



    /*
    If event doesn't exist it won't do anything.
     */
    public static void deleteEvent(String event) {
        if (Main.eventsyml.get().contains("Events." + event)) {
            Main.eventsyml.get().set("Events." + event, null);
            Main.eventsyml.reload();
        }
    }

    /*
    Change displayname of any event.
     */
    public static void changeDisplayName(String event,String displayname) {
        Main.eventsyml.get().set("Events." + event + ".Menu.DisplayName", displayname);
    }



    /*
    This will just set spawnlocation on some location. This method is setting world,
    x, y, z coordinates and also pitch and yaw.
     */
    public static void setEventSpawnLocation(Location spawnlocation, String event) {
        if (isEventCreated(event)) {
            Main.eventsyml.get().set("Events." + event + ".Spawnlocation.world", spawnlocation.getWorld().getName());
            Main.eventsyml.get().set("Events." + event + ".Spawnlocation.x", spawnlocation.getX());
            Main.eventsyml.get().set("Events." + event + ".Spawnlocation.y", spawnlocation.getY());
            Main.eventsyml.get().set("Events." + event + ".Spawnlocation.z", spawnlocation.getZ());
            Main.eventsyml.get().set("Events." + event + ".Spawnlocation.pitch", spawnlocation.getPitch());
            Main.eventsyml.get().set("Events." + event + ".Spawnlocation.yaw", spawnlocation.getYaw());
            Main.eventsyml.reload();
        }
    }

}
