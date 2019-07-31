package world.fluff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.liec0dez.MinimapAPI.MinimapAPI;

/**
 * Hello world!
 *
 */
public class Main extends JavaPlugin {
    private FluffScoreboard sb;

    @Override
    public void onEnable() {
        // Activate custom scoreboard
        sb = new FluffScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        getServer().getPluginManager().registerEvents(new FluffListener(this), this);
    }

    @Override
    public void onDisable() {
        // Clean up
        sb.kill();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(command.getName().equalsIgnoreCase("hello")) {
                sender.sendMessage("Hello, World!");
                return true;
            }
        }
        return false;
    }
}
