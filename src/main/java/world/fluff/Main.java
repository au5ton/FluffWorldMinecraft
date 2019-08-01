package world.fluff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.liec0dez.MinimapAPI.MinimapAPI;

/**
 * Hello world!
 *
 */
public class Main extends JavaPlugin {
    protected DBConnection db;
    protected FluffScoreboard sb;
    protected MinimapAPI map;

    @Override
    public void onEnable() {
        // Setup database
        getDataFolder().mkdir();
        db = new DBConnection(this);

        //map = MinimapAPI.getInstance();

        // Use primary scoreboard
        sb = new FluffScoreboard(db, Bukkit.getScoreboardManager().getMainScoreboard());
        // Register event listener
        getServer().getPluginManager().registerEvents(new FluffListener(db, this), this);
        // setup command handlers
        getCommand("chatcolor").setExecutor(new CommandChatColor(db, this));
        //getCommand("share").setExecutor(new CommandShare(db, this));;
    }

    @Override
    public void onDisable() {
        // Clean up
        db.kill();
        sb.kill();
    }

}
