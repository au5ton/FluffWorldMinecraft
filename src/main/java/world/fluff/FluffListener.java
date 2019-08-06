package world.fluff;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class FluffListener implements Listener {
    private DBConnection db;
    private Main plugin;

    public FluffListener(DBConnection db, Main plugin) {
        this.db = db;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            // Maintain that UUIDs and usernames are in order
            if(!db.checkForPlayerRecord(event.getPlayer())) {
                db.insertPlayerRecord(event.getPlayer());
            }
            else {
                db.updatePlayerUsername(event.getPlayer());
            }
            
            // Set player display color on join
            event.getPlayer().setDisplayName(db.getChatColor(event.getPlayer()) + event.getPlayer().getName() + ChatColor.RESET);
            event.getPlayer().setPlayerListName(db.getChatColor(event.getPlayer()) + event.getPlayer().getName() + ChatColor.RESET);
        }
        catch(Exception e) {
            //
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Appends " (level 123)" at the end of a death message
        event.setDeathMessage(String.format("%s (level %s)", event.getDeathMessage(), event.getEntity().getLevel()));
    }

}