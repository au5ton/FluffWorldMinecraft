package world.fluff;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class FluffListener implements Listener {
    private DBConnection db = null;
    private Main plugin = null;
    public FluffListener(DBConnection db, Main plugin) {
        this.db = db;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Maintain that UUIDs and usernames are in order
        if(!db.checkForPlayerRecord(event.getPlayer())) {
            db.insertPlayerRecord(event.getPlayer());
        }
        else {
            db.updatePlayerUsername(event.getPlayer());
        }
    }
}