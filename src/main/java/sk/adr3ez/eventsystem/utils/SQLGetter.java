package sk.adr3ez.eventsystem.utils;

import org.bukkit.entity.Player;
import sk.adr3ez.eventsystem.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGetter {

    String table = Main.config.get().getString("MySQL.table");

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = Main.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table + " (NICK VARCHAR(100),UUID VARCHAR(100),WINCOUNT INT(100),SECONDCOUNT INT(100),THIRDCOUNT INT(100),JOINCOUNT INT(100),PRIMARY KEY (NICK))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            if (!exists(player)) {
                PreparedStatement ps2 = Main.SQL.getConnection().prepareStatement("INSERT IGNORE INTO " + table + " (NICK,UUID,WINCOUNT,SECONDCOUNT,THIRDCOUNT,JOINCOUNT) VALUES (?,?,?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, player.getUniqueId().toString());
                ps2.setInt(3, 0);
                ps2.setInt(4, 0);
                ps2.setInt(5, 0);
                ps2.setInt(6, 0);
                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(Player nick) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE NICK=?");
            ps.setString(1, nick.getName());
            ResultSet results = ps.executeQuery();
            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void addJoinedCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("UPDATE " + table + " SET JOINCOUNT=? WHERE NICK=?");
            ps.setInt(1, (getJoinedCount(p) + 1));
            ps.setString(2, p.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getJoinedCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("SELECT JOINCOUNT FROM " + table + " WHERE NICK=?");
            ps.setString(1, p.getName());
            ResultSet rs = ps.executeQuery();
            int joincount;

            if (rs.next()) {
                joincount = rs.getInt("JOINCOUNT");
                return joincount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addWinCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("UPDATE " + table + " SET WINCOUNT=? WHERE NICK=?");
            ps.setInt(1, (getWinCount(p) + 1));
            ps.setString(2, p.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getWinCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("SELECT WINCOUNT FROM " + table + " WHERE NICK=?");
            ps.setString(1, p.getName());
            ResultSet rs = ps.executeQuery();
            int wincount;

            if (rs.next()) {
                wincount = rs.getInt("WINCOUNT");
                return wincount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addSecondCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("UPDATE " + table + " SET SECONDCOUNT=? WHERE NICK=?");
            ps.setInt(1, (getSecondCount(p) + 1));
            ps.setString(2, p.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSecondCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("SELECT SECONDCOUNT FROM " + table + " WHERE NICK=?");
            ps.setString(1, p.getName());
            ResultSet rs = ps.executeQuery();
            int wincount;

            if (rs.next()) {
                wincount = rs.getInt("SECONDCOUNT");
                return wincount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addThirdCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("UPDATE " + table + " SET THIRDCOUNT=? WHERE NICK=?");
            ps.setInt(1, (getThirdCount(p) + 1));
            ps.setString(2, p.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getThirdCount(Player p) {
        try {
            PreparedStatement ps = Main.SQL.getConnection().prepareStatement("SELECT THIRDCOUNT FROM " + table + " WHERE NICK=?");
            ps.setString(1, p.getName());
            ResultSet rs = ps.executeQuery();
            int wincount;

            if (rs.next()) {
                wincount = rs.getInt("THIRDCOUNT");
                return wincount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
