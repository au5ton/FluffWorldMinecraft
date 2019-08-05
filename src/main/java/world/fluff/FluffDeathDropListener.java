package world.fluff;

import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

// segmented code for handling item despawn messages
public class FluffDeathDropListener implements Listener {
    private DBConnection db;
    private Main plugin;

    // entity ids of entities dropped by this player
    private HashMap<Player, ArrayList<UUID>> playerDrops;

    public FluffDeathDropListener(DBConnection db, Main plugin) {
        this.db = db;
        this.plugin = plugin;
        playerDrops = new HashMap<Player, ArrayList<UUID>>();
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        // Only trigger for death drops
        if(event.getPlayer().getHealth() == 0) {
            // create hashmap entry if nonexistent
            if(!playerDrops.containsKey(event.getPlayer())) {
                playerDrops.put(event.getPlayer(), new ArrayList<UUID>());
            }
            // add entity id to arraylist of entities dropped by this player
            playerDrops.get(event.getPlayer()).add(event.getItemDrop().getUniqueId());
        }
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        // for every player that has pending drops as a result of a death
        for(Player key : playerDrops.keySet()) {
            // for every entityID
            for(UUID entityId : playerDrops.get(key)) {
                // the entity we're looking for
                if(event.getEntity().getUniqueId().equals(entityId)) {
                    // gather information
                    String playerName = key.getName();
                    String itemName = event.getEntity().getItemStack().getItemMeta().getDisplayName();
                    int amount = event.getEntity().getItemStack().getAmount();

                    // deliver message
                    // "2 Glass dropped by FluffThePanda despawned."
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "" + amount + " " + ChatColor.RESET + itemName + ChatColor.YELLOW + " dropped by " + ChatColor.RESET + playerName + ChatColor.YELLOW + " despawned.");

                    // removes the entry
                    playerDrops.get(key).remove(entityId);
                    // if player has no more pending drops, remove from the hashmap
                    if(playerDrops.get(key).isEmpty()) {
                        playerDrops.remove(key);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemMerge(ItemMergeEvent event) {
        // for every player that has pending drops as a result of a death
        for(Player key : playerDrops.keySet()) {
            // for every entityID
            for(UUID entityId : playerDrops.get(key)) {
                // the entity we're looking for
                if(event.getEntity().getUniqueId().equals(entityId)) {
                    // remove old id
                    playerDrops.get(key).remove(event.getEntity().getUniqueId());
                    // add merged id
                    playerDrops.get(key).add(event.getTarget().getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        // if a player is affected
        if(event.getEntity() instanceof Player) {
            // if there are no drops leftover
            if(event.getRemaining() == 0) {
                // for every player that has pending drops as a result of a death
                for(Player key : playerDrops.keySet()) {
                    // for every entityID
                    for(UUID entityId : playerDrops.get(key)) {
                        // the entity we're looking for
                        if(event.getItem().getUniqueId().equals(entityId)) {
                            // remove old id
                            playerDrops.get(key).remove(event.getItem().getUniqueId());
                        }
                    }
                }
            }
        }
    }
}