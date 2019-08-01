package world.fluff;

import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.liec0dez.MinimapAPI.MinimapAPI;
import me.liec0dez.MinimapAPI.Waypoint;

public class CommandShare implements CommandExecutor {
    private DBConnection db;
    private Main plugin;

    public CommandShare(DBConnection db, Main plugin) {
        this.db = db;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = plugin.getServer().getPlayer(sender.getName());
            Waypoint wp = new Waypoint(new Location(player.getWorld(), 0, 128, 0), "abc123", "[fwmc] Shared Point", 'S', ChatColor.AQUA, false);
            plugin.map.sendWaypoint(wp, player);
            // Collection<String> str = plugin.map.getIdentifiers(player);
            // for(String s : str) {
            //     sender.sendMessage(s);
            // } java.lang.IllegalArgumentException: Channel must contain : separator 
        }
        else {
            sender.sendMessage("Sorry, you have to be a player to use that command.");
        }
        return true;
    }
}