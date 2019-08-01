package world.fluff;

import java.util.Arrays;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
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
        try {
            // Maintain that UUIDs and usernames are in order
            if(!db.checkForPlayerRecord(event.getPlayer())) {
                db.insertPlayerRecord(event.getPlayer());
            }
            else {
                db.updatePlayerUsername(event.getPlayer());
            }
            
            // Set player display color on join
            event.getPlayer().setDisplayName(db.getChatColor(event.getPlayer()) + event.getPlayer().getName());
            event.getPlayer().setPlayerListName(db.getChatColor(event.getPlayer()) + event.getPlayer().getName());
        }
        catch(Exception e) {
            //
        }
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        // Material item = event.getEntity().getItemStack().getType();
        // ArrayList<Material> s = new ArrayList<Material>(Arrays.asList(Material.BONE, Material.ROTTEN_FLESH, Material.STRING, Material.EGG, Material.ARROW, Material.SPIDER_EYE, Material.STICK, Material.ACACIA_SAPLING, Material.SPRUCE_SAPLING, Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING, Material.JUNGLE_SAPLING, Material.OAK_SAPLING));
        // if(s.contains(item)) {
        //     return;
        // }
        // int amount = event.getEntity().getItemStack().getAmount();
        // String itemName = event.getEntity().getItemStack().getType().toString();
        // if(amount > 1) {
        //     Bukkit.broadcastMessage(ChatColor.YELLOW + "" + amount + " " + ChatColor.RESET + itemName + ChatColor.YELLOW + " despawned.");
        // }
        // else {
        //     Bukkit.broadcastMessage(itemName + ChatColor.YELLOW + " despawned.");
        // }
    }
}