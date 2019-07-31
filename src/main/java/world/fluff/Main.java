package world.fluff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.liec0dez.MinimapAPI.MinimapAPI;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class Main extends JavaPlugin {
    private FluffScoreboard sb;

    @Override
    public void onEnable() {
        // Setup database
        getDataFolder().mkdir();
        Connection connection = null;
        try {
            // Configure connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + getDataFolder() + File.separator + "mydatabase.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Create test table
            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next()) {
                getLogger().info("name = " + rs.getString("name"));
                getLogger().info("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e) {
            getLogger().warning(e.getMessage());
        }
        finally {
            try {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e) {
                getLogger().warning(e.getMessage());
            }
        }

        // Activate custom scoreboard
        sb = new FluffScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        // Register event listener
        getServer().getPluginManager().registerEvents(new FluffListener(this), this);
        // setup command handlers
        getCommand("hello").setExecutor(new CommandHello());
    }

    @Override
    public void onDisable() {
        // Clean up
        sb.kill();
    }

}
