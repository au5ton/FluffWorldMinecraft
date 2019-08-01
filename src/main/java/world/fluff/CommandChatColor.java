package world.fluff;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatColor implements CommandExecutor {
    private DBConnection db;
    private Main plugin;

    public CommandChatColor(DBConnection db, Main plugin) {
        this.db = db;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 0) {
                sender.sendMessage("You must specify a color! You can use:");
                sender.sendMessage(ChatColor.BLACK + "black" + ChatColor.DARK_BLUE + " dark_blue" + ChatColor.DARK_GREEN + " dark_green" + ChatColor.DARK_AQUA + " dark_aqua" + ChatColor.DARK_RED + " dark_red" + ChatColor.DARK_PURPLE + " dark_purple" + ChatColor.GOLD + " gold" + ChatColor.GRAY + " gray" + ChatColor.DARK_GRAY + " dark_gray" + ChatColor.BLUE + " blue" + ChatColor.GREEN + " green" + ChatColor.AQUA + " aqua" + ChatColor.RED + " red" + ChatColor.LIGHT_PURPLE + " light_purple" + ChatColor.YELLOW + " yellow" + ChatColor.WHITE + " white");
            }
            else {
                ChatColor color;
                try {
                    color = ChatColor.valueOf(args[0].toUpperCase());
                    db.setChatColor((Player)sender, args[0].toUpperCase());
                    ((Player)sender).setDisplayName(color + sender.getName() + ChatColor.RESET);
                    ((Player)sender).setPlayerListName(color + sender.getName() + ChatColor.RESET);
                    sender.sendMessage(color + "Color successfully changed.");
                }
                catch(Exception e) {
                    sender.sendMessage("Invalid color! You can use:");
                	sender.sendMessage(ChatColor.BLACK + "black" + ChatColor.DARK_BLUE + " dark_blue" + ChatColor.DARK_GREEN + " dark_green" + ChatColor.DARK_AQUA + " dark_aqua" + ChatColor.DARK_RED + " dark_red" + ChatColor.DARK_PURPLE + " dark_purple" + ChatColor.GOLD + " gold" + ChatColor.GRAY + " gray" + ChatColor.DARK_GRAY + " dark_gray" + ChatColor.BLUE + " blue" + ChatColor.GREEN + " green" + ChatColor.AQUA + " aqua" + ChatColor.RED + " red" + ChatColor.LIGHT_PURPLE + " light_purple" + ChatColor.YELLOW + " yellow" + ChatColor.WHITE + " white");
                }
            }
        }
        else {
            sender.sendMessage("Sorry, you have to be a player to use that command.");
        }
        return true;
    }
}