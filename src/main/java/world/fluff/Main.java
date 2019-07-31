package world.fluff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.liec0dez.MinimapAPI.MinimapAPI;

/**
 * Hello world!
 *
 */
public class Main extends JavaPlugin {
    private DBConnection db;
    private FluffScoreboard sb;

    @Override
    public void onEnable() {
        // Setup database
        getDataFolder().mkdir();
        db = new DBConnection(this);

        // Activate custom scoreboard
        sb = new FluffScoreboard(db, Bukkit.getScoreboardManager().getNewScoreboard());
        // Register event listener
        getServer().getPluginManager().registerEvents(new FluffListener(db, this), this);
        // setup command handlers
        getCommand("hello").setExecutor(new CommandHello());
    }

    @Override
    public void onDisable() {
        // Clean up
        db.kill();
        sb.kill();
    }

}
