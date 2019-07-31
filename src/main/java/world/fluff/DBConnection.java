package world.fluff;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class DBConnection {
    private Connection connection;
    private Statement statement;
    private Main plugin;

    public DBConnection(Main plugin) {
        this.plugin = plugin;
        try {
            // Configure connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + File.separator + "fwmc.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Create test table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS players (username string, uuid string, points integer, chat_color string)");
            // TODO: waypoints table
        }
        catch(SQLException e) {
            plugin.getLogger().warning(e.getMessage());
        }
    }

    // Safely close the sqlite connection
    public void kill() {
        try {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e) {
            plugin.getLogger().warning(e.getMessage());
        }
    }

    // Returns if the player exists in the database
    public boolean checkForPlayerRecord(Player player) {
        try {
            PreparedStatement s = connection.prepareStatement("SELECT * FROM players WHERE uuid=?");
            s.setString(1, player.getUniqueId().toString());
            ResultSet rs = s.executeQuery();
            return rs.next(); // return if the query was empty or not
        }
        catch(SQLException e) {
            plugin.getLogger().warning(e.getMessage());
        }
        return false;
    }

    // Inserts a new player record with the default values
    public void insertPlayerRecord(Player player) {
        try {
            PreparedStatement s = connection.prepareStatement("INSERT INTO players(username, uuid, points, chat_color) VALUES(?,?,?,?)");
            s.setString(1, player.getName());
            s.setString(2, player.getUniqueId().toString());
            s.setInt(3, 0);
            s.setString(4, "WHITE");
            s.executeUpdate();
        }
        catch(SQLException e) {
            plugin.getLogger().warning(e.getMessage());
        }
    }

    // maintain the "username" record be current for a UUID
    public void updatePlayerUsername(Player player) {
        try {
            PreparedStatement s = connection.prepareStatement("UPDATE players SET username = ? WHERE uuid = ?");
            s.setString(1, player.getName());
            s.setString(2, player.getUniqueId().toString());
            s.executeUpdate();
        }
        catch(SQLException e) {
            plugin.getLogger().warning(e.getMessage());
        }
    }

    // Change a player's chat color
    public void setChatColor(Player player, String color) {
        try {
            PreparedStatement s = connection.prepareStatement("UPDATE players SET chat_color = ? WHERE uuid = ?");
            s.setString(1, color);
            s.setString(2, player.getUniqueId().toString());
            s.executeUpdate();
        }
        catch(SQLException e) {
            plugin.getLogger().warning(e.getMessage());
        }
    }

    // Get a player's chat color
    public ChatColor getChatColor(Player player) {
        try {
            PreparedStatement s = connection.prepareStatement("SELECT chat_color FROM players WHERE uuid = ?");
            s.setString(1, player.getUniqueId().toString());
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return ChatColor.valueOf(rs.getString("chat_color"));
            }
        }
        catch(IllegalArgumentException e) {
            // db value isn't a real color
            return ChatColor.WHITE;
        }
        catch(SQLException e) {
            plugin.getLogger().warning(e.getMessage());
        }
        return ChatColor.WHITE;
    }
}