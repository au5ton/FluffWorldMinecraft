package world.fluff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 *
 */
public class Main extends JavaPlugin {
    protected DBConnection db;
    protected FluffScoreboard sb;

    @Override
    public void onEnable() {
        // Setup database
        getDataFolder().mkdir();
        db = new DBConnection(this);

        // Use primary scoreboard
        sb = new FluffScoreboard(db, Bukkit.getScoreboardManager().getMainScoreboard());
        // Register event listener
        getServer().getPluginManager().registerEvents(new FluffListener(db, this), this);
        // setup command handlers
        getCommand("chatcolor").setExecutor(new CommandChatColor(db, this));
        getCommand("share").setExecutor(new CommandShare(db, this));
    }

    @Override
    public void onDisable() {
        // Clean up
        db.kill();
        //sb.kill();
    }

}
